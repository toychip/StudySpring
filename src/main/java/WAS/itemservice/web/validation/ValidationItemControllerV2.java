package WAS.itemservice.web.validation;


import WAS.itemservice.domain.item.Item;
import WAS.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }
                // --- 같은 url로 들어오더라도 get, post에 따라 호출되는 메서드가 다르게 설정 ---

//    @PostMapping("/add")        // BindingResult -> Item에 Binding된 결과가 bindingResult에 담김 == 잘 안담기고 오류가 생긴다면 bindingresult에 담김
//    또한 bindingResult가 있으면 오류가 났을때 바로 400 페이지로 가는것이 아닌 어떠한 오류가 났는지 bindingResult에 담고 전달하며, 바로 오류페이지가 아닌 우선 컨트롤러가 호출이된다.
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//        log.info("item.open={}", item.getOpen());

        // Validation Error 보관
//        Map<String, String> errors = new HashMap<>(); [ BindingResult 사용 후 이것 사용 x]

        // Validation Logic
        if (!StringUtils.hasText(item.getItemName())){
//            errors.put("itemName", "상품 이름은 필수입니다.");
            // item은 field 이므로 새로운 객체에 담기
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice()>1000000){
//            errors.put("price", "가격은 1,000 ~ 1,000,000까지 허용합니다. ");
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000 ~ 1,000,000까지 허용합니다."));
        }

        if(item.getQuantity() == null || item.getQuantity()>=9999){
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
                bindingResult.addError(new FieldError("item", "quantity", "수량은 최대 9,999까지 허용합니다."));
        }


        // 특정 필드가 아닌 복합 룰 검증
//         특정 필드가 아니므로 FieldError 사용 불가능
        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
//        if(!errors.isEmpty()){  // error가 empty가 아니면  에러가 없음이 아니면 == 에러가 있으면
        if(bindingResult.hasErrors()){
//            log.info("errors = {}", errors);
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors); 이것을 담지 않아도 됨, bindingResult에 자동으로 담기기 때문
            return "validation/v2/addForm"; // model에 담아서 다시 보여줄거니까 담아서, 입력 폼 뷰로 돌려보냄
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v2/items/{itemId}";
    }

//    @PostMapping("/add")        // BindingResult -> Item에 Binding된 결과가 bindingResult에 담김 == 잘 안담기고 오류가 생긴다면 bindingresult에 담김
//      또한 bindingResult가 있으면 오류가 났을때 바로 400 페이지로 가는것이 아닌 어떠한 오류가 났는지 bindingResult에 담고 전달하며, 바로 오류페이지가 아닌 우선 컨트롤러가 호출이된다.
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//        log.info("item.open={}", item.getOpen());

        // Validation Error 보관
//        Map<String, String> errors = new HashMap<>(); [ BindingResult 사용 후 이것 사용 x]

        // Validation Logic
        if (!StringUtils.hasText(item.getItemName())){
//            errors.put("itemName", "상품 이름은 필수입니다.");
            // item은 field 이므로 새로운 객체에 담기
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
        }                                      // 어떠한 값이 들어온 것인지. item.getItemName, 값이 제대로 안들어왔냐? false , 뒤 defaultMessage를 대신해서 값을 넣어 줄것이면 codes나 arguments를 정해줄 수 있다.

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice()>1000000){
//            errors.put("price", "가격은 1,000 ~ 1,000,000까지 허용합니다. ");
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null,"가격은 1,000 ~ 1,000,000까지 허용합니다."));
        }

        if(item.getQuantity() == null || item.getQuantity()>=9999){
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(),false,null,null ,"수량은 최대 9,999까지 허용합니다."));
        }


        // 특정 필드가 아닌 복합 룰 검증
//         특정 필드가 아니므로 FieldError 사용 불가능
        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item", null,null,"가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
//        if(!errors.isEmpty()){  // error가 empty가 아니면  에러가 없음이 아니면 == 에러가 있으면
        if(bindingResult.hasErrors()){
//            log.info("errors = {}", errors);
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors); 이것을 담지 않아도 됨, bindingResult에 자동으로 담기기 때문
            return "validation/v2/addForm"; // model에 담아서 다시 보여줄거니까 담아서, 입력 폼 뷰로 돌려보냄
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v2/items/{itemId}";
    }

    // @PostMapping("/add")        // BindingResult -> Item에 Binding된 결과가 bindingResult에 담김 == 잘 안담기고 오류가 생긴다면 bindingresult에 담김
//    또한 bindingResult가 있으면 오류가 났을때 바로 400 페이지로 가는것이 아닌 어떠한 오류가 났는지 bindingResult에 담고 전달하며, 바로 오류페이지가 아닌 우선 컨트롤러가 호출이된다.
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//        log.info("item.open={}", item.getOpen());

        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());
        // Validation Error 보관
//        Map<String, String> errors = new HashMap<>(); [ BindingResult 사용 후 이것 사용 x]

        // Validation Logic
        if (!StringUtils.hasText(item.getItemName())){
//            errors.put("itemName", "상품 이름은 필수입니다.");
            // item은 field 이므로 새로운 객체에 담기
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }                                      // 어떠한 값이 들어온 것인지. item.getItemName, 값이 제대로 안들어왔냐? false , 뒤 defaultMessage를 대신해서 값을 넣어 줄것이면 codes나 arguments를 정해줄 수 있다.

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice()>1000000){
//            errors.put("price", "가격은 1,000 ~ 1,000,000까지 허용합니다. ");                                                                                             파라미터 변수 값
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000, 1000000},null));
        }                                                    // 배열로 하는 이유는 두번째 값을 지정할 수 있다. ex) new String[]{"range.item.price", "max.item.quantity"} 등,, 정말 예시일 뿐

        if(item.getQuantity() == null || item.getQuantity()>=9999){
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
            bindingResult.addError(new FieldError("item", "quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999} ,null));
        }


        // 특정 필드가 아닌 복합 룰 검증
//         특정 필드가 아니므로 FieldError 사용 불가능
        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"},new Object[]{10000, resultPrice},null));
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
//        if(!errors.isEmpty()){  // error가 empty가 아니면  에러가 없음이 아니면 == 에러가 있으면
        if(bindingResult.hasErrors()){
//            log.info("errors = {}", errors);
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors); 이것을 담지 않아도 됨, bindingResult에 자동으로 담기기 때문
            return "validation/v2/addForm"; // model에 담아서 다시 보여줄거니까 담아서, 입력 폼 뷰로 돌려보냄
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")        // BindingResult -> Item에 Binding된 결과가 bindingResult에 담김 == 잘 안담기고 오류가 생긴다면 bindingresult에 담김
//    또한 bindingResult가 있으면 오류가 났을때 바로 400 페이지로 가는것이 아닌 어떠한 오류가 났는지 bindingResult에 담고 전달하며, 바로 오류페이지가 아닌 우선 컨트롤러가 호출이된다.
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
//             ** BindingResult에 내장되어 있는 rejactValue와 reject 도입 **
//        rejectValue - FieldError
//        reject    -  objectValue


        //        log.info("item.open={}", item.getOpen());

        log.info("objectName={}", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());
        // Validation Error 보관
//        Map<String, String> errors = new HashMap<>(); [ BindingResult 사용 후 이것 사용 x]

        // Validation Logic
        if (!StringUtils.hasText(item.getItemName())){
            bindingResult.rejectValue("itemName", "required");
//            메시지에 등록된 오류코드가 아님. 뒤에 나올 messageResolver를 위한 오류 코드
//            일단 errorCode -> Object명 field명 조합해서 만들어준다고 생각하기.
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice()>1000000) {
//            errors.put("price", "가격은 1,000 ~ 1,000,000까지 허용합니다. ");                                                                                             파라미터 변수 값
            bindingResult.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity()>=9999){
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null);
        }


        // 특정 필드가 아닌 복합 룰 검증
//         특정 필드가 아니므로 FieldError 사용 불가능
        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }

        // 검증 실패하면 다시 입력 폼으로, 실패 로직
//        if(!errors.isEmpty()){  // error가 empty가 아니면  에러가 없음이 아니면 == 에러가 있으면
        if(bindingResult.hasErrors()){
//            log.info("errors = {}", errors);
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors); 이것을 담지 않아도 됨, bindingResult에 자동으로 담기기 때문
            return "validation/v2/addForm"; // model에 담아서 다시 보여줄거니까 담아서, 입력 폼 뷰로 돌려보냄
        }

        // 성공 로직
        Item savedItem = itemRepository.save(item);
//        redirectAttributes.addAttribute("item", item);
        redirectAttributes.addAttribute("itemId", item.getId());
//        ^에서 redirectAttributes.addAttribute 사용하여 아래 return에 itemId에 값을 넣은 것으로 치환해서 리턴한다.
        redirectAttributes.addAttribute("status", true);
        // ^는 쿼리 파라미터 ?status=true로 넘어가게 된다.
        return "redirect:/validation/v2/items/{itemId}";
    }

    // 상품 수정 폼
    @GetMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")       // Long <-> long 차이, Long은 null 가능, long은 불가능
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
        // GET -> 상품 수정 폼
        // POST -> 상품 수정 처리
    }

}
