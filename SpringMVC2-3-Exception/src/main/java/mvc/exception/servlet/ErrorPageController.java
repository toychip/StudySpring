package mvc.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j// 이제 이렇게 사용 불가
@Controller
public class ErrorPageController {  // error 페이지가 생겼을 때 보다 더 이쁜 페이지를 보여주기 위한 Controller


    //RequestDispatcher 상수로 정의되어 있음
    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";


    @RequestMapping("/error-page/404")  // post던 get이던 한 번에 처리하기 위해 RequestMapping을 사용함
    public String errorPage404(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 404");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")  // post던 get이던 한 번에 처리하기 위해 RequestMapping을 사용함
    public String errorPage500(HttpServletRequest request, HttpServletResponse response) {
        log.info("error-page 500");
        printErrorInfo(request);
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> errorPage500api(
            HttpServletRequest request, HttpServletResponse response) {

        log.info("APi errorPage 500");

        Map<String, Object> result = new HashMap<>();
        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status", request.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());
        // http 상태 코드
        Integer satusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(satusCode));
    }

    private void printErrorInfo(HttpServletRequest request) {
        log.info("ERROR_EXCEPTION{}", request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE{}" , request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE{}", request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI{}", request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME{}", request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE{}", request.getAttribute(ERROR_STATUS_CODE));
        log.info("DispatcherType={}", request.getDispatcherType());
    }
}
