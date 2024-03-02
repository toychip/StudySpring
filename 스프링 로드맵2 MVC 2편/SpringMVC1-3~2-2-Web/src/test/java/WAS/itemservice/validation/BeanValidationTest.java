package WAS.itemservice.validation;

import WAS.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class BeanValidationTest {

    @Test
    public void beanValidation() {
    //given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        //when

        Item item = new Item();
        item.setItemName(" ");      //공백으로 해놓음.
        item.setPrice(0);
        item.setQuantity(10000);        // 9999를 넘었음

        //then
        Set<ConstraintViolation<Item>> validate = validator.validate(item);
        for (ConstraintViolation<Item> Violation : validate) {
            System.out.println("Violation = " + Violation);
            System.out.println("Violation.getMessage() = " + Violation.getMessage());
        }

    }
}
