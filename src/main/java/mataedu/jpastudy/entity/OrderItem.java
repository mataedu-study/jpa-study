package mataedu.jpastudy.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.util.List;

@Entity
public class OrderItem extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "order_id") // 외래 키
    public Order order;

    @Column(name = "order_idx")
    private int orderIndex; // 순서저장

    @OneToOne
    public Book Book;
    @Comment("주문 수량")
    private int quantity;
    private double price;
    public int orderPrice;

}
