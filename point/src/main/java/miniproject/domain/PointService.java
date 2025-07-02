package miniproject.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.annotation.JsonProperty;
import miniproject.event.*;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointAccountRepository pointAccountRepository;

    // 도서 정보 조회용 Book 서비스 WebClient
    private final WebClient bookWebClient = WebClient.create("http://book:8085");

    // 열람 권한 부여용 Member 서비스 WebClient 추가
    private final WebClient memberWebClient = WebClient.create("http://usermanagement:8086");

    // 내부 Book DTO
    static class Book {
        private Long bookId;
        private Boolean isBestseller;

        @JsonProperty("bookId")
        public Long getBookId() { return bookId; }

        @JsonProperty("bookId")
        public void setBookId(Long bookId) { this.bookId = bookId; }

        @JsonProperty("isBestSeller")
        public Boolean getIsBestseller() { return isBestseller; }

        @JsonProperty("isBestSeller")
        public void setIsBestseller(Boolean isBestseller) { this.isBestseller = isBestseller; }
    }

    // BookOpened 이벤트 처리
    public void handleBookOpened(Long userId, Long bookId, boolean isSubscriber) {
        if (isSubscriber) {
            System.out.println("구독자이므로 포인트 차감 없음");

            // viewCount 증가를 위해 BookOpened 이벤트 발행
            BookOpened event = new BookOpened(userId, bookId);
            event.publish();

            return;
        }

        // 비구독자 처리
        Book book = bookWebClient.get()
                .uri("/books/" + bookId)
                .retrieve()
                .bodyToMono(Book.class)
                .onErrorResume(e -> {
                    System.out.println("도서 정보 조회 실패: " + e.getMessage());
                    return Mono.empty();
                })
                .block();

        if (book == null) {
            System.out.println("도서 정보가 없어서 포인트 차감 중단");
            return;
        }

        int deductAmount = Boolean.TRUE.equals(book.getIsBestseller()) ? 1500 : 1000;

        PointAccount account = pointAccountRepository.findByUserId(userId).orElse(null);
        if (account == null) {
            System.out.println("사용자 계좌가 없습니다");
            return;
        }

        if (account.getBalance() >= deductAmount) {
            account.setBalance(account.getBalance() - deductAmount);
            pointAccountRepository.save(account);

            System.out.println("포인트 차감 완료: " + deductAmount + "P");

            // 1. 열람 권한 부여 요청
            try {
                memberWebClient.put()
                        .uri("/members/" + userId + "/grantBook/" + bookId)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block();
                System.out.println("열람 권한 부여 성공");
            } catch (Exception e) {
                System.out.println("열람 권한 부여 실패: " + e.getMessage());
            }

            // 2. viewCount 증가 위한 이벤트 발행
            PointDeducted event = new PointDeducted(userId, bookId, deductAmount, account.getBalance());
            event.publish();

        } else {
            System.out.println("포인트 부족: 현재 보유 " + account.getBalance() + "P, 필요 " + deductAmount + "P");

            PointDeductFailed event = new PointDeductFailed(
                    userId,
                    bookId,
                    account.getBalance(),
                    deductAmount,
                    "잔액 부족"
            );
            event.publish();
        }
    }

    // 포인트 계좌 초기화
    public void initializeAccount(Long userId, boolean isKtMember) {
        PointAccount account = new PointAccount();
        account.setUserId(userId);

        int initialPoint = isKtMember ? 3000 : 1000;
        account.setBalance(initialPoint);

        pointAccountRepository.save(account);

        System.out.println("포인트 계좌 생성 완료 - userId: " + userId + ", 초기 포인트: " + initialPoint);
    }
}
