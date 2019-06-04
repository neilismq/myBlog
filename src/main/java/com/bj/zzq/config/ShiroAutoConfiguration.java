package com.bj.zzq.config;

import lombok.Setter;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ConditionalOnWebApplication
@Setter
public class ShiroAutoConfiguration {
    @Value("${shiro.loginUrl}")
    private String loginUrl;

    @Value("${shiro.logoutUrl}")
    private String logoutUrl;

    @Value("${shiro.loginSuccessUrl}")
    private String loginSuccessUrl;

    @Value("${shiro.unauthorizedUrl}")
    private String unauthorizedUrl;


    /**
     * 启用shiro过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //登陆url
        shiroFilter.setLoginUrl(loginUrl);
        //登陆成功跳转页面
        shiroFilter.setSuccessUrl(loginSuccessUrl);
        //未授权的页面
        shiroFilter.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilter.setSecurityManager(securityManager);
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/**/*.js", "anon");
        map.put("/**/*.css", "anon");
        map.put("/**/*.ico", "anon");
        map.put("/**/*.jpg", "anon");
        map.put("/**/*.png", "anon");
        map.put("/**/*.map", "anon");
        map.put("/**/*.gif", "anon");
        map.put("/front/**", "anon");
        map.put("/admin/logout", "logout");
        map.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(map);
        return shiroFilter;
    }


    //开启注解驱动, RequiresPermissions.class, RequiresRoles.class,RequiresUser.class, RequiresGuest.class, RequiresAuthentication.class
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(UserShiroRealm userShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userShiroRealm);
        return securityManager;
    }

    @Bean
    public UserShiroRealm userShiroRealm(CredentialsMatcher credentialsMatcher) {
        UserShiroRealm shiroRealm = new UserShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public UserCredentialsMatcher userCredentialsMatcher() {
        return new UserCredentialsMatcher();
    }
}
