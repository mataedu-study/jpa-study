package mataedu.jpastudy.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.entity.Author;
import mataedu.jpastudy.domain.entity.Book;
import mataedu.jpastudy.domain.repository.AuthorRepository;
import mataedu.jpastudy.domain.repository.BookRepository;
import mataedu.jpastudy.presentation.BookRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Book save(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.authorId());
        Book book = bookRequestDto.toEntity(author);

        return bookRepository.save(book);
    }

    public Book saveTwoWay(BookRequestDto bookRequestDto) {
        Author author = authorRepository.findById(bookRequestDto.authorId());
        Book book = bookRequestDto.toEntity();

        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllByMemberId(Long memberId) {
        return bookRepository.findAllByAuthorId(memberId);
    }

    public void editAuthor(Long memberId, Long bookId) {
        Author author = authorRepository.findById(memberId);
        Book book = bookRepository.findById(bookId);

        book.changeAuthor(author);
    }
}
