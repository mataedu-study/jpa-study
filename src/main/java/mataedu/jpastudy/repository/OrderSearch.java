package mataedu.jpastudy.repository;

import lombok.Getter;
import lombok.Setter;
import mataedu.jpastudy.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
