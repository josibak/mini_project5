package miniproject.infra;

import lombok.*;
import miniproject.infra.AbstractEvent;

//<<< External Event (infra)
@Data
@ToString
public class SubscriptionExpired extends AbstractEvent {

    private Long userId;

    public SubscriptionExpired() {
        super();
    }
}
//>>> External Event (infra)
