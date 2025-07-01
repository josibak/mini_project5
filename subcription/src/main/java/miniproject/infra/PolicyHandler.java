package miniproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    SubcriptionRepository subcriptionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubscribtionRequested'"
    )
    public void wheneverSubscribtionRequested_SubcriptionRequest(
        @Payload SubscribtionRequested subscribtionRequested
    ) {
        System.out.println(
            "\n\n##### listener SubcriptionRequest : " +
            subscribtionRequested +
            "\n\n"
        );

        // 구독 생성 및 SubcriptionCompleted 이벤트 발행
        Subcription subcription = new Subcription();
        subcription.setUserId(subscribtionRequested.getUserId());
        subcription.setSubscriptionStartedAt(new java.util.Date());
        subcription.setSubscriptionExpiredAt(new java.util.Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30));
        subcription.setStatus("requested");
        subcriptionRepository.save(subcription);

        // SubcriptionCompleted 이벤트 발행
        SubcriptionCompleted completed = new SubcriptionCompleted(subcription);
        completed.publishAfterCommit();
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubcriptionCompleted'"
    )
    public void wheneverSubcriptionCompleted_SubcriptionComplete(
        @Payload SubcriptionCompleted subcriptionCompleted
    ) {
        System.out.println(
            "\n\n##### listener ActivateStatus : " +
            subcriptionCompleted +
            "\n\n"
        );
        // 도메인 메서드 호출
        Subcription.SubcriptionComplete(subcriptionCompleted);
    }
}
//>>> Clean Arch / Inbound Adaptor
