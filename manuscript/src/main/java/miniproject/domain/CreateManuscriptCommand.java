package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class CreateManuscriptCommand {

    private Long authorId;
    private Long manuscriptId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updateAt;
}
