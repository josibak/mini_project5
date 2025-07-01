package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class PublicCompleteCommand {

    private Long publicationId;
    private String postUrl;
    private String title;
    private Long authorId;
    private Date publicAt;
    private String content;
    private String bookId;
    private String status;
    private String summary;

    public boolean validate() {
        return publicationId != null;
    }
}
