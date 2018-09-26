package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * {@link Double}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class DoubleTypeConverter extends AbstractTypeConverter<Double> {
  @Override
  public Class<Double> getTargetType() {
    return Double.class;
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
  public Double convert(Object value, Class<? extends Double> toType) {
    try {
      if (value == null) {
        return null;
      }
      return Double.valueOf(DoublePrimitiveTypeConverter.doubleValue(value));
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
