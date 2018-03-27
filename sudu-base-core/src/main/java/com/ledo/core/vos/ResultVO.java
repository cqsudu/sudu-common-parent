package com.ledo.core.vos;

import java.io.Serializable;

/**
 * 用于对于单个的返回对象
 * <p>
 * 比如返回一个String、int等类型并不是一个json对象，为避免返回非json对象的情况一律返回对象
 * </p>
 * Created by konghang
 */
public class ResultVO <T> implements Serializable {

    private T result;

    public static <T> ResultVO<T> build(T value) {
        return new ResultVO<T>().setResult(value);
    }

    /**
     * 返回值
     * @return
     */
    public T getResult() {
        return result;
    }


    /**
     * 设定值
     * @param result
     * @return
     */
    public ResultVO<T> setResult(T result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultVO<?> resultVO = (ResultVO<?>) o;

        return !(result != null ? !result.equals(resultVO.result) : resultVO.result != null);

    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }
}
