package mataedu.jpastudy.api.service;

import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.api.controller.MemberCreateRequest;
import mataedu.jpastudy.domain.member.Member;
import mataedu.jpastudy.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(MemberCreateRequest request) {
        Member member = request.toEntity();

        return memberRepository.save(member);
    }

}
