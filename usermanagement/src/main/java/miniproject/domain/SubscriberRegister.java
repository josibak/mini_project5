package miniproject.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberRegister {

    private Long userId;

    // eventType이 필요하면 명시적으로 추가할 수도 있음
    private String eventType = "SubscriberRegister";
}
