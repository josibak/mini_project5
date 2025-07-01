package miniproject.domain;

import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ApprovedAuthor_table")
@Data
public class ApprovedAuthor {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long authorId;
}