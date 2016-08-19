package service;

import model.Word;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *         <p>
 *         Lists the methods implemented in service.impl.WordServiceImpl.java
 * @version %I%, %G%
 */
public interface WordService {

    /**
     * @param word word to be saved
     * @return word's id
     */
    int saveWord(Word word);

}
