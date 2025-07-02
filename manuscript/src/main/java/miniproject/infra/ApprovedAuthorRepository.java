package miniproject.infra;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import miniproject.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "approvedAuthors",
    path = "approvedAuthors"
)
public interface ApprovedAuthorRepository
    extends PagingAndSortingRepository<ApprovedAuthor, Long> {}