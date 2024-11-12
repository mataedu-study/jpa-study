package mataedu.jpastudy.presentation;

import mataedu.jpastudy.domain.entity.Book;
import mataedu.jpastudy.domain.entity.Member;

import java.time.LocalDate;

public record BookRequestDto(
        Long authorId,
        String title,
        int price,
        int edition,
        LocalDate publishDate
) {
    public Book toEntity(Member author) {
        return Book.builder()
                .author(author)
                .title(title)
                .price(price)
                .edition(edition)
                .publishDate(publishDate)
                .build();
    }

    public Book toEntity() {
        return Book.builder()
                .price(price)
                .edition(edition)
                .publishDate(publishDate)
                .build();
    }
}
