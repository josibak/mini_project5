package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BookRegistered extends AbstractEvent {

    private Long bookId;
    private String title;
    private String summary;
    private Integer viewCount;
    private Boolean isBestSeller;
    private String imageUrl;
    private String content;

    public BookRegistered(Book aggregate) {
        super(aggregate);
    }

    public BookRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
