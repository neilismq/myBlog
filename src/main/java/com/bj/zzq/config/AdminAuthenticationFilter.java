package com.bj.zzq.config;

import com.bj.zzq.utils.CommonResponse;
import com.bj.zzq.utils.JsonUtils;
import com.bj.zzq.utils.WebUtils;
import lombok.Setter;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/4
 * @Description:
 */

public class AdminAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * 是否允许访问（默认检测是否登录，没有登录走onAccessDenied方法）
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 拒绝访问处理，return false=拒绝访问
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                //allow them to see the login page ;)
                return true;
            }
        } else {
            //如果是ajax请求，直接拦截，不能重定向
            if (WebUtils.isAjaxRequest(request)) {
                response.setContentType("application/json");
                response.setStatus(HttpStatus.OK.value());
                response.setCharacterEncoding("utf-8");
                JsonUtils.writeValue(response.getWriter(), CommonResponse.fail("请登录后再操作"));
                return false;
            }
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    /**
     * 创建登录令牌
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        return super.createToken(request, response);
    }

    /**
     * 登录成功后处理
     *
     * @param token
     * @param subject
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (WebUtils.isAjaxRequest(request)) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding("utf-8");
            HashMap<String, String> body = new HashMap<>();
            body.put("adminIndexUrl", "admin/index");
            JsonUtils.writeValue(response.getWriter(), CommonResponse.success().setBody(body));
            return false;
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    /**
     * 登陆失败后处理
     *
     * @param authenticationToken
     * @param authenticationException
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken authenticationToken, AuthenticationException authenticationException, ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (WebUtils.isAjaxRequest(request)) {
            String errorMessage;
            if (authenticationException instanceof IncorrectCredentialsException) {
                errorMessage = "用户名密码不正确！";
            } else if(authenticationException instanceof UnknownAccountException){
                errorMessage = "用户不存在！";
            }else {
                errorMessage = "登录时出现未知错误！";
            }
            try {
                response.setContentType("application/json");
                response.setStatus(HttpStatus.OK.value());
                response.setCharacterEncoding("utf-8");
                JsonUtils.writeValue(response.getWriter(), CommonResponse.fail(errorMessage));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("登录时未知异常!");
            }
            return false;
        }
        return super.onLoginFailure(authenticationToken, authenticationException, servletRequest, servletResponse);
    }


}
