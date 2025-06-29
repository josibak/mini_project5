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

    public static PointAccountRepository repository() {
        return PointApplication.applicationContext.getBean(PointAccountRepository.class);
    }

    public static void bookOpenedByPoint(BookOpened bookOpened) {
        if (Boolean.TRUE.equals(bookOpened.getSubscribeStatus())) {
            System.out.println("구독자는 포인트 차감 안 함");
            return;
        }

        repository().findByUserId(bookOpened.getUserId()).ifPresentOrElse(pointAccount -> {
            if (pointAccount.getBalance() >= 10) {
                pointAccount.setBalance(pointAccount.getBalance() - 10);
                repository().save(pointAccount);

                PointDeducted deductedEvent = new PointDeducted(pointAccount);
                deductedEvent.publishAfterCommit();

                System.out.println("포인트 차감 성공 -10");
            } else {
                System.out.println("포인트 부족");
            }
        }, () -> {
            System.out.println("해당 유저의 포인트 계정 없음");
        });
    }

    public static void userRegistered(MemberRegistered memberRegistered) {
        if (memberRegistered.getUserId() == null) {
            System.out.println("userId 없음 - userRegistered 무시");
            return;
        }

        Optional<PointAccount> optional = repository().findByUserId(memberRegistered.getUserId());

        if (optional.isPresent()) {
            System.out.println("이미 포인트 계좌가 존재함");
            return;
        }

        PointAccount account = new PointAccount();
        account.setUserId(memberRegistered.getUserId());
        account.setBalance(1000);  // 기본 포인트 지급
        repository().save(account);

        BasicPointGranted granted = new BasicPointGranted(account);
        granted.publishAfterCommit();

        System.out.println("회원가입 시 포인트 계좌 생성 완료 + 기본 포인트 1000 지급");
    }

    public static void ktMemberVerified(KtAuthenticated ktAuthenticated) {
        if (ktAuthenticated.getUserId() == null) {
            System.out.println("userId 없음 - ktMemberVerified 무시");
            return;
        }

        PointAccount account = repository().findByUserId(ktAuthenticated.getUserId())
            .orElseGet(() -> {
                PointAccount newAcc = new PointAccount();
                newAcc.setUserId(ktAuthenticated.getUserId());
                newAcc.setBalance(0);
                return newAcc;
            });

        account.setBalance(account.getBalance() + 3000);  // KT 인증 보너스
        repository().save(account);

        KtPointGranted granted = new KtPointGranted(account);
        granted.publishAfterCommit(); 

        System.out.println("KT 인증 완료: 포인트 +3000");
    }
}

//>>> DDD / Aggregate Root
