package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionFinished extends AbstractEvent {

    private Long subscribeId;
    private Long userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String status;


    public SubscriptionFinished(Subcription aggregate) {
        super(aggregate);
    }

    public SubscriptionFinished() {
        super();
    }
}
//>>> DDD / Domain Event
