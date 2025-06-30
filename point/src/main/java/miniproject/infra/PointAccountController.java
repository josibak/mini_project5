package miniproject.infra;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import miniproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/pointAccounts")
@Transactional
public class PointAccountController {

    @Autowired
    PointAccountRepository pointAccountRepository;

    // 전체 포인트 계좌 조회
    @GetMapping
    public List<PointAccount> getAllAccounts() {
        return (List<PointAccount>) pointAccountRepository.findAll();
    }

    @PostMapping
    public PointAccount createAccount(@RequestBody PointAccount account) {
    return pointAccountRepository.save(account);
}

}
//>>> Clean Arch / Inbound Adaptor
