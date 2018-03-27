package com.ledo.spring.jpa;

import java.util.LinkedHashMap;

/**
 * Created by konghang on 15-12-22.
 */
public class OrderMap extends LinkedHashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 顺序
     *
     * @param key
     */
    public void asc(String key) {
        this.put(key + FilterConstants.SPLIT + FilterConstants.ASC, null);
    }

    /**
     * 倒序
     *
     * @param key
     */
    public void desc(String key) {
        this.put(key + FilterConstants.SPLIT + FilterConstants.DESC, null);
    }

}
