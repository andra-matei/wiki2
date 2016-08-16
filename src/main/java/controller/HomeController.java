package controller;

import keys.BaseKeys;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.tools.CountWords;
import service.tools.WriteFileFromXML;

import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *         <p>
 *         Spring MVC Controller class controlling the view of the Home Page.
 *         </p>
 * @version %I%, %G%
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    /**
     * Injected CountWords bean
     */
    @Autowired
    private CountWords countWords;

    /**
     * Injected WriteFileFromXML bean
     */
    @Autowired
    private WriteFileFromXML writeFileFromXML;

    /**
     * @return the home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome() {
        return "home";
    }

    /**
     * @param title the title of the article
     * @param model holder for model attributes
     * @return the top 10 words with the
     * biggest number of occurences in the article
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addArticle(@RequestParam("title") String title, Model model) {
        List<Word> wordList = writeFileFromXML.writeFileFromXML(title);
        if (wordList == null) {
            wordList = countWords.countWords();
        }
        if (wordList == null) {
            model.addAttribute("eroare", BaseKeys.ERROR_404);
        } else {
            model.addAttribute("articleTitle", title);
            model.addAttribute("wordList", wordList);
        }
        return "words";
    }

    /**
     * @return the uploadFile page
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String showUploadPage() {

        return "uploadFile";
    }

}
