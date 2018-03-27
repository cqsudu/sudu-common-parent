package com.ledo.spring.boot.exceptions;

import com.ledo.core.exceptions.ServiceException;
import com.ledo.core.exceptions.ServiceSysException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的全局异常处理handler
 */
public abstract class DefaultGlobalControllerExceptionHandler {

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 400
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Map handleServiceException(ServiceException ex) {
        logger.error(ex.getMessage(), ex);
        return ex.getErrorMap();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(ServiceSysException.class)
    @ResponseBody
    public Map handleServiceException(ServiceSysException ex) {
        logger.error(ex.getMessage(), ex);
        return ex.getErrorMap();
    }

//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 400
//    @ExceptionHandler(FeignServiceException.class)
//    @ResponseBody
//    public Map handleFeignServiceException(FeignServiceException ex) {
//        logger.error(ex.getMessage(), ex);
//        return ex.getErrorMap();
//    }
    
    // 通用异常的处理，返回500
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Map handlePageException(Throwable ex) {
        logger.error(ex.getMessage(), ex);
        Map<String, Object> map = new HashMap();
        map.put("code", ServiceSysException.CODE_1000_UNKNOWN_SYSTEM_ERROR);
        map.put("message", ex.getMessage());
        return map;
    }
}
