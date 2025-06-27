package miniproject.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;
import miniproject.BookApplication;
import miniproject.domain.BookDesignatedAsBestSeller;
import miniproject.domain.BookRegistered;
import miniproject.domain.ViewCountIncreased;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    private String title;

    private String summary;

    private Integer viewCount;

    private Boolean isBestSeller;

    public static BookRepository repository() {
        BookRepository bookRepository = BookApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    //<<< Clean Arch / Port Method
    public static void publishingCompleted(PublicCompleted publicCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookRegistered bookRegistered = new BookRegistered(book);
        bookRegistered.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if publicCompleted.gptId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> publicationMap = mapper.convertValue(publicCompleted.getGptId(), Map.class);

        repository().findById(publicCompleted.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookRegistered bookRegistered = new BookRegistered(book);
            bookRegistered.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void bookOpenedByPoint(PointBookOpened pointBookOpened) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        ViewCountIncreased viewCountIncreased = new ViewCountIncreased(book);
        viewCountIncreased.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(pointBookOpened.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            ViewCountIncreased viewCountIncreased = new ViewCountIncreased(book);
            viewCountIncreased.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void bookOpenedBySubscription(BookOpened bookOpened) {
        //implement business logic here:

        /** Example 1:  new item 
        Book book = new Book();
        repository().save(book);

        BookDesignatedAsBestSeller bookDesignatedAsBestSeller = new BookDesignatedAsBestSeller(book);
        bookDesignatedAsBestSeller.publishAfterCommit();
        */

        /** Example 2:  finding and process
        

        repository().findById(bookOpened.get???()).ifPresent(book->{
            
            book // do something
            repository().save(book);

            BookDesignatedAsBestSeller bookDesignatedAsBestSeller = new BookDesignatedAsBestSeller(book);
            bookDesignatedAsBestSeller.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
