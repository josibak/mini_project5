package miniproject.domain;

import java.time.LocalDateTime;
import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
// 원고 수정됨
@Data
@ToString
public class ManuscriptUpdated extends AbstractEvent {

    private Long manuscriptId;
    private String title;
    private String content;
    private LocalDateTime updatedAt;
    private Long authorId;

    public ManuscriptUpdated(Manuscript aggregate) {
        super(aggregate);
        this.manuscriptId = aggregate.getManuscriptId();
        this.authorId = aggregate.getAuthorId();
        this.title = aggregate.getTitle();
        this.content = aggregate.getContent();
    }

    public ManuscriptUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
