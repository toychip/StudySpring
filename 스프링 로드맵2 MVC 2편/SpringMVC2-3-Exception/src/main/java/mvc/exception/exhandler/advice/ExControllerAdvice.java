package mvc.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import mvc.exception.exception.UserException;
import mvc.exception.exhandler.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
// (annotations = RestController.class) 이렇게 지정 가능,
// (org.example.controllers) 이렇게 패키지 지정 가능
// 부모 클래스나 자식 클래스 등 직접 정해줄 수 있다.
// ControllerAdcive + ResponseBody
public class ExControllerAdvice {
//     @ExceptionHandler, @InitBinder 기능을 부여해주는 역할을 한다.
//    ControllerAdvice 대상을 지정하지 않으면 모든 컨트롤러의 적용된다. (글로벌 적용)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {       // RestController이므로 오류가 Json그대로 반환이 된다.
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 보통 여기서 어떤 예외인지 선언하면 자식까지 전부 잡아준다. 하지만 위가 아닌 것들은 서버 오류이므로 서버 오류코드로 내보낸다.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
}
