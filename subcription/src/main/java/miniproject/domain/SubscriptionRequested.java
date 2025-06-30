package miniproject.domain;

import lombok.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class SubscriptionRequested extends AbstractEvent {

    private Long userId;
    private Boolean subscribeStatus;

    public SubscriptionRequested(Member aggregate) {
        super(aggregate);
    }

    public SubscriptionRequested() {
        super();
    }
}
