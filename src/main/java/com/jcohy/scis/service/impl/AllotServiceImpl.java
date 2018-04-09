package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.*;
import com.jcohy.scis.repository.AllotRepository;
import com.jcohy.scis.service.AllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Project> findByExpert(Expert expert) {
        List<Allot> allots = allotRepository.findByExpert(expert);
        List<Project> experts = new ArrayList<>();
        for(Allot allot:allots){
            experts.add(allot.getProject());
        }
        return experts;
    }

    @Override
    public Expert findByProject(Project project) {
        List<Allot> allots = allotRepository.findByProject(project);
        if(allots == null){
            return new Expert();
        }
        return allots.get(0).getExpert();
    }

    @Override
    public Allot saveOrUpdate(Allot allot) throws ServiceException {
        Allot dballot =null;
        if(allot.getId() != null){
            dballot = findById(allot.getId());
            if(allot.getProject() != null ) dballot.setProject(allot.getProject());
            if(allot.getExpert() != null ) dballot.setExpert(allot.getExpert());
            if(allot.getContent() != null ) dballot.setContent(allot.getContent());
            if(allot.getProject() != null ) dballot.setProject(allot.getProject());
            if(allot.getStart() != null ) dballot.setStart(allot.getStart());
            if(allot.getEnd() != null ) dballot.setEnd(allot.getEnd());
        }else{
            dballot =allot;
        }
        if(allotRepository.findByProject(dballot.getProject()).size() > 1){
            throw new ServiceException("此项目已存在专家");
        }
        return allotRepository.save(dballot);
    }

    @Override
    public void delete(Integer id) {
        allotRepository.deleteById(id);
    }
}
