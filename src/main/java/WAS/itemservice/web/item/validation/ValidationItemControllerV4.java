package WAS.itemservice.web.item.validation;


import WAS.itemservice.domain.item.Item;
import WAS.itemservice.domain.item.ItemRepository;
import WAS.itemservice.web.item.form.ItemSaveForm;
import WAS.itemservice.web.item.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
/*
        ModelAttribute 를 생략하게 되면
        model에서 갖고오는 item이란 html Form에서 갖고오는 item
        model.addAttribute("itemSaveForm", form)으로 들어가게 되므로 위와 같이 설정해준다.
 */


        if(form.getItemName()!=null && form.getQuantity() !=null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "validation/v4/addForm";
        }

        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v4/items/{itemId}";
    }
    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v4/editForm";
    }

    @PostMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        if(form.getItemName()!=null && form.getQuantity() !=null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "validation/v4/editForm";
        }

//      실제 값이 아닌 값을 수정하기 위한 행동
        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());

        itemRepository.update(itemId, itemParam);
        return "redirect:/validation/v4/items/{itemId}";
        // GET -> 상품 수정 폼
        // POST -> 상품 수정 처리
    }
}
