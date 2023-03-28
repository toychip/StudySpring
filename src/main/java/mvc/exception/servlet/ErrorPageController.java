package mvc.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ErrorPageController {  // error 페이지가 생겼을 때 보다 더 이쁜 페이지를 보여주기 위한 Controller

    @RequestMapping("/error-page/404")  // post던 get이던 한 번에 처리하기 위해 RequestMapping을 사용함
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")  // post던 get이던 한 번에 처리하기 위해 RequestMapping을 사용함
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 500");
        return "error-page/500";
    }
}
