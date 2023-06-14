package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/attachment")
@Slf4j
public class AttachmentController {
    private MessageRepository msgRepo;
    private AttachmentRepository attRepo;

    private AttachmentServiceImpl service;

    @Autowired
    public AttachmentController(MessageRepository msgRepo,
                                AttachmentRepository attRepo,
                                AttachmentServiceImpl service) {
        this.msgRepo = msgRepo;
        this.attRepo = attRepo;
        this.service = service;
    }

   @ModelAttribute(name = "att")
    public AttachmentObject attachmentObject(){
        return new AttachmentObject();
    }


    @GetMapping("/watch/{id}")
    public String watch(@PathVariable Long id,
                        Model model){
        FormMessage message = msgRepo.getByMessageId(id);
        model.addAttribute("message", message);
        return "attachmentPage";
    }

    @GetMapping("/add")
    public String addFilesPage(){
        return "addFile";
    }

    @PostMapping("/add")
    public String addAttachment(@ModelAttribute AttachmentObject attachmentObject){
        attRepo.save(attachmentObject);
        return "redirect:addFile";
    }


    @PostMapping("/confirm/{id}")
    public String confirmFile(@PathVariable Long id,
                              @ModelAttribute(name = "attachment") AttachmentObject attachment,
                              @RequestParam("file") MultipartFile file){
        service.saveAttachment(id, attachment, file);
        return "addFiles";
    }



}
