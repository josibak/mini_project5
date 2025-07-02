package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@NoArgsConstructor
public class BookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private Boolean isSubscriber;

    public BookOpened(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();
    }

    @Override
    public String getEventType() {
        return "userBookOpened";
    }
}
