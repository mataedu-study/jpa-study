package mataedu.jpastudy.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Repository
public class AuthorRepository {
    private final EntityManager em;

    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    public Author findById(Long id) {
        return em.find(Author.class, id);
    }

    public Author save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
        } else {
            em.merge(author);
        }
        return author;
    }

    public void delete(Author author) {
        em.remove(author);
    }
}
