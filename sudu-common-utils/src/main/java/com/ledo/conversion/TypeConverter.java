package com.ledo.conversion;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:09
 */
public interface TypeConverter<T> {

    /**
     * 得到转换的目标类型（目标类型）。
     *
     * @return 转换的目标类型。
     */
    Class<T> getTargetType();

    /**
     * 得到支持转换的类型列表（源类型列表）。
     *
     * @return 支持转换的类型列表。
     */
    List<Class<?>> getSupportedSourceTypes();

    /**
     * 将给定的值转换为给定的类型。
     */
    <M extends AccessibleObject & Member> T convert(
            Map<? extends Object, ? extends Object> parameterMap,
            M m,
            Object value,
            Class<? extends T> toType);

    /**
     * 将给定的值转换为给定的类型。
     *
     * @param parameterMap 在转换过程中需要传递的参数。
     * @param value 需要转换的对象。
     * @param toType 需要转换到对象的类型。
     * @return 转换后的value。
     */
    T convert(
            Map<? extends Object, ? extends Object> parameterMap,
            Object value,
            Class<? extends T> toType);

    /**
     * 将给定的值转换为给定的类型。
     */
    T convert(Object value, Class<? extends T> toType);
}