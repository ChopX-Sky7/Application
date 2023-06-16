package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.entities.Product;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.repos.ProductRepository;
import Makers2Front.Application.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private MessageRepository msgRepo;
    private AttachmentRepository attRepo;

    private ProductRepository pRepo;

    private ProductServiceImpl service;




    @Autowired
    public AdminController(MessageRepository repo, AttachmentRepository attRepo, ProductRepository pRepo, ProductServiceImpl service) {
        this.msgRepo = repo;
        this.attRepo = attRepo;

        this.pRepo = pRepo;
        this.service = service;
    }

    @ModelAttribute(name = "messageList")
    public List<FormMessage> getList() {
        return msgRepo.findAll();
    }

    @ModelAttribute(name = "prod")
    public Product product(){
        return new Product();
    }
    @ModelAttribute(name = "emptyFile")
    public AttachmentObject emptyFile() {
        return new AttachmentObject();
    }

    @GetMapping
    public String admin() {
        return "adminPages";
    }

    @GetMapping("/remove")
    public String remove() {
        return "removeFile";
    }

    @GetMapping("/addproducts")
    public String showForm(){
        return "addProducts";
    }

    @PostMapping("/prodAdd")
    public String addProduct(@ModelAttribute Product product, @RequestParam MultipartFile file){
        service.savePreview(product, file);
        pRepo.save(product);
        return "addProducts";
    }


    @PostMapping("/remove")
    public String remove(@ModelAttribute AttachmentObject att) {
        attRepo.delete(att);
        return "removeFile";
    }



    @GetMapping("/messages")
    public String files(Model model) {
        model.addAttribute("messageList", getList());
        return "addFiles";
    }








}

