package com.xiong.config;

import cn.hutool.extra.mail.UserPassAuthenticator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiong.mapper.UserMapper;
import com.xiong.pojo.User;
import com.xiong.pojo.vo.UserLoginParam;
import com.xiong.service.UserService;
import com.xiong.utils.MD5Util;
import com.xiong.utils.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("*************授权*****************");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:post");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
