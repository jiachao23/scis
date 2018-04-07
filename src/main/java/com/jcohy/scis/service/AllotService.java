package com.jcohy.scis.service;

import com.jcohy.scis.model.Allot;
import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Expert;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 20:41 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: AllotService
 * Description:
 **/
public interface AllotService {

    List<Allot> findAll();

    Allot findById(Integer id);

    List<Allot> findByExpert(Expert expert);
}
