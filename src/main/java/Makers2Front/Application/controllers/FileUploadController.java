package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.impl.MessageServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/feedback")
public class FileUploadController {

    private final MessageServiceImpl service;
    private final MessageRepository repo;

    @Autowired
    public FileUploadController(MessageServiceImpl service, MessageRepository repo) {
        this.service = service;
        this.repo = repo;
    }



    @GetMapping
    public String showForm() {
        return "send-files";
    }

    @ModelAttribute(name = "formMessage")
    public FormMessage formMessage(){
        return new FormMessage();
    }


    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                    @ModelAttribute("formMessage") FormMessage message){
        message.setSendAt();
        if (service.saveFile(file, message)){
            repo.save(message);
            log.info("saved: {}" , message);}
        else {repo.save(message);
        log.info("saved without file: {}", message);}
    return "redirect:feedback";
    }


}
