package miniproject.domain;

import miniproject.event.BookOpened;
import miniproject.event.KtAuthenticated;
import miniproject.event.MemberRegistered;
import miniproject.event.PointBookOpened;
import miniproject.event.SubscribeFinished;
import miniproject.event.SubscriberRegistered;
import miniproject.event.SubscribtionRequested;
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
public class Member  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long userId;
    private Long bookId;
    private String name;
    private String email;
    private Boolean isSubscriber;
    private Boolean isKtUser;

    @Transient
    public Object Repository;

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



    public void openBookPoint(OpenBookPointCommand command){
        
        //implement business logic here:
        this.bookId = command.getBookId();
        this.isSubscriber = command.getSubscribeStatus();
        
        PointBookOpened pointBookOpened = new PointBookOpened(this);
        pointBookOpened.publishAfterCommit();
    }


    public void subscribtionRequest(SubscribtionRequestCommand command){
        
        //implement business logic here:
        this.isSubscriber = command.getSubscribe();

        SubscribtionRequested event = new SubscribtionRequested(this);
        event.publishAfterCommit();
        // SubscribtionRequested subscribtionRequested = new SubscribtionRequested(this);
        // subscribtionRequested.publishAfterCommit();
    }

    
    public void registerMember(RegisterMemberCommand registerMemberCommand){
        
        //implement business logic here:
        
        miniproject.external.MemberQuery memberQuery = new miniproject.external.MemberQuery();
        // memberQuery.set??()        
        memberQuery.setEmail(registerMemberCommand.getEmail());

        miniproject.external.MemberInfo result =
            miniproject.external.ServiceProvider.INSTANCE.member(memberQuery);
        
        this.name = result.getName();
        this.email = result.getEmail();
        this.isSubscriber = false;
        this.isKtUser = result.getIsKtUser();

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
        this.isSubscriber = bookOpenCommand.getSubscribeStatus();

        BookOpened bookOpened = new BookOpened(this);
        bookOpened.publishAfterCommit();
    }

}

