package miniproject.infra;

import lombok.RequiredArgsConstructor;
import miniproject.domain.Member;
import miniproject.domain.MemberRepository;
import miniproject.domain.LoginRequest;

import javax.transaction.Transactional;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    // 0. 전체 회원 목록 조회
    @GetMapping
    public ResponseEntity<?> getAllMembers() {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    // 1. 회원가입
    @PostMapping
    public ResponseEntity<?> register(@RequestBody Member member) {
        boolean exists = memberRepository.existsByEmail(member.getEmail());
        if (exists) {
            return ResponseEntity
                    .status(409)
                    .body("이미 가입된 이메일입니다.");
        }

        member.setSubscribeStatus(false);
        Member saved = memberRepository.save(member);
        return ResponseEntity.ok(saved);
    }

    // 2. 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Member> optionalMember = memberRepository.findByEmail(request.getEmail());

        if (optionalMember.isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body("존재하지 않는 계정입니다.");
        }

        Member member = optionalMember.get();

        if (!member.getPassword().equals(request.getPassword())) {
            return ResponseEntity
                    .status(401)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        return ResponseEntity.ok(member);
    }

    // 3. 구독 신청
    @Transactional
    @PostMapping("/{id}/subscribe")
    public String subscribe(@PathVariable Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        member.requestSubscription();
        return "구독 신청 완료";
    }

    // 4. 책 열람 (Kafka 이벤트 발행)
    @PostMapping("/{id}/openBook")
    @Transactional
    public String openBook(@PathVariable Long id, @RequestParam Long bookId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        member.openBook(bookId);
        memberRepository.save(member);
        return "도서 열람 권한 처리 완료";
    }

    // 5. 책 열람 권한 부여
    @PutMapping("/{userId}/grantBook/{bookId}")
    public ResponseEntity<?> grantBookAccess(@PathVariable Long userId, @PathVariable Long bookId) {
        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        member.grantBookAccess(bookId);
        memberRepository.save(member);
        return ResponseEntity.ok("열람 권한 부여 완료");
    }

    // 6. 책 열람 여부 확인
    @GetMapping("/{userId}/canRead")
    public ResponseEntity<Boolean> canReadBook(@PathVariable Long userId, @RequestParam Long bookId) {
        Member member = memberRepository.findById(userId).orElse(null);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        boolean result = member.canReadBook(bookId);
        return ResponseEntity.ok(result);
    }
}
