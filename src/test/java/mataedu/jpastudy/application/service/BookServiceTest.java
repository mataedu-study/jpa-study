package mataedu.jpastudy.application.service;

import jakarta.transaction.Transactional;
import mataedu.jpastudy.domain.entity.Book;
import mataedu.jpastudy.domain.entity.Member;
import mataedu.jpastudy.domain.repository.BookRepository;
import mataedu.jpastudy.domain.repository.MemberRepository;
import mataedu.jpastudy.presentation.BookRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    BookService bookService;

    @Autowired
    MemberRepository memberRepository;

    Member member;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        member = memberRepository.save(provideMember("test", 40));
    }

    @DisplayName("책을 하나 저장한다.")
    @Test
    void save() {
        // given
        BookRequestDto bookRequestDto = new BookRequestDto(member.getId(), "title", 10000, 1, LocalDate.now());

        // when
        Book book = bookService.save(bookRequestDto);

        // then
        assertThat(book.getId()).isNotNull();
        assertThat(book.getAuthor().getId()).isEqualTo(member.getId());
    }

    @DisplayName("책의 저자를 변경한다.")
    @Transactional
    @Test
    void editAuthor() {
        // given
        Member changeMember = memberRepository.save(provideMember("test2", 41));
        Book book = bookRepository.save(provideBook(member));

        // when
        book.changeAuthor(changeMember);

        // then
        assertThat(book.getAuthor().getId()).isEqualTo(changeMember.getId());
    }

    Member provideMember(String userName, int age) {
        return Member.builder()
                .username(userName)
                .age(age)
                .build();
    }

    Book provideBook(Member member) {
        return Book.builder()
                .author(member)
                .title("testBook")
                .price(20000)
                .publishDate(LocalDate.now().minusYears(1))
                .edition(1)
                .author(member)
                .build();
    }
}