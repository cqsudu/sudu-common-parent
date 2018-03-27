package com.ledo.core.exceptions;

public interface BaseExceptionConstants {
    interface StartCode{
        long COMMON_EXCEPTION_START_CODE = 10000; //公共错误code开始10000结束于11999
        long OAUTH_EXCEPTION_START_CODE = 11000; //oauth错误code开始11000结束于11999
    }

    long getCode();
    String getMessage();

    default ServiceException build(){
        return ServiceException.build(this);
    }
    default ServiceException build(Throwable cause){
        return ServiceException.build(this,cause);
    }

}
