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
    SubcriptionRepository subcriptionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SubscriptionRequested'"
    )

    public void wheneverSubscriptionRequested_SubcriptionRequest(
        @Payload SubscriptionRequested subscriptionRequested
    ) {
        SubscriptionRequested event = subscriptionRequested;
        System.out.println(
            "\n\n##### listener SubcriptionRequest : " +
            subscriptionRequested +
            "\n\n"
        );

        // Sample Logic //
        Subcription.subcriptionRequest(event);
    }
}
