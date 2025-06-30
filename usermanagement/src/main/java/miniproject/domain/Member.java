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

    public void subscriptionRequest(SubscriptionRequestCommand subscriptionRequestCommand){
        
        this.subscribeStatus = subscriptionRequestCommand.getSubscribeStatus();

        SubscriptionRequested subscriptionRequested = new SubscriptionRequested(this);
        subscriptionRequested.publishAfterCommit();
    }

    public void registerMember(RegisterMemberCommand registerMemberCommand){
        
        this.name = registerMemberCommand.getName();
        this.email = registerMemberCommand.getEmail();

        miniproject.external.MemberQuery memberQuery = new miniproject.external.MemberQuery();
        memberQuery.setName(this.name);
        memberQuery.setEmail(this.email);

        miniproject.external.Service service = MemberApplication.applicationContext.getBean(miniproject.external.Service.class);
        service.member(memberQuery);

        MemberRegistered memberRegistered = new MemberRegistered(this);
        memberRegistered.publishAfterCommit();
    }

    public void authKt(AuthKtCommand authKtCommand){

        this.name = authKtCommand.getName();
        this.email = authKtCommand.getEmail();
        this.isKtUser = authKtCommand.getIsKtUser();

        KtAuthenticated ktAuthenticated = new KtAuthenticated(this);
        ktAuthenticated.publishAfterCommit();
    }

    public void bookOpen(BookOpenCommand bookOpenCommand){

        this.bookId = bookOpenCommand.getBookId();
        this.subscribeStatus = bookOpenCommand.getSubscribeStatus();

        BookOpened bookOpened = new BookOpened(this);
        bookOpened.publishAfterCommit();
    }
}
