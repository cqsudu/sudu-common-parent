package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * boolean 的类型转换器。
 *
 */
public class BooleanPrimitiveTypeConverter extends AbstractTypeConverter<Boolean> {

  public static boolean booleanValue(Object value) {
    if (value == null) return false;
    Class<?> c = value.getClass();
    if (c == Boolean.class) {
      return ((Boolean) value).booleanValue();
    }
    if (c == Character.class) {
      return ((Character) value).charValue() != 0;
    }
    if (value instanceof Number) {
      return ((Number) value).doubleValue() != 0;
    }
    if (c == String.class) {
      return Boolean.parseBoolean((String) value);
    }
    return true;
  }

  @Override
  public Class<Boolean> getTargetType() {
    return Boolean.TYPE;
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
    return booleanValue(value);
  }
}
