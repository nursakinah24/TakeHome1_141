/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.takehome1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Asus
 */

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/mypage")
    public String inputData( @RequestParam(value = "varName") String nameInput,
            @RequestParam(value = "varLoc") String locInput,
            @RequestParam(value = "varImage") String imgInput,
            Model input
    )
    {
        input.addAttribute("name", nameInput);
        input.addAttribute("loc", locInput);
        return "viewpage";
    }
        
    @RequestMapping(value = "/image-resource{varImage}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("varImage")String photo) throws IOException{
        if (!photo.equals("")|| photo != null) {
            try{
                Path filename = Paths.get("uploads",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            }catch (Exception e) {
            }
            
        }
        return ResponseEntity.badRequest().build();

    }
}
