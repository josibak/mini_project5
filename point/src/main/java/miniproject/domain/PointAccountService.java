package miniproject.domain;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PointAccountService {

    @Autowired
    PointAccountRepository pointAccountRepository;

    public void handleBookOpened(BookOpened bookOpened) {
        if (Boolean.TRUE.equals(bookOpened.getSubscribeStatus())) {
            System.out.println("구독자는 포인트 차감 안 함");
            return;
        }

        pointAccountRepository.findByUserId(bookOpened.getUserId()).ifPresentOrElse(account -> {
            if (account.getBalance() >= 10) {
                account.setBalance(account.getBalance() - 10);
                pointAccountRepository.save(account);

                PointDeducted deductedEvent = new PointDeducted(account);
                deductedEvent.publishAfterCommit();

                System.out.println("포인트 차감 성공 -10");
            } else {
                System.out.println("포인트 부족");
            }
        }, () -> {
            System.out.println("해당 유저의 포인트 계정 없음");
        });
    }

    public void handleUserRegistered(MemberRegistered event) {
        if (event.getUserId() == null) {
            System.out.println("userId 없음 - userRegistered 무시");
            return;
        }

        if (pointAccountRepository.findByUserId(event.getUserId()).isPresent()) {
            System.out.println("이미 포인트 계좌 존재");
            return;
        }

        PointAccount account = new PointAccount();
        account.setUserId(event.getUserId());
        account.setBalance(1000);
        pointAccountRepository.save(account);

        new BasicPointGranted(account).publishAfterCommit();
        System.out.println("회원가입 포인트 지급 완료");
    }

    public void handleKtAuthenticated(KtAuthenticated event) {
        if (event.getUserId() == null) {
            System.out.println("userId 없음 - kt 인증 무시");
            return;
        }

        PointAccount account = pointAccountRepository.findByUserId(event.getUserId())
                .orElseGet(() -> {
                    PointAccount acc = new PointAccount();
                    acc.setUserId(event.getUserId());
                    acc.setBalance(0);
                    return acc;
                });

        account.setBalance(account.getBalance() + 3000);
        pointAccountRepository.save(account);

        new KtPointGranted(account).publishAfterCommit();
        System.out.println("KT 인증 포인트 +3000");
    }
}
