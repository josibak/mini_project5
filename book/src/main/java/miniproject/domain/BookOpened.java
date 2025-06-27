package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class BookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private Boolean subscribeStatus;
}
