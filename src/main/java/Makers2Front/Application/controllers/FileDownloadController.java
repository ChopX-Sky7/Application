package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.impl.ErrorLogServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Slf4j
@RequestMapping("/download")
public class FileDownloadController {

    private MessageRepository repo;
    private ErrorLogServiceImpl logService;

    private AttachmentRepository repoA;

    @Autowired
    public FileDownloadController(MessageRepository repo, ErrorLogServiceImpl logService) {
        this.repo = repo;
        this.logService = logService;
    }


    @GetMapping("file/{id}")
    public ResponseEntity<Object> file(@PathVariable Long id) {

        AttachmentObject att = repoA.getByAttId(id);
        File file = new File(att.getFileLink());
        String path = att.getFileLink();

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            logService.log(e);
        }

        HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            ResponseEntity<Object> response = ResponseEntity.ok().headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/zip")).body(resource);

        return response;
    }
}
