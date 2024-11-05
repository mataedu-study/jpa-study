package mataedu.jpastudy.application.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Member;
import mataedu.jpastudy.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAllMembers() {
        EntityManager em = memberRepository.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try (em) {
            tx.begin();
            List<Member> memberList = memberRepository.findAll();
            tx.commit();
            return memberList;
        } catch (Exception e) {
            tx.rollback();
            throw e;
        }
    }
}
