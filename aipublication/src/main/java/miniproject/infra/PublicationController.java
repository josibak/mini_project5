package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import miniproject.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/publications")
@Transactional
public class PublicationController {

    @Autowired
    PublicationRepository publicationRepository;

    @RequestMapping(
        value = "/publications/{id}/publiccomplete",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Publication publicComplete(
        @PathVariable(value = "id") Long id,
        @RequestBody PublicCompleteCommand publicCompleteCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /publication/publicComplete  called #####");
        Optional<Publication> optionalPublication = publicationRepository.findById(
            id
        );

        optionalPublication.orElseThrow(() -> new Exception("No Entity Found"));
        Publication publication = optionalPublication.get();
        publication.publicComplete(publicCompleteCommand);

        publicationRepository.save(publication);
        return publication;
    }

    @PostMapping(
        value = "/publications",
        produces = "application/json;charset=UTF-8"
    )
    public Publication requestPublication(
        @RequestBody PublicationRequested publicationRequested
    ) {
        // 출간 요청 정보 저장 및 이벤트 발행
        Publication publication = Publication.publicRequest(publicationRequested);
        // publicRequest에서 저장 및 이벤트 발행까지 처리
        return publication;
    }

    @GetMapping(
    value = "/publications",
    produces = "application/json;charset=UTF-8"
    )
    public Iterable<Publication> getPublications() {
        return publicationRepository.findAll();
}

}
//>>> Clean Arch / Inbound Adaptor

