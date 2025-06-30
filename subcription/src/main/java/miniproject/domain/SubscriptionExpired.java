package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscriptionExpired extends AbstractEvent {

    private Long subscribeId;
    private String userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String status;


    public SubscriptionExpired(Subcription aggregate) {
        super(aggregate);
    }

    public SubscriptionExpired() {
        super();
    }
}
//>>> DDD / Domain Event
