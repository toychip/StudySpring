package hello.proxy.app.v2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequestMapping
@ResponseBody
@RequiredArgsConstructor
/* @RequestMapping 만 사용해도 컨트롤러 역할을 함.
@Controller를 사용하지 않은 이유는 자동 컴포넌트 스캔의 대상이 되기 때문*/
public class OrderControllerV2 {

    private final OrderServiceV2 orderService;

    @GetMapping("/v2/request")
    public String request(final String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    @GetMapping("/v2/no-log")
    public String noLog() {
        return "noLog";
    }
}
