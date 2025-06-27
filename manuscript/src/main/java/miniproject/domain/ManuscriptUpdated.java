package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ManuscriptUpdated extends AbstractEvent {

    private Long manuscriptId;
    private String title;
    private String content;
    private Date updatedAt;
    private Long authorId;

    public ManuscriptUpdated(Manuscript aggregate) {
        super(aggregate);
    }

    public ManuscriptUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
