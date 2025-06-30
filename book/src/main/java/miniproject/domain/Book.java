package miniproject.domain;

import lombok.*;
import javax.persistence.*;

import miniproject.BookApplication;

@Entity
@Table(name = "Book_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    private String title;

    private String summary;

    @Lob
    private String content;

    private String postUrl;

    private Integer viewCount = 0;

    private Boolean isBestSeller = false;

    /**
     * 기존 static 방식에서 사용하던 repository 참조 헬퍼
     * 이제는 publishingCompleted만 여전히 static이라 유지
     */
    public static BookRepository repository() {
        return BookApplication.applicationContext.getBean(BookRepository.class);
    }

    /**
     * ✅ 인스턴스 메서드로 변경된 조회수 증가 로직
     * - static 메서드는 Spring의 JPA 트랜잭션 관리 대상이 아님
     * - 따라서 Entity 객체를 통해 직접 메서드 호출하고 save 해야 DB 반영됨
     */
    public void increaseViewCount() {
        if (this.viewCount == null) this.viewCount = 0;
        this.viewCount++;
        System.out.println("increaseViewCount called. New viewCount: " + this.viewCount);


        if (this.viewCount >= 100 && !Boolean.TRUE.equals(this.isBestSeller)) {
            this.isBestSeller = true;
            System.out.println("isBestSeller set to true");
        }
    }

    /**
     * ✅ 그대로 유지: 출판 완료 이벤트 수신 시 Book 인스턴스를 생성하고 저장
     * - 이벤트에 포함된 책 정보로 새로운 Book 엔티티 생성
     */
    public static void publishingCompleted(PublicCompleted event) {
        Book book = new Book();
        book.setTitle(event.getTitle());
        book.setSummary(event.getSummary());
        book.setContent(event.getContent());
        book.setPostUrl(event.getPostUrl());
        book.setViewCount(0);
        book.setIsBestSeller(false);
        repository().save(book);
    }
}
