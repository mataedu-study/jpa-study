package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import mataedu.jpastudy.domain.Author;
import mataedu.jpastudy.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
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

    @Test
    void findByAuthorName() {
        // given
        Author author1 = Author.of("test1");
        Author author2 = Author.of("test2");
        Author author3 = Author.of("test3");
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        Book book1 = Book.of("책이름1");
        book1.setAuthor(author1);
        Book book2 = Book.of("책이름2");
        book2.setAuthor(author2);
        Book book3 = Book.of("책이름3");
        book3.setAuthor(author3);

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);

        em.flush();
        em.clear();

        // when
        List<Book> bookList = bookRepository.findByAuthorName("test1");

        // then
        assertThat(bookList).hasSize(1);
    }

    @Test
    void fetchTest() {
        // given
        Author author1 = Author.of("test1");
        Author author2 = Author.of("test2");
        Author author3 = Author.of("test3");
        em.persist(author1);
        em.persist(author2);
        em.persist(author3);

        Book book1 = Book.of("책이름1");
        book1.setAuthor(author1);
        Book book2 = Book.of("책이름2");
        book2.setAuthor(author2);
        Book book3 = Book.of("책이름3");
        book3.setAuthor(author3);

        em.persist(book1);
        em.persist(book2);
        em.persist(book3);

        em.flush();
        em.clear();

        // when
        List<Book> bookList = bookRepository.findAll();

        // then
        for (Book book : bookList) {
            System.out.println("book.getAuthor().getName() = " + book.getAuthor().getName());
        }
    }
}