package com.bj.zzq.config;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.model.SettingEntity;
import com.bj.zzq.service.ArticleService;
import com.bj.zzq.service.SettingService;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
