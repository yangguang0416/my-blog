package com.xiong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiong.pojo.User;
import com.xiong.pojo.vo.UserCheckParam;
import com.xiong.pojo.vo.UserLoginParam;
import com.xiong.utils.R;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {
    R register(User user);

    R login(UserLoginParam userLoginParam, HttpSession session);

    R getCurrentUser(HttpSession session);
}
