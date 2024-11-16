package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final EntityManager em;

    public void save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    public Book findOne(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
//        return em.createQuery("select b from Book b join fetch b.author a", Book.class)
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    public List<Book> findByAuthorName(String authorName) {
        return em.createQuery("select b from Book b " +
                        "join fetch b.author a " +
                        "where a.name = :authorName", Book.class)
                .setParameter("authorName", authorName)
                .getResultList();
    }
}
