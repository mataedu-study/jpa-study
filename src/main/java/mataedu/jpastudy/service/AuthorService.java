package mataedu.jpastudy.service;


import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.entity.Author;
import mataedu.jpastudy.entity.Book;
import mataedu.jpastudy.repository.AuthorRepository;
import mataedu.jpastudy.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> getAuthorBooks(String name) {
        Optional<Author> optionalAuthor = authorRepository.findByName(name);

        optionalAuthor.orElseThrow(() -> new IllegalArgumentException("해당 저자의 도서가 존재하지 않습니다."));
        //System.out.println("optionalAuthor.get().getBooks() 호출 전");
        List<Book> books = optionalAuthor.get().getBooks();
        //System.out.println("optionalAuthor.get().getBooks() 호출 후");
        //books.forEach(book -> System.out.println(book.getTitle())); // 각 Book 엔티티를 개별적으로 접근. 왜 쿼리 안날라가지.

        return optionalAuthor.get().getBooks(); //여기선 발생 안하네
    }

    @Transactional
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Book> getAuthorBooksFetchJoin(String authorName) {
        // return bookRepository.findBooks(authorName);
        return null;
    }
}
