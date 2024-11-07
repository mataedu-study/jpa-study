package mataedu.jpastudy.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.utils.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static Member of(String name, String email) {
        return new Member(name, email);
    }

    public void updateEmail(String email) {
        this.email = email;
    }
}
