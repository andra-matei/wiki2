package service;

import model.Article;
import model.Word;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by azburatura on 8/18/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@EnableWebMvc
@Transactional
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testSaveArticleService() {
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");

        int id = articleService.saveArticle(article);

        Assert.assertNotEquals(id, 0);
    }

    @Test
    public void testFindArticleByNameService() {
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");
        articleService.saveArticle(article);

        article = articleService.findArticleByName("Test");

        Assert.assertEquals("Test", article.getTitle());
    }

    @Test
    public void testUpdateArticleService() {
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");
        articleService.saveArticle(article);

        article = articleService.findArticleByName("Test");

        List<Word> words = new ArrayList<Word>();
        words.add(new Word());
        words.add(new Word());
        words.add(new Word());
        words.add(new Word());
        words.add(new Word());
        article.setWords(words);

        article = articleService.updateArticle(article);

        Assert.assertEquals(5, article.getWords().size());
    }

}
