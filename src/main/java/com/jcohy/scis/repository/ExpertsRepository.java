package com.jcohy.scis.repository;

import com.jcohy.scis.model.Experts;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 18:50 2018/4/6
 * Email: jia_chao23@126.com
 * ClassName: ExpertsRepository
 * Description:
 **/
public interface ExpertsRepository extends JpaRepository<Experts,Integer> {

    Experts findByNum(Integer num);

    Experts findByName(String name);
}
