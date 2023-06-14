package Makers2Front.Application.services.impl;

import Makers2Front.Application.entities.FormMessage;
import Makers2Front.Application.repos.MessageRepository;
import Makers2Front.Application.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;


@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final ErrorLogServiceImpl logService;
    private final MessageRepository repo;


    @Autowired
    public MessageServiceImpl(ErrorLogServiceImpl logService, MessageRepository repo) {
        this.logService = logService;
        this.repo = repo;
    }


    @Override
    public boolean saveFile(MultipartFile file, FormMessage message) {
        if (!file.isEmpty()) {
            message.setHasFile(true);
            message.setConfirmed(false);
            message.setSize(file.getSize());
            message.setSendAt();
            message.setFileLink("src/main/resources/static/content/" + message.getCategory() + message.getSendAt() + "-uploaded.zip");
            try (BufferedOutputStream stream =
                         new BufferedOutputStream
                                 (new FileOutputStream(message.getFileLink()))) {
                byte[] bytes = file.getBytes();
                stream.write(bytes);
                return true;
            } catch (Exception e) {
                logService.log(e);
                return false;
            }
        }
        return false;
    }

    @Override
    public FormMessage getMessage(Long id) {
        return repo.getByMessageId(id);
    }

    @Override
    public void deleteMessage(FormMessage message) {
        repo.delete(message);
    }
}
