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

    @Autowired
    ApprovedAuthorRepository approvedAuthorRepository;  

    // 원고 생성 (C)
    @PostMapping(value = "/manuscripts/createmanuscript", produces = "application/json;charset=UTF-8")
    public Manuscript createManuscript(
        HttpServletRequest request,
        HttpServletResponse response,
        @RequestBody CreateManuscriptCommand createManuscriptCommand
    ) throws Exception {
        System.out.println("##### /manuscript/createManuscript  called #####");

        // 1. 승인된 작가인지 확인
        Long authorId = createManuscriptCommand.getAuthorId();
        if (!approvedAuthorRepository.existsById(authorId)) {
            throw new IllegalAccessException("승인되지 않은 작가입니다. authorId = " + authorId);
        }

        // 2. 원고 생성 및 저장
        Manuscript manuscript = new Manuscript();
        manuscript.createManuscript(createManuscriptCommand);
        manuscriptRepository.save(manuscript);

        return manuscript;
    }

    // 전체 원고 조회 (R)
    @GetMapping("/manuscripts")
    public Iterable<Manuscript> getAllManuscripts() {
        return manuscriptRepository.findAll();
    }

    // 원고 수정 (U)
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
        System.out.println("##### /manuscript/requestPublication  called #####");

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
        System.out.println("##### /manuscript/saveFinalManuscript  called #####");

        Optional<Manuscript> optionalManuscript = manuscriptRepository.findById(id);

        optionalManuscript.orElseThrow(() -> new Exception("No Entity Found"));
        
        Manuscript manuscript = optionalManuscript.get();
        manuscript.saveFinalManuscript(saveFinalManuscriptCommand);

        manuscriptRepository.save(manuscript);
        return manuscript;
    }

    @GetMapping("/test/approved-authors")
    public Iterable<ApprovedAuthor> getAll() {
        return approvedAuthorRepository.findAll();
    }
}
//>>> Clean Arch / Inbound Adaptor
