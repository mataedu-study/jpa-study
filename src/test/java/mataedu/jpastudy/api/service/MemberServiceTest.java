package mataedu.jpastudy.api.service;

import mataedu.jpastudy.api.controller.MemberCreateRequest;
import mataedu.jpastudy.domain.member.Member;
import mataedu.jpastudy.domain.member.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("요청한 dto 로 member 를 생성하고 저장할 수 있다.")
    void createMember() {
        // given
        MemberCreateRequest request = new MemberCreateRequest("이름", "이메일");

        // when
        Member savedMember = memberService.createMember(request);

        // then
        assertThat(savedMember).isNotNull();

        List<Member> savedMemberList = memberRepository.findAll();
        assertThat(savedMemberList).hasSize(1);
    }


}