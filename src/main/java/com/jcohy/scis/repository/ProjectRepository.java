package com.jcohy.scis.repository;

import com.jcohy.scis.model.Expert;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.model.Student;
import com.jcohy.scis.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 18:51 2018/4/6
 * Email: jia_chao23@126.com
 * ClassName: ProjectRepository
 * Description:
 **/
public interface ProjectRepository extends JpaRepository<Project,Integer>{

    Project findAllByName(String name);

    List<Project> findByStudent(Student student);

    List<Project> findByTeacher(Teacher teacher);

}
