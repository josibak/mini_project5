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

@Data
@Entity
@Table(name="Member_table")
public class Member  {

    public static final String Repository = null;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    private Long userId;    
       
    private Long bookId;     
    
    private String name;    
    
    private String email;    
    
    private String subscribeStatus;    
    
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

    public void openBookPoint(OpenBookPointCommand openBookPointCommand){

        this.bookId = openBookPointCommand.getBookId();
        this.subscribeStatus = openBookPointCommand.getSubscribeStatus();

        PointBookOpened pointBookOpened = new PointBookOpened(this);
        pointBookOpened.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void subscriptionRequest(SubscriptionRequestCommand command){
        
        //implement business logic here:
        this.subscribeStatus = command.getSubscribeStatus();

        SubscriptionRequested event = new SubscriptionRequested(this);
        event.publishAfterCommit();
        // SubscribtionRequested subscribtionRequested = new SubscribtionRequested(this);
        // subscribtionRequested.publishAfterCommit();
    }

    public void registerMember(RegisterMemberCommand registerMemberCommand){
        
        //implement business logic here:
        

        miniproject.external.MemberQuery memberQuery = new miniproject.external.MemberQuery();      
        memberQuery.setEmail(registerMemberCommand.getEmail());

        miniproject.external.MemberInfo result =
            MemberApplication.applicationContext
                .getBean(miniproject.external.Service.class)
                .member(memberQuery);
        
        this.name = result.getName();
        this.email = result.getEmail();
        this.subscribeStatus = "NOSUBSCRIBE";
        this.isKtUser = result.getIsktUser();

        MemberRegistered memberRegistered = new MemberRegistered(this);
        memberRegistered.publishAfterCommit();
    }

    public void authKt(AuthKtCommand authKtCommand){
        
        //implement business logic here:
        this.name = authKtCommand.getName();         // 선택 사항
        this.email = authKtCommand.getEmail();       // 선택 사항
        this.isKtUser = authKtCommand.getIsKtUser();

        KtAuthenticated ktAuthenticated = new KtAuthenticated(this);
        ktAuthenticated.publishAfterCommit();
    }

    public void bookOpen(BookOpenCommand bookOpenCommand){
        
        //implement business logic here:
        this.bookId = bookOpenCommand.getBookId();
        this.subscribeStatus = bookOpenCommand.getSubscribeStatus();

        BookOpened bookOpened = new BookOpened(this);
        bookOpened.publishAfterCommit();
    }
}
