package miniproject.domain;

import java.util.Optional;
import javax.persistence.*;
import lombok.Data;
import miniproject.PointApplication;
import miniproject.domain.events.*;
import miniproject.external.BookService;
import miniproject.external.BookInfo;

@Entity
@Table(name = "PointAccount_table")
@Data
public class PointAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointAccountId;

    private Long userId;

    private Integer balance = 0;

    public static PointAccountRepository repository() {
        return PointApplication.applicationContext.getBean(PointAccountRepository.class);
    }

    // 회원가입 시 포인트 계좌 생성 및 기본 포인트 지급
    public static void userRegistered(MemberRegistered event) {
        PointAccount account = new PointAccount();
        account.setUserId(event.getUserId());
        account.setBalance(1000);  // 초기 잔액

        repository().save(account);

        BasicPointGranted granted = new BasicPointGranted(account);
        granted.publishAfterCommit();
    }

    // KT 인증 시 보너스 포인트 지급
    public static void ktMemberVerified(KtAuthenticated event) {
        Optional<PointAccount> optional = repository().findByUserId(event.getUserId());
        if (optional.isPresent()) {
            PointAccount account = optional.get();
            account.setBalance(account.getBalance() + 1000); // KT 보너스 1000P
            repository().save(account);

            KtPointGranted granted = new KtPointGranted(account);
            granted.publishAfterCommit();
        }
    }

    // 책 열람 시 포인트 차감
    public static void bookOpenedByPoint(PointBookOpened event) {
        Optional<PointAccount> optional = repository().findByUserId(event.getUserId());
        if (optional.isPresent()) {
            PointAccount account = optional.get();

            // 외부 Book 서비스에서 도서 정보 조회 (FeignClient or Stub)
            BookService bookService = PointApplication.applicationContext.getBean(BookService.class);
            BookInfo book = bookService.getBookInfo(event.getBookId());

            // int cost = book.getIsBestSeller() ? 1500 : 1000;
            int cost = 500;


            if (account.getBalance() >= cost) {
                account.setBalance(account.getBalance() - cost);
                repository().save(account);

                PointDeducted deducted = new PointDeducted(account);
                deducted.setBookId(event.getBookId());
                deducted.publishAfterCommit();
            } else {
                PointInsufficient insufficient = new PointInsufficient(account);
                insufficient.setBookId(event.getBookId());
                insufficient.publishAfterCommit();
            }
        }
    }
}
