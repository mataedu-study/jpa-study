package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import mataedu.jpastudy.domain.Author;
import mataedu.jpastudy.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    EntityManager em;

    @Test
    void save() {
        // given
        Author author = Author.of("저자이름");
        em.persist(author);

        Book book = Book.of("책이름");
        book.setAuthor(author);
        em.persist(book);

        // when
        em.flush();
        em.clear();

        // then
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor()).isEqualTo(author);
    }

    @Test
    void findOne() {
        // given
        Author author = Author.of("저자이름");
        em.persist(author);

        Book book = Book.of("책이름");
        book.setAuthor(author);
        em.persist(book);

        em.flush();
//        em.clear();

        // when
        Book findBook = em.find(Book.class, book.getId());

        // then
        assertThat(findBook.getId()).isNotNull();
        assertThat(findBook.getAuthor()).isEqualTo(author);

    }
}