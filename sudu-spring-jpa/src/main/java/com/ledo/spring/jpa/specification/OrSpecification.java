package com.ledo.spring.jpa.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.List;

/**
 * 组合
 *
 * Created by konghang on 2017/4/11.
 */
public class OrSpecification<T> extends DefaultSpecification<T> {

    public OrSpecification(List<Specification> specifications) {
        super(specifications);
    }

    @Override
    protected Specifications<T> combine(Specifications<T> specs, Specification<T> next) {
        return specs.or(next);
    }
}
