package miniproject.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import miniproject.AipublicationApplication;
import miniproject.service.AIPublicationService;

@Entity
@Table(name = "Publication_table")
@Data
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long publicationId;

    private Long manuscriptId;
    private String summary;

    @Column(length = 1000) // 또는 columnDefinition = "TEXT"
    private String postUrl;

    private String title;
    private Long authorId;
    private Date publicAt;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String bookId;
    private String status;

    public static PublicationRepository repository() {
        return AipublicationApplication.applicationContext.getBean(PublicationRepository.class);
    }

    // 1. 출간 요청시: 출간정보 저장 + 표지자동등록요청됨 이벤트 발행
    public static Publication publicRequest(PublicationRequested event) {
        Publication publication = new Publication();
        publication.setManuscriptId(event.getManuscriptId());
        publication.setTitle(event.getTitle());
        publication.setContent(event.getContent());
        publication.setAuthorId(event.getAuthorId());
        publication.setStatus("requested");
        repository().save(publication);

        // 이벤트 발행
        AiRequested aiRequested = new AiRequested(publication);
        aiRequested.publishAfterCommit();

        return publication;
    }

    // 2. 표지자동등록요청됨 이벤트 수신: AI 결과 반영 + status "ai_ready"
    public static void aiRequest(AiRequested event) {
        repository().findById(event.getPublicationId()).ifPresent(publication -> {
            AIPublicationService aiService = AipublicationApplication.applicationContext.getBean(AIPublicationService.class);
            System.out.println("AI서비스 시작! "+ publication.getPublicationId());
            String summary = aiService.generateSummary(publication.getContent());
            String coverUrl = aiService.generateCover(publication.getTitle(), publication.getContent());

            publication.setSummary(summary);
            publication.setPostUrl(coverUrl);
            publication.setStatus("ai_ready");  // AI 작업 완료 상태
            repository().save(publication);
        });
    }

    // 3. 출간 작업 완료: status "completed" + 출간 작업 완료됨 이벤트 발행
    public void publicComplete(PublicCompleteCommand publicCompleteCommand) {
        this.postUrl = publicCompleteCommand.getPostUrl();
        this.title = publicCompleteCommand.getTitle();
        this.authorId = publicCompleteCommand.getAuthorId();
        this.publicAt = publicCompleteCommand.getPublicAt();
        this.content = publicCompleteCommand.getContent();
        this.bookId = publicCompleteCommand.getBookId();
        this.status = publicCompleteCommand.getStatus();
        this.summary = publicCompleteCommand.getSummary();
        this.status = "completed";
        this.publicAt = new Date();
        repository().save(this);

        PublicCompleted publicCompleted = new PublicCompleted(this);
        publicCompleted.publishAfterCommit();
    }
}

