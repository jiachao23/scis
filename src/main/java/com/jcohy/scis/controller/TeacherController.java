package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.MajorService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private MajorService majorService;

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

    @GetMapping("/list")
    @ResponseBody
    public PageJson<Teacher> teacher(@SessionAttribute("user") Teacher teacher , ModelMap map){
        List<Teacher> teachers = teacherService.findAll();
        PageJson<Teacher> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(teachers.size());
        page.setData(teachers);
        return page;
    }

    @GetMapping("/search")
    @ResponseBody
    public PageJson search(String keyword,String dept){
        List<Teacher> teachers = new ArrayList<>();
        if(!StringUtils.isEmpty(keyword)){
            boolean isNum = keyword.matches("[0-9]+");
            if(isNum){
                Teacher teacher = teacherService.findByNum(Integer.parseInt(keyword));
                teachers.add(teacher);
            }else{
                Teacher teacher = teacherService.findByName(keyword);
                teachers.add(teacher);
            }
        }else{
            List<Teacher> teacherList = teacherService.findAll();
            if(!StringUtils.isEmpty(dept)){
                List<Teacher> list = teacherList.stream().filter(x -> x.getDept().getName().equals(dept)).collect(Collectors.toList());
                teachers = list;
            }else{
                teachers = teacherList;
            }
        }

        PageJson<Teacher> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(teachers.size());
        page.setData(teachers);
        return page;
    }

    @GetMapping("/student")
    public String teacher(@RequestParam(required = false) Integer id, ModelMap map){
        List<Major> majors = majorService.findAll();
        map.put("majors",majors);
        return "student/student";
    }
}
