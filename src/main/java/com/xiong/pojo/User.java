package com.xiong.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import java.io.Serializable;

@Data
@TableName("`user`")
public class User implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(value = "user_id")
    private Integer userId;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    @Email()
    private String email;

    @TableField(value = "created")
    private String created;

    @TableField(value = "last_modified")
    private String lastModified;

}
