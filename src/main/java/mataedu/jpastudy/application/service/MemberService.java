package mataedu.jpastudy.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Member;
import mataedu.jpastudy.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(String username, Integer age) {
        Member member = new Member(username, age);
        return memberRepository.save(member);
    }

    public Member modifyAge(Long id, Integer age) {
        Member member = memberRepository.findById(id);
        member.modifyAge(age);
        return member;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }
}
