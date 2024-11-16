package mataedu.jpastudy.service;


import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Author;
import mataedu.jpastudy.domain.Book;
import mataedu.jpastudy.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long itemId, String name) {
        Book findBook = bookRepository.findOne(itemId);
        findBook.setName(name);
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book findOne(Long itemId) {
        return bookRepository.findOne(itemId);
    }
}
