package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class FinalManuscriptSaved extends AbstractEvent {

    private Long manuscriptId;
    private String title;
    private String content;
    private Date updatedAt;
    private Long authorId;

    public FinalManuscriptSaved(Manuscript aggregate) {
        super(aggregate);
    }

    public FinalManuscriptSaved() {
        super();
    }
}
//>>> DDD / Domain Event
