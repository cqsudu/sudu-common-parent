package com.ledo.spring.jpa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by konghang on 2017/4/12.
 */
public abstract class AbstractRepository<R extends JpaRepository> {

    protected R _this;

    @Autowired
    public void setRepository(R repository){
        _this = repository;
    }
}
