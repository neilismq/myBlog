package com.bj.zzq.controller;

import com.bj.zzq.dao.ArticleDao;
import com.bj.zzq.dao.AdminDao;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.model.TagEntity;
import com.bj.zzq.model.TagEntityExample;
import com.bj.zzq.service.ArticleTagService;
import com.bj.zzq.service.TagService;
import com.bj.zzq.utils.CommonResponse;
import com.bj.zzq.utils.CommonUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
//@RequestMapping(value = "/admin-admin-admin123456")
public class AdminController {
    @Value(value = "${shiro.loginSuccessUrl}")
    private String adminIndexUrl;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleTagService articleTagService;

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "admin/login";
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
    public String articleAdd(ModelMap map) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tagEntities = tagService.selectByExample(tagEntityExample);
        map.put("tags", tagEntities);
        return "admin/articleManage/add";
    }

    @RequestMapping(value = "/tag/add", method = RequestMethod.GET)
    public String tagAdd(ModelMap mav) {
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.setOrderByClause("create_time desc");
        List<TagEntity> tags = tagService.selectByExample(tagEntityExample);
        mav.put("tags", tags);
        return "admin/tagManage/add";
    }

    @ResponseBody
    @RequestMapping(value = "/tag/add", method = RequestMethod.POST)
    public CommonResponse addTag(String tagName) {

        if (StringUtils.isBlank(tagName)) {
            return CommonResponse.fail("标签名称不能为空");
        }
        TagEntityExample example = new TagEntityExample();
        example.createCriteria().andNameEqualTo(tagName);
        List<TagEntity> tagEntities = tagService.selectByExample(example);
        if (tagEntities != null && tagEntities.size() > 0) {
            return CommonResponse.fail("标签已存在，请检查");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setId(CommonUtils.newUUID());
        tagEntity.setName(tagName);
        tagEntity.setCreateTime(new Date());
        tagService.insert(tagEntity);
        return CommonResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/tag/update", method = RequestMethod.POST)
    public CommonResponse updateTag(String tagId, String tagName) {

        if (StringUtils.isBlank(tagName)) {
            return CommonResponse.fail("标签名称不能为空");
        }
        TagEntityExample example = new TagEntityExample();
        example.createCriteria().andNameEqualTo(tagName);
        List<TagEntity> tagEntities = tagService.selectByExample(example);
        if (tagEntities != null && tagEntities.size() > 0) {
            return CommonResponse.fail("标签已存在，请检查");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagName);
        tagEntity.setCreateTime(new Date());
        TagEntityExample tagEntityExample = new TagEntityExample();
        tagEntityExample.createCriteria().andIdEqualTo(tagId);
        tagService.update(tagEntity, tagEntityExample);
        return CommonResponse.success();
    }

    @ResponseBody
    @RequestMapping(value = "/tag/delete", method = RequestMethod.POST)
    public CommonResponse deleteTag(String tagId) {

        if (StringUtils.isBlank(tagId)) {
            return CommonResponse.fail("标签id不能为空");
        }
        //删除中间表数据
        articleTagService.deleteByTagId(tagId);
        //删除tag
        tagService.deleteById(tagId);
        return CommonResponse.success();
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
