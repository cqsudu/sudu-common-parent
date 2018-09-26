package com.ledo.conversion;

import com.google.common.collect.Lists;
import com.ledo.utils.PrimitiveUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:14
 */
@Slf4j
public abstract class AbstractTypeConverter<T> implements TypeConverter<T> {

    @Override
    public List<Class<?>> getSupportedSourceTypes() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.addAll(Lists.newArrayList(PrimitiveUtils.getAllPrimitiveArrayClasses()));
        classes.addAll(Lists.newArrayList(PrimitiveUtils.getAllWrapperArrayClasses()));
        return classes;
    }

    /**
     * 将给定的值转换为给定的类型。该方法默认调用 {@link #convert(Map, Object, Class)} 完成处理。
     *
     * @param parameterMap 在转换过程中需要传递的参数。
     * @param m 在转换过程中需要传入的构造方法、方法或者字段。
     * @param value 需要转换的对象。
     * @param toType 需要转换到对象的类型。
     * @return 转换后的value。
     */
    @Override
    public <M extends AccessibleObject & Member> T convert(
            Map<? extends Object, ? extends Object> parameterMap,
            M m,
            Object value,
            Class<? extends T> toType) {
        return convert(parameterMap, value, toType);
    }

    /**
     * 将给定的值转换为给定的类型。该方法默认调用 {@link #convert(Object, Class)} 完成处理。
     *
     * @param parameterMap 在转换过程中需要传递的参数。
     * @param value 需要转换的对象。
     * @param toType 需要转换到对象的类型。
     * @return 转换后的value。
     */
    @Override
    public T convert(
            Map<? extends Object, ? extends Object> parameterMap,
            Object value,
            Class<? extends T> toType) {
        return convert(value, toType);
    }

    /**
     * 将给定的值转换为给定的类型。
     *
     * @param value 需要转换的对象。
     * @param toType 需要转换到对象的类型。
     * @return 转换后的value。
     */
    @Override
    public abstract T convert(Object value, Class<? extends T> toType);
}