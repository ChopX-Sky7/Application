package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.services.impl.AttachmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dummy")
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
    public String dummy(Model model){
        model.addAttribute("list", repo.findAll());
        return "dummy";
    }

}
