package mvcFeatures.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import mvcFeatures.springmvc.basic.SampleData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")    // 기존에 하던 방법
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody                           // @ResponseBody를 활용하여 HttpServlet의 request.get~ 이런거 사용 안해도 됨
    @RequestMapping("/request-param-v2")    // 입력값을 다른 이름의 변수에 담기 가능. username으로 받은 값을 memberName에다가 넣음.
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")    // @ResponseBody를 사용하고 간단하게 하는 방법
    public String requestParamV3(@RequestParam String username, @RequestParam int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody                                   // RequestParam 생략
    @RequestMapping("/request-param-v4")                    // String, int, Integer 생략 가능
    public String requestParamV4(String username, int age){ // 요청 파라미터와 이름이 같다면 @RequestParam 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody                                   // 필수값 코드 생성
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // RequestParam이 true면 필수로 들어와야하는 값이 된다.
            @RequestParam(required = false) int age){ // 요청 파라미터와 이름이 같다면 @RequestParam 생략 가능

        /*
        여기서 int가 값이 안들어오면 required = false 이기 때문에 안들어와도 가능하지만 서버에서 500 오류를 내보낸다.
        그 이유는 int = null 이 들어갈 수 없다. 이럴 때는 Integer 로 선언하면 가능하다. Integer 는 객체형이기 때문에
        Integer = null이 가능한 것이다.
        Integer A = null 가능
        int a = null 불가능
         */

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody                               // 기본값 생성
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age){
        /*
            defaultValue가 있다면 사실 required 속성을 적을 필요가 없다. 기본 값으로 값을 넣어주기 때문
            defaultValue를 사용하면 int로 선언해도 기본 값으로 -1이 들어가므로 상관 없다.
            defaultValue는 "" 빈 문자의 값이여도 기본값으로 처리를 해준다.
         */
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody                                // map으로 파라미터 받기
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
/*
파라미터의 값이 1개가 확실하다면 'Map'을 사용해도 되지만, 그렇지 않다면 'MultiValueMap'을 사용하자.
보통 1개이지 2개를 쓰는 경우는 거의 없다.
 */

    @ResponseBody
    @RequestMapping("model-attribute-v1")   // ModelAttribute 도입
    public String modelAttributeV1(@ModelAttribute SampleData sampleData){

//        @ModelAttribute 가 있으면 SampleData 객체가 생성되고, 요청 파라미터 값도 모두 들어가 있다. 자동으로 생성해줌.

        log.info("username={}, age={}", sampleData.getUsername(), sampleData.getAge());
        log.info("sampleDate={}", sampleData);  // 이것도 가능 ToString이 있어야 하지만 @Data로 인해서 자동으로 생성해주었기 때문
        return "ok";

        /*
        바인딩 오류
        age = abc처럼 숫자가 들어가야 할 곳엠 문자를 넣으면 BindException이 발생한다. 이런 바인딩 오류를 처리하는 방법은 검증 부분에서 본다.
         */
    }

    @ResponseBody
    @RequestMapping("model-attribute-v2")       // @ModelAttribute 생략
    public String modelAttributeV2(SampleData sampleData) {
        /*
         생략해도 괜찮다. 하지만 requestParam도 생략가능하므로 혼란이 발생할수있다.

         @RequestParam 과 @ModelAttribute 생략을 어떻게 구분하는가?
         String, int, Integer 같은 단순 타입 -> RequestParam
         나머지는 전부 ModelAttribute argument resolver로 지정해둔 타입 외)
         */

        log.info("username={}, age={}", sampleData.getUsername(), sampleData.getAge());
        log.info("sampleDate={}", sampleData);  // 이것도 가능 ToString이 있어야 하지만 @Data로 인해서 자동으로 생성해주었기 때문
        return "ok";
    }

}

