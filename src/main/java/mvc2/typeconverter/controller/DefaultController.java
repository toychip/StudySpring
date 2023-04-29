package mvc2.typeconverter.controller;

import mvc2.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DefaultController {

    @GetMapping("/get-v1")
    public String getv1(HttpServletRequest request) {
        String data = request.getParameter("data"); // 문자 타입 조회
        Integer intValue = Integer.valueOf(data);
        return "ok";
    }

    @GetMapping("/get-v2")
    public String useSpring(@RequestParam Integer data) {    // requestparam 에서 바꿈, Spring이 숫자로 바꿔서 전달해줌
        return "ok";
    }

    @GetMapping("/ip-port")
    public String inPort(@RequestParam IpPort ipPort){
        System.out.println("ipPort.getIp() = " + ipPort.getIp());
        System.out.println("ipPort.getPort() = " + ipPort.getPort());
        return "ok";
    }



}
