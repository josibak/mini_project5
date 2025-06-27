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
    private String userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;

    public SubcriptionCompleted(Subcription aggregate) {
        super(aggregate);
    }

    public SubcriptionCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
