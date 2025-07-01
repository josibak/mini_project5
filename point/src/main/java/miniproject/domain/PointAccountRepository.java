package miniproject.domain;

import miniproject.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "pointAccounts",
    path = "pointAccounts"
)
public interface PointAccountRepository extends JpaRepository<PointAccount, Long> {
    Optional<PointAccount> findByUserId(Long userId);
}


