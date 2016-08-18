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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Created by azburatura on 8/18/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@EnableWebMvc
@Transactional
/**
 * (non Java-doc)
 *
 * Test for WordRepository.java
 */
public class JpaWordRepository {

    /**
     * Injected WordRepository bean
     */
    @Autowired
    private WordRepository wordRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * (non Java-doc)
     * testing the method saveWord()
     */
    @Test
    public void testSaveWord() {
        Word word = new Word();
        word.setName("Test");
        word.setOccurrences(5);
        Article article = new Article();
        article.setTitle("Test");
        article.setUrl("TestUrl");

        entityManager.persist(article);
        word.setArticle(article);

        int id = wordRepository.saveWord(word);
        Assert.assertNotEquals(id, 0);
    }
}
