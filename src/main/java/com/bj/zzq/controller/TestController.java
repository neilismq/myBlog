package com.bj.zzq.controller;

import com.bj.zzq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping
public class TestController {


    @RequestMapping("/")
    public String test() {
        /**
         * 此处相当于又发了一次，http://localhost:8888/index.html的请求，然后映射到静态页面/static/index.html
         */
        return "/index.html";
    }

}
