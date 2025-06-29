package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
@Transactional
public class BookController {

    @Autowired
    KafkaProcessor kafkaProcessor;

    // 도서 열람 API
    @PostMapping("/books/{id}/open")
    public void openBook(
            @PathVariable("id") Long bookId,
            @RequestBody OpenBookRequest request
    ) {
        BookOpened event = new BookOpened();
        event.setBookId(bookId);
        event.setUserId(request.getUserId());
        event.setSubscribeStatus(request.getSubscribeStatus());

        // Kafka로 이벤트 직접 발행
        kafkaProcessor.outboundTopic().send(
            MessageBuilder.withPayload(event).build()
        );
    }

    // 요청 바디 DTO 클래스
    public static class OpenBookRequest {
        private Long userId;
        private Boolean subscribeStatus;

        public Long getUserId() {
            return userId;
        }
        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Boolean getSubscribeStatus() {
            return subscribeStatus;
        }
        public void setSubscribeStatus(Boolean subscribeStatus) {
            this.subscribeStatus = subscribeStatus;
        }
    }
}
//>>> Clean Arch / Inbound Adaptor
