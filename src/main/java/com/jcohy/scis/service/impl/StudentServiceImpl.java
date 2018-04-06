package com.jcohy.scis.service.impl;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Student;
import com.jcohy.scis.repository.StudentRepository;
import com.jcohy.scis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.service.impl
 * Description  :
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student login(Integer num, String password) throws Exception {
        return studentRepository.findAdminByNum(num);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }


    @Override
    public Student findById(Integer id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student findByName(String name) {
        return studentRepository.findAdminByName(name);
    }

    @Override
    public Student saveOrUpdate(Student user) throws ServiceException {
        Student dbUser =null;
        if(user.getId() != null){
            dbUser = findById(user.getId());
            if(user.getNum() != null ) dbUser.setNum(user.getNum());
            if(user.getName() != null ) dbUser.setName(user.getName());
            if(user.getPassword() != null ) dbUser.setPassword(user.getPassword());
            if(user.getBirth() != null ) dbUser.setBirth(user.getBirth());
            if(user.getMajor() != null ) dbUser.setMajor(user.getMajor());
            if(user.getPhone() != null ) dbUser.setPhone(user.getPhone());
            if(user.getSclass() != null ) dbUser.setSclass(user.getSclass());
            if(user.getSex() != null ) dbUser.setSex(user.getSex());
        }else{
            dbUser =user;
        }

        return studentRepository.save(dbUser);
    }

    @Override
    public boolean checkUser(Integer num) {
        Student dbUser = studentRepository.findAdminByNum(num);
        return dbUser != null;
    }


    @Override
    public void delete(Integer id) {
        if(id == null){
            throw new ServiceException("主键不能为空");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public void updatePassword(Student user, String oldpassword, String password1, String password2) {
        if(StringUtils.isBlank(oldpassword) || StringUtils.isBlank(password1) || StringUtils.isBlank(password2)){
            throw new ServiceException("参数不完整");
        }

        if(!password1.equals(password2)){
            throw new ServiceException("两次输入密码不一致");
        }
        Student dbUser = findById(user.getId());
        if(!user.getPassword().equals(oldpassword)){
            throw new ServiceException("旧密码不正确");
        }
        dbUser.setPassword(password1);
        studentRepository.saveAndFlush(dbUser);
    }
}
