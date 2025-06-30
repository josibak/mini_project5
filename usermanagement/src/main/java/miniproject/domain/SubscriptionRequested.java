package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionRequested extends AbstractEvent {

    private Long userId;
    private Boolean subscribe;

    public SubscriptionRequested(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();    //
        this.subscribe = aggregate.getSubscribeStatus();    //
    }

    public SubscriptionRequested() {
        super();
    }
}
//>>> DDD / Domain Event
