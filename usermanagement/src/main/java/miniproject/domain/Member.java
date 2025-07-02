package miniproject.domain;

import lombok.Data;
import javax.persistence.*;
import miniproject.UsermanagementApplication;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member_table")
@Data
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
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
        MemberRegistered event = new MemberRegistered(this);
        event.publishAfterCommit();
    }

    // === 구독 신청 ===
    public void requestSubscription() {
        SubscriptionRequested event = new SubscriptionRequested(this);
        event.publishAfterCommit();
    }

    // === 책 열람 ===
    public void openBook(Long bookId) {
        UserBookOpened event = new UserBookOpened(this, bookId);
        event.publishAfterCommit();
    }

    // === 도서 열람 권한 확인 ===
    public boolean canReadBook(Long bookId) {
        return subscribeStatus || (purchasedBookIds != null && purchasedBookIds.contains(bookId));
    }

    // === 열람 권한 부여 (포인트 차감 완료 시 호출) ===
    public void grantBookAccess(Long bookId) {
        if (purchasedBookIds == null) {
            purchasedBookIds = new ArrayList<>();
        }
        if (!purchasedBookIds.contains(bookId)) {
            purchasedBookIds.add(bookId);
        }
    }
}
