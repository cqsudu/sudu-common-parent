package com.ledo.conversion;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 09:53
 */
@Slf4j
public abstract class TypeConverterUtils {

    private static final TypeConverterManager TYPE_CONVERTER_MANAGER;

    static {
        TYPE_CONVERTER_MANAGER = new SimpleTypeConverterManager();
        SimpleTypeConverterManager.registerDefaultConverter(TYPE_CONVERTER_MANAGER);
    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(Object value, Class<T> toType) {
        return (T) convertValue(value, toType);
    }

    /**
     * 转换类型。该方法使用 {@link #getTypeConverterManager()} 得到的 类型转换器管理器 中注册的规则进行转换。
     *
     * @param value 要转换类型的值。
     * @param toType 要转换到类型的Class对象。
     * @return 转换后的value,如果转换失败，则返回原对象。
     * @see TypeConverter#convert(Object, Class)
     */
    @SuppressWarnings("unchecked")
    public static Object convertValue(Object value, Class<?> toType) {
        if (toType == null) {
            return value;
        }
        if (toType.isInstance(value)) {
            return value;
        }
        if (value != null) {
            if (toType.isArray()) {
                TypeConverter<Object> typeConverter = getArrayTypeConvert(value, toType);
                try {
                    return typeConverter == null
                            ? value
                            : typeConverter.convert(value, (Class<Object>) toType);
                } catch (Exception e) {
                    return value;
                }
            } else {
                TypeConverter<Object> typeConverter =
                        (TypeConverter<Object>)
                                TYPE_CONVERTER_MANAGER.getTypeConverter(value.getClass(), toType);
                try {
                    return typeConverter == null ? value : typeConverter.convert(value, toType);
                } catch (Exception e) {
                    return value;
                }
            }
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    private static TypeConverter<Object> getArrayTypeConvert(Object value, Class<?> toType) {
        TypeConverter<Object> typeConverter =
                (TypeConverter<Object>) TYPE_CONVERTER_MANAGER.getTypeConverter(value.getClass(), toType);
        if (typeConverter == null && !toType.getComponentType().isPrimitive()) {
            Class<?> clazz = Object[].class;
            typeConverter =
                    (TypeConverter<Object>) TYPE_CONVERTER_MANAGER.getTypeConverter(value.getClass(), clazz);
        }
        return typeConverter;
    }

    /**
     * 转换类型。该方法使用 {@link #getTypeConverterManager()} 得到的 类型转换器管理器 中注册的规则进行转换。
     *
     * @param parameterMap 在转换过程中需要传递的参数。
     * @param value 要转换类型的值。
     * @param toType 要转换到类型的Class对象。
     * @return 转换后的value,如果转换失败，则返回原对象。
     * @see TypeConverter#convert(Map, Object, Class)
     */
    @SuppressWarnings("unchecked")
    public static Object convertValue(
            Map<? extends Object, ? extends Object> parameterMap, Object value, Class<?> toType) {
        if (toType == null) {
            return value;
        }
        if (toType.isInstance(value)) {
            return value;
        }
        if (value != null) {
            if (toType.isArray()) {
                TypeConverter<Object> typeConverter = getArrayTypeConvert(value, toType);
                try {
                    return typeConverter == null
                            ? value
                            : typeConverter.convert(parameterMap, value, (Class<? extends Object[]>) toType);
                } catch (Exception e) {
                    return value;
                }
            } else {
                TypeConverter<Object> typeConverter =
                        (TypeConverter<Object>)
                                TYPE_CONVERTER_MANAGER.getTypeConverter(value.getClass(), toType);
                try {
                    return typeConverter == null ? value : typeConverter.convert(parameterMap, value, toType);
                } catch (Exception e) {
                    return value;
                }
            }
        }
        return value;
    }

    /**
     * 转换类型。该方法使用 {@link #getTypeConverterManager()} 得到的 类型转换器管理器 中注册的规则进行转换。
     *
     * @param parameterMap 在转换过程中需要传递的参数。
     * @param m 在转换过程中需要传入的构造方法、方法或者字段。
     * @param value 要转换类型的值。
     * @param toType 要转换到类型的Class对象。
     * @return 转换后的value,如果转换失败，则返回原对象。
     * @see TypeConverter#convert(Map, AccessibleObject, Object, Class)
     */
    @SuppressWarnings("unchecked")
    public static <M extends AccessibleObject & Member> Object convertValue(
            Map<? extends Object, ? extends Object> parameterMap, M m, Object value, Class<?> toType) {
        if (toType == null) {
            return value;
        }
        if (toType.isInstance(value)) {
            return value;
        }
        if (value != null) {
            if (toType.isArray()) {
                TypeConverter<Object> typeConverter = getArrayTypeConvert(value, toType);
                try {
                    return typeConverter == null
                            ? value
                            : typeConverter.convert(parameterMap, m, value, (Class<? extends Object[]>) toType);
                } catch (Exception e) {
                    return value;
                }
            } else {
                TypeConverter<Object> typeConverter =
                        (TypeConverter<Object>)
                                TYPE_CONVERTER_MANAGER.getTypeConverter(value.getClass(), toType);
                try {
                    return typeConverter == null
                            ? value
                            : typeConverter.convert(parameterMap, m, value, toType);
                } catch (Exception e) {
                    return value;
                }
            }
        }
        return value;
    }

    /**
     * @return 类型转换管理器 的单例。
     */
    public static final TypeConverterManager getTypeConverterManager() {
        return TYPE_CONVERTER_MANAGER;
    }
}