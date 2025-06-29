package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class PointDeducted extends AbstractEvent {

    private Long pointAccountId;
    private Long userId;
    private Integer balance;

    public PointDeducted(PointAccount pointAccount) {
        super(pointAccount);
        this.pointAccountId = pointAccount.getPointAccountId();
        this.userId = pointAccount.getUserId();
        this.balance = pointAccount.getBalance();
    }

    public PointDeducted() {
        super();
    }
}
//>>> DDD / Domain Event
