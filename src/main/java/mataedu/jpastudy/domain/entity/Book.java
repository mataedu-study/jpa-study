package mataedu.jpastudy.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.common.BaseEntity;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "BOOK_TITLE_EDITION_UNIQUE",
        columnNames = {"title", "edition"}
)})
public class Book extends BaseEntity {
    private String title;
    private LocalDate publishDate;
    private int price;
    private int edition;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Author author;

    @Builder
    public Book(String title, LocalDate publishDate, int price, int edition, Author author) {
        this.title = title;
        this.publishDate = publishDate;
        this.price = price;
        this.edition = edition;
        this.author = author;
    }

    public void changeAuthor(Author author) {
        this.author = author;
    }

    public void setAuthor(Author author) {
        if (this.author != null) {
            this.author.getBooks().remove(this);
        }
        this.author = author;
        author.getBooks().add(this);
    }
}
