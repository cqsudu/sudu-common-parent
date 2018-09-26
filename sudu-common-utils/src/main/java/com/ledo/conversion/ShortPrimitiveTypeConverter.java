package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * short 的类型转换器。
 *
 */
public class ShortPrimitiveTypeConverter extends AbstractTypeConverter<Short> {

  public static short shortValue(Object value) throws NumberFormatException {
    return (short) IntTypeConverter.intValue(value);
  }

  @Override
  public Class<Short> getTargetType() {
    return Short.TYPE;
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
  public Short convert(Object value, Class<? extends Short> toType) {
    try {
      return shortValue(value);
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
