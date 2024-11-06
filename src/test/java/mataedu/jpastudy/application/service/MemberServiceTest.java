package mataedu.jpastudy.application.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import mataedu.jpastudy.domain.entity.Member;
import mataedu.jpastudy.domain.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("멤버 저장")
    @Test
    void save() {
        // given
        String userName = "A";
        int age = 10;

        // when
        Member result = memberService.save(userName, age);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @DisplayName("Id를 가지고 조회")
    @Test
    void findById() {
        // given
        Member member = memberRepository.save(provideMember("테스트", 10));
        Long id  = member.getId();

        // when
        Member result = memberService.getMemberById(id);

        // then
        assertThat(result).isEqualTo(member);
    }

    @DisplayName("전체 조회")
    @Test
    void findAll() {
        // given
        List<Member> members = List.of(
                provideMember("테스트", 10),
                provideMember("테스트", 10),
                provideMember("테스트", 10),
                provideMember("테스트", 10)
        );

        members.forEach(member -> memberRepository.save(member));

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList).hasSize(4);
    }

    Member provideMember(String userName, int age) {
        return Member.builder()
                .username(userName)
                .age(age)
                .build();
    }
}