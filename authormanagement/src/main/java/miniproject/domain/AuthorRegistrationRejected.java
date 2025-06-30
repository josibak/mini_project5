package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorRegistrationRejected extends AbstractEvent {

    private Long authorId;
    private Author.RegistrationStatus registrationStatus;

    public AuthorRegistrationRejected(Author aggregate) {
        super(aggregate);
    }

    public AuthorRegistrationRejected() {
        super();
    }
}

