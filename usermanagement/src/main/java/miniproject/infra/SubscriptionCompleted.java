package miniproject.infra;

import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< External Event (infra)
@Data
@ToString
public class SubscriptionCompleted extends AbstractEvent {

    private Long userId;

    public SubscriptionCompleted() {
        super();
    }
}
//>>> External Event (infra)
