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

    // Kafka에서 들어오는 문자열 처리 (디버깅용)
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        // Optional: 로그 찍기 등
    }

    // 구독 완료 이벤트 수신 → subscribeStatus = true
    @StreamListener(KafkaProcessor.INPUT)
    public void onSubscriptionCompleted(@Payload SubscriptionCompleted event) {
        System.out.println("##### SubscriptionCompleted 이벤트 수신됨: " + event);
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            System.out.println("########################Before activate :  " + member.getSubscribeStatus());
            member.activateSubscription(); // 상태 변경 로직은 Member 엔티티 내부
            System.out.println("(\"########################After activate : " + member.getSubscribeStatus());
            memberRepository.save(member);
            System.out.println("(\"########################Saved member : " + member);
        });
    }

    // 구독 만료 이벤트 수신 → subscribeStatus = false
    @StreamListener(KafkaProcessor.INPUT)
    public void onSubscriptionFinished(@Payload SubscriptionFinished event) {
        System.out.println("##### SubscriptionFinished 이벤트 수신됨: " + event);
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.deactivateSubscription();
            memberRepository.save(member);
        });
    }
}
