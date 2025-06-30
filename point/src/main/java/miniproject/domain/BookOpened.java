package miniproject.domain;

import lombok.Data;
import lombok.ToString;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class BookOpened extends AbstractEvent {
    private Long bookId;
    private Long userId;
    private Boolean subscribeStatus;
    
    public boolean validate() {
    return bookId != null && userId != null;
    }
}
