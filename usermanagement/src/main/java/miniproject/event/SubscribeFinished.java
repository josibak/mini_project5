package miniproject.event;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SubscribeFinished extends AbstractEvent {

    private Long userId;
    private Boolean subscribeStatus;

    public SubscribeFinished(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();
        this.subscribeStatus = aggregate.getSubscribeStatus();
    }

    public SubscribeFinished() {
        super();
    }
}
//>>> DDD / Domain Event
