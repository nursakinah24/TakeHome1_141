/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.takehome1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/myPage")
    public String inputData( @RequestParam(value = "varName") String nameInput,
            @RequestParam(value = "varLoc") String locInput,
            @RequestParam(value = "varImage") MultipartFile imgInput,
            Model input
    ) throws IOException{ byte[] img = imgInput.getBytes();
        String encodeImage = Base64.encodeBase64String(img);
        String imageInput = "data:image/png;base64,".concat(encodeImage);
        
        input.addAttribute("name", nameInput);
        input.addAttribute("loc", locInput);
        input.addAttribute("img", imageInput);
        
        return "viewpage";
    }
}
