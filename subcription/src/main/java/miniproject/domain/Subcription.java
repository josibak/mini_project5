package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.SubcriptionApplication;
import miniproject.domain.SubcriptionCompleted;
import miniproject.domain.SubscriptionExpired;

@Entity
@Table(name = "Subcription_table")
@Data
//<<< DDD / Aggregate Root
public class Subcription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subscribeId;

    private Long userId;

    private Date subscriptionStartedAt;

    private Date subscriptionExpiredAt;

    @PostPersist
    public void onPostPersist() {
        SubcriptionCompleted subcriptionCompleted = new SubcriptionCompleted(
            this
        );
        subcriptionCompleted.publishAfterCommit();

        SubscriptionExpired subscriptionExpired = new SubscriptionExpired(this);
        subscriptionExpired.publishAfterCommit();
    }

    public static SubcriptionRepository repository() {
        SubcriptionRepository subcriptionRepository = SubcriptionApplication.applicationContext.getBean(
            SubcriptionRepository.class
        );
        return subcriptionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void subcriptionRequest(
        SubscriptionRequested subscribtionRequested
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Subcription subcription = new Subcription();
        repository().save(subcription);

        */

        /** Example 2:  finding and process
        

        repository().findById(subscribtionRequested.get???()).ifPresent(subcription->{
            
            subcription // do something
            repository().save(subcription);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
