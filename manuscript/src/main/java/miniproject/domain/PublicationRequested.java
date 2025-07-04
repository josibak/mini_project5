package miniproject.domain;

import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
// 출간 요청됨.
@Data
@ToString
public class PublicationRequested extends AbstractEvent {

    private Long manuscriptId;
    private Long authorId;
    private String title;
    private String content;

    public PublicationRequested(Manuscript aggregate) {
        super(aggregate);
        this.manuscriptId = aggregate.getManuscriptId();
        this.authorId = aggregate.getAuthorId();
        this.title = aggregate.getTitle();
        this.content = aggregate.getContent();
    }

    public PublicationRequested() {
        super();
    }
}
//>>> DDD / Domain Event
