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
 * Created by andmatei on 8/11/2016.
 */
@Component
public class WriteFileFromXML {

    private String titleForUrl;

    @Autowired
    private ArticleService articleService;

    public String getTitleForUrl() {
        return titleForUrl;
    }

    public List<Word> writeFileFromXML(String titleForUrl) {
        Article article = articleService.findArticleByName(titleForUrl);
        if (article == null) {
            this.titleForUrl = titleForUrl;
            URL url = null; //Reading
            try {
                url = new URL(BaseKeys.URL_WIKI + titleForUrl);
                URLConnection urlConnection = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                BufferedWriter out = new BufferedWriter(new FileWriter("D:\\Homework\\wikiweb\\fisierXML.txt"));
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
            return article.getWords();
        }

    }
}
