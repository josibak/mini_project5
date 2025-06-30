package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class SubscribtionRequestCommand {

    private Long userId;
    // private Boolean subscribeStatus;
    private Boolean subscribe;
}
