package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BookDesignatedAsBestSeller extends AbstractEvent {

    private Long bookId;
    private Integer viewCount;
    private Boolean isBestSeller;

    public BookDesignatedAsBestSeller(Book aggregate) {
        super(aggregate);
    }

    public BookDesignatedAsBestSeller() {
        super();
    }
}
//>>> DDD / Domain Event
