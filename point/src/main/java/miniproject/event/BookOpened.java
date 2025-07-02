package miniproject.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import miniproject.infra.*;  

@Data
@NoArgsConstructor
public class BookOpened extends AbstractEvent {

    private Long userId;
    private Long bookId;

    public BookOpened(Object aggregate) {
        super(aggregate);
    }

    public BookOpened(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
