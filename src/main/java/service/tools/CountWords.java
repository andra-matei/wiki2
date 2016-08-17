package service.tools;

import keys.BaseKeys;
import model.Article;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ArticleService;
import service.WordService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Component
public class CountWords {
    ExecutorService executor = Executors.newFixedThreadPool(4);
    private long startTime,endTime, duration;

    /**
     * Injected WriteFileFromXML bean
     */
    @Autowired
    private WriteFileFromXML writeFileFromXML;

    /**
     * Injected ArticleService bean
     */
    @Autowired
    private ArticleService articleService;

    /**
     * Injected WordService bean
     */
    @Autowired
    private WordService wordService;

    /**
     * The method gets the title from the final url and splits it.
     * It opens the XML file and reads each line of it, then it splits
     * every line into words.
     * The method puts each word found and its number of occurences
     * into  a map only if the word is not a preposition, it is not
     * a tag and it is human readable.
     * After that, the list of words is being ordered by the number
     * of occurences, it takes the first 10 and persists them in the database.
     *
     * @return the list of the top 10 words
     */
    public List<Word> countWords() {
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        Article article = new Article();

        String titles = allTitles();

        article.setTitle(titles);
        article.setUrl(BaseKeys.URL_WIKI + article.getTitle());
        articleService.saveArticle(article);


        //////////////////////////////////////////////////////////////////////////////////////////////
        startTime = System.currentTimeMillis();

        Future future = executor.submit(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader in = new BufferedReader(new FileReader(BaseKeys.PATH_TO_XML_FILE))) {
                    putTheWordsInMap(in, wordCount);
                } catch (Exception e) {
                    System.err.println("Exception");
                }

            }
        });

        // method get() while the task is still running => blocking the execution
        // //until the task is properly executed and the result is available
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        endTime = System.currentTimeMillis();
        duration = endTime-startTime;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("duration is :    "+duration);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //////////////////////////////////////////////////////////////////////////////////////////////

        if(persisting(wordCount, article)) {
           return article.getWords();
       }else{
           return null;
       }
    }

    /**
     * @param inputFile
     * @param wordCount contains each word from the article
     *                  "article" with its occurrences
     * @throws IOException
     */
    private void putTheWordsInMap(BufferedReader inputFile, Map<String, Integer> wordCount) throws IOException {
        String line;
        while ((line = inputFile.readLine()) != null) {
            line = line.toUpperCase(); // convert to lower case
            String[] words = line.split("\\s+"); //split the line on whitespace, would return an array of words
            for (String word : words) {
                if (word.length() == 0) {
                    continue;
                }
                Integer occurrences = wordCount.get(word);
                if (occurrences == null) {
                    occurrences = 1;
                } else {
                    occurrences++;
                }
                if (!isPreposition(word) && (!word.toString().startsWith("<")) && (word.toString().matches("[a-zA-Z]+"))
                        && (word.toString().length() >=2)) {
                    wordCount.put(word, occurrences);
                }
            }
        }
    }

    /**
     * @return the concatenation of all titles
     */
    private String allTitles() {
        String[] titlesArray = writeFileFromXML.getTitleForUrl().split(BaseKeys.SEPARATOR_FOR_TITLE);
        String titles = "";
        for (String s : titlesArray) {
            titles += s + " ";
        }
        return titles;
    }

    /**
     * Calculates the first ten words from the article "article"
     * and persists them in the database.
     *
     * @param wordCount contains each word from the article
     *                  "article" with its occurrences
     * @param article
     */
    private boolean persisting(Map<String, Integer> wordCount, Article article) {
        List<Integer> values = new ArrayList<>();
        values.addAll(wordCount.values());
        Collections.sort(values, Collections.reverseOrder());

        int lastWord = -1;
        int counter = 0;
        if (values.size() >= 10) {
            for (Integer i : values.subList(0, 10)) {
                if (lastWord == i) // without duplicates
                    continue;
                lastWord = i;
                for (String s : wordCount.keySet()) {
                    if (wordCount.get(s) == i) { // which have this value
                        if (counter <= 9) {
                            Word wordEntity = new Word();
                            wordEntity.setName(s);
                            wordEntity.setOccurrences(i);
                            wordEntity.setArticle(article);
                            wordService.saveWord(wordEntity);

                            article.getWords().add(wordEntity);
                            articleService.updateArticle(article);
                        }

                        counter++;
                    }
                }

            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * It compares the string "word" to each one from the enum PrepositionEnum.
     * If it finds a match, it means the string "word" is a preposition.
     *
     * @param word a string to be analysed
     * @return true if the word is a preposition
     * false, otherwise
     */
    private boolean isPreposition(String word) {
        for (PrepositionsEnum preposition : PrepositionsEnum.values()) {
            if (word.equals(preposition.toString())) {
                return true;
            }
        }
        return false;
    }


}
