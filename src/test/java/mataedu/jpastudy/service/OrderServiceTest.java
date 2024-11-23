package mataedu.jpastudy.service;

import jakarta.persistence.EntityManager;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.domain.Member;
import mataedu.jpastudy.domain.Order;
import mataedu.jpastudy.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @Test
    @DisplayName("상품주문")
    public void createOrder() {
        // given
        Member member = createMember();

        String name = "아라라라";
        int bookPrice = 10000;

        Item book = createItem(name, bookPrice);

        // when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order order = orderRepository.findOne(orderId);

        assertNotNull(order);

    }



    private Item createItem(String name, int price) {
        Item item = new Item(name, price);
        em.persist(item);
        return item;
    }

    private Member createMember() {
        Member member = new Member(10, "회원1");
        em.persist(member);
        return member;
    }
}