package miniproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    // public void whatever(@Payload String eventString) {}
    public void wheneverSubscriberRegistered_AddToSubscribers(@Payload SubscriberRegistered event){
        if (!event.validate()) return;
        
        System.out.println("[SubscriberRegistered] event received:" + event.toJson());

        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(event.getSubscribeStatus());
            memberRepository.save(member);
        });
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSubscriberFinished_HandleExpiration(@Payload SubscribeFinished event){
        if (!event.validate()) return;

        System.out.println("[SubscribeFinished] event received: " + event.toJson());

        memberRepository.findById(event.getUserId()).ifPresent(member -> {
            member.setSubscribeStatus(false);
            memberRepository.save(member);
        });
    }
}
//>>> Clean Arch / Inbound Adaptor
