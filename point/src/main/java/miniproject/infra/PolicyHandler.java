package miniproject.infra;

import javax.transaction.Transactional;

import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PointAccountService pointAccountService;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        // 디버깅용. 모든 이벤트 수신 로그 찍기
        System.out.println("##### Received Raw Event: " + eventString);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookOpened'"
    )
    public void wheneverBookOpened_BookOpenedByPoint(@Payload BookOpened event) {
        System.out.println("##### listener BookOpened : " + event);
        pointAccountService.handleBookOpened(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MemberRegistered'"
    )
    public void wheneverMemberRegistered_UserRegistered(@Payload MemberRegistered event) {
        System.out.println("##### listener MemberRegistered : " + event);
        pointAccountService.handleUserRegistered(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='KtAuthenticated'"
    )
    public void wheneverKtAuthenticated_KtMemberVerified(@Payload KtAuthenticated event) {
        System.out.println("##### listener KtAuthenticated : " + event);
        pointAccountService.handleKtAuthenticated(event);
    }
}
