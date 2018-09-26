package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;
import com.ledo.utils.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * {@link List}的类型转换器。
 *
 */
public class ListTypeConverter extends AbstractTypeConverter<List<?>> {

  @SuppressWarnings("unchecked")
  public static <E extends List<?>> E listValue(Object value, Class<? extends E> listClassType) {
    List<Object> list = null;
    if ((Class<?>) listClassType == List.class) {
      // 使用 ArrayList 作为实现
      list = new ArrayList<Object>();
    } else { // 如果是具体的类则使用该类的类型
      try {
        list = (List<Object>) Reflections.createObject(listClassType);
      } catch (Exception e) {
        throw new TypeConversionException(e);
      }
    }
    list.addAll((List<Object>) value);
    return (E) list;
  }

  @Override
  public Class<List<?>> getTargetType() {
    Class<?> listClass = List.class;
    return (Class<List<?>>) listClass;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    return Arrays.asList(Collection.class, Object[].class);
  }

  @Override
  public List<?> convert(Object value, Class<? extends List<?>> toType) {
    return listValue(value, toType);
  }
}
