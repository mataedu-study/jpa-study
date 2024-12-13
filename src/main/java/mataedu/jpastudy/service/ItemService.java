package mataedu.jpastudy.service;

import lombok.RequiredArgsConstructor;
import mataedu.jpastudy.domain.Item;
import mataedu.jpastudy.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    public void reduce(Long itemId) {
        Item item = itemRepository.findOne(itemId);

        if (item.getStock() <= 0) {
            throw new IllegalArgumentException("재고가 없단다");
        }

        item.reduceStock();
    }
}
