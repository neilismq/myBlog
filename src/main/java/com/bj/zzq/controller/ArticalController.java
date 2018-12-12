package com.bj.zzq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class ArticalController {


    @RequestMapping("/")
    public String test() {
        /**
         * 此处相当于又发了一次，http://localhost:8888/index.html的请求，然后映射到静态页面/static/index.html
         */
        return "blog/index";
    }
    @RequestMapping("/archives")
    public String archives() {
        return "blog/archives";
    }

}
