package WAS.itemservice.web.item.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemUpdateForm {

    // save는 id가 필요 없음

    @NotNull
    private String id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    // 수정에서는 9999제한이 없다.
    private Integer quantity;

//    @Email
//    private String mail;
//    이런 것 또한 가능함.

}
