package com.jcohy.scis.repository;

import com.jcohy.scis.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Copyright  : 2015-2033 Beijing Startimes Communication & Network Technology Co.Ltd
 * Created by jiac on 2018/1/16 13:53.
 * ClassName  : TypeRepository
 * Description  :
 */
public interface TypeRepository extends JpaRepository<Type,Integer> {

    List<Type> findByNum(Long num);
}
