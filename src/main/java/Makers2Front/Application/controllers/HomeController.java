package Makers2Front.Application.controllers;

import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@Slf4j
public class HomeController {

    private final AttachmentServiceImpl attService;

    @Autowired
    public HomeController(AttachmentServiceImpl attService) {
        this.attService = attService;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("home")
    public String homepage() {
        return "index";
    }

    @GetMapping("/donate")
    public String donate() {
        return "donat";
    }

    @RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable String imageName) throws IOException {
        File voidImg = new File("src/main/resources/static/img/preview/void.svg");
        try {

            File image = new File("src/main/resources/static/img/preview/" + imageName);
            return Files.readAllBytes(image.toPath());
        } catch (Exception e){
            log.info("Catch exception: {}", e.getMessage());
            return Files.readAllBytes(voidImg.toPath());
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
