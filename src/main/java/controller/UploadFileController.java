package controller;

import keys.BaseKeys;
import model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.tools.ParseFile;
import service.utility.UtilityController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 * @version %I%, %G%
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadFileController {

    /**
     * Injected ParseFile bean
     */
    @Autowired
    private ParseFile parseFile;

    /**
     * Injected ReadFromFileController bean
     */
    @Autowired
    ReadFromFileController readFromFileController;

    /**
     * Injected UtilityController bean
     */
    @Autowired
    UtilityController utilityController;

    /**
     * @param file  the file to be uploaded
     * @param model holder for model attributes
     * @return a message if the file is empty
     * an error if the fiel can't be uploaded
     * a string so the file can be read
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            try {
                File dir = new File(BaseKeys.ROOT_PATH_TO_DIRECTORY);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String pathToFile = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
                File serverFile = new File(pathToFile);

                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                    stream.write(file.getBytes());
                    stream.flush();
                    List<Word> wordList = parseFile.readFromFile(pathToFile);
                    utilityController.whatShouldItDisplay(model, wordList);
                    return "words";
                }
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("eroare", BaseKeys.ERROR_404);
                return "words";
            }
        } else {
            model.addAttribute("eroare", BaseKeys.ERROR_404);
            return "words";
        }
    }

    /**
     * Method that returns you to upload file page, uses the get method
     *
     * @return uploadFile.jsp
     */
    @RequestMapping(method = RequestMethod.GET)
    public String returnToUpload() {
        return "uploadFile";
    }

}
