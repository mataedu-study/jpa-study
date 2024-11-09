package mataedu.jpastudy.domain.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    public Member save(Member member) {
        if (member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }

        return member;
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name like :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Long deleteById(Long id) {
        em.remove(em.find(Member.class, id));

        return id;
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}
