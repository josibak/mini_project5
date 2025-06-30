package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional; 
import javax.persistence.*;
import lombok.Data;
import miniproject.PointApplication;
import miniproject.domain.BasicPointGranted;
import miniproject.domain.KtPointGranted;
import miniproject.domain.PointDeducted;

@Entity
@Table(name = "PointAccount_table")
@Data
public class PointAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointAccountId;

    @Column(nullable = false, unique = true)
    private Long userId;

    private Integer balance;
}

//>>> DDD / Aggregate Root
