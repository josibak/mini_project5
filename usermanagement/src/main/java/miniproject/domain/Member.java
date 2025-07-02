package miniproject.domain;

import miniproject.domain.SubscriberRegistered;
import miniproject.domain.SubscribeFinished;
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
@Table(name="Member_table")
@Data

//<<< DDD / Aggregate Root
public class Member  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
private Long userId;    
    
    
private Long bookId;    
    
    
private String name;    
    
    
private String email;    
    
    
// private Boolean subscribeStatus;    
private Boolean isSubscriber;
    
private Boolean isKtUser;

    @PostPersist
    public void onPostPersist(){


        SubscriberRegistered subscriberRegistered = new SubscriberRegistered(this);
        subscriberRegistered.publishAfterCommit();



        SubscribeFinished subscribeFinished = new SubscribeFinished(this);
        subscribeFinished.publishAfterCommit();

    
    }

    public static MemberRepository repository(){
        MemberRepository memberRepository = UsermanagementApplication.applicationContext.getBean(MemberRepository.class);
        return memberRepository;
    }



//<<< Clean Arch / Port Method
    public void openBookPoint(OpenBookPointCommand openBookPointCommand){
        
        //implement business logic here:
        

        PointBookOpened pointBookOpened = new PointBookOpened(this);
        pointBookOpened.publishAfterCommit();
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

    public void authKt(AuthKtCommand authKtCommand){
        
        //implement business logic here:
        


        KtAuthenticated ktAuthenticated = new KtAuthenticated(this);
        ktAuthenticated.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void bookOpen(BookOpenCommand bookOpenCommand){
        
        //implement business logic here:
        


        BookOpened bookOpened = new BookOpened(this);
        bookOpened.publishAfterCommit();
    }
//>>> Clean Arch / Port Method



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
