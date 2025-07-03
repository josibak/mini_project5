package miniproject.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

// //<<< EDA / CQRS
@Entity
@Table(name = "Userpage_table")
@Data
public class Userpage {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long userId;

    private String name;
    private String email;
    private String subscribeStatus;
    private String isKtUser;
    private String pointBalance;

    public void setIsKtUser(String isKtUser) {
        this.isKtUser = isKtUser;
    }

    public void setSubscribeStatus(String subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
