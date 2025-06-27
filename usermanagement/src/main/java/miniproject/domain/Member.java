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
    
    
private Boolean subscribeStatus;    
    
    
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
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void subscribtionRequest(SubscribtionRequestCommand subscribtionRequestCommand){
        
        //implement business logic here:
        


        SubscribtionRequested subscribtionRequested = new SubscribtionRequested(this);
        subscribtionRequested.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void registerMember(RegisterMemberCommand registerMemberCommand){
        
        //implement business logic here:
        

        miniproject.external.MemberQuery memberQuery = new miniproject.external.MemberQuery();
        // memberQuery.set??()        
          = MemberApplication.applicationContext
            .getBean(miniproject.external.Service.class)
            .member(memberQuery);

        MemberRegistered memberRegistered = new MemberRegistered(this);
        memberRegistered.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
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



}
//>>> DDD / Aggregate Root
