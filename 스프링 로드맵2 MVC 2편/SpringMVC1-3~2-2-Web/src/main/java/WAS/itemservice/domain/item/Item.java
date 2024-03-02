package WAS.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data //위험해서 사용하지 않는 것이 좋음. 데이터를 중간에 담아주는 DTO는 사용 가능
//@Getter  @Setter

// ObjectError 추가
// 가격 * 수량 >= 10000 이어야 한다.

//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", // 복잡하고 대응이 어렵기 때문에실제 사용을 하지 않는다.
//message = "총 합이 10000원 넘게 입력해주세요.")
public class Item {

    /*
    Validation 애노테이션
    NotBlank        ->      빈값 + 공백만 있는 경우를 허용하지 않는다.
    NotNull         ->      null을 허용하지 않는다.
    Range           ->      범위 않의 값이어야 한다. min default = 0
     */

    //@NotNull(groups = UpdateCheck.class)    // 수정 요구사항으로 추가
    private Long id;
//    @NotBlank(message = "공백x")    default 메시지 생성:w

    /*
    에러 코드가 NotBlank.item.itemName 이런 방식으로 typeMisMatch 와 같은 논리로 동작한다.
     */
    //@NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
//    @Range(min = 4, max =20)
    private String itemName;

   // @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
   // @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;      // NULL이 못들어가므로 Integer 타입으로 선언

    //@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    //@Max(value = 9999, groups = {SaveCheck.class}) //수정 요구사항 추가
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
