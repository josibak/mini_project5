package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateManuscriptCommand {

    private Long manuscriptId;
    private String title;
    private String content;
    private Long authorId;
}
