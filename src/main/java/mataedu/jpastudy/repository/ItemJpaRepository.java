package mataedu.jpastudy.repository;

import mataedu.jpastudy.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<Item, Integer> {
}
