package miniproject.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AuthKtCommand {

    private Long userId;
    private String name;
    private String email;
    private Boolean isKtUser;
}
