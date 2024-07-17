package com.xiong.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiong.mapper.UserMapper;
import com.xiong.pojo.User;
import com.xiong.pojo.vo.UserCheckParam;
import com.xiong.pojo.vo.UserLoginParam;
import com.xiong.service.UserService;
import com.xiong.utils.MD5Util;
import com.xiong.utils.R;
import com.xiong.utils.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public R register(User user) {
        //检查账号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        //数据库查询 (查询数量 >0 存在)
        Integer total = userMapper.selectCount(queryWrapper);
        if (total > 0){
            log.info("UserServiceImpl.register业务结束,结果{}","账号存在，不可注册!");
            return R.fail("账号已经存在,不可重复注册!");
        }

        //密码加密 (MD5) + 加盐处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);

        //数据库插入
        int rows = userMapper.insert(user);
        if (rows == 0){
            log.info("UserServiceImpl.register业务结束,结果{}","数据插入失败,注册失败!");
            return R.fail("注册失败,请稍后再试!");
        }

        log.info("UserServiceImpl.register业务结束,结果{}","注册成功!");
        return R.ok("注册成功");
    }

    @Override
    public R login(UserLoginParam userLoginParam, HttpSession session) {
        //密码加密 (MD5) + 加盐处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);
        userLoginParam.setPassword(newPwd);

        //数据库查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userLoginParam.getUsername());
        queryWrapper.eq("password",newPwd);

        User user = userMapper.selectOne(queryWrapper);

        //结果
        if (user == null){
            log.info("UserServiceImpl.login业务结束,结果{}","账号密码错误!");
            return R.fail("账号或密码错误!");
        }

        log.info("UserServiceImpl.login业务结束,结果{}","登录成功!");
        session.setAttribute(UserConstants.USER_LOGIN,user);

        //不反回 密码
        user.setPassword(null);
        return R.ok("登录成功",user);
    }

    @Override
    public R getCurrentUser(HttpSession session) {
        User user = (User) session.getAttribute(UserConstants.USER_LOGIN);
        return R.ok("获取用户信息成功！",user);
    }

}
