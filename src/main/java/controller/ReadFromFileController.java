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
 * Created by andmatei on 8/12/2016.
 */
@Controller
@RequestMapping(value = "/readFile")
public class ReadFromFileController {

    @Autowired
    private ParseFile parseFile;

    @RequestMapping(method = RequestMethod.POST)
    public String readFromFile(Model model) {

        List<Word> wordList = parseFile.readFromFile(null);
        model.addAttribute("wordListFromFile", wordList);

        return "readingFile";
    }

}
