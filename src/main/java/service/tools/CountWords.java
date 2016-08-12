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
import java.util.*;

/**
 * Created by andmatei on 8/11/2016.
 */
@Component
public class CountWords {

    @Autowired
    private WriteFileFromXML writeFileFromXML;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WordService wordService;

    public List<Word> countWords() {
        Map<String, Integer> wordCount = new LinkedHashMap<>();

        Article article = new Article();
        String[] titlesArrays = writeFileFromXML.getTitleForUrl().split("%0A");
        String titles = "";
        for (String s : titlesArrays) {
            titles += s + " ";
        }
        article.setTitle(titles);
        article.setUrl(BaseKeys.URL_WIKI + article.getTitle());
        articleService.saveArticle(article);

        try {
            BufferedReader in = new BufferedReader(new FileReader("D:\\Homework\\wikiweb\\fisierXML.txt"));
            String line;
            while ((line = in.readLine()) != null) {
                line = line.toUpperCase(); // convert to lower case
                String[] words = line.split("\\s+"); //split the line on whitespace, would return an array of words
                for (String word : words) {
                    if (word.length() == 0) {
                        continue;
                    }
                    Integer occurences = wordCount.get(word);
                    if (occurences == null) {
                        occurences = 1;
                    } else {
                        occurences++;
                    }
                    if (!isPreposition(word) && (!word.toString().startsWith("<")) && (word.toString().matches("[a-zA-Z]+"))) {
                        wordCount.put(word, occurences);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Exception");
        }

        List<Integer> values = new ArrayList<>();
        values.addAll(wordCount.values());
        Collections.sort(values, Collections.reverseOrder());
        int last_i = -1;

        int counter = 0;
        for (Integer i : values.subList(0, 9)) {
            if (last_i == i) // without duplicates
                continue;
            last_i = i;
            for (String s : wordCount.keySet()) {
                if (wordCount.get(s) == i) { // which have this value
                    if (counter <= 10) {
                        Word wordEntity = new Word();
                        wordEntity.setName(s);
                        wordEntity.setOccurences(i);
                        wordEntity.setArticle(article);
                        wordService.saveWord(wordEntity);

                        article.getWords().add(wordEntity);
                        articleService.updateArticle(article);
                    }

                    System.out.println(s + " " + i);
                    counter++;
                }
            }

        }
        return article.getWords();
    }

    private boolean isPreposition(String w) {
        for (PrepositionsEnum preposition : PrepositionsEnum.values()) {
            if (w.equals(preposition.toString())) {
                return true;
            }
        }
        return false;
    }
}
