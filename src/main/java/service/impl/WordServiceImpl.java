package service.impl;

import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WordRepository;
import service.WordService;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Service
@Transactional
public class WordServiceImpl implements WordService {

    /**
     * Injected WordRepository bean
     */
    @Autowired
    private WordRepository wordRepository;

    /*
     * (non-Javadoc)
	 *
	 * @see repository.WordRepository.java
	 */
    @Override
    public void saveWord(Word word) {
        wordRepository.saveWord(word);
    }
}
