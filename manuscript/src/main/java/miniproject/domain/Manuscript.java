package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.Getter;
import miniproject.ManuscriptApplication;
import miniproject.domain.FinalManuscriptSaved;

@Entity
@Table(name = "Manuscript_table")
@Data
@EntityListeners(AuditingEntityListener.class)
//<<< DDD / Aggregate Root
public class Manuscript {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long manuscriptId;  // 원고 ID

    private Long authorId;  // 작가 ID

    private String title;   // 제목

    private String content; // 내용

    private boolean publicationRequested = false; // 출간 상태

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성시간

    @LastModifiedDate
    private LocalDateTime updateAt;  // 수정시간

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

        
        this.authorId = createManuscriptCommand.getAuthorId();
        this.title = createManuscriptCommand.getTitle();
        this.content = createManuscriptCommand.getContent();

        ManuscriptCreated manuscriptCreated = new ManuscriptCreated(this);
        manuscriptCreated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void updateManuscript(
        UpdateManuscriptCommand updateManuscriptCommand
    ) {
        //implement business logic here:
        if (updateManuscriptCommand.getTitle() != null) {
            this.title = updateManuscriptCommand.getTitle();
        }
        if (updateManuscriptCommand.getContent() != null) {
            this.content = updateManuscriptCommand.getTitle();
        } 

        ManuscriptUpdated manuscriptUpdated = new ManuscriptUpdated(this);
        manuscriptUpdated.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void requestPublication(
        RequestPublicationCommand requestPublicationCommand
    ) {
        //implement business logic here:

        // 이미 출간 요청이 되어 있는 경우 중복 방지
        if (this.publicationRequested) {
            throw new IllegalStateException("이미 출간이 요청된 원고입니다.")
        }

        // 출간 요청 상태로 변경
        this.publicationRequested = true;

        // 출간 요청 이벤트 발행
        PublicationRequested event = new PublicationRequested(this);
        event.publishAfterCommit();

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void saveFinalManuscript(
        SaveFinalManuscriptCommand saveFinalManuscriptCommand
    ) {
        //implement business logic here:
        FinalManuscriptSaved event = new FinalManuscriptSaved(this);
        event.publishAfterCommit();

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
