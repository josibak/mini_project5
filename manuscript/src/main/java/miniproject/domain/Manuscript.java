package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.ManuscriptApplication;
import miniproject.domain.FinalManuscriptSaved;

@Entity
@Table(name = "Manuscript_table")
@Data
//<<< DDD / Aggregate Root
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manuscriptId;

    private Long authorId;

    private String title;

    private String content;

    private Date createdAt;

    private Date updateAt;

    @PostUpdate
    public void onPostUpdate() {
        FinalManuscriptSaved finalManuscriptSaved = new FinalManuscriptSaved(
            this
        );
        finalManuscriptSaved.publishAfterCommit();
    }

    public static ManuscriptRepository repository() {
        ManuscriptRepository manuscriptRepository = ManuscriptApplication.applicationContext.getBean(
            ManuscriptRepository.class
        );
        return manuscriptRepository;
    }

    //<<< Clean Arch / Port Method
    public void createManuscript(
        CreateManuscriptCommand createManuscriptCommand
    ) {
        //implement business logic here:

        ManuscriptCreated manuscriptCreated = new ManuscriptCreated(this);
        manuscriptCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateManuscript(
        UpdateManuscriptCommand updateManuscriptCommand
    ) {
        //implement business logic here:

        ManuscriptUpdated manuscriptUpdated = new ManuscriptUpdated(this);
        manuscriptUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void requestPublication(
        RequestPublicationCommand requestPublicationCommand
    ) {
        //implement business logic here:

        PublicationRequested publicationRequested = new PublicationRequested(
            this
        );
        publicationRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void saveFinalManuscript(
        SaveFinalManuscriptCommand saveFinalManuscriptCommand
    ) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
