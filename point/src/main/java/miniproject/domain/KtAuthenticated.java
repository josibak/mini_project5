package miniproject.domain;

import java.util.*;
import lombok.*;
import miniproject.domain.*;
import miniproject.infra.AbstractEvent;

@Data
@ToString
public class KtAuthenticated extends AbstractEvent {

    private Long userId;
    private String name;
    private String email;
    private Boolean isKtUser;
}
