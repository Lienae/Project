package idusw.leafton.model.repository;

import idusw.leafton.model.DTO.MemberDTO;
import idusw.leafton.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Member entity에 접근
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /* id와 password를 매개변수로 받아 해당 매개변수로 member entity에 레코드를 조회하는 메서드로,
    레코드가 없을 경우가 많기 때문에 리턴값을 Optional로 준다. */
    @Query("SELECT m from Member m where m.email = :email and m.password = :password")
    Optional<Member> loginCheck(String email, String password);

    //member의 정보를 삽입하는 메서드, 삽입 후 삽입한 객체를 그대로 반환한다.
    @Override
    <S extends Member> S save(S member);

    Optional<Member> findById(Long memberId);
}
