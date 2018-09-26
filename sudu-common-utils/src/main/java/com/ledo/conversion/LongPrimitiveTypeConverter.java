package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * long 的类型转换器。
 *
 */
public class LongPrimitiveTypeConverter extends AbstractTypeConverter<Long> {

  public static long longValue(Object value) throws NumberFormatException {
    if (value == null) {
      return 0L;
    }
    Class<?> c = value.getClass();
    if (c.getSuperclass() == Number.class) {
      return ((Number) value).longValue();
    }
    if (c == Boolean.class) {
      return ((Boolean) value).booleanValue() ? 1 : 0;
    }
    if (c == Character.class) {
      return ((Character) value).charValue();
    }
    return Long.parseLong(StringTypeConverter.stringValue(value, true));
  }

  @Override
  public Class<Long> getTargetType() {
    return Long.TYPE;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    List<Class<?>> classes = super.getSupportedSourceTypes();
    classes.add(Object[].class);
    classes.add(Collection.class);
    classes.add(CharSequence.class);
    classes.add(CharSequence[].class);
    return classes;
  }

  @Override
  public Long convert(Object value, Class<? extends Long> toType) {
    try {
      return longValue(value);
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
