package com.gran.imperva.goodservice.dao;

import com.gran.imperva.goodservice.dao.models.Good;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface GoodDAO extends CrudRepository<Good, Integer> {

    @Override
    Set<Good> findAll();

}
