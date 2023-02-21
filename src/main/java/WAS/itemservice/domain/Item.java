package WAS.itemservice.domain;

import lombok.Data;

@Data //위험해서 사용하지 않는 것이 좋음. 데이터를 중간에 담아주는 DTO는 사용 가능
//@Getter  @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;      // NULL이 못들어가므로 Integer 타입으로 선언
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
