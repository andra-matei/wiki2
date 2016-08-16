package controller;

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
        model.addAttribute("wordListFromFile", wordList);
        return "readingFile";
    }
}
