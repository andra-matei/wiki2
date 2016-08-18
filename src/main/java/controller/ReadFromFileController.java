package controller;

import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.tools.ParseFile;
import service.utility.UtilityController;

import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
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
     * Injected UtilityController bean
     */
    @Autowired
    UtilityController utilityController;

    /**
     * @param model holder for model attributes
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String readFromFile(Model model) {
        List<Word> wordList = parseFile.readFromFile(null);
        utilityController.whatShouldItDisplay(model, wordList);
        return "words";
    }
}
