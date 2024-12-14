package mataedu.jpastudy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

import java.util.List;

@Entity
public class Order extends BaseEntity {

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)

    @OrderColumn
    public List<OrderItem> orderItems;
    public String orderPersonName;
    public String orderDate;
    public String status;
    public int totalPrice;
    public int totalQuantity;
    public String deliveryAddress;
    public String deliveryMessage;

}
