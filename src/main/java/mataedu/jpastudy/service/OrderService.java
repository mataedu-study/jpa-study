package mataedu.jpastudy.service;

import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.domain.Member;
import mataedu.jpastudy.domain.Order;
import mataedu.jpastudy.domain.OrderItem;
import mataedu.jpastudy.repository.ItemRepository;
import mataedu.jpastudy.repository.MemberRepository;
import mataedu.jpastudy.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;


    // 주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item);

        // 주문 생성
        Order order = Order.createOrder(member, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    };

}
