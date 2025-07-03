package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class PublicationRequested extends AbstractEvent {

    private Long manuscriptId;
    private Long authorId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updateAt;

    public PublicationRequested() {
    // 기본 생성자
    }
    public PublicationRequested(Publication publication) {
        this.manuscriptId = publication.getManuscriptId();
        this.authorId = publication.getAuthorId();
        this.title = publication.getTitle();
        this.content = publication.getContent();
        this.createdAt = new Date();
        this.updateAt = new Date();
    }
}
