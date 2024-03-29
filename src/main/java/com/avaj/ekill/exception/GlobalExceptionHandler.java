package com.avaj.ekill.exception;

import com.avaj.ekill.result.CodeMsg;
import com.avaj.ekill.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHamdler(HttpServletRequest request,Exception e) {
        e.printStackTrace();
        System.out.println("-----------------------------------------------------------------------我抓到你了");
        if (e instanceof GlobalException) {
            System.out.println("-----------------------------------------------------------------------我抓到你了111");
            GlobalException ex = (GlobalException)e;
            return Result.error(ex.getCodeMsg());
        } else if (e instanceof BindException) {
            System.out.println("-----------------------------------------------------------------------我抓到你了222");
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            System.out.println("-----------------------------------------------------------------------我抓到你了333");
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
