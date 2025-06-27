package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private Boolean subscribeStatus;

    public BookOpened(Member aggregate) {
        super(aggregate);
    }

    public BookOpened() {
        super();
    }
}
//>>> DDD / Domain Event
