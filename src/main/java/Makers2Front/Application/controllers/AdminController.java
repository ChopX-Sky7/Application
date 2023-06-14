package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private MessageRepository msgRepo;


    @Autowired
    public AdminController(MessageRepository repo) {
        this.msgRepo = repo;
    }





    @ModelAttribute(name = "messageList")
    public List<FormMessage> getList() {
        return msgRepo.findAll();
    }

    @GetMapping
    public String admin() {
        return "adminPages";
    }

    @GetMapping("/filemanagement")
    public String files(Model model) {
        model.addAttribute("messageList", getList());
        return "addFiles";
    }







}

