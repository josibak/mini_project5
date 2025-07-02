package miniproject.domain;

import lombok.RequiredArgsConstructor;
import miniproject.event.PointDeductFailed;
import miniproject.event.PointDeducted;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import com.fasterxml.jackson.annotation.JsonProperty;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointAccountRepository pointAccountRepository;

    // WebClient로 외부 Book 서비스 호출
    private final WebClient webClient = WebClient.create("http://book:8085");

    /**
     * 도서 정보를 받아오기 위한 내부 DTO 클래스
     * - WebClient를 통해 /books/{id} 호출 시 바인딩됨
     */
    static class Book {
        private Long bookId;
        private Boolean isBestseller;

        @JsonProperty("bookId")
        public Long getBookId() {
            return bookId;
        }

        @JsonProperty("bookId")
        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        @JsonProperty("isBestSeller")
        public Boolean getIsBestseller() {
            return isBestseller;
        }

        @JsonProperty("isBestSeller")
        public void setIsBestseller(Boolean isBestseller) {
            this.isBestseller = isBestseller;
        }
    }

    /**
     * BookOpened 이벤트 수신 시 처리 로직
     * - 구독자는 포인트 차감 없이 도서 열람 가능
     * - 비구독자는 포인트 차감 필요 (베스트셀러는 1500, 일반도서는 1000)
     * - 차감 성공 시 PointDeducted 이벤트 발행
     * - 실패 시 PointDeductFailed 이벤트 발행 (viewCount 증가하지 않게)
     */
    public void handleBookOpened(Long userId, Long bookId, boolean isSubscriber) {
        if (isSubscriber) {
            // ✅ 구독자는 포인트 차감 없이 도서 열람 가능
            System.out.println("구독자이므로 포인트 차감 없음");

            // 여기서 BookOpened 이벤트 발행하면 viewCount 증가 가능 (원하면 구현 가능)
            return;
        }

        // ✅ 비구독자는 포인트 차감 필요 → 먼저 도서 정보 조회
        Book book = webClient.get()
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

        // ✅ 책 가격 결정 (베스트셀러는 1500, 일반은 1000)
        int deductAmount = Boolean.TRUE.equals(book.getIsBestseller()) ? 1500 : 1000;

        // ✅ 사용자 포인트 계좌 조회
        PointAccount account = pointAccountRepository.findByUserId(userId).orElse(null);
        if (account == null) {
            System.out.println("사용자 계좌가 없습니다");
            return;
        }

        if (account.getBalance() >= deductAmount) {
            // ✅ 포인트 충분할 경우 차감 후 저장
            account.setBalance(account.getBalance() - deductAmount);
            pointAccountRepository.save(account);

            System.out.println("포인트 차감 완료: " + deductAmount + "P");

            // ✅ 성공 이벤트 발행 → Book 서비스에서 viewCount 증가 처리
            PointDeducted event = new PointDeducted(userId, bookId, deductAmount, account.getBalance());
            event.publish();

        } else {
            // ✅ 포인트 부족할 경우 실패 이벤트 발행 (viewCount 증가 X)
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

    /**
     * 회원가입 시 포인트 계좌 초기화 로직
     * - KT 회원: 3000P
     * - 일반 회원: 1000P
     */
    public void initializeAccount(Long userId, boolean isKtMember) {
        PointAccount account = new PointAccount();
        account.setUserId(userId);

        int initialPoint = isKtMember ? 3000 : 1000;
        account.setBalance(initialPoint);

        pointAccountRepository.save(account);

        System.out.println("포인트 계좌 생성 완료 - userId: " + userId + ", 초기 포인트: " + initialPoint);
    }
}
