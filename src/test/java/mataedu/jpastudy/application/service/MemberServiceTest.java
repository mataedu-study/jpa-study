package mataedu.jpastudy.application.service;

import mataedu.jpastudy.domain.entity.Author;
import mataedu.jpastudy.domain.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    AuthorService authorService;

    @Autowired
    AuthorRepository authorRepository;

    @DisplayName("멤버 저장")
    @Test
    void save() {
        // given
        String userName = "A";
        int age = 10;

        // when
        Author result = authorService.save(userName, age);

        // then
        assertThat(result.getId()).isNotNull();
    }

    @DisplayName("Id를 가지고 조회")
    @Test
    void findById() {
        // given
        Author author = authorRepository.save(provideMember("테스트", 10));
        Long id  = author.getId();

        // when
        Author result = authorService.getMemberById(id);

        // then
        assertThat(result.getId()).isEqualTo(author.getId());
    }

    @DisplayName("전체 조회")
    @Test
    void findAll() {
        // given
        List<Author> authors = List.of(
                provideMember("테스트", 10),
                provideMember("테스트", 10),
                provideMember("테스트", 10),
                provideMember("테스트", 10)
        );

        authors.forEach(member -> authorRepository.save(member));

        // when
        List<Author> authorList = authorRepository.findAll();

        // then
        assertThat(authorList).hasSize(4);
    }

    Author provideMember(String userName, int age) {
        return Author.builder()
                .username(userName)
                .age(age)
                .build();
    }
}