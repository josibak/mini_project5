package miniproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.AuthormanagementApplication;

@Entity
@Table(name = "Author_table")
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorId;

    private String name;

    private String bio;

    private String portfolio;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    public static AuthorRepository repository() {
        AuthorRepository authorRepository = AuthormanagementApplication.applicationContext.getBean(
            AuthorRepository.class
        );
        return authorRepository;
    }

    public static enum RegistrationStatus{
        PENDING,
        APPROVED,
        REJECTED
    }

    public void requestAuthorRegistration(
        RequestAuthorRegistrationCommand requestAuthorRegistrationCommand
    ) {
        AuthorRegistrationRequested authorRegistrationRequested = new AuthorRegistrationRequested(
            this
        );
        authorRegistrationRequested.publishAfterCommit();
    }

    public void approveAuthorRegistration(
       
    ) {
        AuthorRegistrationApproved authorRegistrationApproved = new AuthorRegistrationApproved(
            this
        );
        authorRegistrationApproved.publishAfterCommit();
    }

    public void rejectAuthorRegistration(
        
    ) {
        AuthorRegistrationRejected authorRegistrationRejected = new AuthorRegistrationRejected(
            this
        );
        authorRegistrationRejected.publishAfterCommit();
    }

}

