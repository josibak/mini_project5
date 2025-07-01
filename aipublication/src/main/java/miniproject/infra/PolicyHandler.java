package miniproject.infra;

import javax.transaction.Transactional;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;

@Service
@Transactional
public class PolicyHandler {

    // 출간 요청됨 이벤트 수신 → 출간정보 저장 및 표지자동등록요청됨 이벤트 발행
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublicationRequested'"
    )
    public void wheneverPublicationRequested(@Payload PublicationRequested event) {
        System.out.println("##### listener PublicationRequested : " + event);
        Publication.publicRequest(event);
    }

    // 표지자동등록요청됨 이벤트 수신 → AI 결과 반영
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='AiRequested'"
    )
    public void wheneverAiRequested(@Payload AiRequested event) {
        System.out.println("##### listener AiRequested : " + event);
        Publication.aiRequest(event);
    }
}
