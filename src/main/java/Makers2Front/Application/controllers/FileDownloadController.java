package Makers2Front.Application.controllers;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import com.ibm.icu.text.Transliterator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@Slf4j
@RequestMapping("/download")
public class FileDownloadController {

    private MessageRepository repo;


    private AttachmentRepository repoA;

    @Autowired
    public FileDownloadController(MessageRepository repo, AttachmentRepository repoA) {
        this.repo = repo;
        this.repoA = repoA;
    }


    @GetMapping("file/{id}")
    public ResponseEntity<Object> file(@PathVariable String id) {
        AttachmentObject att = repoA.getByAttId(Long.parseLong(id));
        File file = new File(att.getFileLink());
        String path = att.getFileLink();

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            log.info("Catched exception: {}", e.getMessage());
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/zip; charset=UTF-8");
           headers.add("Content-Disposition",
                   String.format("attachment; filename=makers2Front.zip"));
        ResponseEntity<Object> response = ResponseEntity.ok().headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/zip")).body(resource);
        return response;
    }
}
