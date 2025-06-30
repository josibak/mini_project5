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
        Book.increaseViewCount(bookOpened);
    }
}
//>>> Clean Arch / Inbound Adaptor
