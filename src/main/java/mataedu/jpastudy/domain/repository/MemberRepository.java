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

    public void save(String username, Integer age) {
//        TypedQuery<Member> query = em.createQuery("INSERT INTO Member (username, age) VALUES (?, ?)", Member.class);
//        query.setParameter("username", username);
//        query.setParameter(?, age);
//        return query.getSingleResult();
        em.persist(new Member(username, age));
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return em.getEntityManagerFactory();
    }
}
