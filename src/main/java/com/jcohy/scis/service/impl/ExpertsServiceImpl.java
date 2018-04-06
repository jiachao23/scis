package com.jcohy.scis.service.impl;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Experts;
import com.jcohy.scis.repository.ExpertsRepository;
import com.jcohy.scis.service.ExpertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:14 2018/4/6
 * Email: jia_chao23@126.com
 * ClassName: ExpertsServiceImpl
 * Description:
 **/
@Service
public class ExpertsServiceImpl implements ExpertsService{

    @Autowired
    private ExpertsRepository expertsRepository;

    @Override
    public Experts login(Integer num, String password) throws Exception {
        return expertsRepository.findByNum(num);
    }

    @Override
    public Page<Experts> findAll(Pageable pageable) {
        return expertsRepository.findAll(pageable);
    }

    @Override
    public List<Experts> findAll() {
        return expertsRepository.findAll();
    }

    @Override
    public Experts findById(Integer id) {
        return expertsRepository.findById(id).get();
    }

    @Override
    public Experts findByName(String name) {
        return expertsRepository.findByName(name);
    }

    @Override
    public void saveOrUpdate(Experts user) throws ServiceException {
        expertsRepository.save(user);
    }

    @Override
    public boolean checkUser(Integer num) {
        Experts dbUser = expertsRepository.findByNum(num);
        return dbUser != null;

    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        expertsRepository.deleteById(id);
    }

    @Override
    public void updatePassword(Experts user, String oldpassword, String password1, String password2) {
        if(StringUtils.isBlank(oldpassword) || StringUtils.isBlank(password1) || StringUtils.isBlank(password2)){
            throw new ServiceException("参数不完整");
        }

        if(!password1.equals(password2)){
            throw new ServiceException("两次输入密码不一致");
        }
        Experts dbUser = findById(user.getId());
        if(!user.getPassword().equals(oldpassword)){
            throw new ServiceException("旧密码不正确");
        }
        dbUser.setPassword(password1);
        expertsRepository.saveAndFlush(dbUser);
    }
}
