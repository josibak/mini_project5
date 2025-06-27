package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RequestPublicationCommand {

    private Long manuscriptId;
    private Long authorId;
    private String title;
    private String content;
}
