package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository {
    private final EntityManager em;

    public void save(Author author) {
        if (author.getId() == null) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    public Author findOne(Long id) {
        return em.find(Author.class, id);
    }

    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }
}
