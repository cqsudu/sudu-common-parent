package com.ledo.conversion;

import java.util.Collection;
import java.util.List;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-25 10:16
 */
public class BytePrimitiveArrayTypeConverter extends ArrayTypeConverterSupport<byte[]> {

    public BytePrimitiveArrayTypeConverter(TypeConverterManager typeConverterManager) {
        super(typeConverterManager);
    }

    @Override
    public Class<byte[]> getTargetType() {
        return byte[].class;
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