package com.xiong.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiong.pojo.Post;
import com.xiong.pojo.vo.PostIdParam;
import com.xiong.pojo.vo.UserIdParam;
import com.xiong.pojo.vo.UserLoginParam;
import com.xiong.service.PostService;

import com.xiong.utils.R;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("posts")
    public R creatPost(@RequestBody @Validated Post post, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("请求参数有误!");
        }
        return postService.creatPost(post);
    }


    @GetMapping("posts")
    public R searchUserPostById(Integer uid,Integer flag) {
        if (uid<=0) {
            return R.fail("参数错误!通过用户ID获取文章失败");
        }
        //后面传参
        Integer pageNum = 1;
        Integer pageSize = 10;

        PageHelper.startPage(pageNum,pageSize);

        List<Post> posts = postService.searchUserPostById(uid,flag);
        if (posts.size() == 0){
            return R.fail("查询失败或没有查到文章");
        }
        PageInfo<Post> pageInfo=new PageInfo<>(posts);
        return R.ok("文章查询成功",pageInfo);
    }

    @GetMapping("posts/{id}")
    public R searchPostById(@PathVariable Integer id) {

        if (id <= 0) {
            return R.fail("参数错误!通过文章ID获取文章失败");
        }
        return postService.searchPostById(id);
    }

    @PutMapping("posts/{id}")
    public R updatePostById(@PathVariable Integer id,@RequestBody @Validated Post post, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数错误，不可更新");
        }
        return postService.updatePostById(id ,post);
    }

    @DeleteMapping("posts/{id}")
    public R deletePostById(@PathVariable Integer id) {
        if (id <= 0) {
            return R.fail("参数错误，不可删除");
        }
        return postService.deletePostById(id);
    }


}
