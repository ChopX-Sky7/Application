package Makers2Front.Application.services.impl;

import Makers2Front.Application.entities.ErrorLog;
import Makers2Front.Application.repos.ErrorsRepository;
import Makers2Front.Application.services.ErrorsLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ErrorLogServiceImpl implements ErrorsLogService {


    ErrorsRepository repo;

    @Autowired
    public ErrorLogServiceImpl(ErrorsRepository repo) {
        this.repo = repo;
    }

    @Override
    public void log(Exception e) {
        ErrorLog errorLog = new ErrorLog();
        String path = "src/main/resources/static/logs/" + "#" + errorLog.getId() + errorLog.getCaughtIn();
        errorLog.setLogFileLink(path);
        errorLog.setCaughtIn();
        errorLog.setStackTrace(ExceptionUtils.getStackTrace(e));
        try (BufferedOutputStream stream =
                     new BufferedOutputStream
                             (new FileOutputStream(path))) {
            stream.write(errorLog.toString().getBytes());
        } catch (Exception ex) {
            this.log(ex);
        }
        repo.save(errorLog);
    }

    @Override
    public Optional<ErrorLog> getLogById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<ErrorLog> getAllLogs() {
        return repo.findAll();
    }


}
