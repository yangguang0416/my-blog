package com.xiong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.pojo.Post;
import com.xiong.pojo.vo.PostIdParam;
import com.xiong.pojo.vo.UserIdParam;
import com.xiong.utils.R;

import java.util.List;

public interface PostService extends IService<Post> {

    R searchPostById(Integer id);
    R creatPost(Post post);
    List<Post> searchUserPostById(Integer uid,Integer flag);
    R updatePostById(Integer id ,Post post);
    R deletePostById(Integer id);



}
