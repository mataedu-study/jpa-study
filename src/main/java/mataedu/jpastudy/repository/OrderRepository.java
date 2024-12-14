package mataedu.jpastudy.repository;

import mataedu.jpastudy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT o FROM Order o")
    List<Order> findAllOrders();  // N+1 문제가 발생하는 쿼리


    @Query("SELECT o FROM Order o JOIN FETCH o.orderItems")
    List<Order> findAllOrdersWithOrderItems();  // Fetch Join을 사용한 해결
}
