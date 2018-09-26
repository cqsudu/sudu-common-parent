package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * {@link Short}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class ShortTypeConverter extends AbstractTypeConverter<Short> {

  @Override
  public Class<Short> getTargetType() {
    return Short.class;
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
      if (value == null) {
        return null;
      }
      return Short.valueOf(ShortPrimitiveTypeConverter.shortValue(value));
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
