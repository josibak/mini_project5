package miniproject.domain;

import lombok.Data;
import lombok.ToString;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class BookOpened extends AbstractEvent {
    private Long bookId;
    private String title;
    private Long userId;
    private Boolean subscribeStatus;
    
    public boolean validate() {
        return bookId != null && userId != null && title != null;
    }
}
