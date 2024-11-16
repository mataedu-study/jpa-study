package mataedu.jpastudy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public void updateAuthor(Author author) {
        this.author = author;
    }

    public static Book of(String name, Author author) {
        return new Book(name, author);
    }
}
