package mataedu.jpastudy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.repository.ItemJpaRepository;
import mataedu.jpastudy.repository.ItemRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {

    private final ItemJpaRepository itemJpaRepository;
    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemJpaRepository.save(item);
    }

    public void reduce(Long itemId) {
//        Item item = itemJpaRepository.findById(Math.toIntExact(itemId)).orElseThrow();
        Item item = itemRepository.findOne(itemId);
//        Item item = itemRepository.findOneWithLock(itemId);
        log.info("item : {}", item);

        if (item.getStock() <= 0) {
            throw new IllegalArgumentException("재고가 없단다");
        }

        item.reduceStock();
    }

    public void reduceRetry(Long itemId) throws InterruptedException {
//        Item item = itemJpaRepository.findById(Math.toIntExact(itemId)).orElseThrow();
        Item item = itemRepository.findOne(itemId);
        log.info("item : {}", item);

        int maxRetries = 5;

        for (int count = 0; count < maxRetries; count++) {
            try {
                if (item.getStock() <= 0) {
                    throw new IllegalArgumentException("재고가 없단다");
                }

                item.reduceStock();
                return;
            } catch (ObjectOptimisticLockingFailureException e) {
                if (count == maxRetries) {
                    throw e;
                }
                Thread.sleep(500);
            }
        }
    }
}
