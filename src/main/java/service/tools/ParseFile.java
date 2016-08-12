package service.tools;

import keys.BaseKeys;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andmatei on 8/12/2016.
 */
@Component
public class ParseFile {

    @Autowired
    private CountWords countWords;

    @Autowired
    private WriteFileFromXML writeFileFromXML;

    public List<Word> readFromFile(String filePath) {
        if (filePath == null) {
            filePath = BaseKeys.LOCAL_FILE_PATH;
        }
        List<Word> wordList = new ArrayList<Word>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder titles = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                titles.append(line + "%0A");
            }

            List<Word> wordsFromDatabase = writeFileFromXML.writeFileFromXML(titles.toString());
            if (wordsFromDatabase != null) {
                wordList.addAll(wordsFromDatabase);
            } else {
                List<Word> topTenWords = countWords.countWords();
                wordList.addAll(topTenWords);
            }

            return wordList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
