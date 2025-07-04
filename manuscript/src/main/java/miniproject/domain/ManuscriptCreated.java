package miniproject.domain;

import java.time.LocalDateTime;
import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
// 원고 생성됨
@Data
@ToString
public class ManuscriptCreated extends AbstractEvent {

    private Long manuscriptId;
    private Long authorId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ManuscriptCreated(Manuscript aggregate) {
        super(aggregate);
    }

    public ManuscriptCreated() {
        super();
    }
}
//>>> DDD / Domain Event
