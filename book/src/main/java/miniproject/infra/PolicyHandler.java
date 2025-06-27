package miniproject.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
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
        PublicCompleted event = publicCompleted;
        System.out.println(
            "\n\n##### listener PublishingCompleted : " +
            publicCompleted +
            "\n\n"
        );

        // Sample Logic //
        Book.publishingCompleted(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PointBookOpened'"
    )
    public void wheneverPointBookOpened_BookOpenedByPoint(
        @Payload PointBookOpened pointBookOpened
    ) {
        PointBookOpened event = pointBookOpened;
        System.out.println(
            "\n\n##### listener BookOpenedByPoint : " + pointBookOpened + "\n\n"
        );

        // Sample Logic //
        Book.bookOpenedByPoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='BookOpened'"
    )
    public void wheneverBookOpened_BookOpenedBySubscription(
        @Payload BookOpened bookOpened
    ) {
        BookOpened event = bookOpened;
        System.out.println(
            "\n\n##### listener BookOpenedBySubscription : " +
            bookOpened +
            "\n\n"
        );

        // Sample Logic //
        Book.bookOpenedBySubscription(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
