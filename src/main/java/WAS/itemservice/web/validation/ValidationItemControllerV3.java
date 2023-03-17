package WAS.itemservice.web.validation;


import WAS.itemservice.domain.item.Item;
import WAS.itemservice.domain.item.ItemRepository;
import WAS.itemservice.domain.item.SaveCheck;
import WAS.itemservice.domain.item.UpdateCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v3/items")
@RequiredArgsConstructor
public class ValidationItemControllerV3 {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v3/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v3/addForm";
    }

//    @PostMapping("/add")    // Validated를 사용하므로써 item에 대한 검증을 자동으로 해줌, 검증 한 후 똑같이 bindingResult에 담겨있음
//                          @Validated는 검증기를 실행하라는 애노테이션이다. 이게 있으면 앞서 WebDataBinder에 등록한 검증기를 찾아서 실행
//                          여러 검증기를 등록한다면 어떤 검증기가 실행되어야할지 구분이 필요한대 이때 support가 사용된다.
//                          여기서는 supports(Item.class) 호출되고, 결과가 'utre'임로 'ItemValidator'의 'validate()'가 호출된다.
    public String addItem(@Validated @ModelAttribute Item item, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {

        if(item.getItemName()!=null && item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v3/addForm";
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v3/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV2(@Validated(SaveCheck.class) @ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if(item.getItemName()!=null && item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v3/addForm";
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v3/items/{itemId}";
    }
    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v3/editForm";
    }

//    @PostMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute Item item,BindingResult bindingResult) {

        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "validation/v3/editForm";
        }

        itemRepository.update(itemId, item);
        return "redirect:/validation/v3/items/{itemId}";
        // GET -> 상품 수정 폼
        // POST -> 상품 수정 처리
    }
    @PostMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String editV2(@PathVariable Long itemId, @Validated(UpdateCheck.class) @ModelAttribute Item item, BindingResult bindingResult) {

        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "validation/v3/editForm";
        }

        itemRepository.update(itemId, item);
        return "redirect:/validation/v3/items/{itemId}";
        // GET -> 상품 수정 폼
        // POST -> 상품 수정 처리
    }
}
