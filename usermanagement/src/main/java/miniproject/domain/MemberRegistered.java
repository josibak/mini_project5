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
public class MemberRegistered extends AbstractEvent {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private Boolean isKtUser;
    private Boolean subscribeStatus;

    public MemberRegistered(Member member) {
        super(member);
        this.userId = member.getUserId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.isKtUser = member.getIsKtUser();
        this.subscribeStatus = member.getSubscribeStatus();
        this.setEventType("MemberRegistered");
    }
}
//>>> DDD / Domain Event
