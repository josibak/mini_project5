package miniproject.infra;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/authors")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(
        value = "",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Author requestAuthorRegistration(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestAuthorRegistrationCommand requestAuthorRegistrationCommand
    ) throws Exception {
        Author author = new Author();
        author.requestAuthorRegistration(requestAuthorRegistrationCommand);
        authorRepository.save(author);
        return author;
    }

    @RequestMapping(
        value = "/{id}/approveauthorregistration",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author approveAuthorRegistration(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.approveAuthorRegistration();

        authorRepository.save(author);
        return author;
    }

    @RequestMapping(
        value = "/{id}/rejectauthorregistration",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author rejectAuthorRegistration(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.rejectAuthorRegistration();

        authorRepository.save(author);
        return author;
    }

    // 대기 상태의 작가들만 조회
    @RequestMapping(
        value = "",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public List<Author> getAuthors() throws Exception {
        return authorRepository.findByRegistrationStatus(Author.RegistrationStatus.PENDING);
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8"
    )
    public Author getAuthor(@PathVariable(value = "id") Long id) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        return author;
    }

}
