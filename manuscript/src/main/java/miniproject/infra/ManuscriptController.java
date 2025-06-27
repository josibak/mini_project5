package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/manuscripts")
@Transactional
public class ManuscriptController {

    @Autowired
    ManuscriptRepository manuscriptRepository;

    @RequestMapping(
        value = "/manuscripts/createmanuscript",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript createManuscript(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateManuscriptCommand createManuscriptCommand
    ) throws Exception {
        System.out.println("##### /manuscript/createManuscript  called #####");
        Manuscript manuscript = new Manuscript();
        manuscript.createManuscript(createManuscriptCommand);
        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/{id}/updatemanuscript",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript updateManuscript(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateManuscriptCommand updateManuscriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /manuscript/updateManuscript  called #####");
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(
            id
        );

        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        Manuscript manuscript = optionalManuscript.get();
        manuscript.updateManuscript(updateManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/requestpublication",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript requestPublication(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody RequestPublicationCommand requestPublicationCommand
    ) throws Exception {
        System.out.println(
            "##### /manuscript/requestPublication  called #####"
        );
        Manuscript manuscript = new Manuscript();
        manuscript.requestPublication(requestPublicationCommand);
        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @RequestMapping(
        value = "/manuscripts/{id}/savefinalmanuscript",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Manuscript saveFinalManuscript(
        @PathVariable(value = "id") Long id,
        @RequestBody SaveFinalManuscriptCommand saveFinalManuscriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /manuscript/saveFinalManuscript  called #####"
        );
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(
            id
        );

        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        Manuscript manuscript = optionalManuscript.get();
        manuscript.saveFinalManuscript(saveFinalManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }
}
//>>> Clean Arch / Inbound Adaptor
