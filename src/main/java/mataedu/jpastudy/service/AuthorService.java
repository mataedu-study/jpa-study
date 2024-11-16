package mataedu.jpastudy.service;

import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Author;
import mataedu.jpastudy.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void updateAuthor(Long authorId, String name) {
        Author findAuthor = authorRepository.findOne(authorId);
        findAuthor.setName(name);
    }

    public List<Author> findAuthors() {
        return authorRepository.findAll();
    }

    public Author findOne(Long authorId) {
        return authorRepository.findOne(authorId);
    }
}
