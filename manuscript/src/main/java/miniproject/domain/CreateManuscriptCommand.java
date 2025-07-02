package miniproject.domain;

import java.time.LocalDateTime;
import lombok.Data;

// 원고 생성
@Data
public class CreateManuscriptCommand {

    private Long authorId;
    private Long manuscriptId;
    private String title;
    private String content;
    // private LocalDateTime createdAt;
    // private LocalDateTime updateAt;
}