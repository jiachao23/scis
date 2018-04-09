package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.AllotService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private AllotService allotService;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    @GetMapping("/project/list")
    @ResponseBody
    public PageJson<Project> all(@SessionAttribute("user") Student student , ModelMap map){
//        Student student = studentService.findByNum(num);
        List<Project> projects = projectService.findByStudent(student.getNum());
        for(Project project : projects){
            Expert expert = allotService.findByProject(project);
            if(expert == null){
                project.setExpert(null);
            }
            project.setExpert(expert);
        }
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
     * 获取教师信息
     * @return
     */
    @PostMapping("/save")
    public JsonResult saveOrUpdate(@SessionAttribute("user") Student student,Project project){
        project.setStudent(student);
        try {
            projectService.saveOrUpdate(project);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

}
