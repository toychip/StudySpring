package WAS.itemservice.web.item.validation;

import WAS.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // Item 클래스와 들어온 clazz가 같은 타입인가 검증하는 로직
        // Item == (자식) subItem
        // 자식 클래스들까지 같이 검증해주는 isAssignableFrom를 사용한다. ==이 아닌,
    }

    @Override    //BindinResult의 부모클래스인 Errors를 사용함, 그러므로 당연히 BindingResult 사용 가능
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
//            메시지에 등록된 오류코드가 아님. 뒤에 나올 messageResolver를 위한 오류 코드
//            일단 errorCode -> Object명 field명 조합해서 만들어준다고 생각하기.
        }

        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice()>1000000) {
//            errors.put("price", "가격은 1,000 ~ 1,000,000까지 허용합니다. ");                                                                                             파라미터 변수 값
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity()>=9999){
//            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }


        // 특정 필드가 아닌 복합 룰 검증
//         특정 필드가 아니므로 FieldError 사용 불가능
        if(item.getItemName()!=null &&item.getQuantity() !=null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
//                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
                // Object는 넘어올 값이 없음. 실패할 확률도 없음
            }
        }
    }
}
