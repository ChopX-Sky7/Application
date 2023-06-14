package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.repos.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileViewingController {

    private AttachmentRepository repository;

    @Autowired
    public FileViewingController(AttachmentRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public String files(){
        return "files";
    }


    @GetMapping("/watch/{id}")
    public String file(@PathVariable Long id,
                       Model model){
        model.addAttribute("preview", repository.getByAttId(id).getPreviewLink());
        model.addAttribute("file", repository.getByAttId(id));
        return "file";
    }

}
