package miniproject.infra;


import javax.transaction.Transactional;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ManuscriptRepository manuscriptRepository;  // 원고 작성 관리 레포지토리

    @Autowired
    private ApprovedAuthorRepository approvedAuthorRepository;  // 승인된 작가 모음 레포지토리

    @StreamListener(
        value = KafkaProcessor.INPUT
        // condition = "headers['type']=='AuthorRegistrationApproved'"
    )
    public void wheneverAuthorRegistrationApproved_SaveAuthor(
        @Payload String eventString
    ) {
        System.out.println("Raw Event String: " + eventString);

        try {
            JSONObject eventJson = new JSONObject(eventString);
            String type = eventJson.optString("eventType");
            
            // "작가 승인됨" 이벤트만 처리
            if ("AuthorRegistrationApproved".equals(type)) {
                // '작가 승인됨'이벤트 수신 시 ReadModel에 승인완료 작가 저장 처리
                 System.out.println("\n\n##### listener AuthorRegistrationApproved : " + eventJson + "\n\n");
                
                ApprovedAuthor approved = new ApprovedAuthor();

                // 메시지에 들어오는 값에 맞춰서 세팅
                approved.setAuthorId(eventJson.optLong("authorId"));
                approvedAuthorRepository.save(approved);

                System.out.println("✅ 승인된 작가 저장 완료 → authorId: " + approved.getAuthorId());

            }
        } catch (Exception e) {
            System.out.println("Kafka 메시지 파싱 오류: " + e.getMessage());
            e.printStackTrace();
        }
        
        
    }
}
//>>> Clean Arch / Inbound Adaptor