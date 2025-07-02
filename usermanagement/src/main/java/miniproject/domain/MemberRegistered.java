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
    private boolean isKtUser;

    public MemberRegistered(Member aggregate) {
        super(aggregate);
        if (aggregate != null) {
            this.userId = aggregate.getUserId();
            this.isKtUser = aggregate.getIsKtUser() != null && aggregate.getIsKtUser();
        }
    }

    @Override
    public String getEventType() {
        return "UserRegistered";
    }

    public Long getUserId() {
        return userId;
    }
}
