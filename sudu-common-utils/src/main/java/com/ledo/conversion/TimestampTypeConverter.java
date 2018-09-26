package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * {@link Timestamp}的类型转换器。
 *
 * @author Agreal·Lee (e-mail:lixiang@yiji.com)
 */
public class TimestampTypeConverter extends AbstractTypeConverter<Timestamp> {

  @Override
  public Class<Timestamp> getTargetType() {
    return Timestamp.class;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    return Arrays.asList(CharSequence.class, String[].class, Date.class);
  }

  @Override
  public Timestamp convert(Object value, Class<? extends Timestamp> toType) {
    try {
      return (Timestamp) DateTypeConverter.dateValue(value, toType);
    } catch (ClassCastException e) {
      throw new TypeConversionException(e);
    }
  }
}
