package com.ledo.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:46
 */
@Slf4j
public class ConverterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5811678321082505224L;

    private Class<?> sourceType;
    private Class<?> targetType;

    /**
     * 构造一个 ConverterNotFoundException 。
     *
     * @param sourceType 没有找到的类型转换器的源类型。
     * @param sourceType 没有找到的类型转换器的目标类型。
     */
    public ConverterNotFoundException(Class<?> sourceType, Class<?> targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    /**
     * 构造一个 ConverterNotFoundException 。
     *
     * @param message 详细消息。
     * @param sourceType 没有找到的类型转换器的源类型。
     * @param sourceType 没有找到的类型转换器的目标类型。
     */
    public ConverterNotFoundException(String message, Class<?> sourceType, Class<?> targetType) {
        super(message);
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    public Class<?> getSourceType() {
        return (Class<?>) this.sourceType;
    }

    public Class<?> getTargetType() {
        return this.targetType;
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        return getClass().getName()
                + " [sourceType="
                + getSourceType()
                + ", targetType="
                + getTargetType()
                + "]"
                + (message == null ? "" : message);
    }
}
