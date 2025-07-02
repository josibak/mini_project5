package miniproject.domain;

import java.util.Optional;
import javax.persistence.*;
import lombok.Data;
import miniproject.PointApplication;
import lombok.*;


@Entity
@Table(name = "PointAccount_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointAccountId;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private Integer balance = 0;
}