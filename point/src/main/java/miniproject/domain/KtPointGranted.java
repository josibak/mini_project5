package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class KtPointGranted extends AbstractEvent {

    private Long pointAccountId;
    private Long userId;
    private Integer balance;
    private Integer amount;

    public KtPointGranted(PointAccount pointAccount) {
        super(pointAccount);
        this.pointAccountId = pointAccount.getPointAccountId();
        this.userId = pointAccount.getUserId();
        this.balance = pointAccount.getBalance();
        this.amount = 3000; // KT 인증 시 지급 포인트
    }

    public KtPointGranted() {
        super();
    }
}
//>>> DDD / Domain Event
