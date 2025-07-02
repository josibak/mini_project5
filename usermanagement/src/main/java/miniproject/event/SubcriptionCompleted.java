package miniproject.event;

import lombok.Data;
import java.util.Date;

@Data
public class SubcriptionCompleted {
    private Long subscribeId;
    private Long userId;
    private Date subscriptionStartedAt;
    private Date subscriptionExpiredAt;
    private String status;
}