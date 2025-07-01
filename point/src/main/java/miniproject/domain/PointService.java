package miniproject.domain;

import lombok.RequiredArgsConstructor;
import miniproject.config.kafka.KafkaProcessor;
import miniproject.event.PointDeductFailed;
import miniproject.event.PointDeducted;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointAccountRepository pointAccountRepository;
    private final WebClient webClient = WebClient.create("http://book:8085");

    // 도서 정보 DTO
    static class Book {
        private Long bookId;
        private Boolean isBestseller;

        public Boolean getIsBestseller() {
            return isBestseller;
        }

        public void setIsBestseller(Boolean isBestseller) {
            this.isBestseller = isBestseller;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }
    }

    // 도서 열람 이벤트 처리
    public void handleBookOpened(Long userId, Long bookId, boolean isSubscriber) {
        if (isSubscriber) {
            System.out.println("구독자이므로 포인트 차감 없음");
            return;
        }

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

        int deductAmount = book.getIsBestseller() ? 1500 : 1000;

        PointAccount account = pointAccountRepository.findByUserId(userId).orElse(null);
        if (account == null) {
            System.out.println("사용자 계좌가 없습니다");
            return;
        }

        if (account.getBalance() >= deductAmount) {
            account.setBalance(account.getBalance() - deductAmount);
            pointAccountRepository.save(account);

            System.out.println("포인트 차감 완료: " + deductAmount + "P");

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

    // 계좌 초기화 (회원가입 시)
    public void initializeAccount(Long userId, boolean isKtMember) {
        PointAccount account = new PointAccount();
        account.setUserId(userId);

        int initialPoint = isKtMember ? 3000 : 0;
        account.setBalance(initialPoint);

        pointAccountRepository.save(account);
        System.out.println("포인트 계좌 생성 완료 - userId: " + userId + ", 초기 포인트: " + initialPoint);
    }

    // KT 인증 보너스 지급
    public void addKtBonus(Long userId) {
        PointAccount account = pointAccountRepository.findByUserId(userId).orElse(null);
        if (account == null) {
            System.out.println("KT 보너스 지급 실패 - 계좌 없음");
            return;
        }

        int bonus = 3000;
        account.setBalance(account.getBalance() + bonus);
        pointAccountRepository.save(account);
        System.out.println("KT 보너스 지급 완료 - userId: " + userId + ", 보너스: " + bonus + "P");
    }
}
