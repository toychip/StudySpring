package com.example.userservice.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;
}
