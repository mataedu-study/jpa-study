package mataedu.jpastudy.domain.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepository {
    private final EntityManager em;

    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }
        return book;
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> findAllByAuthorId(Long authorId) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author.id = :authorId", Book.class);
        return query.setParameter("authorId", authorId).getResultList();
    }

    public Book findById(Long id) {
        return em.find(Book.class, id);
    }
}
