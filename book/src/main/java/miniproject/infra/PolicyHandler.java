package miniproject.infra;

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
    BookRepository bookRepository;

    // 기본 이벤트 로그용 (사용 X)
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    /**
     * 출판 완료 이벤트 수신 시 새로운 도서 등록 처리
     * Static 방식이지만, 출판된 정보로 새 Book 생성하므로 괜찮음
     */
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PublicCompleted'"
    )
    public void wheneverPublicCompleted_PublishingCompleted(
        @Payload PublicCompleted publicCompleted
    ) {
        System.out.println(
            "\n\n##### listener PublishingCompleted : " + publicCompleted + "\n\n"
        );
        Book.publishingCompleted(publicCompleted);
    }

    /**
     * 도서 열람 이벤트 수신 시 조회수 증가
     * ✅ 기존 static 방식에서 → 영속성 객체로 조회 후 인스턴스 메서드로 처리
     * ✅ 트랜잭션 내에서 dirty checking 또는 save()로 DB 반영
     */
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookOpened'"
    )
    public void wheneverBookOpened_IncreaseViewCount(
        @Payload BookOpened bookOpened
    ) {
        System.out.println(
            "\n\n##### listener IncreaseViewCount : " + bookOpened + "\n\n"
        );

        // 🔥 JPA가 관리하는 영속성 객체에서 조회수 증가 처리
        bookRepository.findById(bookOpened.getBookId()).ifPresent(book -> {
            book.increaseViewCount(); // 내부 로직에서 viewCount += 1
            bookRepository.save(book); // (옵션) save로 명시적 반영
        });
    }
}
//>>> Clean Arch / Inbound Adaptor
