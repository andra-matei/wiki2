package service.impl;

import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WordRepository;
import service.WordService;

/**
 * Created by azburatura on 8/11/2016.
 */
@Service
@Transactional
public class WordServiceImpl implements WordService {

    @Autowired
    private WordRepository wordRepository;

    @Override
    public void saveWord(Word word) {
        wordRepository.saveWord(word);
    }
}
