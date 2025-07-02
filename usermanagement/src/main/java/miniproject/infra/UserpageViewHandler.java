package miniproject.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;
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
            userpage.setIsKtUser(NO);
            userpage.setSubscribeStatus(NOTSUBSCRIBED);
            userpage.setName(memberRegistered.getName());
            userpage.setEmail(memberRegistered.getEmail());
            // view 레파지 토리에 save
            userpageRepository.save(userpage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
