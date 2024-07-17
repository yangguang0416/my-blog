package com.xiong.controller;

import com.xiong.pojo.User;
import com.xiong.pojo.vo.UserCheckParam;
import com.xiong.pojo.vo.UserLoginParam;
import com.xiong.service.PostService;
import com.xiong.service.UserService;
import com.xiong.utils.R;
import com.xiong.utils.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result,HttpSession session) {
        if (result.hasErrors()) {
            //若存在异常,则传入参数不符合要求
            return R.fail("参数错误，不可登录!");
        }
        return userService.login(userLoginParam,session);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result){

        if (result.hasErrors()){
            //若存在异常,则传入参数不符合要求
            return R.fail("参数错误，不可注册!");
        }
        return userService.register(user);
    }

    @GetMapping("me")
    public R getCurrentUser(HttpSession session){
        if (session == null){
            return R.fail("当前未登录");
        }

        return userService.getCurrentUser(session);
    }




}


