package WAS.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data //위험해서 사용하지 않는 것이 좋음. 데이터를 중간에 담아주는 DTO는 사용 가능
//@Getter  @Setter
public class Item {

    /*
    Validation 애노테이션
    NotBlank        ->      빈값 + 공백만 있는 경우를 허용하지 않는다.
    NotNull         ->      null을 허용하지 않는다.
    Range           ->      범위 않의 값이어야 한다. min default = 0
     */

    private Long id;
//    @NotBlank(message = "공백x")    default 메시지 생성
    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;      // NULL이 못들어가므로 Integer 타입으로 선언

    @NotNull
    @Max(9999)
    private Integer quantity;


//    private Boolean open;   // 판매 여부

//    private List<String> regions;   // 등록 지역
//    private ItemType itemType;  // 상품 종류
//    private String deliveryCode;    // 배송 방식
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
