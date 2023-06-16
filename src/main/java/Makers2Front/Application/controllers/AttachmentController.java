package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequestMapping("/attachment")
@Slf4j
public class AttachmentController {
    private final MessageRepository msgRepo;
    private final AttachmentRepository attRepo;

    private final AttachmentServiceImpl service;

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
        return "messagePage";
    }

    @GetMapping("/add")
    public String addFilesPage(){
        return "addFile";
    }

    @PostMapping("/add")
    public String addAttachment(@ModelAttribute AttachmentObject attachmentObject){
        attRepo.save(attachmentObject);
        log.info("New attachment from admin: {}", attachmentObject);
        return "addFile";
    }

    @GetMapping("file/{id}")
    public ResponseEntity<Object> file(@PathVariable String id) {
        FormMessage msg = msgRepo.getByMessageId(Long.parseLong(id));
        File file = new File(msg.getFileLink());
        String path = msg.getFileLink();

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            log.info("Catched exception: {}", e.getMessage());
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/zip");
        headers.add("Content-Disposition",
                "attachment; filename=makers2Front-message.zip");
        ResponseEntity<Object> response = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .body(resource);
        return response;
    }


    @PostMapping("/confirm/{id}")
    public String confirmFile(@PathVariable Long id,
                              @ModelAttribute(name = "att") AttachmentObject attachment,
                              @RequestParam("file") MultipartFile file){
        service.saveAttachment(id, attachment, file);
        return "addFiles";
    }



}
