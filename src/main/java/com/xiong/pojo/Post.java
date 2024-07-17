package com.xiong.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@TableName("`post`")
public class Post implements Serializable {

    public static final Long serialVersionUID = 1L;


    @TableId(value = "post_id")
    private Integer postId;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "created")
    private String created;

    @TableField(value = "last_modified")
    private String lastModified;
}
