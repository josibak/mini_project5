package miniproject.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // ✅ WebClient (usermanagement 서비스용)
    private final WebClient webClient = WebClient.create("http://usermanagement:8086");

    // [1] 책 전체 조회 API
    @GetMapping
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().forEach(result::add);
        return result;
    }

    // [2] 단일 책 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // ✅ [3] 책 본문 열람 API (열람 권한 확인 포함)
    @GetMapping("/{bookId}/content")
    public ResponseEntity<?> readBookContent(@PathVariable Long bookId, @RequestParam Long userId) {
        // [1] 유저가 해당 도서를 열람할 수 있는지 usermanagement 서비스에 확인
        Boolean canRead = webClient.get()
                .uri("/members/" + userId + "/canRead?bookId=" + bookId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false)
                .block();

        if (!Boolean.TRUE.equals(canRead)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("열람 권한이 없습니다.");
        }

        // [2] 열람 가능하면 도서 본문 반환
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("도서를 찾을 수 없습니다."));
        return ResponseEntity.ok(book);
    }

    // [4] 책 등록 API
    @PostMapping
    @Transactional
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // [5] 책 삭제 API
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
