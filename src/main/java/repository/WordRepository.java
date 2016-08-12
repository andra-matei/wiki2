package repository;

import model.Word;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by azburatura on 8/11/2016.
 */
@Repository
public class WordRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void saveWord(Word word) {
        entityManager.persist(word);
        entityManager.flush();
    }

}
