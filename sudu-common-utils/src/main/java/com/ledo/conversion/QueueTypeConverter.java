package com.ledo.conversion;

import com.ledo.exception.TypeConversionException;
import com.ledo.utils.Reflections;

import java.util.*;

/**
 * {@link Queue}的类型转换器。
 *
 */
public class QueueTypeConverter extends AbstractTypeConverter<Queue<?>> {

  @SuppressWarnings("unchecked")
  public static <E extends Queue<?>> E queueValue(Object value, Class<? extends E> queueClassType) {
    Queue<Object> queue = null;
    if ((Class<?>) queueClassType == Queue.class) {
      // 使用 LinkedList 作为实现
      queue = new LinkedList<Object>();
    } else { // 如果是具体的类则使用该类的类型
      try {
        queue = (Queue<Object>) Reflections.createObject(queueClassType);
      } catch (Exception e) {
        throw new TypeConversionException(e);
      }
    }
    queue.addAll((Queue<Object>) value);
    return (E) queue;
  }

  @Override
  public Class<Queue<?>> getTargetType() {
    Class<?> queueClass = Queue.class;
    return (Class<Queue<?>>) queueClass;
  }

  @Override
  public List<Class<?>> getSupportedSourceTypes() {
    return Arrays.asList(Collection.class, Object[].class);
  }

  @Override
  public Queue<?> convert(Object value, Class<? extends Queue<?>> toType) {
    return queueValue(value, toType);
  }
}
