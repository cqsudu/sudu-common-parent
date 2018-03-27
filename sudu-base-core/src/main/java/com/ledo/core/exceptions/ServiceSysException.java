package com.ledo.core.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务系统异常
 * Created by konghang
 */
public class ServiceSysException extends RuntimeException {

    public static final long CODE_1000_UNKNOWN_SYSTEM_ERROR = 1000;

    private long code;

    public static ServiceSysException build(long code, String message, Throwable cause) {
        return new ServiceSysException(code, message, cause);
    }

    public static ServiceSysException build(String message, Throwable cause) {
        return build(CODE_1000_UNKNOWN_SYSTEM_ERROR, message, cause);
    }

    public static ServiceSysException build(Throwable cause) {
        return build(CODE_1000_UNKNOWN_SYSTEM_ERROR, "unknown error", cause);
    }

    public ServiceSysException(long code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceSysException(long code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceSysException(BaseExceptionConstants baseExceptionConstant) {
        super(baseExceptionConstant.getMessage());
        this.code = baseExceptionConstant.getCode();
    }

    public long getCode() {
        return code;
    }

    public ServiceSysException setCode(long code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "ServiceSysException{" +
                "code=" + code +
                '}';
    }

    public Map<String, Object> getErrorMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", this.code);
        map.put("message", this.getMessage());
        return map;
    }
}