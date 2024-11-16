package mataedu.jpastudy.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mataedu.jpastudy.domain.Author;
import mataedu.jpastudy.domain.Book;
import mataedu.jpastudy.repository.AuthorRepository;
import mataedu.jpastudy.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void saveBook() {
        // given
        Author author = Author.of("저자이름");
        authorRepository.save(author);

        Book book = new Book("책이름", author);

        // when
        bookRepository.save(book);

        // then
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor()).isEqualTo(author);
    }

    @Test
    void updateBook() {
    }

    @Test
    void findBooks() {
    }

    @Test
    void findOne() {
        // given
        Author author = Author.of("저자이름");
        authorRepository.save(author);

        Book book = new Book("책이름", author);
        bookRepository.save(book);

        em.clear();

        // when
        Book findBook = bookRepository.findOne(book.getId());

        // then
        assertThat(findBook.getId()).isNotNull();
        assertThat(findBook.getAuthor().getName()).isEqualTo("저자이름");
    }
}