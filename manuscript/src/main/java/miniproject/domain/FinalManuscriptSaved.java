package miniproject.domain;

import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
// 
@Data
@ToString
public class FinalManuscriptSaved extends AbstractEvent {

    private Long manuscriptId;
    private String title;
    private String content;
    private Long authorId;

    public FinalManuscriptSaved(Manuscript aggregate) {
        super(aggregate);
        this.manuscriptId = aggregate.getManuscriptId();
        this.title = aggregate.getTitle();
        this.content = aggregate.getContent();
        this.authorId = aggregate.getAuthorId();
    }

    public FinalManuscriptSaved() {
        super();
    }
}
//>>> DDD / Domain Event
