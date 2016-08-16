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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Adrian Zburatura
 * @author Andra Matei
 *         <p>
 *         Spring MVC Controller class
 *         </p>
 * @version %I%, %G%
 */
@Controller
@RequestMapping(value = "/uploadFileController")
public class UploadFileController {

    /**
     * Injected ParseFile bean
     */
    @Autowired
    private ParseFile parseFile;

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
                byte[] bytes = file.getBytes();
                String roothPath = BaseKeys.ROOT_PATH_TO_DIRECTORY; //path to directory

                File dir = new File(roothPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String pathToFile = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
                File serverFile = new File(pathToFile);

                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

                    stream.write(bytes);
                    stream.flush();

                    List<Word> wordList = parseFile.readFromFile(pathToFile);
                    model.addAttribute("wordListFromFile", wordList);

                    return "readingFile";
                }
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName() + " because the file was empty.";
        }
    }

}
