package mataedu.jpastudy.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Author extends BaseEntity {
    private String username;
    private Integer age;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @Builder
    public Author(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public void modifyAge(Integer age) {
        this.age = age;
    }
}
