package WAS.itemservice.domain.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long id; // db에 저장되는 Index

    @NotEmpty
    @Email
    private String loginId; // 사용자가 로그인할때 사용하는 id

    @NotEmpty
    private String name; // 사용자 이름

//    @NotEmpty
//    private Integer phoneNumber;

    @NotEmpty
    private String password;

}
