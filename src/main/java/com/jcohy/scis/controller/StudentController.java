package com.jcohy.scis.controller;

import com.google.gson.Gson;
import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jiac on 2018/4/3.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/list")
    @ResponseBody
    public PageJson<Project> all(@SessionAttribute("user") Student student , ModelMap map){
        System.out.println(student.toString());
//        Student student = studentService.findByNum(num);
        List<Project> projects = projectService.findByStudent(student.getNum());
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

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @PostMapping("/password/")
    public JsonResult update(Student student){
        try {
            Student stu = studentService.saveOrUpdate(student);
            return JsonResult.ok().set("data",stu);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
    }



    /**
     * 获取教师信息
     * @return
     */
    @GetMapping("/teacher/{teacherId}")
    public JsonResult getteachers(@PathVariable("teacherId") Integer teacherId){
        Teacher teacher = null;
        try {
            teacher = teacherService.findById(teacherId);
        }catch (Exception e){
            return JsonResult.fail("此教师不存在");
        }
        return JsonResult.ok("获取成功").set("data",teacher);
    }



}
