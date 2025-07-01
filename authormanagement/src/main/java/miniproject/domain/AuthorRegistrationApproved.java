package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class AuthorRegistrationApproved extends AbstractEvent {

    private Long authorId;

    public AuthorRegistrationApproved(Author aggregate) {
        super(aggregate);
    }

    public AuthorRegistrationApproved() {
        super();
    }
}

