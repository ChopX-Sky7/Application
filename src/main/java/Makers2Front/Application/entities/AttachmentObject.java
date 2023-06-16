
package Makers2Front.Application.entities;


import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class AttachmentObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attId;

    @Column(columnDefinition = "text")
    private String fileLink;

    @Column(columnDefinition = "text")
    private String previewLink;
    private String previewName;

    private String name;
    private String fileFullDescription;
    private String fileShortDescription;
    private String addedAt;

    private String mainTag;

    private String priority;

    public void setAddedAt() {
        this.addedAt = new SimpleDateFormat("_dd.MM.yyyy_H.mm").format(new Date());
    }

}
