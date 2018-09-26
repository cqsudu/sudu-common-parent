package com.ledo.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:15
 */
public class TypeConversionException extends RuntimeException {

    /** 版本号 */
    private static final long serialVersionUID = -7926442618855461965L;

    public TypeConversionException() {
        super();
    }

    public TypeConversionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TypeConversionException(String msg) {
        super(msg);
    }

    public TypeConversionException(Throwable cause) {
        super(cause);
    }
}