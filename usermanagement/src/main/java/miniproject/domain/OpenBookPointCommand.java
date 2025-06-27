package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class OpenBookPointCommand {

    private Long userId;
    private Long bookId;
    private Boolean subscribeStatus;
}
