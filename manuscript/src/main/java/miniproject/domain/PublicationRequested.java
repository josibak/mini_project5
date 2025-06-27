package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PublicationRequested extends AbstractEvent {

    private Long manuscriptId;
    private Long authorId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updateAt;

    public PublicationRequested(Manuscript aggregate) {
        super(aggregate);
    }

    public PublicationRequested() {
        super();
    }
}
//>>> DDD / Domain Event
