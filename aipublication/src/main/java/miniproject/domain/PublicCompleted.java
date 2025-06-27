package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PublicCompleted extends AbstractEvent {

    private Long publicationId;
    private Long manuscriptId;
    private Long authorId;
    private String summary;
    private String postUrl;
    private String title;
    private Date publicAt;
    private String content;

    public PublicCompleted(Publication aggregate) {
        super(aggregate);
    }

    public PublicCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
