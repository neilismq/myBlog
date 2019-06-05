package com.bj.zzq.config;

import org.springframework.boot.autoconfigure.web.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/5
 * @Description:
 */
@Controller
public class ErrorPageHandler implements ErrorController {

    /**
     * 获取错误路径
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        return "/error";
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == 403){
            return "/error/403";
        }else if(statusCode == 404){
            return "/error/404";
        }else{
            return "/error/500";
        }
    }

}
