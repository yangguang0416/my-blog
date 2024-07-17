package com.xiong.config;

import com.xiong.utils.UserConstants;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();

//        filterMap.put("/api/auth/login","anon");
//        filterMap.put("/api/auth/register","anon");
//        filterMap.put("/api/auth/me","authc");
//        filterMap.put("/api/posts/**","authc");
//        //授权
//        filterMap.put("/api/posts/**","perms[user:post]");
//        //添加过滤器
//        bean.setFilterChainDefinitionMap(filterMap);
//        bean.setLoginUrl("/api/auth/login");
//        bean.setUnauthorizedUrl("/api/auth/login");
        return bean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


}
