package miniproject.domain;

import lombok.Data;

// 원고 생성
@Data
public class CreateManuscriptCommand {

    private Long authorId;
    private Long manuscriptId;
    private String title;
    private String content;
}