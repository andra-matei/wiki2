package repository;

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
public class JpaArticleRepository {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void testSaveArticle() throws Exception {
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");
        article.setWords(new ArrayList<Word>());
        int id = articleRepository.saveArticle(article);

        Assert.assertNotEquals(id, 0);
    }

    @Test
    public void testFindArticleByName() {
        Article article = new Article();
        article.setTitle("Test");
        articleRepository.saveArticle(article);

        Article savedArticle = articleRepository.findArticleByName("Test");

        Assert.assertEquals("Test", savedArticle.getTitle());
    }

    @Test
    public void testUpdateArticle() throws Exception {
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");
        articleRepository.saveArticle(article);

        article = articleRepository.findArticleByName("Test");

        List<Word> words = new ArrayList<Word>();
        words.add(new Word());
        words.add(new Word());
        words.add(new Word());
        article.setWords(words);

        article = articleRepository.updateArticle(article);

        Assert.assertEquals(3, article.getWords().size());

    }

}
