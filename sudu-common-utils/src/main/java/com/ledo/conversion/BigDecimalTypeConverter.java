package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * {@link BigDecimal}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class BigDecimalTypeConverter extends AbstractTypeConverter<BigDecimal> {

  public static BigDecimal bigDecValue(Object value) throws NumberFormatException {
    if (value == null) return null;
    Class<?> c = value.getClass();
    if (c == BigDecimal.class) {
      return (BigDecimal) value;
    }
    if (c == BigInteger.class) {
      return new BigDecimal((BigInteger) value);
    }
    if (c.getSuperclass() == Number.class) {
      return new BigDecimal(((Number) value).doubleValue());
    }
    if (c == Boolean.class) {
      return BigDecimal.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
    }
    if (c == Character.class) {
      return BigDecimal.valueOf(((Character) value).charValue());
    }
    return new BigDecimal(StringTypeConverter.stringValue(value, true));
  }

  @Override
  public Class<BigDecimal> getTargetType() {
    return BigDecimal.class;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    return Arrays.asList(
        Number.class,
        Boolean.class,
        Character.class,
        CharSequence.class,
        CharSequence[].class,
        BigInteger.class);
  }

  @Override
  public BigDecimal convert(Object value, Class<? extends BigDecimal> toType) {
    try {
      return bigDecValue(value);
    } catch (Exception e) {
      throw new TypeConversionException(e);
    }
  }
}
