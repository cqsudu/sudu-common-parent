package com.ledo.core.exceptions;

public interface BaseExceptionConstants {
    interface StartCode{
        long COMMON_EXCEPTION_START_CODE = 10000; //公共错误code开始10000结束于11999
        long OAUTH_EXCEPTION_START_CODE = 11000; //oauth错误code开始11000结束于11999
        long SYSUSER_EXCEPTION_START_CODE = 12000; //SysUser错误code开始12000结束于12999
        long USER_EXCEPTION_START_CODE = 13000; //User错误code开始13000结束于13999
        long WEIXIN_EXCEPTION_START_CODE = 14000; //Weixin错误code开始14000结束于14999
        long VALIDATION_EXCEPTION_START_CODE = 14000; //VALIDATION错误code开始15000结束于15999

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
