package WAS.itemservice.web.argumentresolver;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  // 파라미터에만 적용
@Retention(RetentionPolicy.RUNTIME) // 동작할때까지 에노테이션이 남아있어야 함.
public @interface Login {
}
