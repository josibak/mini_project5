package miniproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import miniproject.UsermanagementApplication;

@Entity
@Table(name = "member_table")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String password;
    private Boolean subscribeStatus = false; // 구독 여부
    private Boolean isKtUser = false;        // KT 유저 여부

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "member_purchased_books",
        joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "book_id")
    private List<Long> purchasedBookIds = new ArrayList<>();

    // === Repository Helper ===
    public static MemberRepository repository() {
        return UsermanagementApplication.applicationContext.getBean(MemberRepository.class);
    }

    // === 이벤트 발행: 회원가입 완료 ===
    @PostPersist
    public void onPostPersist() {
        new MemberRegistered(this).publishAfterCommit();
    }

    // === 구독 신청 이벤트 발행 ===
    public void requestSubscription() {
        new SubscriptionRequested(this).publishAfterCommit();
    }

    // === 책 열람 이벤트 발행 ===
    public void openBook(Long bookId) {
        new UserBookOpened(this, bookId).publishAfterCommit();
    }

    // === 책 열람 권한 여부 확인 ===
    public boolean canReadBook(Long bookId) {
        return Boolean.TRUE.equals(subscribeStatus) || purchasedBookIds.contains(bookId);
    }

    // === 열람 권한 부여 (포인트 차감 완료 시 호출) ===
    public void grantBookAccess(Long bookId) {
        if (!purchasedBookIds.contains(bookId)) {
            purchasedBookIds.add(bookId);
        }
    }

    // === 구독 상태 ON ===
    public void activateSubscription() {
        this.subscribeStatus = true;
    }

    // === 구독 상태 OFF ===
    public void deactivateSubscription() {
        this.subscribeStatus = false;
    }
}
