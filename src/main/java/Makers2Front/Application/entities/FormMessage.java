package Makers2Front.Application.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class FormMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    private boolean isConfirmed;
    private boolean hasFile;

    @Column(columnDefinition = "text")
    private String fileDescription;

    @Column(columnDefinition = "text")
    private String fileLink;

    private long size;

    private String tags;
    private String name;
    private String sendAt;

    private String category;

    public void setSendAt() {
        this.sendAt = new SimpleDateFormat("_dd.MM.yyyy_H.mm").format(new Date());
    }



}
