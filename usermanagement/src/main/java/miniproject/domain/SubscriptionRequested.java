package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequested extends AbstractEvent {

    private Long userId;

    public SubscriptionRequested(Member member) {
        super();
        this.userId = member.getUserId();
    }
}
//>>> DDD / Domain Event
