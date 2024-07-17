package com.xiong.Interceptor;

import com.xiong.pojo.User;
import com.xiong.utils.R;
import com.xiong.utils.UserConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User user = (User) request.getSession().getAttribute(UserConstants.USER_LOGIN);
        if (user == null){
            request.setAttribute("msg", "没有权限，请先登陆");
            request.getRequestDispatcher("/api/auth/login").forward(request, response);
            log.info("用户未登录");
            return false;
        }
        return true;
    }

}
