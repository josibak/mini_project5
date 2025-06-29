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
    }

    public KtAuthenticated() {
        super();
    }
}
//>>> DDD / Domain Event
