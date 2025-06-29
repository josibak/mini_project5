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
    PointAccountRepository pointAccountRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

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
        PointAccount.bookOpenedByPoint(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='MemberRegistered'"
    )
    public void wheneverMemberRegistered_UserRegistered(
        @Payload MemberRegistered memberRegistered
    ) {
        MemberRegistered event = memberRegistered;
        System.out.println(
            "\n\n##### listener UserRegistered : " + memberRegistered + "\n\n"
        );

        // Sample Logic //
        PointAccount.userRegistered(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='KtAuthenticated'"
    )
    public void wheneverKtAuthenticated_KtMemberVerified(
        @Payload KtAuthenticated ktAuthenticated
    ) {
        KtAuthenticated event = ktAuthenticated;
        System.out.println(
            "\n\n##### listener KtMemberVerified : " + ktAuthenticated + "\n\n"
        );

        // Sample Logic //
        PointAccount.ktMemberVerified(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
