package com.ledo.core.vos;

import java.io.Serializable;

/**
 * 错误VO, 所有出错的状况都抛出这个对象
 * Created by konghang
 */
public class ErrorVO implements Serializable {

    private static final long serialVersionUID = -8087205942082302627L;
    private long code;
    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorVO errorVO = (ErrorVO) o;

        return code == errorVO.code;

    }

    @Override
    public int hashCode() {
        return (int) (code ^ (code >>> 32));
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
