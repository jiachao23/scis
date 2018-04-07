package com.jcohy.scis.service.impl;

import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.repository.ExpertsRepository;
import com.jcohy.scis.repository.ProjectRepository;
import com.jcohy.scis.repository.StudentRepository;
import com.jcohy.scis.repository.TeacherRepository;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 16:07 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: ProjectServiceImpl
 * Description:
 **/
@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ExpertsRepository expertsRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public Project findByName(String name) {
        return projectRepository.findAllByName(name);
    }

    @Override
    public List<Project> findByStudent(Integer num) {
        return projectRepository.findByStudent(studentRepository.findAdminByNum(num));
    }

    @Override
    public List<Project> findByTeacher(Integer num) {
        return projectRepository.findByTeacher(teacherRepository.findTeacherByNum(num));
    }

    @Override
    public List<Project> findByExpert(Integer num) {
        return null;
    }

    @Override
    public Project saveOrUpdate(Project user) throws ServiceException {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}