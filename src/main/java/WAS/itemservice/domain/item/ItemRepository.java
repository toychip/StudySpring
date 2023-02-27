package WAS.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    //    key인 id가 Long타입이기 때문에
    private static final Map<Long, Item> store = new HashMap<>();
    // 실무에서는 멀티쓰레드 문제가 있기 때문에 HashMap이 아닌 CurrentHashMap을 사용해야한다.
    private static long sequence = 0L;  // Spring 컨테이너 안에서 사용하면 어차피 싱글톤이기 때문에 static을 사용하지 않아도 된다.
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }
//                                  정석으로 하려면 ItemParamDTO 를 생성하는게 맞다.
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void clearStore(){
        store.clear();
    }


}
