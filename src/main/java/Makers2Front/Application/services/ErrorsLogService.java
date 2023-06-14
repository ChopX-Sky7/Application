package Makers2Front.Application.services;


import Makers2Front.Application.entities.ErrorLog;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ErrorsLogService {

    void log(Exception e);

    Optional<ErrorLog> getLogById(Long id);

    //ErrorLog getLogByDate(Date date);

    List<ErrorLog> getAllLogs();


}
