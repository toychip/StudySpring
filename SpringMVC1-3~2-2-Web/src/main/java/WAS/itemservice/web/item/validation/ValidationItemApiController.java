package WAS.itemservice.web.item.validation;

import WAS.itemservice.web.item.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API 컨트롤러 호출");
        if (bindingResult.hasErrors()){
//            Json Api형식으로 들어온 것이다.
            log.info("검증 오류 발생 errors{}", bindingResult);
            return bindingResult.getAllErrors(); // Field Error, Object Error 전부 반환
        }
        log.info("성공 로직 실행");
        return form;
    }
}
