package com.bj.zzq.config;

import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/6
 * @Description:
 */
@Component("proxyFilter")
public class RequestContextFilter implements Filter {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String path = request.getServletContext().getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        try {
            configuration.setSharedVariable("base", basePath);
            request.setAttribute("baseUrl", basePath);
            chain.doFilter(request, response);
        } catch (TemplateModelException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

    }
}
