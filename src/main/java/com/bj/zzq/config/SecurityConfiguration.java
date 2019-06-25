package com.bj.zzq.config;

import com.github.pagehelper.PageHelper;
import freemarker.template.TemplateModelException;
import lombok.Setter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

@Configuration
@ConditionalOnWebApplication
@Setter
public class SecurityConfiguration {
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
        map.put(logoutUrl, "logout");
        map.put("/admin/**", "authc");
        map.put("/**", "anon");
        HashMap<String, Filter> filterHashMap = new HashMap<>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl(loginUrl);
        filterHashMap.put("logout", logoutFilter);
        filterHashMap.put("authc", new AdminAuthenticationFilter());
        shiroFilter.setFilters(filterHashMap);
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
    //todo:shiro缓存管理
    @Bean
    public SecurityManager securityManager(AdminShiroRealm adminShiroRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(adminShiroRealm);
        return securityManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //超时时间为3个小时，单位-毫秒
        sessionManager.setGlobalSessionTimeout(10800000);
        return sessionManager;
    }

    @Bean
    public AdminShiroRealm userShiroRealm(CredentialsMatcher credentialsMatcher) {
        AdminShiroRealm shiroRealm = new AdminShiroRealm();
        shiroRealm.setCredentialsMatcher(credentialsMatcher);
        return shiroRealm;
    }

    @Bean
    public UserCredentialsMatcher userCredentialsMatcher() {
        return new UserCredentialsMatcher();
    }


    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean() {
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("proxyFilter");
        bean.addInitParameter("targetFilterLifecycle", "true");
        bean.addUrlPatterns("/*");
//        bean.addUrlPatterns("/admin/**","/front/**");
        return bean;
    }

}
