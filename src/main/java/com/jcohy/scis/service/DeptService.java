package com.jcohy.scis.service;

import com.jcohy.scis.model.Dept;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 22:08 2018/4/6
 * Email: jia_chao23@126.com
 * ClassName: DeptService
 * Description:
 **/
public interface DeptService {

    List<Dept> findAll();

    Dept findById(Integer id);
}
