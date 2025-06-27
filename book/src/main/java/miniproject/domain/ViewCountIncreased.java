package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ViewCountIncreased extends AbstractEvent {

    private Long bookId;
    private Integer viewCount;

    public ViewCountIncreased(Book aggregate) {
        super(aggregate);
    }

    public ViewCountIncreased() {
        super();
    }
}
//>>> DDD / Domain Event
