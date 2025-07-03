package miniproject.infra;

import javax.transaction.Transactional;

import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.Member;
import miniproject.domain.MemberRepository;
import miniproject.domain.SubscriptionCompleted;
import miniproject.domain.SubscriptionFinished;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

// <<< Clean Arch / Inbound Adaptor >>>
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    MemberRepository memberRepository;

    // 디버깅용 전체 수신 로그
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        System.out.println("[Kafka Raw Payload] " + eventString);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void debugRawMessage(org.springframework.messaging.Message<?> message) {
        System.out.println("=== [Kafka Raw Message] ===");
        System.out.println("Payload: " + message.getPayload());
        System.out.println("Headers: " + message.getHeaders());
    }

    // 구독 완료 이벤트 수신 → subscribeStatus = true
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubcriptionCompleted'"
    )
    public void onSubscriptionCompleted(@Payload SubscriptionCompleted event) {
        System.out.println("##### SubscriptionCompleted 이벤트 수신됨: " + event);
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            System.out.println("########################Before activate : " + member.getSubscribeStatus());
            member.activateSubscription(); // Member 엔티티 내부 메서드
            System.out.println("########################After activate : " + member.getSubscribeStatus());
            memberRepository.save(member);
            System.out.println("########################Saved member : " + member);
        });
    }

    // 구독 만료 이벤트 수신 → subscribeStatus = false
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubscriptionFinished'"
    )
    public void onSubscriptionFinished(@Payload SubscriptionFinished event) {
        System.out.println("##### SubscriptionFinished 이벤트 수신됨: " + event);
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.deactivateSubscription(); // Member 엔티티 내부 메서드
            memberRepository.save(member);
            System.out.println("##### Updated member (subscription off): " + member);
        });
    }
}
