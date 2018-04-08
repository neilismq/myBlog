package com.zzq.bj.login;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.MD5;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zzq on 2018/4/7.
 */
@Controller
@RequestMapping("login" )
public class LoginCtr {
    @RequestMapping("login")
    public ModelAndView login(HttpSession session, HttpServletResponse response, HttpServletRequest request){
        ModelAndView index = new ModelAndView("login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Cookie pageToken = new Cookie("pageToken",  username+password);
        pageToken.setPath("/");
        response.addCookie(pageToken);
        return index;
    }
}
