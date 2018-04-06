package com.jcohy.scis.service;

import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Major;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 22:10 2018/4/6
 * Email: jia_chao23@126.com
 * ClassName: MajorService
 * Description:
 **/
public interface MajorService {

    List<Major> findAll();

    Major findById(Integer id);

    List<Major> findByDept(Dept dept);
}
