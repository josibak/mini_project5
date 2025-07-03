package miniproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
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
