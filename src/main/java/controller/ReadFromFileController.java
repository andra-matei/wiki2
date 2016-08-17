package controller;

import keys.BaseKeys;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Controller
@RequestMapping(value = "/readFile")
public class ReadFromFileController {

    /**
     * Injected ParseFile bean
     */
    @Autowired
    private ParseFile parseFile;

    /**
     * @param model holder for model attributes
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String readFromFile(Model model) {
        List<Word> wordList = parseFile.readFromFile(null);
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
        return "words";
    }
}
