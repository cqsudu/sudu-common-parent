package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * boolean数组的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class BooleanPrimitiveArrayTypeConverter extends ArrayTypeConverterSupport<boolean[]> {

  public BooleanPrimitiveArrayTypeConverter(TypeConverterManager typeConverterManager) {
    super(typeConverterManager);
  }

  @Override
  public Class<boolean[]> getTargetType() {
    return boolean[].class;
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
