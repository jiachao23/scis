package com.jcohy.scis.service.impl;

import com.jcohy.date.DateUtils;
import com.jcohy.scis.exception.ServiceException;
import com.jcohy.scis.model.Notice;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.repository.*;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 16:07 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: ProjectServiceImpl
 * Description:
 **/
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private NoticeRepository noticeRepository;

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
    public Project saveOrUpdate(Project project) throws ServiceException {
        if(project.getId() == null){
            project.setAReason("");
            project.setEReason("");
            project.setAStatus(0);
            project.setEStatus(0);
        }
        return projectRepository.save(project);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public void changeStatus(Integer id, String role, String advise) {
        try {
            Project project = projectRepository.findById(id).get();
            Notice notice = new Notice();
            switch (role) {
                case "admin":
                    notice.setStudentNum(project.getStudent().getNum());
                    notice.setProjectName(project.getName());
                    notice.setOperation("项目由管理员审核");
                    notice.setContent(advise);
                    notice.setStatus(project.getAStatus() == 0 ? "通过" : "撤回");
                    notice.setDate(DateUtils.getCurrentDateStr());
                    projectRepository.changeAdminStatus(project.getAStatus() == 0 ? 1 : 0, advise, project.getId());
                    noticeRepository.save(notice);
                    break;
                case "expert":
                    notice.setStudentNum(project.getStudent().getNum());
                    notice.setProjectName(project.getName());
                    notice.setOperation("项目专家审核");
                    notice.setContent(advise);
                    notice.setStatus(project.getAStatus() == 0 ? "通过" : "撤回");
                    notice.setDate(DateUtils.getCurrentDateStr());
                    projectRepository.changeExpertStatus(project.getAStatus() == 0 ? 1 : 0, advise, project.getId());
                    noticeRepository.save(notice);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
