package miniproject.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
import miniproject.event.SubcriptionCompleted;
import miniproject.event.SubscriptionExpired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class UserpageViewHandler {

    private static final String NO = null;
    private static final String NOTSUBSCRIBED = null;
    //<<< DDD / CQRS
    @Autowired
    private UserpageRepository userpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberRegistered_then_CREATE_1(
        @Payload MemberRegistered memberRegistered
    ) {
        try {
            if (!memberRegistered.validate()) return;

            // view 객체 생성
            Userpage userpage = new Userpage();
            // view 객체에 이벤트의 Value 를 set 함
            userpage.setUserId(memberRegistered.getUserId());
            userpage.setName(memberRegistered.getName());
            userpage.setEmail(memberRegistered.getEmail());
            userpage.setIsKtUser(memberRegistered.isKtUser() ? "YES" : "NO");
            userpage.setSubscribeStatus("NOTSUBSCRIBED");
            // view 레파지 토리에 save
            userpageRepository.save(userpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubcriptionCompleted_then_UPDATE(@Payload SubcriptionCompleted event) {
        try {
            if (event.getUserId() == null) return;

            Optional<Userpage> optional = userpageRepository.findById(event.getUserId());
            if (optional.isPresent()) {
                Userpage userpage = optional.get();
                userpage.setSubscribeStatus("SUBSCRIBED");
                userpageRepository.save(userpage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenSubscriptionExpired_then_UPDATE(@Payload SubscriptionExpired event) {
        try {
            if (event.getUserId() == null) return;

            // SubscriptionExpired는 userId가 String으로 올 수 있으므로 변환
            Long userId = null;
            try {
                userId = Long.parseLong(event.getUserId());
            } catch (Exception ex) {
                System.out.println("Invalid userId format in SubscriptionExpired event");
                return;
            }

            Optional<Userpage> optional = userpageRepository.findById(userId);
            if (optional.isPresent()) {
                Userpage userpage = optional.get();
                userpage.setSubscribeStatus("NOTSUBSCRIBED");
                userpageRepository.save(userpage);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
