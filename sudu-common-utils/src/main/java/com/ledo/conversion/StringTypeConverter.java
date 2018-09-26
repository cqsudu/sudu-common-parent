package com.ledo.conversion;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * {@link String}的类型转换器。
 *
 */
public class StringTypeConverter extends AbstractTypeConverter<String> {

  public static String stringValue(Object value, boolean trim) {
    String result;
    if (value == null) {
      result = null;
    } else {
      if (value.getClass().isArray()) {
        StringBuilder builder = new StringBuilder();
        if (value instanceof Object[]) {
          for (Object o : (Object[]) value) {
            builder.append(o);
          }
        } else {
          int length = Array.getLength(value);
          for (int i = 0; i < length; i++) {
            builder.append(Array.get(value, i));
          }
        }
        result = builder.toString();
      } else {
        result = value.toString();
      }
      if (trim) {
        result = result.trim();
      }
    }
    return result;
  }

  public static String stringValue(Object value) {
    return stringValue(value, false);
  }

  @Override
  public Class<String> getTargetType() {
    return String.class;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    return Arrays.<Class<?>>asList(TypeConverterManager.ALL_SOUECE_TYPE_CLASS);
  }

  @Override
  public String convert(Object value, Class<? extends String> toType) {
    return stringValue(value);
  }
}
