package miniproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionFinished {

    private Long userId;

    private String eventType = "SubscriptionFinished";
}
