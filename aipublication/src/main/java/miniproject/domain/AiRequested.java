package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AiRequested extends AbstractEvent {

    private Long publicationId;
    private Long manuscriptId;
    private String summary;
    private String postUrl;
    private String title;
    private Long authorId;
    private Date publicAt;
    private String content;
    private String bookId;
    private String status;

    public AiRequested(Publication aggregate) {
        super(aggregate);
            this.publicationId = aggregate.getPublicationId();
            this.manuscriptId = aggregate.getManuscriptId();
            this.summary = aggregate.getSummary();
            this.postUrl = aggregate.getPostUrl();
            this.title = aggregate.getTitle();
            this.authorId = aggregate.getAuthorId();
            this.publicAt = aggregate.getPublicAt();
            this.content = aggregate.getContent();
            this.bookId = aggregate.getBookId();
            this.status = aggregate.getStatus();
        
    }

    public AiRequested() {
        super();
    }
}
//>>> DDD / Domain Event
