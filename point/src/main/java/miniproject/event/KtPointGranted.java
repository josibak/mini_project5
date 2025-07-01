package miniproject.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miniproject.infra.AbstractEvent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KtPointGranted extends AbstractEvent {
    private Long userId;
    private Integer bonusAmount;
    private Integer balanceAfter;
}
