package mataedu.jpastudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book extends BaseEntity {

    @Comment("도서명")
    public String title;

    @Comment("저자")
    @ManyToOne(fetch = FetchType.LAZY) //@ManyToOne default 즉시로딩이므로 설정 필요
    public Author author;

    @Comment("출판사")
    public String publisher;

    @Comment("가격")
    public int price;


    // 연관관계 하나더 만들어서
    // 엮은이 만들고 공유!!!

    public void changeAuthor(Author getAuthor) {
        this.author = getAuthor;
        getAuthor.getBooks().add(this);
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }
}
