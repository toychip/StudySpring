package review.exception.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import review.exception.api.dto.MemberDto;
import review.exception.custom_exception.BadRequestException;
import review.exception.custom_exception.UserException;

@Slf4j
@RestController
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }
        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-except")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "나의 이름은! " + id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, // 404
                "잘못된 요청 오류입니다. ResponseStatusException 사용",
                new IllegalArgumentException());
    }

    @GetMapping("/api/default/handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }
}
