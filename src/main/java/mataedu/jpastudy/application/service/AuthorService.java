package mataedu.jpastudy.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Author;
import mataedu.jpastudy.domain.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author save(String username, Integer age) {
        Author author = new Author(username, age);
        return authorRepository.save(author);
    }

    public Author modifyAge(Long id, Integer age) {
        Author author = authorRepository.findById(id);
        author.modifyAge(age);
        return author;
    }

    public Author getMemberById(Long id) {
        return authorRepository.findById(id);
    }

    public List<Author> getMembers() {
        return authorRepository.findAll();
    }
}
