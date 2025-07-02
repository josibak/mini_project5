package miniproject.infra;

import javax.transaction.Transactional;

import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;
import org.springframework.messaging.handler.annotation.Headers;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    BookRepository bookRepository;

    // 기본 이벤트 로그용 (사용 X)
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    /**
     * Kafka 메시지 전체 수신 핸들러
     * - Header 기반 조건 필터링 제거
     * - Payload 내부의 "type" 값을 기준으로 분기 처리
     */
    @StreamListener(KafkaProcessor.INPUT)
    public void routeEvent(@Payload String payload) {
        System.out.println("Kafka Raw Payload: " + payload);

        try {
            JSONObject json = new JSONObject(payload);
            String type = json.optString("eventType");

            if ("PublicCompleted".equals(type)) {
                // 출판 완료 이벤트 수신 시 새로운 도서 등록 처리
                System.out.println("\n\n##### listener PublishingCompleted : " + json + "\n\n");

                Book book = new Book();
                book.setTitle(json.optString("title"));
                book.setSummary(json.optString("summary"));
                book.setContent(json.optString("content"));
                book.setPostUrl(json.optString("postUrl"));
                book.setViewCount(0);
                book.setIsBestSeller(false);

                bookRepository.save(book);
            }

            else if ("BookOpened".equals(type) || "PointDeducted".equals(type)) {
                System.out.println("\n\n##### listener IncreaseViewCount (by PointDeducted): " + json + "\n\n");

                Long bookId = json.getLong("bookId");

                bookRepository.findById(bookId).ifPresent(book -> {
                    System.out.println(">> Book 찾음: " + book.getTitle());
                    book.increaseViewCount(); // 내부 로직에서 viewCount += 1
                    bookRepository.save(book);
                    System.out.println(">> 조회수 증가 저장 완료");
                });
            }

        } catch (Exception e) {
            System.out.println("Kafka 메시지 파싱 오류: " + e.getMessage());
        }
    }

    // Kafka 메시지 수신 로그 디버깅용
    @StreamListener(KafkaProcessor.INPUT)
    public void debug(@Payload String payload, @Headers Map<String, Object> headers) {
        System.out.println("Kafka Raw Payload: " + payload);
        System.out.println("Kafka Headers: " + headers);
    }
}
//>>> Clean Arch / Inbound Adaptor
