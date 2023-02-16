package mvcFeatures.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/mapping/users"))
public class MappingClassController {

//    --회원 관리 API--
//    회원 목록 조회: GET      /users
//    회원 등록: POST         /users
//    회원 조회: GET,         /users/{userId}
//    회원 수정: PATCH        /users/{userId}
//    회원 삭제: DELETE,      /users/{userId}

//    GET /mapping/users
    @GetMapping
    public String users(){
        return "get users";
    }

//    POST /mapping/users
    @PostMapping
    public String addUser(){
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId = " + userId;
    }
//    둘의 차이
//    @RequestParam     / 링크는 그대로
//    @PathVariable     /~~/~~ 입력을 받아서 그 값을 해당 값으로 설정함

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId = " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId = " + userId;
    }

}
