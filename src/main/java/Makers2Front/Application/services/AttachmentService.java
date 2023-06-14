package Makers2Front.Application.services;

import Makers2Front.Application.entities.AttachmentObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    void savePreview(AttachmentObject attachment,
                     MultipartFile file);

    void saveAttachment(Long id,
                        AttachmentObject attachment,
                        MultipartFile file);

    void deleteAttachment(AttachmentObject attachment);


    List<AttachmentObject> previewFiles(String tag);
}
