package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.AllotService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 20:38 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: ExpertController
 * Description:
 **/
@Controller
@RequestMapping("/expert")
public class ExpertController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AllotService allotService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ProjectService projectService;
    @GetMapping("/project/list")
    @ResponseBody
    public PageJson<Allot> all(@SessionAttribute("user") Expert expert , ModelMap map){
//        Student student = studentService.findByNum(num);
//        Allot allots = allotService.findByExpert(expert);
        PageJson<Allot> page = new PageJson<>();
//        page.setCode(0);
//        page.setMsg("成功");
//        page.setCount(allots.size());
//        page.setData(allots);
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
