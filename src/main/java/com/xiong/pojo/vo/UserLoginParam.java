package com.xiong.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;


//登录实体类
@Data
public class UserLoginParam {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
