package WAS.itemservice.web.form;


import WAS.itemservice.domain.item.DeliveryCode;
import WAS.itemservice.domain.item.Item;
import WAS.itemservice.domain.item.ItemRepository;
import WAS.itemservice.domain.item.ItemType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
public class FormItemController {
    private final ItemRepository itemRepository;

//    @ModelAttribute("regions")
//    public Map<String, String> regions(){
//
//        Map<String, String> regions = new LinkedHashMap<>();
//        regions.put("SEOUL", "서울"); // SEOUL 은 식별하는 키, 서울은 사용자에게 단순히 보영주기 위한 키
//        regions.put("BUSAN", "부산");
//        regions.put("JEJU", "제주");
//        return regions;
//
////        똑같은 코드를 여러번 하는 것이 싫으므로 위에서 생성 후 자동 반환
////        이렇게 선언을 하면 해당 컨트롤러를 요청할때 'regions' 에서 반환한 값이 자동으로 모델('model')에 담기게 된다.
//    }
//
//    @ModelAttribute("itemTypes")
//    public ItemType[] itemTypes(){
//
//        // enum 형식을 .values 를 사용하면 enum 에 있는 값들을 배열로 넘겨줌
////        ItemType[] values = ItemType.values();
////        return values;
////      이를 inline으로 한줄로 해서 리턴
//        return ItemType.values();
//    }
//
//
//    @ModelAttribute("deliveryCodes")
//    public List<DeliveryCode> deliveryCodes(){
//        List<DeliveryCode> deliveryCodes = new ArrayList<>();
//        deliveryCodes.add(new DeliveryCode("FAST", "빠른배송"));
//        deliveryCodes.add(new DeliveryCode("NORMAL", "일반배송"));
//        deliveryCodes.add(new DeliveryCode("SLOW", "느린배송"));
//        return deliveryCodes;
//    }

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v1/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "validation/v1/addForm";
    }
                // --- 같은 url로 들어오더라도 get, post에 따라 호출되는 메서드가 다르게 설정 ---
//    @PostMapping("/add")

    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,     // 여기서는 int 와 Integer 중 아무거나 사용해도 된다.
                       @RequestParam Integer quantity,
                       Model model){
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        // model and view를 생각하자. view에 보내주는 것을 item 객체에 담아서 보낸다는 점을 생각하면된다.
        return "form/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item ,Model model){
//                                         | 여기서 정한 이름을 그대로 view에다가 전달 해준다.
//                                         그래서 제일 밑 model.addAttribute("item", item) 를 생략해도 된다.
//                                         또한 Model model 도 생략이 가능하다.
//                                      @modelAttribute는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)로 입력해준다.
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//        위 코드를 @ModelAttribute 하나로 정리
        itemRepository.save(item);

//        model.addAttribute("item", item); 자동 추가, 생략 가능

        return "form/item";
    }
    //    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
//                                     ('item') 이것을 지우면 default 설정은
//                                     클래스면 Item -> 앞글자만 소문자로 바꾼 item으로 바뀌어서 model에 담기게 된다.
        itemRepository.save(item);
        return "form/item";
    }
    //    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);   // @ModelAttribute 또한 생략 가능.
        return "form/item";         // 단순한 타입들 String, int, Integer 가 들어오면 @RequestParam이 적용되고
//                                      객체가 들어오면 @ModelAttribute가 적용된다.
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/form/items/" + item.getId();
//
//                                         id를 그대로 사용하는 것은 매우 위험하므로 RedirectAttributes를 사용하는 것이 좋다.
    }


    @PostMapping("/add")
    public String addItem(Item itemId, RedirectAttributes redirectAttributes) {
//        log.info("item.open={}", item.getOpen());
//        log.info("item.regions={}", item.getRegions());
//        log.info("item.itemType={}", item.getItemType());

//        Item item = itemRepository.save(item);
        Item item = itemRepository.save(itemId);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v1/items/{itemId}";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v1/editForm";
    }

    @PostMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/validation/v1/items/{itemId}";
        // GET -> 상품 수정 폼
        // POST -> 상품 수정 처리
    }

    /*
    테스트용 데이터 추가
     */
//    @PostConstruct
//    public void init(){
//        itemRepository.save(new Item("itemA", 10000, 10));
//        itemRepository.save(new Item("itemB", 20000, 20));
//    }

}
