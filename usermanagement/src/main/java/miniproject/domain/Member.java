package miniproject.domain;

import miniproject.UsermanagementApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;

@Entity
@Table(name = "member_table")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
private Long userId;    
    
    
private Long bookId;    
    
    
private String name;    
    
    
private String email;    
    
    
// private Boolean subscribeStatus;    
private Boolean isSubscriber;
    
private Boolean isKtUser;

    public static MemberRepository repository(){
        MemberRepository memberRepository = UsermanagementApplication.applicationContext.getBean(MemberRepository.class);
        return memberRepository;
    }


    public void subscribtionRequest(SubscribtionRequestCommand command){
        // SubscribtionRequested 이벤트 생성
        SubscribtionRequested event = new SubscribtionRequested(this);
        event.publishAfterCommit();
    }

    public void registerMember(RegisterMemberCommand command){
        this.name = command.getName();
        this.email = command.getEmail();
        this.isSubscriber = false;
        this.isKtUser = command.getIsKtUser();

        MemberRegistered memberRegistered = new MemberRegistered(this);
        memberRegistered.publishAfterCommit();
    }


    public void bookOpen(BookOpenCommand command){
        BookOpened bookOpened = new BookOpened(this);
        bookOpened.setBookId(command.getBookId());
        bookOpened.setIsSubscriber(this.getIsSubscriber() != null && this.getIsSubscriber());

        bookOpened.publishAfterCommit();
    }


    public Long getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
//>>> DDD / Aggregate Root
