package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PointBookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private Boolean subscribeStatus;

    public PointBookOpened(Member aggregate) {
        super(aggregate);
    }

    public PointBookOpened() {
        super();
    }
}
//>>> DDD / Domain Event
