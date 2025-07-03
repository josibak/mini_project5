package miniproject.infra;

import javax.transaction.Transactional;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import miniproject.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired; 
import miniproject.domain.*;
import miniproject.service.AIPublicationService;


@Service
@Transactional
public class PolicyHandler {

    @Autowired
    PublicationRepository publicationRepository;
    
    @Autowired
    AIPublicationService aiPublicationService;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    
    // 출간 요청됨 이벤트 수신 → 출간정보 저장 및 표지자동등록요청됨 이벤트 발행
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublicationRequested'"
    )
    public void wheneverPublicationRequested(@Payload PublicationRequested event) {
        System.out.println("##### listener PublicationRequested : " + event);
        Publication publication = new Publication();
        publication.setManuscriptId(event.getManuscriptId());
        publication.setTitle(event.getTitle());
        publication.setContent(event.getContent());
        publication.setAuthorId(event.getAuthorId());
        publication.setStatus("requested");
        publicationRepository.save(publication);

        // 이벤트 발행
        AiRequested aiRequested = new AiRequested(publication);
        aiRequested.publishAfterCommit();

    }

    // 표지자동등록요청됨 이벤트 수신 → AI 결과 반영
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type'] == 'AiRequested'"
    )
    public void wheneverAiRequested(@Payload AiRequested event) {
        System.out.println("##### listener AiRequested : " + event);
        publicationRepository.findById(event.getPublicationId()).ifPresent(publication -> {
            String summary = aiPublicationService.generateSummary(publication.getContent());
            String coverUrl = aiPublicationService.generateCover(publication.getTitle(), publication.getContent());

            publication.setSummary(summary);
            publication.setPostUrl(coverUrl);
            publication.setStatus("ai_ready");  // AI 작업 완료 상태
            publicationRepository.save(publication);
        });
    }
}
