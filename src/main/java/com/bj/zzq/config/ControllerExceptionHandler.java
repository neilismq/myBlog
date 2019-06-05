package com.bj.zzq.config;

import com.bj.zzq.utils.CommonResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/5
 * @Description: Controller层全局异常处理
 */
@ControllerAdvice(value = "com.bj.zzq.controller")
public class ControllerExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public CommonResponse exceptionHandler(Exception e) {
        return CommonResponse.fail("服务器内部错误，具体请登录服务器上查看日志");
    }

}
