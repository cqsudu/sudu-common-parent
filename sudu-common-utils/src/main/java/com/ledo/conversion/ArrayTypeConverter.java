package com.ledo.conversion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:12
 */

public class ArrayTypeConverter extends ArrayTypeConverterSupport<Object[]> {

    public ArrayTypeConverter(TypeConverterManager typeConverterManager) {
        super(typeConverterManager);
    }

    @Override
    public Class<Object[]> getTargetType() {
        return Object[].class;
    }

    @Override
    public List<Class<?>> getSupportedSourceTypes() {
        return Arrays.asList(Object[].class, Collection.class);
    }
}