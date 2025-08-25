package hello.Apple.service;


import hello.Apple.repository.Item;
import hello.Apple.repository.ItemRepository;
import hello.Apple.repository.ItemSearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        return itemRepository.findAll(cond);
    }


    // itemId 에 해당하는 item 을 찾는 메서들
    public Item findById(ItemSearchCond cond) {

        return itemRepository.findById(cond);
    }
}
