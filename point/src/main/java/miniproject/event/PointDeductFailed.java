package miniproject.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miniproject.infra.AbstractEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDeductFailed extends AbstractEvent {
    private Long userId;
    private Long bookId;
    private Integer balanceBefore;
    private Integer attemptedAmount;
    private String reason;
}

// package miniproject.event;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class PointDeductFailed {
//     private Long userId;       // 사용자 ID
//     private Long bookId;       // 도서 ID
//     private Integer balance;   // 현재 보유 포인트
//     private Integer required;  // 차감하려 했던 포인트
//     private String reason = "INSUFFICIENT_POINTS";  // 실패 사유
// }
