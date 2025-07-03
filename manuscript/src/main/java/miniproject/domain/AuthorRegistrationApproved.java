package miniproject.domain;

import lombok.Data;
import miniproject.infra.AbstractEvent;

@Data
public class AuthorRegistrationApproved extends AbstractEvent {

    private Long authorId;
    private Integer registrationStatus;
}