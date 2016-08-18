package service.utility;

import keys.BaseKeys;
import model.Article;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ArticleService;
import service.WordService;
import service.tools.PrepositionsEnum;
import service.tools.WriteFileFromXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Component
public class UtilityCountWords {

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
     * @param inputFile
     * @param wordCount contains each word from the article
     *                  "article" with its occurrences
     * @throws IOException
     */
    public void putTheWordsInMap(BufferedReader inputFile, Map<String, Integer> wordCount) throws IOException {
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
                if (!isPreposition(word) && (!word.startsWith("<")) && (word.matches("[a-zA-Z]+"))
                        && (word.length() >= 2)) {
                    wordCount.put(word, occurrences);
                }
            }
        }
    }

    /**
     * @return the concatenation of all titles
     */
    public String concatenationOfAllTitles() {
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
    public boolean persisting(Map<String, Integer> wordCount, Article article) {
        List<Integer> values = new ArrayList<>();
        values.addAll(wordCount.values());
        Collections.sort(values, Collections.reverseOrder());

        int lastWord = -1;
        int counter = 0;
        if (values.size() >= 10) {
            for (Integer i : values.subList(0, 10)) {
                if (lastWord != i) {
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
            }
            return true;
        } else { return false;}
    }

    /**
     * It compares the string "word" to each one from the enum PrepositionEnum.
     * If it finds a match, it means the string "word" is a preposition.
     *
     * @param word a string to be analysed
     * @return true if the word is a preposition
     * false, otherwise
     */
    public boolean isPreposition(String word) {
        for (PrepositionsEnum preposition : PrepositionsEnum.values()) {
            if (word.equals(preposition.toString())) {
                return true;
            }
        }
        return false;
    }
}
