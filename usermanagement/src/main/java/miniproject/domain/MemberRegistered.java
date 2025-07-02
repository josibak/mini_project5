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
        if (aggregate != null) {
            this.userId = aggregate.getUserId();
            this.name = aggregate.getName();
            this.email = aggregate.getEmail();
        }
    }

    public MemberRegistered() {
        super();
    }

    public Long getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
//>>> DDD / Domain Event
