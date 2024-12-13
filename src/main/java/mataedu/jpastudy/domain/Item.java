package mataedu.jpastudy.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

//    @Version
//    private Integer version;

    private int stock;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void reduceStock() {
        this.stock--;
    }

    public void reduceStock(int amount) {
        this.stock -= amount;
    }
}
