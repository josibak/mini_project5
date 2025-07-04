package miniproject.domain;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import miniproject.domain.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
public interface MemberRepository extends PagingAndSortingRepository<Member, Long> {

    List<Member> findByIsKtUser(Boolean isKtUser);

    List<Member> findBySubscribeStatus(Boolean subscribeStatus);

    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);
}
