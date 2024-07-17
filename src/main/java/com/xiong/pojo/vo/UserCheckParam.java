package com.xiong.pojo.vo;


import lombok.Data;

import javax.validation.constraints.NotBlank;

//检测账号不为null实体类
@Data
// 使用 JSR303 校验 前端传递的参数
public class UserCheckParam {

    //字符串不允许为null
    @NotBlank
    private String userName;
}
