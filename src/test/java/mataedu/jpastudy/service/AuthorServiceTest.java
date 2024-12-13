package mataedu.jpastudy.service;

import jakarta.persistence.EntityManager;
import mataedu.jpastudy.entity.Author;
import mataedu.jpastudy.entity.Book;
import mataedu.jpastudy.repository.AuthorRepository;
import mataedu.jpastudy.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("저자의 책 리스트를 조회힌다. N+1 ")
    @Transactional
    void getAuthorBooks() {
        //given
        Author author = Author.builder().name("김영한").build();
        authorService.addAuthor(author);

        bookService.addBook("JPA", author.getName());
        bookService.addBook("Spring", author.getName());
        bookService.addBook("Spring Boot", author.getName());

        em.flush();
        em.clear();

        //when
        System.out.println("---------------------");
        Author findAuthor = repository.findById(author.getId()).get();
        List<Book> resultBooks = authorService.getAuthorBooks(findAuthor.getName()); // 여기선 books 조회할때 생길거라 생각했는 n+1 안생기고
        //System.out.println("22222222222222");
        resultBooks.forEach(book -> System.out.println(book.getTitle())); // 이렇게 직접 book의 필드로 접근 해야만 생김 => ??? 쿼리 안나감
        System.out.println("---------------------");

        //then
        assertThat(resultBooks).hasSize(3);
    }

    @Test
    @DisplayName("저자의 책 리스트를 조회힌다. fetch join ")
    void getAuthorBooksFetchJoin() {
        //given
        Author author = Author.builder().name("김영한").build();
        authorService.addAuthor(author);

        bookService.addBook("JPA", author.getName());
        bookService.addBook("Spring", author.getName());
        bookService.addBook("Spring Boot", author.getName());

        //when
        System.out.println("---------------------");
        List<Book> result = authorService.getAuthorBooksFetchJoin(author.getName());
        System.out.println("---------------------");

        //then
        assertThat(result).hasSize(3);
    }


    @Test
    @DisplayName("저자 추가")
    void addAuthor() {
        //given
        Author author = Author.builder().name("김영한").build();

        //when
        Author authorResult = authorService.addAuthor(author);

        //then
        assertThat(authorResult.getName()).isEqualTo(author.getName());
    }


}