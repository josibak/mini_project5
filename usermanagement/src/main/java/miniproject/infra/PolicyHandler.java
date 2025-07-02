package miniproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
import miniproject.event.SubscribeFinished;
import miniproject.event.SubscriberRegistered;
import miniproject.infra.SubscriptionCompleted;
import miniproject.infra.SubscriptionExpired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    MemberRepository memberRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriptionCompleted_Handle(@Payload SubscriptionCompleted event) {
        if (!event.validate()) return;

        System.out.println("[SubscriptionCompleted] event received: " + event.toJson());

        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(true);
            memberRepository.save(member);

            // SubscriberRegistered 이벤트 발행
            SubscriberRegistered subscriberRegistered = new SubscriberRegistered(member);
            subscriberRegistered.publishAfterCommit();
        });
    }

    // subscriptionExpired 이벤트 수신 → SubscribeFinished 이벤트 발행
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriptionExpired_Handle(@Payload SubscriptionExpired event) {
        if (!event.validate()) return;

        System.out.println("[SubscriptionExpired] event received: " + event.toJson());

        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(false);
            memberRepository.save(member);

            // SubscribeFinished 이벤트 발행
            SubscribeFinished subscribeFinished = new SubscribeFinished(member);
            subscribeFinished.publishAfterCommit();
        });
    }
}
//>>> Clean Arch / Inbound Adaptor
