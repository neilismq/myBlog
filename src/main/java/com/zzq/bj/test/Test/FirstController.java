package com.zzq.bj.test.Test;

import com.zzq.bj.dao.OrmDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zzq on 2018/3/9.
 */
@Controller()
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
        ModelAndView index = new ModelAndView("content");
//        List<Map<String, Object>> maps = ormDao.queryForList("select * from comment", new Object[]{});
//        System.out.println(maps);
        return index;
    }

}
