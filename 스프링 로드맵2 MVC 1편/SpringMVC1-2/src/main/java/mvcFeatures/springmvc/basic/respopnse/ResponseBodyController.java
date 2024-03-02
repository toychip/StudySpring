package mvcFeatures.springmvc.basic.respopnse;

import lombok.extern.slf4j.Slf4j;
import mvcFeatures.springmvc.basic.SampleData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody 전체가 다 붙어서 하나하나 다 붙일필요가 없음
//@RestController -> (@ResponseBody + @Controller)
public class ResponseBodyController {
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public HttpEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<SampleData> responseBodyJsonV1() {
        SampleData sampleData = new SampleData();
        sampleData.setUsername("userA");
        sampleData.setAge(5);

        return new ResponseEntity<>(sampleData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) // 위와 같이 상태 선언 가능
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public SampleData responseBodyJsonV2() {
        SampleData sampleData = new SampleData();
        sampleData.setUsername("userA");
        sampleData.setAge(5);

        return sampleData;
    }
}