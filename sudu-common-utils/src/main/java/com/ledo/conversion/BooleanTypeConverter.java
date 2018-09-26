package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * {@link Boolean}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class BooleanTypeConverter extends AbstractTypeConverter<Boolean> {

  @Override
  public Class<Boolean> getTargetType() {
    return Boolean.class;
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
  public Boolean convert(Object value, Class<? extends Boolean> toType) {
    if (value == null) {
      return null;
    }
    return BooleanPrimitiveTypeConverter.booleanValue(value) ? Boolean.TRUE : Boolean.FALSE;
  }
}
