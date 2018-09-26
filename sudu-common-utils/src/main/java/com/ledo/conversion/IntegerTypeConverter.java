package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * {@link Integer}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class IntegerTypeConverter extends AbstractTypeConverter<Integer> {
  @Override
  public Class<Integer> getTargetType() {
    return Integer.class;
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
  public Integer convert(Object value, Class<? extends Integer> toType) {
    try {
      if (value == null) {
        return null;
      }
      return Integer.valueOf(IntTypeConverter.intValue(value));
    } catch (NumberFormatException e) {
      throw new TypeConversionException(e);
    }
  }
}
