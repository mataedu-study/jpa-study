package mataedu.jpastudy.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final EntityManager em;

    public List<Member> findAll() {
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        return query.getResultList();
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public Member save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member;
    }

    public void delete(Member member) {
        em.remove(member);
    }
}
