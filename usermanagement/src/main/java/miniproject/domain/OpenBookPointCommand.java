package miniproject.domain;
import lombok.Data;

@Data
public class OpenBookPointCommand {

    private Long userId;
    private Long bookId;
    private String subscribeStatus;
}
