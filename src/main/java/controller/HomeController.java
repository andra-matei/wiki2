package controller;

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
 * Created by azburatura on 8/10/2016.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private CountWords countWords;

    @Autowired
    private WriteFileFromXML writeFileFromXML;

    @RequestMapping(method = RequestMethod.GET)
    public String showHome() {

        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addArticle(@RequestParam("title") String title, Model model) {

        List<Word> wordList = writeFileFromXML.writeFileFromXML(title);
        if (wordList == null) {
            wordList = countWords.countWords();
        }
        model.addAttribute("wordList", wordList);

        return "words";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.GET)
    public String showUploadPage() {
        return "uploadFile";
    }

}
