package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.util.Collection;
import java.util.List;

/**
 * byte 的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class BytePrimitiveTypeConverter extends AbstractTypeConverter<Byte> {

  public static byte byteValue(Object value) throws NumberFormatException {
    return (byte) IntTypeConverter.intValue(value);
  }

  @Override
  public Class<Byte> getTargetType() {
    return Byte.TYPE;
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
  public Byte convert(Object value, Class<? extends Byte> toType) {
    try {
      return byteValue(value);
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
