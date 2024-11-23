package mataedu.jpastudy.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.domain.Member;
import mataedu.jpastudy.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
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
