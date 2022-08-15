package com.tmy.studentmanage.controller.disposeexception;

import com.tmy.studentmanage.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 谭明扬
 * @version 8.0
 */
//作为springMvc的异常处理器
@RestControllerAdvice
public class DisposeException {
    @ExceptionHandler
    public Result doException(Exception e){
        e.printStackTrace();
        return new Result(false,"服务器故障请稍后再试");
    }
}
