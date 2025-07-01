package miniproject.domain;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public PublicationRequested(Manuscript aggregate) {
        super(aggregate);
    }

    public PublicationRequested() {
        super();
    }
}
//>>> DDD / Domain Event
