package miniproject.event;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriberRegistered extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private String name;
    private Boolean subscribeStatus;

    public SubscriberRegistered(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();
        this.bookId = aggregate.getBookId();
        this.name = aggregate.getName();
        this.subscribeStatus = aggregate.getSubscribeStatus();
    }

    public SubscriberRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
