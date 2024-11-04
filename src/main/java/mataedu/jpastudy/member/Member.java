package mataedu.jpastudy.member;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.utils.BaseEntity;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    private String name;

    private String email;

    private Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static Member of(String name, String email) {
        return new Member(name, email);
    }
}
