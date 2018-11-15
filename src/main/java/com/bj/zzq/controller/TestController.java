package com.bj.zzq.controller;

import com.bj.zzq.dao.Test;
import com.bj.zzq.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/q")
public class TestController {

    @Autowired
    private TestService testService;
    @RequestMapping("/a")
    public Object test(){
        List<Integer> test = testService.getData();
        return test;
    }
}
