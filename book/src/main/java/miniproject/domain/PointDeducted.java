package miniproject.domain;

import lombok.Data;

@Data
public class PointDeducted {
    private String eventType;
    private Long timestamp;
    private Long userId;
    private Long bookId;
    private Integer balanceBefore;
    private Integer attemptedAmount;
    private String reason;
}
