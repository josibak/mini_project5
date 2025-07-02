package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;
    private Boolean isSubscriber;

    public UserBookOpened(Member member, Long bookId) {
        super(member);
        this.userId = member.getUserId();
        this.bookId = bookId;
        this.isSubscriber = member.getSubscribeStatus();
    }
}
//>>> DDD / Domain Event
