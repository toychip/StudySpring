package WAS.itemservice.validation;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {
    // error.Object.field 등등 넣으면 여러가지를 반환해줌
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
   void messageCodesResolverObject() {
    //given
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
//            messageCode = required.item
//            messageCode = required
        }
//        아래의 오류코드를 갖고 순서를 찾는 것을 reject, rejectValue가 해주는 거임, 디테일한 것이 우선순위, 범용적인게 다음 순위
//        new ObjectError("item", new String[]{"required,item", "required"})
        assertThat(messageCodes).containsExactly("required.item","required");
    }

    @Test
    void messageCodesResolverField(){
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes) {
            System.out.println("messageCode = " + messageCode);
//            messageCode = required.item.itemName      가장 정밀
//            messageCode = required.itemName           객체명 생략
//            messageCode = required.java.lang.String   타입명
//            messageCode = required                    전체
        }
//        BindingResult.rejectValue가 내부적으로 codesResolver.resolveMessageCodes를 사용함
//        new FieldError("item", "itemName", rejectValue, BindingFieldFalseTrue, messageCodes, argument)를 만들어줌
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
    }

}
