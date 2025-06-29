package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class MemberRegistered extends AbstractEvent {

    private Long userId;
    private String name;
    private String email;

    public MemberRegistered(Member aggregate) {
        super(aggregate);
    }

    public MemberRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
