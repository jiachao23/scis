package com.jcohy.scis.service;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.*;

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

    List<Project> findByExpert(Expert expert);

    List<Expert> findByProject(Project project);

    Allot saveOrUpdate(Allot allot) throws ServiceException;

    void delete(Integer id);
}
