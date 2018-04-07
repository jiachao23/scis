package com.jcohy.scis.service.impl;

import com.jcohy.scis.model.Allot;
import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Expert;
import com.jcohy.scis.repository.AllotRepository;
import com.jcohy.scis.service.AllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 20:43 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: AllotServiceImpl
 * Description:
 **/
@Service
public class AllotServiceImpl implements AllotService{

    @Autowired
    private AllotRepository allotRepository;

    @Override
    public List<Allot> findAll() {
        return allotRepository.findAll();
    }

    @Override
    public Allot findById(Integer id) {
        return allotRepository.findById(id).get();
    }

    @Override
    public List<Allot> findByExpert(Expert expert) {
        return allotRepository.findByExpert(expert);
    }
}
