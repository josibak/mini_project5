package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SubscriptionRequestCommand {

    private Long userId;
    private String subscribeStatus;
}
