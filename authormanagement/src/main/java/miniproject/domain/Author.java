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
    
    public void requestAuthorRegistration(RequestAuthorRegistrationCommand requestAuthorRegistrationCommand) {
        this.name = requestAuthorRegistrationCommand.getName();
        this.bio = requestAuthorRegistrationCommand.getBio();
        this.portfolio = requestAuthorRegistrationCommand.getPortfolio();
        this.registrationStatus = RegistrationStatus.PENDING;
    }

    public void approveAuthorRegistration() {
        if (this.registrationStatus != RegistrationStatus.PENDING) {
            throw new IllegalStateException("승인 또는 거절은 PENDING 상태에서만 가능합니다.");
        }
        this.registrationStatus = RegistrationStatus.APPROVED;
        AuthorRegistrationApproved authorRegistrationApproved = new AuthorRegistrationApproved(this);
        authorRegistrationApproved.publishAfterCommit();
    }

    public void rejectAuthorRegistration() {
        if (this.registrationStatus != RegistrationStatus.PENDING) {
            throw new IllegalStateException("승인 또는 거절은 PENDING 상태에서만 가능합니다.");
        }
        this.registrationStatus = RegistrationStatus.REJECTED;
    }

}

