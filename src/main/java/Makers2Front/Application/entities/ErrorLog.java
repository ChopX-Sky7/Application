package Makers2Front.Application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class ErrorLog {
    private final SimpleDateFormat sdf = new SimpleDateFormat("_dd.MM.yyyy_H.mm");

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String caughtIn;

    private String logFileLink;
    @Transient
    private String stackTrace;

    public void setCaughtIn() {
        this.caughtIn =  sdf.format(new Date());
    }


    public String toString() {
        return "ErrorLog(id=" + this.getId() + ",\n\n caughtIn=" + this.getCaughtIn() + ",\n\n\n stackTrace=" + this.getStackTrace() + ")";
    }
}
