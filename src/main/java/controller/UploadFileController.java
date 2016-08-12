package controller;

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
 * Created by azburatura on 8/12/2016.
 */
@Controller
@RequestMapping(value = "/uploadFile")
public class UploadFileController {

    @Autowired
    private ParseFile parseFile;

    @RequestMapping(method = RequestMethod.POST)
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) {

        if (!file.isEmpty()) {
            try {

                byte[] bytes = file.getBytes();
                String roothPath = "D:\\uploadFiles";

                File dir = new File(roothPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {

                    stream.write(bytes);
                    List<Word> wordList = parseFile.readFromFile(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
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
