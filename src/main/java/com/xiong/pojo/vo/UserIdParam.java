package com.xiong.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserIdParam {

    @NotNull
    private Integer UserID;
}