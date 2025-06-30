package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
// @RequestMapping(value="/authors")
@Transactional
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping(
        value = "/authors",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Author requestAuthorRegistration(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestAuthorRegistrationCommand requestAuthorRegistrationCommand
    ) throws Exception {
        System.out.println(
            "##### /author/requestAuthorRegistration  called #####"
        );
        Author author = new Author();
        author.setName(requestAuthorRegistrationCommand.getName());
        author.setBio(requestAuthorRegistrationCommand.getBio());
        author.setPortfolio(requestAuthorRegistrationCommand.getPortfolio());
        author.setRegistrationStatus(Author.RegistrationStatus.PENDING);
        //author.requestAuthorRegistration(requestAuthorRegistrationCommand);
        authorRepository.save(author);
        return author;
    }

    @RequestMapping(
        value = "/authors/{id}/approveauthorregistration",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author approveAuthorRegistration(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /author/approveAuthorRegistration  called #####"
        );
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.setRegistrationStatus(Author.RegistrationStatus.APPROVED);
        //author.approveAuthorRegistration(approveAuthorRegistrationCommand);

        authorRepository.save(author);
        return author;
    }

    @RequestMapping(
        value = "/authors/{id}/rejectauthorregistration",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Author rejectAuthorRegistration(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /author/rejectAuthorRegistration  called #####"
        );
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        optionalAuthor.orElseThrow(() -> new Exception("No Entity Found"));
        Author author = optionalAuthor.get();
        author.setRegistrationStatus(Author.RegistrationStatus.REJECTED);
        //author.rejectAuthorRegistration(rejectAuthorRegistrationCommand);

        authorRepository.save(author);
        return author;
    }
}
