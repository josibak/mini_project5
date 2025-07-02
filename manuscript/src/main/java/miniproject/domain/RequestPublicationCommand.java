package miniproject.domain;

import lombok.Data;

// 출간 요청
@Data
public class RequestPublicationCommand {
    private boolean publicationRequested; // 출간 상태
}
