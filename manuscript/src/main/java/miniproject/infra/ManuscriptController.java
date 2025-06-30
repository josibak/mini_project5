package miniproject.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/manuscripts")
@Transactional
public class ManuscriptController {

    @Autowired
    ManuscriptRepository manuscriptRepository;

    // 원고 생성
    @PostMapping(value = "/manuscripts/createmanuscript", produces = "application/json;charset=UTF-8")
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

    // 원고 수정
    @PutMapping(value = "/manuscripts/{id}/updatemanuscript", produces = "application/json;charset=UTF-8")
    public Manuscript updateManuscript(
        @PathVariable(value = "id") Long id,
        @RequestBody UpdateManuscriptCommand updateManuscriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /manuscript/updateManuscript  called #####");
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(id);
        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        Manuscript manuscript = optionalManuscript.get();
        manuscript.updateManuscript(updateManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    // 원고 출간 요청
    @PostMapping(value = "/manuscripts/{id}/requestpublication", produces = "application/json;charset=UTF-8")
    public Manuscript requestPublication(
        @PathVariable("id") Long id,
        @RequestBody RequestPublicationCommand requestPublicationCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /manuscript/requestPublication  called #####"
        );

        // 기존 원고 찾기
        Manuscript manuscript = manuscriptRepository.findById(id)
            .orElseThrow(() -> new Exception("No Entity Found"));

        manuscript.requestPublication(requestPublicationCommand);
        return manuscriptRepository.save(manuscript);
    }

    // 원고 최종 저장
    @PostMapping(value = "/manuscripts/{id}/savefinalmanuscript", produces = "application/json;charset=UTF-8")
    public Manuscript saveFinalManuscript(
        @PathVariable(value = "id") Long id,
        @RequestBody SaveFinalManuscriptCommand saveFinalManuscriptCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println(
            "##### /manuscript/saveFinalManuscript  called #####"
        );
        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(id);

        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        
        Manuscript manuscript = optionalManuscript.get();
        manuscript.saveFinalManuscript(saveFinalManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }
}
//>>> Clean Arch / Inbound Adaptor
