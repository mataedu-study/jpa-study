package mataedu.jpastudy.service;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.repository.ItemJpaRepository;
import mataedu.jpastudy.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@ActiveProfiles("local")
//@Rollback(value = false)
@Slf4j
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemJpaRepository itemJpaRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("100명 요청자 재고 감소 테스트")
    void reduceTest() throws InterruptedException {
        // given
        final int requestCnt = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(requestCnt);
        CountDownLatch countDownLatch = new CountDownLatch(requestCnt);
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        Item item = new Item("테스트 아이템", 1000, 50);
        itemRepository.save(item);

        // when

        for (int i = 0; i < requestCnt; i++) {
            log.info("requestCnt : {}", i);
            executorService.submit(() -> {
                try {
                    itemService.reduce(item.getId());
                    success.incrementAndGet();
                    log.info("item : {}", item);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    fail.incrementAndGet();
                    log.info("item : {}", item);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        // then
        Item findItem = itemRepository.findOne(item.getId());

        System.out.println("findItem = " + findItem);
        System.out.println("success = " + success);
        System.out.println("fail = " + fail);
    }

}