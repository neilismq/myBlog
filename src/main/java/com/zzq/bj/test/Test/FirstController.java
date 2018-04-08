package com.zzq.bj.test.Test;

import com.zzq.bj.dao.OrmDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zzq on 2018/3/9.
 */
@Controller
@RequestMapping("aaa" )
public class FirstController {
    private OrmDao ormDao;

    public OrmDao getOrmDao() {
        return ormDao;
    }
    @Resource
    public void setOrmDao(OrmDao ormDao) {
        this.ormDao = ormDao;
    }

    @RequestMapping("bbb")
    public ModelAndView registerUser(){
        ModelAndView index = new ModelAndView("login");
//        List<Map<String, Object>> maps = ormDao.queryForList("select * from comment", new Object[]{});
//        System.out.println(maps);
        return index;
    }
    @ResponseBody
    @RequestMapping("insertContent")
    public String insertMarkDownText(String content,HttpServletResponse response){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //ormDao.save("insert into article values(?,?)",new Object[]{uuid,content});
        //System.out.println(content);
        Map map=ormDao.queryForMap("select * from article where id='a4171355750141f9b418b912ea950f44'", null);
        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        return  map.get("content").toString();

    }

}
