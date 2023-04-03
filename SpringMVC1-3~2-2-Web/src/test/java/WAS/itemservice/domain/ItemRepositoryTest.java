package WAS.itemservice.domain;

import WAS.itemservice.domain.item.Item;
import WAS.itemservice.domain.item.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
    //given
    Item item = new Item("itemA", 10000, 10);
    //when
    Item savedItem = itemRepository.save(item);
    //then
    Item findITem = itemRepository.findById(item.getId());

    assertThat(findITem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 10000, 10);
        //when

        itemRepository.save(item1);
        itemRepository.save(item2);
        //then
        List<Item> result = itemRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void update() {
    //given
    Item item = new Item("itemA", 10000, 10);
    Item saveItem = itemRepository.save(item);
    Long itemId = saveItem.getId();
    //when
    Item updateId = new Item("item2", 20000, 30);
    itemRepository.update(itemId, updateId);

    //then
    Item findItem = itemRepository.findById(itemId);
    assertThat(findItem.getItemName()).isEqualTo(updateId.getItemName());
    assertThat(findItem.getPrice()).isEqualTo(updateId.getPrice());
    assertThat(findItem.getQuantity()).isEqualTo(updateId.getQuantity());
    }

}