package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.*;
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
    public Allot findByExpert(Expert expert) {
        return allotRepository.findByExpert(expert);
    }

    @Override
    public Expert findByProject(Project project) {
        Allot allot = allotRepository.findByProject(project);
        if(allot == null){
            return new Expert();
        }
        return allot.getExpert();
    }

    @Override
    public Allot saveOrUpdate(Allot allot) throws ServiceException {
        Allot dballot =null;
        if(allot.getId() != null){
            dballot = findById(allot.getId());
            if(allot.getExpert() != null ) dballot.setExpert(allot.getExpert());
            if(allot.getContent() != null ) dballot.setContent(allot.getContent());
            if(allot.getProject() != null ) dballot.setProject(allot.getProject());
            if(allot.getStart() != null ) dballot.setStart(allot.getStart());
            if(allot.getEnd() != null ) dballot.setEnd(allot.getEnd());
        }else{
            dballot =allot;
        }
        return allotRepository.save(dballot);
    }

    @Override
    public void delete(Integer id) {
        allotRepository.deleteById(id);
    }
}
