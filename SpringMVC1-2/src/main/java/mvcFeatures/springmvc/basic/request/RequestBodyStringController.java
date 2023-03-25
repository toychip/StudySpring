package mvcFeatures.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // 어떤문자를 어떤걸로 인코딩해서 받을거야 지정안하면 default를 받게 됨 os의 기본 설정이라든가 등
        log.info("messageBody={}", messageBody);
        response.getWriter().write("pok-1");
    }

    @PostMapping("request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

//        InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
//        OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("pok-2");
    }

    @PostMapping("request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        /*
        HttpEntity ->
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        너는 문자구나. httpBody에 있믄 거를 문자를 받아서 너한테 넣어줄게 이런 코드를 대신 실행해줄게. httpMessageConverter가 동작
        */
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");

        /*
        요청 파라미터를 조회하는 기능과 전혀 관계가 없다. @RequestParam, @Modelattribute
        이것들은 쿼리 스트링(?id=toybo) or Post방식으로 Html form을 보낼때만
        컨텐츠 타입이 xurl ...
        이러할때를 제외하고는 httpEntity를 사용하여 데이터를 직접 꺼내야함.
        view 조회 x
         */
    }

    @ResponseBody   //http 응답 코드에다가 간단하고 편리하게 ok를 반환한다.
    @PostMapping("request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        /*
        이제야 짝이 맞게 된다. 들어오는 바디를 @RequestBody 애노테이션을 활용하여 쉽게 받음
        @RequestBody를 사용하면 HttpEntity를 사용하지 않아도 된다. HttpBody 메시지를 읽어서 messageBody에 넣어준다.

        참고) 헤더의 정보를 알고 싶으면 @RequestHeader를 사용할면 된다.

        참고2) 제일 중요
        요청 파라미터 vs HTTP 메시지 바디
        요청 파라미터를 조회하는 기능: @ModelAttribute, @RequestParam
        HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody

        @ResponseBody
        ResponseBody를 사용하면 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달할 수 있다.
        이 경우 view를 사용하지 않는다 !
         */
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
