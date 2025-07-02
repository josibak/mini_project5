package miniproject.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miniproject.infra.AbstractEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDeducted extends AbstractEvent {
    private Long userId;
    private Long bookId;
    private Integer deductedAmount;
    private Integer balanceAfter;
}
