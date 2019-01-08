package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.dao.UserDao;
import com.bj.zzq.model.AdminEntity;
import com.bj.zzq.model.AdminEntityExample;
import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ArticleDao articleDao;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
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

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "blog/login";
    }

    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "admin/template";
    }


    @RequestMapping(value = "/article/add", method = RequestMethod.GET)
    public String articleAdd() {
        return "admin/articleManage/add";
    }

    @RequestMapping(value = "/article/query")
    public String articleQuery(Map map, PageInfo pageInfo) {
        if (pageInfo.getPageSize() == 0) {
            pageInfo.setNavigatePages(5);
            pageInfo.setPageSize(10);
            pageInfo.setPageNum(1);
        }
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        //分页合理化
        page.setReasonable(true);

        ArticleEntityExample articleEntityExample = new ArticleEntityExample();
        articleEntityExample.setOrderByClause("create_time desc");
        articleDao.selectArticleByExample(articleEntityExample);

        PageInfo info = new PageInfo(page, pageInfo.getNavigatePages());
        BeanUtils.copyProperties(info, pageInfo);
        map.put("pageInfo", pageInfo);
        return "admin/articleManage/query";
    }
}
