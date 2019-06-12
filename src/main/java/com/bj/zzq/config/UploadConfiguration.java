package com.bj.zzq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.servlet.MultipartConfigElement;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/12
 * @Description: 静态文件访问目录
 */
@Configuration
public class UploadConfiguration extends WebMvcConfigurationSupport {
    @Value("${application.img.uploadPath}")
    private String imgUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/", "file:" +
                imgUploadPath);
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html/");
        registry.addResourceHandler("/plugins/**").addResourceLocations("classpath:/static/plugins/");
    }

    /**
     * 设置上传图片路径
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        multipartConfigFactory.setLocation(imgUploadPath);
        return multipartConfigFactory.createMultipartConfig();
    }

}
