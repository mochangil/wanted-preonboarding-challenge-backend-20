package preonboarding.market.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import preonboarding.market.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
//    @Query("select m from Member m where m.email = :email")
//    Member findByEmail(@Param("email") String email);
    Optional<Member> findByEmail(String email);

}
