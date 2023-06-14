package Makers2Front.Application.controllers;

import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@Slf4j

public class HomeController {

    private AttachmentServiceImpl attService;

    @Autowired
    public HomeController(AttachmentServiceImpl attService) {
        this.attService = attService;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/donate")
    public String donate() {
        return "donat";
    }

    @RequestMapping(value = "/image/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable String imageName) throws IOException {

        File image = new File("src/main/resources/static/img/preview/" + imageName);
        return Files.readAllBytes(image.toPath());
    }
}
