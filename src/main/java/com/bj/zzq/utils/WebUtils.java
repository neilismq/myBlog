package com.bj.zzq.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Utils - Web
 *
 * @author SHOP++ Team
 * @version 6.1
 */
public final class WebUtils {


    /**
     * 不可实例化
     */
    private WebUtils() {
    }

    /**
     * 获取HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes != null && requestAttributes instanceof ServletRequestAttributes ? ((ServletRequestAttributes) requestAttributes).getRequest() : null;
    }

    /**
     * 获取HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return requestAttributes != null && requestAttributes instanceof ServletRequestAttributes ? ((ServletRequestAttributes) requestAttributes).getResponse() : null;
    }

    /**
     * 判断是否为AJAX请求
     *
     * @param request HttpServletRequest
     * @return 是否为AJAX请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        Assert.notNull(request, "[Assertion failed] - request is required; it must not be null");

        return StringUtils.equalsIgnoreCase(request.getHeader("X-Requested-With"), "XMLHttpRequest");
    }


    /**
     * 重定向
     *
     * @param request          HttpServletRequest
     * @param response         HttpServletResponse
     * @param url              URL
     * @param contextRelative  是否相对上下文路径
     * @param http10Compatible 是否兼容HTTP1.0
     */
    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url, boolean contextRelative, boolean http10Compatible) {
        Assert.notNull(request, "[Assertion failed] - request is required; it must not be null");
        Assert.notNull(response, "[Assertion failed] - response is required; it must not be null");
        Assert.hasText(url, "[Assertion failed] - url must have text; it must not be null, empty, or blank");

        StringBuilder targetUrl = new StringBuilder();
        if (contextRelative && url.startsWith("/")) {
            targetUrl.append(request.getContextPath());
        }
        targetUrl.append(url);
        String encodedRedirectURL = response.encodeRedirectURL(String.valueOf(targetUrl));
        if (http10Compatible) {
            try {
                response.sendRedirect(encodedRedirectURL);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        } else {
            response.setStatus(303);
            response.setHeader("Location", encodedRedirectURL);
        }
    }

    /**
     * 重定向
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param url      URL
     */
    public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) {
        sendRedirect(request, response, url, true, true);
    }


}