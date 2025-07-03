package miniproject.domain;

import miniproject.domain.*;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "subcriptions",
    path = "subcriptions"
)
public interface SubcriptionRepository
    extends PagingAndSortingRepository<Subcription, Long> {
        Subcription findTopByUserIdOrderBySubscribeIdDesc(Long userId);
        Optional<Subcription> findByUserId(Long userId);
}
