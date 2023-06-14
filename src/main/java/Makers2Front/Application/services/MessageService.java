package Makers2Front.Application.services;

import Makers2Front.Application.entities.FormMessage;
import org.springframework.web.multipart.MultipartFile;

public interface MessageService {
    boolean saveFile(MultipartFile file, FormMessage message);

    FormMessage getMessage(Long id);

    void deleteMessage(FormMessage message);
}
