package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class KtPointGranted extends AbstractEvent {

    private Long pointAccountId;
    private Long userId;
    private Integer balance;
    private Integer amount;

    public KtPointGranted(PointAccount aggregate) {
        super(aggregate);
    }

    public KtPointGranted() {
        super();
    }
}
//>>> DDD / Domain Event
