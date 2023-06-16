package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/dummy")
@Slf4j
public class dummy {

    private AttachmentServiceImpl service;
    private AttachmentRepository repo;


    @Autowired
    public dummy(AttachmentServiceImpl service, AttachmentRepository repo) {
        this.service = service;
        this.repo = repo;
    }

  /*  @ModelAttribute(name = "list")
    List<AttachmentObject> list(){
        return repo.findAll();
    }*/

    @GetMapping
    public String dummy(Model model) {
        model.addAttribute("list", repo.findAll());
        return "dummy";
    }

    @GetMapping("/watch/{id}")
    public String watch(@PathVariable Long id,
                        Model model){
        AttachmentObject attachmentObject = repo.getByAttId(id);
        model.addAttribute("message", attachmentObject);

        return "attachmentPage";
    }

    @RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] getImage(@PathVariable String id) throws IOException {
        // AttachmentObject att =  repo.getByAttId(Long.parseLong(id));

        File voidImg = new File("src/main/resources/static/img/preview/void.svg");
        try {

            File image = new File("src/main/resources/static/img/preview/" + id);
            return Files.readAllBytes(image.toPath());
        } catch (Exception e) {
            log.info("Catch exception: {}", e.getMessage());
            return Files.readAllBytes(voidImg.toPath());
        }

    }
}
