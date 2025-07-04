package miniproject.domain;

import lombok.Data;

// 원고 수정
@Data
public class UpdateManuscriptCommand {

    private String title;
    private String content;
}
