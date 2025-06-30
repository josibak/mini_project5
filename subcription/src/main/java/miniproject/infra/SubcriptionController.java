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
@RequestMapping("/subcriptions") 
@Transactional
public class SubcriptionController {

    @Autowired
    SubcriptionRepository subcriptionRepository;

    @PostMapping
    public Subcription create(@RequestBody SubscribtionRequested request) {
        Subcription.subcriptionRequest(request);
        // 저장된 최신 구독을 반환
        return subcriptionRepository.findTopByUserIdOrderBySubscribeIdDesc(request.getUserId());
    }
    @GetMapping
    public java.util.List<Subcription> getAll() {
        java.util.List<Subcription> result = new java.util.ArrayList<>();
        subcriptionRepository.findAll().forEach(result::add);
        return result;
    }

    @GetMapping("/{userId}")
    public Optional<Subcription> getByUserId(@PathVariable Long userId) {
        return Optional.ofNullable(subcriptionRepository.findTopByUserIdOrderBySubscribeIdDesc(userId));
    }
}
//>>> Clean Arch / Inbound Adaptor
