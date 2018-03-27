package com.ledo.spring.jpa;

import java.util.*;

public class FilterMap extends LinkedHashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String OPERATOR = FilterConstants.AND;

    /**
     * 设置FilterMap默认的连接符为and
     */
    public void and(){
        OPERATOR = FilterConstants.AND;
    }

    /**
     * 设置FilterMap默认的连接符为or
     */
    public void or(){
        OPERATOR = FilterConstants.OR;
    }

    /**
     * 是否是and连接
     * @return
     */
    public Boolean isAnd(){
        return FilterConstants.AND.equals(OPERATOR);
    }

    /**
     * 等于
     *
     * @param propertyName
     * @param value
     */
    public void eq(String propertyName, Object value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.EQ, value);
    }

    /**
     * 不等于
     *
     * @param propertyName
     * @param value
     */
    public void ne(String propertyName, Object value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.NE, value);
    }
    /**
     * 等于
     *
     * @param propertyName
     * @param value
     */
    public void eqOrIsNull(String propertyName, Object value) {
        if(null==value) {
            this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISNULL, null);
        }else {
            this.put(propertyName + FilterConstants.SPLIT + FilterConstants.EQ, value);
        }
    }

    /**
     * 不等于
     *
     * @param propertyName
     * @param value
     */
    public void neOrIsNotNull(String propertyName, Object value) {
        if(null==value) {
            this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISNOTNULL, null);
        }else {
            this.put(propertyName + FilterConstants.SPLIT + FilterConstants.NE, value);
        }
    }

    /**
     * 大于
     *
     * @param propertyName
     * @param value
     */
    public void gt(String propertyName, Integer value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GT, value);
    }

    /**
     * 大于
     *
     * @param propertyName
     * @param value
     */
    public void gt(String propertyName, Date value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GTDATE, value);
    }

    /**
     * 大于
     *
     * @param propertyName
     * @param value
     */
    public void gt(String propertyName, Long value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GTLONG, value);
    }

    /**
     * 大于
     *
     * @param propertyName
     * @param value
     */
    public void gt(String propertyName, Double value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GTDOUBLE, value);
    }

    /**
     * 大于等于
     *
     * @param propertyName
     * @param value
     */
    public void ge(String propertyName, String value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GESTRING, value);
    }

    /**
     * 大于等于
     *
     * @param propertyName
     * @param value
     */
    public void ge(String propertyName, Integer value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GE, value);
    }

    /**
     * 大于等于
     *
     * @param propertyName
     * @param value
     */
    public void ge(String propertyName, Date value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GEDATE, value);
    }

    /**
     * 大于等于
     *
     * @param propertyName
     * @param value
     */
    public void ge(String propertyName, Long value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GELONG, value);
    }

    /**
     * 大于等于
     *
     * @param propertyName
     * @param value
     */
    public void ge(String propertyName, Double value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.GEDOUBLE, value);
    }

    /**
     * 小于
     *
     * @param propertyName
     * @param value
     */
    public void lt(String propertyName, Integer value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LT, value);
    }

    /**
     * 小于
     *
     * @param propertyName
     * @param value
     */
    public void lt(String propertyName, Date value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LTDATE, value);
    }

    /**
     * 小于
     *
     * @param propertyName
     * @param value
     */
    public void lt(String propertyName, Long value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LTLONG, value);
    }

    /**
     * 小于
     *
     * @param propertyName
     * @param value
     */
    public void lt(String propertyName, Double value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LTDOUBLE, value);
    }

    /**
     * 小于等于
     *
     * @param propertyName
     * @param value
     */
    public void le(String propertyName, String value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LESTRING, value);
    }
    /**
     * 小于等于
     *
     * @param propertyName
     * @param value
     */
    public void le(String propertyName, Integer value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LE, value);
    }

    /**
     * 小于等于
     *
     * @param propertyName
     * @param value
     */
    public void le(String propertyName, Date value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LEDATE, value);
    }

    /**
     * 小于等于
     *
     * @param propertyName
     * @param value
     */
    public void le(String propertyName, Long value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LELONG, value);
    }

    /**
     * 小于等于
     *
     * @param propertyName
     * @param value
     */
    public void le(String propertyName, Double value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LEDOUBLE, value);
    }

    /**
     * 相似
     *
     * @param propertyName
     * @param value
     */
    public void like(String propertyName, String value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.LIKE, value);
    }

    /**
     * 不相似
     *
     * @param propertyName
     * @param value
     */
    public void notLike(String propertyName, String value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.NOTLIKE, value);
    }

    /**
     * 包含
     *
     * @param propertyName
     * @param value
     */
    @SuppressWarnings("rawtypes")
    public void in(String propertyName, Collection value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.IN, value);
    }

    /**
     * 不包含
     *
     * @param propertyName
     * @param value
     */
    @SuppressWarnings("rawtypes")
    public void notIn(String propertyName, Collection value) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.NOTIN, value);
    }

    /**
     * 之间
     *
     * @param propertyName
     * @param lo
     * @param go
     */
    public void between(String propertyName, Integer lo, Integer go) {
        Map<String, Integer> between = new HashMap<String, Integer>();
        between.put(FilterConstants.LO, lo);
        between.put(FilterConstants.GO, go);
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.BETWEEN, between);
    }

    /**
     * 之间
     *
     * @param propertyName
     * @param lo
     * @param go
     */
    public void between(String propertyName, Long lo, Long go) {
        Map<String, Long> between = new HashMap<String, Long>();
        between.put(FilterConstants.LO, lo);
        between.put(FilterConstants.GO, go);
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.BETWEENLONG, between);
    }

    /**
     * 之间
     *
     * @param propertyName
     * @param lo
     * @param go
     */
    public void between(String propertyName, String lo, String go) {
        Map<String, String> between = new HashMap<String, String>();
        between.put(FilterConstants.LO, lo);
        between.put(FilterConstants.GO, go);
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.BETWEENSTRING, between);
    }

    /**
     * 之间
     *
     * @param propertyName
     * @param lo
     * @param go
     */
    public void between(String propertyName, Date lo, Date go) {
        Map<String, Date> between = new HashMap<String, Date>();
        between.put(FilterConstants.LO, lo);
        between.put(FilterConstants.GO, go);
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.BETWEENDATE, between);
    }

    /**
     * 空
     *
     * @param propertyName
     */
    public void isNull(String propertyName) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISNULL, null);
    }

    /**
     * 非空
     *
     * @param propertyName
     */
    public void isNotNull(String propertyName) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISNOTNULL, null);
    }

    /**
     * 空
     *
     * @param propertyName
     */
    public void isEmpty(String propertyName) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISEMPTY, null);
    }

    /**
     * 非空
     *
     * @param propertyName
     */
    public void isNotEmpty(String propertyName) {
        this.put(propertyName + FilterConstants.SPLIT + FilterConstants.ISNOTEMPTY, null);
    }

    /**
     * 或者
     *
     * @param filterMap
     */
    public void or(FilterMap filterMap) {
        this.put(FilterConstants.SPLIT + FilterConstants.OR, filterMap);
    }

    /**
     * and连接
     *
     * @param filterMap
     */
    public void and(FilterMap filterMap){
        this.put(FilterConstants.SPLIT + FilterConstants.AND, filterMap);
    }

}
