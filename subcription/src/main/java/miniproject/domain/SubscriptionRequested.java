package miniproject.domain;

import lombok.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class SubscriptionRequested extends AbstractEvent {

    private Long userId;
    private String subscribeStatus;

    public SubscriptionRequested(Long userId, String subscribeStatus) {
        super();
        this.userId = userId;
        this.subscribeStatus = subscribeStatus;
    }

    public SubscriptionRequested() {
        super();
    }
}
