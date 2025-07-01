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


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    
    //출판 완료 이벤트 수신 시 새로운 도서 등록 처리
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

    
    //도서 열람 이벤트 수신 시 조회수 증가

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

        //조회수 증가 처리
        bookRepository.findById(bookOpened.getBookId()).ifPresent(book -> {
            book.increaseViewCount(); // 내부 로직에서 viewCount += 1
            bookRepository.save(book);
        });
    }



    //로그찍기용
    @StreamListener(KafkaProcessor.INPUT)
    public void debug(@Payload String payload, @Headers Map<String, Object> headers) {
    System.out.println("Kafka Raw Payload: " + payload);
    System.out.println("Kafka Headers: " + headers);
    }

}
//>>> Clean Arch / Inbound Adaptor
