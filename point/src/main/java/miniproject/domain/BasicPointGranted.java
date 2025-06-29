package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class BasicPointGranted extends AbstractEvent {

    private Long pointAccountId;
    private Long userId;
    private Integer balance;
    private Integer amount;

    public BasicPointGranted(PointAccount pointAccount) {
        super(pointAccount);
        this.pointAccountId = pointAccount.getPointAccountId();
        this.userId = pointAccount.getUserId();
        this.balance = pointAccount.getBalance();
        this.amount = 1000;  // 기본 포인트 지급
    }

    public BasicPointGranted() {
        super();
    }
}
//>>> DDD / Domain Event
