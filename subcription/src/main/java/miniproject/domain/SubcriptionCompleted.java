package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubcriptionCompleted extends AbstractEvent {

    private Long subscribeId;
    private Long userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String status;


    public SubcriptionCompleted(Subcription aggregate) {
        super(aggregate);
        this.subscribeId = aggregate.getSubscribeId();
        this.userId = aggregate.getUserId();
        this.subscriptionStartedAt = aggregate.getSubscriptionStartedAt();
        this.subscriptionExpiredAt = aggregate.getSubscriptionExpiredAt();
        this.status = aggregate.getStatus();
    }

    public SubcriptionCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
