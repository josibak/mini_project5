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
import miniproject.domain.SubscriptionFinished;

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

    private String status;

    public static SubcriptionRepository repository() {
        SubcriptionRepository subcriptionRepository = SubcriptionApplication.applicationContext.getBean(
            SubcriptionRepository.class
        );
        return subcriptionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void subcriptionRequest(SubscribtionRequested subscribtionRequested) {
        // 이벤트만 발행, 상태 변경/저장 X
        subscribtionRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
