package mataedu.jpastudy.domain.member;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("멤버 저장 테스트")
    void saveTest() {
        // given
        Member member = Member.of("이름", "이메일");

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        assertThat(savedMember.getId()).isNotNull();

    }

    @Test
    @DisplayName("이름으로 멤버 list를 찾을 수 있다.")
    void findByNameTest() {
        // given
        Member member1 = Member.of("홍길동1", "이메일");
        Member member2 = Member.of("홍길동2", "이메일");
        Member member3 = Member.of("홍길동3", "이메일");
        Member member4 = Member.of("홍길동4", "이메일");

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        // when
        List<Member> result = memberJpaRepository.findByName(member1.getName());

        // then
        assertThat(result).hasSize(1);
    }
    
    @Test
    @DisplayName("멤버 업데이트 테스트")
    void updateTest() {
        // given
        Member member = Member.of("홍길동1", "이메일");
        em.persist(member);
        em.flush();
        em.clear();

        String changedEmail = "변경했지롱";
        member.updateEmail(changedEmail);

        // when
        Member savedMember = memberJpaRepository.save(member);

        // then
        assertThat(savedMember.getId()).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo(changedEmail);

    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() {
        // given
        Member member = Member.of("홍길동1", "이메일");
        em.persist(member);
        em.flush();
        em.clear();

        Long id = member.getId();

        // when
        Long deletedId = memberJpaRepository.deleteById(id);

        // then
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                        .getResultList();

        assertThat(result).hasSize(0);
    }

}