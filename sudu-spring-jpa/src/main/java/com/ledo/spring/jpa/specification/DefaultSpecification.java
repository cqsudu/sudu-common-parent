package com.ledo.spring.jpa.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

/**
 * Created by konghang on 2017/4/11.
 */
public abstract class DefaultSpecification <T> implements Specification<T> {

    protected List<Specification> specifications;

    public DefaultSpecification(List<Specification> specifications) {
        this.specifications = specifications;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if(specifications.size() == 0){
            Specifications<T> specs = Specifications.where(null);
            return specs.toPredicate(root, query, cb);
        }

        Iterator<Specification> iterator = specifications.iterator();
        Specification<T> first = iterator.next();
        Specifications<T> specs = Specifications.where(first);
        while (iterator.hasNext()){
            specs = combine(specs, iterator.next());
        }
        return specs.toPredicate(root, query, cb);
    }

    protected abstract Specifications<T> combine(Specifications<T> specs, Specification<T> next);
}
