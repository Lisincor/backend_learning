package com.projectprac.tlias_prac.exception;


import com.projectprac.tlias_prac.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
