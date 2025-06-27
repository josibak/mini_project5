package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.AipublicationApplication;
import miniproject.domain.PublicCompleted;

@Entity
@Table(name = "Publication_table")
@Data
//<<< DDD / Aggregate Root
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long publicationId;

    private Long manuscriptId;

    private String summary;

    private String postUrl;

    private String title;

    private String authorId;

    private Date publicAt;

    private String content;

    private String bookId;

    @PostPersist
    public void onPostPersist() {
        PublicCompleted publicCompleted = new PublicCompleted(this);
        publicCompleted.publishAfterCommit();
    }

    public static PublicationRepository repository() {
        PublicationRepository publicationRepository = AipublicationApplication.applicationContext.getBean(
            PublicationRepository.class
        );
        return publicationRepository;
    }
}
//>>> DDD / Aggregate Root
