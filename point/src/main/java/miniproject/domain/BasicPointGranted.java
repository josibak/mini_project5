package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BasicPointGranted extends AbstractEvent {

    private Long pointDeducted;
    private Long userId;
    private Integer balance;
    private Integer amount;
  

    public BasicPointGranted(PointAccount aggregate) {
        super(aggregate);
    }

    public BasicPointGranted() {
        super();
    }
}
//>>> DDD / Domain Event
