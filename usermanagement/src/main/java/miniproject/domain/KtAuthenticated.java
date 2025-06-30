package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class KtAuthenticated extends AbstractEvent {

    private Long userId;
    private String name;
    private String email;
    private Boolean isKtUser;

    public KtAuthenticated(Member aggregate) {
        super(aggregate);
        this.userId = aggregate.getUserId();
        this.name = aggregate.getName();
        this.email = aggregate.getEmail();
        this.isKtUser = aggregate.getIsktUser();
    }

    public KtAuthenticated() {
        super();
    }
}
//>>> DDD / Domain Event
