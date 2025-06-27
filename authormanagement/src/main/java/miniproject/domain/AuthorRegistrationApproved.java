package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorRegistrationApproved extends AbstractEvent {

    private Long authorId;
    private Integer registrationStatus;

    public AuthorRegistrationApproved(Author aggregate) {
        super(aggregate);
    }

    public AuthorRegistrationApproved() {
        super();
    }
}
//>>> DDD / Domain Event
