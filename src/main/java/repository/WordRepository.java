package repository;

import model.Word;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Repository
public class WordRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * @param word the word to be persisted
     */
    public int saveWord(Word word) {
        entityManager.persist(word);
        entityManager.flush();
        return word.getId();
    }

}
