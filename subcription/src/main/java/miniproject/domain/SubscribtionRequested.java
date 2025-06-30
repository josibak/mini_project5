package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class SubscribtionRequested extends AbstractEvent {

    private Long subscribeId;
    private Long userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String status;

    public SubscribtionRequested(Subcription aggregate) {
        super(aggregate);
        this.subscribeId = aggregate.getSubscribeId();
        this.userId = aggregate.getUserId();
        this.subscriptionStartedAt = aggregate.getSubscriptionStartedAt();
        this.subscriptionExpiredAt = aggregate.getSubscriptionExpiredAt();
        this.status = aggregate.getStatus();
    } 
    public SubscribtionRequested() {
        super();
    }
}
