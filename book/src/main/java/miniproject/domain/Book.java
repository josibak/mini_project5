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

    // 도서 열람 시 조회수 증가
    public static void increaseViewCount(BookOpened bookOpened) {
        repository().findById(bookOpened.getBookId()).ifPresent(book -> {
            if (book.getViewCount() == null) book.setViewCount(0);
            book.setViewCount(book.getViewCount() + 1);

            if (book.getViewCount() >= 100 && !Boolean.TRUE.equals(book.getIsBestSeller())) {
                book.setIsBestSeller(true);
            }

            repository().save(book);
        });
    }

    // 출판 완료 처리
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
