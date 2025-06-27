package miniproject.domain;

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
    }

    public SubscribeFinished() {
        super();
    }
}
//>>> DDD / Domain Event
