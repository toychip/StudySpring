package mvcFeatures.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
//Controller는 반환 값이 String이면 뷰 이름으로 인식된다. 그 후 뷰를 찾고 뷰가 렌더링이된다.
//RestController는 반환 값으로 뷰를 찾는 것이 아니다. 그대로 string으로 출력한다.
public class logTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());   == @Slf4j

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        log.trace(" trace log{}", name);

        // 현재 로그는 debug, 즉 개발 서버에서 보는 것이다.
        log.debug(" debug log{}", name); // 개발 서버

        //현재 정보는 중요한 비지니스 정보야 or 운영시스템에서도 봐야할 정보야
        log.info(" info log={}", name); // 운영 서버

        // 위험한거야
        log.warn(" warn log={}", name);

        //에러
        log.error(" error log={}", name);

        return "ok";
    }
}
