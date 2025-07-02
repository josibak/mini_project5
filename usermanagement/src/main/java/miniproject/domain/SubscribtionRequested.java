package miniproject.domain;

import java.time.LocalDate;
import java.util.*;

import javax.validation.OverridesAttribute;

import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
@NoArgsConstructor
public class SubscribtionRequested extends AbstractEvent {

    private Long userId;

    public SubscribtionRequested(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();
    }

    @Override
    public String getEventType() {
        return "SubscribtionRequested";
    }
}
//>>> DDD / Domain Event
