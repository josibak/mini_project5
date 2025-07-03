package miniproject.infra;

import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
import miniproject.event.SubcriptionCompleted;
import miniproject.event.SubscriptionExpired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class PolicyHandler {

    @Autowired
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubcriptionCompleted_UpdateMember(@Payload SubcriptionCompleted event) {
        if (event.getUserId() == null) return;

        System.out.println("### [Event Received] SubcriptionCompleted: " + event);

        Optional<Member> memberOptional = memberRepository.findById(event.getUserId());

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 상태 업데이트
            member.setIsSubscriber(true);
            memberRepository.save(member);

            System.out.println("### [Update Success] userId " + event.getUserId() + " is now a subscriber.");
        } else {
            System.out.println("### [Update Failed] No member found for userId " + event.getUserId());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriptionExpired_UpdateMember(@Payload SubscriptionExpired event) {
        if (event.getUserId() == null) return;

        System.out.println("### [Event Received] SubscriptionExpired: " + event);

        Optional<Member> memberOptional = memberRepository.findById(event.getUserId());

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setIsSubscriber(false);  // 또는 member.setSubscribeStatus(false)
            memberRepository.save(member);

            System.out.println("### [Update Success] userId " + event.getUserId() + " is now unsubscribed.");
        } else {
            System.out.println("### [Update Failed] No member found for userId " + event.getUserId());
        }
    }

}
