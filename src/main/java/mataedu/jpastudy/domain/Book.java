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

    public Book(String name) {
        this.name = name;
    }

    public void setAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    public static Book of(String name) {
        return new Book(name);
    }
}
