package miniproject.infra;

import lombok.RequiredArgsConstructor;
import miniproject.domain.Member;
import miniproject.domain.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    // 1. 회원가입
    @PostMapping
    public Member register(@RequestBody Member member) {
        return memberRepository.save(member); // onPostPersist에서 이벤트 자동 발행
    }

    // 2. 구독 신청
    @PostMapping("/{id}/subscribe")
    public String subscribe(@PathVariable Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        member.requestSubscription();
        return "구독 신청 완료";
    }

    // 3. 책 열람 (Kafka 이벤트 발행)
    @PostMapping("/{id}/openBook")
    public String openBook(@PathVariable Long id, @RequestParam Long bookId) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        member.openBook(bookId);
        memberRepository.save(member);
        return "도서 열람 처리 완료";
    }

    // 4. 책 열람 권한 부여 API (PointService가 호출)
    @PutMapping("/{userId}/grantBook/{bookId}")
    public ResponseEntity<?> grantBookAccess(@PathVariable Long userId, @PathVariable Long bookId) {
        Member member = memberRepository.findById(userId)
                .orElse(null);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        member.grantBookAccess(bookId);
        memberRepository.save(member); // 권한 저장

        return ResponseEntity.ok().body("열람 권한 부여 완료");
    }
    @GetMapping("/{userId}/canRead")

    public ResponseEntity<Boolean> canReadBook(@PathVariable Long userId, @RequestParam Long bookId) {
        Member member = memberRepository.findById(userId)
                .orElse(null);

        if (member == null) {
            return ResponseEntity.notFound().build();
        }

        boolean result = member.canReadBook(bookId);
        return ResponseEntity.ok(result);
    }
}
