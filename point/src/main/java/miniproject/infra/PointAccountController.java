package miniproject.infra;

import miniproject.domain.PointAccount;
import miniproject.domain.PointAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pointAccounts")
public class PointAccountController {

    @Autowired
    private PointAccountRepository pointAccountRepository;

    // 포인트 계좌 조회 (운영용)
    @GetMapping("/{userId}")
    public ResponseEntity<?> getPointAccount(@PathVariable Long userId) {
        Optional<PointAccount> accountOpt = pointAccountRepository.findByUserId(userId);
        if (accountOpt.isPresent()) {
            return ResponseEntity.ok(accountOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 포인트 수동 지급 (운영용)
    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addPoint(@PathVariable Long userId, @RequestParam int amount) {
        Optional<PointAccount> accountOpt = pointAccountRepository.findByUserId(userId);
        if (accountOpt.isPresent()) {
            PointAccount account = accountOpt.get();
            account.setBalance(account.getBalance() + amount);
            pointAccountRepository.save(account);
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.badRequest().body("계좌 없음");
        }
    }

    // 포인트 수동 차감 (운영용)
    @PostMapping("/{userId}/deduct")
    public ResponseEntity<?> deductPoint(@PathVariable Long userId, @RequestParam int amount) {
        Optional<PointAccount> accountOpt = pointAccountRepository.findByUserId(userId);
        if (accountOpt.isPresent()) {
            PointAccount account = accountOpt.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                pointAccountRepository.save(account);
                return ResponseEntity.ok("차감 성공");
            } else {
                return ResponseEntity.badRequest().body("포인트 부족");
            }
        } else {
            return ResponseEntity.badRequest().body("계좌 없음");
        }
    }
}
