package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * {@link Long}的类型转换器。
 *
 */
public class LongTypeConverter extends AbstractTypeConverter<Long> {

  @Override
  public Class<Long> getTargetType() {
    return Long.class;
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
      if (value == null) {
        return null;
      }
      return Long.valueOf(LongPrimitiveTypeConverter.longValue(value));
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
