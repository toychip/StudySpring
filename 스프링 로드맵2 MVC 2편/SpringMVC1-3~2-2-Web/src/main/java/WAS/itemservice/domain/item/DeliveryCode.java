package WAS.itemservice.domain.item;


import lombok.AllArgsConstructor;
import lombok.Data;


/*
fast : 빠른 배송
normal : 일반 배송
slow : 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {

    private String code;
    private String displayName;

}
