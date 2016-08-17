package service.tools;

import keys.BaseKeys;
import model.Article;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ArticleService;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Component
public class WriteFileFromXML {

    private String titleForUrl;
    private long duration;

    /**
     * Injected concrete ArticleService bean
     */
    @Autowired
    private ArticleService articleService;

    /**
     * @return the title to be added to the Wiki root URL
     */
    public String getTitleForUrl() {
        return titleForUrl;
    }

    /**
     * The method looks for the titleForUrl in the database.
     * If it was previously looked for then it will get the
     * data from database. If it didn't then it will put the body
     * of the article into a XML file.
     *
     * @param titleForUrl is the title of the article
     * @return the top 10 words with the biggest number of occurences
     * it is returned from the database if the article was searched for previously
     */
    public List<Word> writeFileFromXML(String titleForUrl) {
        long start = System.currentTimeMillis();
        Article article = articleService.findArticleByName(allTitles(titleForUrl));
        if (article == null) {
            this.titleForUrl = titleForUrl;
            URL url = null;
            try {
                url = new URL(BaseKeys.URL_WIKI + titleForUrl);
                URLConnection urlConnection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                BufferedWriter out = new BufferedWriter(new FileWriter(BaseKeys.PATH_TO_XML_FILE));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.write(inputLine);
                    out.newLine();
                }
                in.close();
                out.close();

            } catch (MalformedURLException e) {
                System.err.println("malformedException");
            } catch (IOException e) {
                System.err.println("ioexception");
            }
            return null;
        } else {
            long end = System.currentTimeMillis();
            duration = end - start;
            return article.getWords();
        }
    }

    /**
     * @return the concatenation of all titles
     */
    private String allTitles(String titleForUrl) {
        String[] titlesArray = titleForUrl.split(BaseKeys.SEPARATOR_FOR_TITLE);
        String titles = "";
        for (String s : titlesArray) {
            titles += s + " ";
        }
        return titles;
    }

    public long getDuration() {
        return duration;
    }
}
