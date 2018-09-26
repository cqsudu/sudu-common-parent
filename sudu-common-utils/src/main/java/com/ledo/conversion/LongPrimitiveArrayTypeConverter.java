package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * long数组的类型转换器。
 *
 */
public class LongPrimitiveArrayTypeConverter extends ArrayTypeConverterSupport<long[]> {

  public LongPrimitiveArrayTypeConverter(TypeConverterManager typeConverterManager) {
    super(typeConverterManager);
  }

  @Override
  public Class<long[]> getTargetType() {
    return long[].class;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    List<Class<?>> classes = super.getSupportedSourceTypes();
    classes.add(Object[].class);
    classes.add(Collection.class);
    classes.add(CharSequence[].class);
    return classes;
  }
}
