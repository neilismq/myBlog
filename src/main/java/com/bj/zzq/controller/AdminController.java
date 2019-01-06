package com.bj.zzq.controller;

import com.bj.zzq.dao.UserDao;
import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.model.AdminEntityExample;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequestMapping
@Controller
public class AdminController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(username)) {
            return "账号不能为空";
        }
        if (StringUtils.isBlank(password)) {
            return "密码不能为空";
        }
        AdminEntityExample example = new AdminEntityExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<AdminEntity> adminEntities = userDao.selectUserEntityByExample(example);
        if (adminEntities != null) {
            AdminEntity adminEntity = adminEntities.get(0);
            if (password.equals(adminEntity.getPassword())) {
                //todo:根据用户名密码生成token,放到cookie中，然后重定向到首页
                String tokenOrigin = username + "fdsfds$&*#$^3hjkdshfds" + password;
                String token = MD5Encoder.encode(tokenOrigin.getBytes("UTF-8"));

            }
        }
        return null;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "blog/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "admin/admin_template";
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "aricle_manage";
    }
    @RequestMapping(value = "/articleManage", method = RequestMethod.GET)
    public String partial() {
        return "admin/article_manage";
    }
}
