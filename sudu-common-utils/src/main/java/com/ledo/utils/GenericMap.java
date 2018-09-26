package com.ledo.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohong@acooly.cn
 * date 2018-09-13 21:22
 */
@Getter
@Setter
public class GenericMap<K, V> {
    private K key;
    private V value;

    public GenericMap(K k, V v){
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}