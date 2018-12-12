package com.bj.zzq.controller;

import com.bj.zzq.model.ArticleEntity;
import com.bj.zzq.model.ArticleEntityExample;
import com.bj.zzq.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping
public class ArticalController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/")
    public String test(Map map) {
        /**
         * 此处相当于又发了一次，http://localhost:8888/index.html的请求，然后映射到静态页面/static/index.html
         */
        ArticleEntityExample example = new ArticleEntityExample();
        example.setOrderByClause("update_time desc");
        List<ArticleEntity>  list = blogService.selectByExample(example);
        if (list != null && list.size() > 0) {
            ArticleEntity articleEntity = list.get(0);
            map.put("article",articleEntity);
        }
        return "blog/index";
    }

    @RequestMapping("/archives")
    public String archives() {
        return "blog/archives";
    }

}
