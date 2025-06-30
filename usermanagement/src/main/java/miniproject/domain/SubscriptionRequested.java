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

    public SubscrptionRequested(Member aggregate) {
        super(aggregate);
    }

    public SubscriptionRequested() {
        super();
    }
}
//>>> DDD / Domain Event
