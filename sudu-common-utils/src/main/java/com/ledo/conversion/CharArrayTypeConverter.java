package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * char数组的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class CharArrayTypeConverter extends ArrayTypeConverterSupport<char[]> {

  public CharArrayTypeConverter(TypeConverterManager typeConverterManager) {
    super(typeConverterManager);
  }

  @Override
  public Class<char[]> getTargetType() {
    return char[].class;
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
