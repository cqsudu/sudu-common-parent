package com.ledo.spring.jpa;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.utils.collection.CollectionUtil;
import org.springside.modules.utils.collection.MapUtil;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by konghang on 16/3/18.
 */
public class QueryUtils {

    public static final String DELETE_ALL_QUERY_STRING = "delete from %s x";

    /**
     * 构建过滤条件
     *
     * @param cb
     * @param root
     * @param filterMap
     * @return
     */
    public static Predicate buildPredicates(CriteriaBuilder cb, Root root, FilterMap filterMap){
        if(MapUtil.isEmpty(filterMap)){
            return null;
        }

        List<Predicate> predicates = Lists.newArrayList();
        Predicate orPredicate = null;
        Predicate andPredicate = null;
        for (Map.Entry<String, Object> entry: filterMap.entrySet()) {
            String key = entry.getKey();
            String[] pt = key.split(FilterConstants.SPLIT);
            String propertyName = pt[0];
            String type = pt[1];
            Object value = entry.getValue();

            Path<?> path = null;
            if (StringUtils.isNotBlank(propertyName)) {
                path = root.get(propertyName);
            }

            if (type.equals(FilterConstants.OR)) {
                FilterMap subMap = (FilterMap) value;
                if (MapUtil.isNotEmpty(subMap)) {
                    orPredicate = buildPredicates(cb, root, subMap);
                }
            } else if (type.equals(FilterConstants.AND)) {
                FilterMap subMap = (FilterMap) value;
                if (MapUtil.isNotEmpty(subMap)) {
                    andPredicate = buildPredicates(cb, root, subMap);
                }
            } else {
                if (filterMap.isAnd()) {
                    Predicate predicate = buildPredicate(cb, type, root, path, value);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                } else {
                    Predicate predicate = buildPredicate(cb, type, root, path, value);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                }
            }
        }

        if(filterMap.isAnd()){
            if(andPredicate != null){
                predicates.add(andPredicate);
            }
            if(CollectionUtil.isNotEmpty(predicates)){
                Predicate and = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                if(orPredicate != null){
                    return cb.or(and,orPredicate);
                }
                return and;
            }
        }else {
            if(orPredicate != null){
                predicates.add(orPredicate);
            }
            if(CollectionUtil.isNotEmpty(predicates)){
                Predicate or = cb.or(predicates.toArray(new Predicate[predicates.size()]));
                if(andPredicate != null){
                    return cb.and(or,andPredicate);
                }
                return or;
            }
        }

        return null;
    }

    private static Predicate buildPredicate(CriteriaBuilder cb, String type, Root root, Path<?> property, Object value){
        Predicate predicate = null;
        if(value != null){
            if (type.equals(FilterConstants.EQ)) {
                predicate = cb.equal(property, value);
            } else if (type.equals(FilterConstants.NE)) {
                predicate = cb.notEqual(property, value);
            } else if (type.equals(FilterConstants.GT)) {
                predicate = cb.greaterThan(property.as(Integer.class), (Integer) value);
            } else if (type.equals(FilterConstants.GTLONG)) {
                predicate = cb.greaterThan(property.as(Long.class), (Long) value);
            } else if (type.equals(FilterConstants.GTDOUBLE)) {
                predicate = cb.greaterThan(property.as(Double.class), (Double) value);
            } else if (type.equals(FilterConstants.GTDATE)) {
                predicate = cb.greaterThan(property.as(Date.class), (Date) value);
            } else if (type.equals(FilterConstants.GE)) {
                predicate = cb.greaterThanOrEqualTo(property.as(Integer.class), (Integer) value);
            } else if (type.equals(FilterConstants.GESTRING)) {
                predicate = cb.greaterThanOrEqualTo(property.as(String.class), (String) value);
            } else if (type.equals(FilterConstants.GELONG)) {
                predicate = cb.greaterThanOrEqualTo(property.as(Long.class), (Long) value);
            } else if (type.equals(FilterConstants.GEDOUBLE)) {
                predicate = cb.greaterThanOrEqualTo(property.as(Double.class), (Double) value);
            } else if (type.equals(FilterConstants.GEDATE)) {
                predicate = cb.greaterThanOrEqualTo(property.as(Date.class), (Date) value);
            } else if (type.equals(FilterConstants.LT)) {
                predicate = cb.lessThan(property.as(Integer.class), (Integer) value);
            } else if (type.equals(FilterConstants.LTLONG)) {
                predicate = cb.lessThan(property.as(Long.class), (Long) value);
            } else if (type.equals(FilterConstants.LTDOUBLE)) {
                predicate = cb.lessThan(property.as(Double.class), (Double) value);
            } else if (type.equals(FilterConstants.LTDATE)) {
                predicate = cb.lessThan(property.as(Date.class), (Date) value);
            } else if (type.equals(FilterConstants.LE)) {
                predicate = cb.lessThanOrEqualTo(property.as(Integer.class), (Integer) value);
            } else if(type.equals(FilterConstants.LESTRING)){
                predicate = cb.lessThanOrEqualTo(property.as(String.class), (String) value);
            } else if (type.equals(FilterConstants.LELONG)) {
                predicate = cb.lessThanOrEqualTo(property.as(Long.class), (Long) value);
            } else if (type.equals(FilterConstants.LEDOUBLE)) {
                predicate = cb.lessThanOrEqualTo(property.as(Double.class), (Double) value);
            } else if (type.equals(FilterConstants.LEDATE)) {
                predicate = cb.lessThanOrEqualTo(property.as(Date.class), (Date) value);
            } else if (type.equals(FilterConstants.LIKE)) {
                predicate = cb.like(property.as(String.class), String.valueOf(value));
            } else if (type.equals(FilterConstants.NOTLIKE)) {
                predicate = cb.notLike(property.as(String.class), String.valueOf(value));
            } else if (type.equals(FilterConstants.IN)) {
                Collection collection = (Collection) value;
                if (collection.size() > 0) {
                    CriteriaBuilder.In in = cb.in(property);
                    Iterator iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        in.value(iterator.next());
                    }
                    predicate = in;
                }
            } else if (type.equals(FilterConstants.NOTIN)) {
                Collection collection = (Collection) value;
                if (collection.size() > 0) {
                    CriteriaBuilder.In in = cb.in(property);
                    Iterator iterator = collection.iterator();
                    while (iterator.hasNext()) {
                        in.value(iterator.next());
                    }
                    predicate = cb.not(in);
                }
            } else if (type.equals(FilterConstants.BETWEEN)) {
                Map<String, Integer> map = (Map<String, Integer>) value;
                Integer lo = map.get(FilterConstants.LO);
                Integer go = map.get(FilterConstants.GO);
                predicate = cb.between(property.as(Integer.class), lo, go);
            }else if (type.equals(FilterConstants.BETWEENLONG)) {
                Map<String, Long> map = (Map<String, Long>) value;
                Long lo = map.get(FilterConstants.LO);
                Long go = map.get(FilterConstants.GO);
                predicate = cb.between(property.as(Long.class), lo, go);
            }else if (type.equals(FilterConstants.BETWEENSTRING)) {
                Map<String, String> map = (Map<String, String>) value;
                String lo = map.get(FilterConstants.LO);
                String go = map.get(FilterConstants.GO);
                predicate = cb.between(property.as(String.class), lo, go);
            }  else if (type.equals(FilterConstants.BETWEENDATE)) {
                Map<String, Date> map = (Map<String, Date>) value;
                Date lo = map.get(FilterConstants.LO);
                Date go = map.get(FilterConstants.GO);
                predicate = cb.between(property.as(Date.class), lo, go);
            }
//            }else if (type.equals(FilterConstants.OR)) {
//                FilterMap subMap = (FilterMap) value;
//                predicate = cb.or(buildPredicates(cb, root, subMap));
//            }else if (type.equals(FilterConstants.AND)) {
//                FilterMap subMap = (FilterMap) value;
//                predicate = cb.and(buildPredicates(cb,root,subMap));
//            }
        } else {
            if (type.equals(FilterConstants.ISNULL)) {
                predicate = cb.isNull(property);
            } else if (type.equals(FilterConstants.ISNOTNULL)) {
                predicate = cb.isNotNull(property);
            } else if (type.equals(FilterConstants.ISEMPTY)) {
                predicate = cb.equal(property, "");
            } else if (type.equals(FilterConstants.ISNOTEMPTY)) {
                predicate = cb.notEqual(property, "");
            }
        }
        return predicate;
    }

    /**
     * 构建排序
     *
     * @param cb
     * @param root
     * @param orderMap
     * @return
     */
    public static List<Order> buildOrders(CriteriaBuilder cb, Root root, OrderMap orderMap){
        if(orderMap == null || orderMap.isEmpty()){
            return Collections.emptyList();
        }

        List<Order> orders = new ArrayList<Order>(orderMap.size());
        for (String key : orderMap.keySet()) {
            String[] pt = key.split(FilterConstants.SPLIT);
            String propertyName = pt[0];
            String type = pt[1];
            if (type.equals(FilterConstants.ASC)) {
                orders.add(cb.asc(root.get(propertyName)));
            } else if (type.equals(FilterConstants.DESC)) {
                orders.add(cb.desc(root.get(propertyName)));
            }
        }

        return orders;
    }

    /**
     * 获取字符串
     *
     * @param template
     * @param entityName
     * @return
     */
    public static String getQueryString(String template, String entityName) {
        Preconditions.checkState(null != entityName && entityName.length() > 0, "Entity name must not be null or empty!");
        return String.format(template, entityName);
    }

    public static <T> Specification<T> buildSpecification(final FilterMap filterMap) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return buildPredicates(cb, root, filterMap);
            }
        };
    }
}
