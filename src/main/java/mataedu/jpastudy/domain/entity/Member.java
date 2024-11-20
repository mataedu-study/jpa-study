package mataedu.jpastudy.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mataedu.jpastudy.common.BaseEntity;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseEntity {
    private String email;
    private String name;
    @OneToMany
    private List<Order> orders;
}
