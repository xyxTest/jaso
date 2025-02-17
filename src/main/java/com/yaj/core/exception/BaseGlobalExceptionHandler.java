package com.yaj.core.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.yaj.common.exception.BusinessExceptionErrorEnum;
import com.yaj.common.response.ResponseData;
import com.yaj.core.log.tools.LogHelper;

import javax.servlet.http.HttpServletResponse;

public class BaseGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @SuppressWarnings("deprecation")
    @ExceptionHandler(BaseBusinessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    protected ResponseData<Object> exceptionHandler(BaseBusinessException e, HttpServletResponse response) {
        ResponseData<Object> rd;
        try {
            rd = new ResponseData<Object>();
        } catch (BaseBusinessException e1) {
            return new ResponseData<Object>(e.getErrorCode(), e.getErrorMessage());
        }
        rd.setCode(e.getErrorCode());
        rd.setMessage(e.getErrorMessage());
        if (e.getExtraMessage() != null) {
            rd.setData(e.getExtraMessage());
        }
        LogHelper.log(BaseGlobalExceptionHandler.class, e);
        return rd;
    }

    @SuppressWarnings("deprecation")
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    protected ResponseData<String> exceptionHandler(Exception e, HttpServletResponse response) {
        LogHelper.log(BaseGlobalExceptionHandler.class, "[System Exception] ", e);
        return new ResponseData<String>(BusinessExceptionErrorEnum.SYSTEM_ERROR.getCode(), BusinessExceptionErrorEnum.SYSTEM_ERROR.getMessage());
    }

}
