package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * int数组的类型转换器。
 *
 */
public class IntArrayTypeConverter extends ArrayTypeConverterSupport<int[]> {

  public IntArrayTypeConverter(TypeConverterManager typeConverterManager) {
    super(typeConverterManager);
  }

  @Override
  public Class<int[]> getTargetType() {
    return int[].class;
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
