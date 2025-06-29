package miniproject.domain;

import miniproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Optional;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "pointAccounts",
    path = "pointAccounts"
)
public interface PointAccountRepository
    extends PagingAndSortingRepository<PointAccount, Long> {
        // 포인트 계좌 userId로 찾기 메소드
        Optional<PointAccount> findByUserId(Long userId);
    }

