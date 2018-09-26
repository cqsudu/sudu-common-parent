package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:13
 */
@Slf4j
public abstract class ArrayTypeConverterSupport<T> extends AbstractTypeConverter<T> {

    private TypeConverterManager typeConverterManager;

    public ArrayTypeConverterSupport(TypeConverterManager typeConverterManager) {
        this.typeConverterManager = typeConverterManager;
    }

    @SuppressWarnings("unchecked")
    public static Object arrayValue(
            Class<? extends Object> toType, Object value, TypeConverterManager typeConverterManager) {
        Object result = null;
        Class<? extends Object> sourceType = value.getClass();
        if (sourceType.isArray() && toType.isArray()) {
            Class<?> componentType = toType.getComponentType();
            int length = Array.getLength(value);
            result = Array.newInstance(componentType, length);
            TypeConverter<Object> typeConversion =
                    (TypeConverter<Object>)
                            typeConverterManager.getTypeConverter(sourceType.getComponentType(), componentType);
            if (typeConversion == null) {
                return value;
            }
            for (int i = 0; i < length; i++) {
                Array.set(result, i, typeConversion.convert(Array.get(value, i), componentType));
            }
        } else if (Collection.class.isAssignableFrom(sourceType) && toType.isArray()) {
            Class<?> componentType = toType.getComponentType();
            int length = ((Collection<?>) value).size();
            result = Array.newInstance(componentType, length);
            int i = 0;
            for (Object o : (Collection<?>) value) {
                Array.set(result, i, conver(typeConverterManager, o, toType));
                i++;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private static Object conver(
            TypeConverterManager typeConverterManager, Object source, Class<?> targetType) {
        if (source == null) {
            return null;
        }
        TypeConverter<?> typeConverter =
                typeConverterManager.getTypeConverter(source.getClass(), targetType);
        if (typeConverter == null) {
            return source;
        }
        return typeConverter.convert(source, (Class) targetType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convert(Object value, Class<? extends T> toType) {
        try {
            return (T) arrayValue(toType, value, this.typeConverterManager);
        } catch (Exception e) {
            throw new TypeConversionException(e);
        }
    }
}
