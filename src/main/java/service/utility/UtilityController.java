package service.utility;

import controller.ReadFromFileController;
import controller.UploadFileController;
import keys.BaseKeys;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import service.tools.ParseFile;

import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *         <p>
 *         Spring MVC Controller class controlling the reading of titles from a file.
 *         </p>
 * @version %I%, %G%
 */

@Component
public class UtilityController {

    /**
     * Injected ReadFromFileController bean
     */
    @Autowired
    ReadFromFileController readFromFileController;

    /**
     * Injected UploadFileController bean
     */
    @Autowired
    UploadFileController uploadFileController;

    /**
     * Injected ParseFile bean
     */
    @Autowired
    ParseFile parseFile;

    public void whatShouldItDisplay(Model model, List<Word> wordList) {
        boolean fromDatabase = parseFile.isFromDatabase();
        String title = wordList.get(0).getArticle().getTitle();

        if (wordList == null || (wordList.size() == 0)) {
            model.addAttribute("eroare", BaseKeys.ERROR_404);
        } else {
            model.addAttribute("articleTitle", title);
            model.addAttribute("wordList", wordList);
            if (fromDatabase) {
                model.addAttribute("timeSpent", parseFile.getWriteFileFromXML().getDuration());
                model.addAttribute("source", BaseKeys.DATABASE_SOURCE);
            } else {
                model.addAttribute("timeSpent", parseFile.getCountWords().getDuration());
                model.addAttribute("source", BaseKeys.WIKI_SOURCE);
            }
        }
    }

}
