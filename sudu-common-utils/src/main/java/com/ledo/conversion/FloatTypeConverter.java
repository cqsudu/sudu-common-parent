package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * {@link Float}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class FloatTypeConverter extends AbstractTypeConverter<Float> {

  @Override
  public Class<Float> getTargetType() {
    return Float.class;
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
  public Float convert(Object value, Class<? extends Float> toType) {
    try {
      if (value == null) {
        return null;
      }
      return Float.valueOf(FloatPrimitiveTypeConverter.floatcharValue(value));
    } catch (NumberFormatException e) {
      throw new TypeConversionException(e);
    }
  }
}
