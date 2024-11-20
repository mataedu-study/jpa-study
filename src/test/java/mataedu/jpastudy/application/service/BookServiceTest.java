package mataedu.jpastudy.application.service;

import jakarta.transaction.Transactional;
import mataedu.jpastudy.domain.entity.Author;
import mataedu.jpastudy.domain.entity.Book;
import mataedu.jpastudy.domain.repository.AuthorRepository;
import mataedu.jpastudy.domain.repository.BookRepository;
import mataedu.jpastudy.presentation.BookRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookServiceTest {
    @Autowired
    BookService bookService;

    @Autowired
    AuthorRepository authorRepository;

    Author author;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        author = authorRepository.save(provideMember("test", 40));
    }

    @DisplayName("책을 하나 저장한다.")
    @Test
    void save() {
        // given
        BookRequestDto bookRequestDto = new BookRequestDto(author.getId(), "title", 10000, 1, LocalDate.now());

        // when
        Book book = bookService.save(bookRequestDto);

        // then
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor().getId()).isEqualTo(author.getId());
    }

    @DisplayName("양방향 연관관계시 책을 하나 저장한다.")
    @Transactional
    @Test
    void saveTwoWay() {
        // given
        BookRequestDto bookRequestDto = new BookRequestDto(author.getId(), "title", 10000, 1, LocalDate.now());

        // when
        Book book = bookService.saveTwoWay(bookRequestDto);

        // then
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor().getId()).isEqualTo(author.getId());
        assertThat(author.getBooks().stream().map(Book::getTitle)).containsOnly(book.getTitle());
    }

    @DisplayName("Unique 조건을 사용하는지 테스트")
    @TestFactory
    Stream<DynamicTest> saveUniqueTest() {
        return Stream.of(
                DynamicTest.dynamicTest("Book 저장1", () -> {
                    // given
                    BookRequestDto bookRequestDto = new BookRequestDto(author.getId(), "title", 10000, 1, LocalDate.now());

                    // when
                    Book book = bookService.save(bookRequestDto);

                    // then
                    assertThat(book.getId()).isNotNull();
                    assertThat(book.getAuthor().getId()).isEqualTo(author.getId());
                }),
                DynamicTest.dynamicTest("Book 저장할 때 실패함", () -> {
                    // given
                    BookRequestDto bookRequestDto = new BookRequestDto(author.getId(), "title", 10000, 1, LocalDate.now());

                    // when
                    Book book = bookService.save(bookRequestDto);

                    // then
                    assertThat(book.getId()).isNotNull();
                    assertThat(book.getAuthor().getId()).isEqualTo(author.getId());
                })
        );
    }

    @DisplayName("책의 저자를 변경한다.")
    @Transactional
    @Test
    void editAuthor() {
        // given
        Author changeAuthor = authorRepository.save(provideMember("test2", 41));
        Book book = bookRepository.save(provideBook(author));

        // when
        book.changeAuthor(changeAuthor);

        // then
        assertThat(book.getAuthor().getId()).isEqualTo(changeAuthor.getId());
    }

    Author provideMember(String userName, int age) {
        return Author.builder()
                .username(userName)
                .age(age)
                .build();
    }

    Book provideBook(Author author) {
        return Book.builder()
                .author(author)
                .title("testBook")
                .price(20000)
                .publishDate(LocalDate.now().minusYears(1))
                .edition(1)
                .author(author)
                .build();
    }
}