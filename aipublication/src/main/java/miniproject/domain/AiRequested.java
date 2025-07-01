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
    }

    public AiRequested() {
        super();
    }
}
//>>> DDD / Domain Event
