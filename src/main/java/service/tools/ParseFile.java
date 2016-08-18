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
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Component
public class ParseFile {

    /**
     * Injected concrete CountWords bean
     */
    @Autowired
    private CountWords countWords;

    /**
     * Injected concrete WriteFileFromXML bean
     */
    @Autowired
    private WriteFileFromXML writeFileFromXML;

    private boolean fromDatabase;

    /**
     * The method gets the path to a file with article titles.
     * It appends each title to one containing them all.
     * When added to the wiki root url, this new title will display the page with the
     * body of each article.
     * If the article was already looked for, it means that it exists in the database and the
     * top 10 words are already there.
     * Otherwise, it will be necessarily to calculate the 10 words.
     *
     * @param filePath the path to the file containing article titles
     * @return the list of the top 10 words with the biggest number of occurences
     * from all articles
     */
    public List<Word> readFromFile(String filePath) {
        if (filePath == null) {
            filePath = BaseKeys.LOCAL_FILE_PATH;
        }
        List<Word> wordList = new ArrayList<Word>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder titles = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                titles.append(line + BaseKeys.SEPARATOR_FOR_TITLE);
            }

            List<Word> wordsFromDatabase = writeFileFromXML.writeFileFromXML(titles.toString());
            if (wordsFromDatabase != null) {
                wordList.addAll(wordsFromDatabase);
                fromDatabase = true;
            } else {
                List<Word> topTenWords = countWords.countWords();
                wordList.addAll(topTenWords);
                fromDatabase = false;
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

    /**
     *
     * @return the boolean database
     */
    public boolean isFromDatabase() {
        return fromDatabase;
    }

    /**
     *
     * @return countWords
     */
    public CountWords getCountWords() {
        return countWords;
    }

    /**
     *
     * @return writeFileFromXML
     */
    public WriteFileFromXML getWriteFileFromXML() {
        return writeFileFromXML;
    }
}
