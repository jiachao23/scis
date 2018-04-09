package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Expert;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.service.AllotService;
import com.jcohy.scis.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 18:07 2018/4/7
 * Email: jia_chao23@126.com
 * ClassName: ProjectController
 * Description:
 **/
@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AllotService allotService;

    @GetMapping("/all")
    @ResponseBody
    public PageJson<Project> all(){
        List<Project> projects = projectService.findAll();
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

}
