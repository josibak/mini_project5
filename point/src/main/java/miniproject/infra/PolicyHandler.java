package miniproject.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.domain.PointService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler {

    @Autowired
    PointService pointService;

    ObjectMapper objectMapper = new ObjectMapper();

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {
        // Just a fallback catch-all method
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverMemberRegistered_InitializeAccount(@Payload String eventString) {
        try {
            JSONObject eventJson = new JSONObject(eventString);
            if (!eventJson.has("eventType") || !eventJson.getString("eventType").equals("MemberRegistered")) return;

            Long userId = eventJson.getLong("userId");
            boolean isKtMember = eventJson.optBoolean("isKtUser", false);
            pointService.initializeAccount(userId, isKtMember);

        } catch (Exception e) {
            System.out.println("Error handling UserRegistered: " + e.getMessage());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverBookOpened_HandleDeduction(@Payload String eventString) {
        try {
            JSONObject eventJson = new JSONObject(eventString);
            if (!eventJson.has("eventType") || !eventJson.getString("eventType").equals("userBookOpened")) return;

            Long userId = eventJson.getLong("userId");
            Long bookId = eventJson.getLong("bookId");
            boolean isSubscriber = eventJson.getBoolean("isSubscriber");

            pointService.handleBookOpened(userId, bookId, isSubscriber);

        } catch (Exception e) {
            System.out.println("Error handling BookOpened: " + e.getMessage());
        }
    }
}
