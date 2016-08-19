package service.tools;

import keys.BaseKeys;
import model.Article;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ArticleService;
import service.utility.UtilityCountWords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Component
public class CountWords {

    private ExecutorService executor = Executors.newFixedThreadPool(4);
    private long duration;

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


    @Autowired
    UtilityCountWords utilityCountWords;

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

        article.setTitle(utilityCountWords.concatenationOfAllTitles());
        article.setUrl(BaseKeys.URL_WIKI + article.getTitle());
        articleService.saveArticle(article);

        //////////////////////////////////////////////////////////////////////////////////////////////
        long startTime = System.currentTimeMillis();

        /**
         * Java8 lambda expression
         */
        Future future = executor.submit(() -> {
            try (BufferedReader in = new BufferedReader(new FileReader(BaseKeys.PATH_TO_XML_FILE))) {
                utilityCountWords.putTheWordsInMap(in, wordCount);
            } catch (Exception e) {
                System.err.println("Exception");
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

        if (utilityCountWords.persisting(wordCount, article)) {
            long endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Total time is :    " + duration);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            //////////////////////////////////////////////////////////////////////////////////////////////
            return article.getWords();
        } else {
            return null;
        }
    }


    public long getDuration() {
        return duration;
    }
}
