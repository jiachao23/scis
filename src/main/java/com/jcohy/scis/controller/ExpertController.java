package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.AllotService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
public class ExpertController extends BaseController{

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
    public PageJson<Project> all(@SessionAttribute("user") Expert expert , ModelMap map){
        PageRequest pageRequest = getPageRequest();

        List<Project> projects = allotService.findByExpert(expert);
        projects.stream().forEach(string ->{
            string.setExpert(expert);
            string.setOperator("expert");
        });

        if(projects.size()>((pageRequest.getPageNumber()+1)*pageRequest.getPageSize())){
            List<Project> projects1 = projects.subList(0, pageRequest.getPageSize());
            projects.clear();
            projects.addAll(projects1);
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
}
