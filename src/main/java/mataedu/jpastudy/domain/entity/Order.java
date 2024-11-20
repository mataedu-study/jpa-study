package mataedu.jpastudy.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import mataedu.jpastudy.common.BaseEntity;

import java.util.List;

@Entity
public class Order extends BaseEntity {
    @ManyToOne
    private Member member;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
