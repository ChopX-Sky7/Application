package Makers2Front.Application.services.impl;

import Makers2Front.Application.entities.AttachmentObject;
import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.AttachmentRepository;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    private MessageRepository msgRepo;
    private AttachmentRepository attRepo;


    @Autowired
    public AttachmentServiceImpl(MessageRepository msgRepo, AttachmentRepository attRepo) {
        this.msgRepo = msgRepo;
        this.attRepo = attRepo;

    }


    @Override
    public void savePreview(AttachmentObject attachment,
                            MultipartFile file) {
        attachment.setPreviewName(attachment.getName() + "-preview.png");
        attachment.setPreviewLink(attachment.getPreviewName());
        try (BufferedOutputStream stream =
                     new BufferedOutputStream
                             (new FileOutputStream("src/main/resources/static/img/preview/" +attachment.getPreviewLink()))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (Exception e) {
           log.info("Catch exception: {}", e.getMessage());
        }
    }

    @Override
    public void saveAttachment(Long id,
                               AttachmentObject attachment,
                               MultipartFile file) {
        savePreview(attachment, file);
        FormMessage message = msgRepo.getByMessageId(id);
        File msgFile = new File(message.getFileLink());
        attachment.setFileLink("src/main/resources/static/content/confirmed/" + attachment.getName() + message.getCategory() + ".zip");
        File attFile = new File(attachment.getFileLink());
        msgFile.renameTo(attFile);
        attachment.setAddedAt();

        attRepo.save(attachment);
    }

    @Override
    public void deleteAttachment(AttachmentObject attachment) {
        attRepo.delete(attachment);
    }

    @Override
    public List<AttachmentObject> previewFiles(String tag) {
        return attRepo.getAllByMainTag(tag);
    }
}
