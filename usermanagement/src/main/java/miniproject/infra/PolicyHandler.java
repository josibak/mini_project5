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
    MemberRepository memberRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriberRegister_UpdateStatus(@Payload SubscriberRegister event) {
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(true);
            memberRepository.save(member);
        });
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriptionFinish_UpdateStatus(@Payload SubscriptionFinished event) {
        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(false);
            memberRepository.save(member);
        });
    }
}