package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.model.Student;
import com.jcohy.scis.model.Teacher;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: TeacherController
 * Description:
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController{

    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ProjectService projectService;
    @GetMapping("/project/list")
    @ResponseBody
    public PageJson<Project> all(@SessionAttribute("user") Teacher teacher , ModelMap map){
//        Student student = studentService.findByNum(num);
        List<Project> projects = projectService.findByTeacher(teacher.getNum());
        PageJson<Project> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(projects.size());
        page.setData(projects);
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){
        List<Teacher> teachers = teacherService.findAll();
        map.put("teachers",teachers);
        if(id != null){
            Project project = projectService.findById(id);
            map.put("project",project);
        }
        return "student/form";
    }

}
