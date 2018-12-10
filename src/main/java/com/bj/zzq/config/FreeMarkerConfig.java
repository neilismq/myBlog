package com.bj.zzq.config;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 设置自定义模版继承标签
 */
@Configuration
public class FreeMarkerConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    freemarker.template.Configuration configuration;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("block", new BlockDirective());
        configuration.setSharedVariable("override", new OverrideDirective());
        configuration.setSharedVariable("extends", new ExtendsDirective());
    }
}