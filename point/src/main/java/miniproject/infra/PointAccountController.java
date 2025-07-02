package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pointAccounts")
public class PointAccountController {

    @Autowired
    private PointAccountService pointAccountService;

    @PostMapping("/{userId}")
    public ResponseEntity<PointAccount> createAccount(@PathVariable String userId) {
        return ResponseEntity.ok(pointAccountService.createAccount(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<PointAccount> getAccount(@PathVariable String userId) {
        return ResponseEntity.ok(pointAccountService.getAccount(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<PointAccount> addPoint(
            @PathVariable String userId,
            @RequestParam int amount) {
        return ResponseEntity.ok(pointAccountService.addPoint(userId, amount));
    }

    @PostMapping("/{userId}/deduct")
    public ResponseEntity<String> deductPoint(
            @PathVariable String userId,
            @RequestParam int amount) {
        boolean success = pointAccountService.deductPoint(userId, amount);
        return success ? ResponseEntity.ok("차감 성공")
                       : ResponseEntity.badRequest().body("포인트 부족");
    }

    @GetMapping("/{userId}/check")
    public ResponseEntity<String> checkEnough(
            @PathVariable String userId,
            @RequestParam int amount) {
        boolean result = pointAccountService.hasEnoughPoint(userId, amount);
        return ResponseEntity.ok(result ? "가능" : "불가능");
    }
}
