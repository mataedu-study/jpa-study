package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.domain.Member;
import mataedu.jpastudy.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public Item findOneWithLock(Long id) {
        return em.find(Item.class, id, LockModeType.PESSIMISTIC_WRITE);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m" +
                " where o.status = :status " +
                " and m.name like :name";
        return em.createQuery(jpql, Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }


}
