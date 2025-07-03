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

    public static BookRepository repository() {
        return BookApplication.applicationContext.getBean(BookRepository.class);
    }

  
    //변경된 조회수 증가 로직
    public void increaseViewCount() {
        if (this.viewCount == null) this.viewCount = 0;
        this.viewCount++;
        System.out.println("increaseViewCount called. New viewCount: " + this.viewCount);


        if (this.viewCount >= 100 && !Boolean.TRUE.equals(this.isBestSeller)) {
            this.isBestSeller = true;
            System.out.println("isBestSeller set to true");
        }
    }


    //출판 완료 이벤트 수신 시 Book 인스턴스를 생성하고 저장
    //이벤트에 포함된 책 정보로 새로운 Book 엔티티 생성
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
