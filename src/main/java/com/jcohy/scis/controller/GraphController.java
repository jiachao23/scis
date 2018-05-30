package com.jcohy.scis.controller;

import com.jcohy.scis.common.Graph;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.model.Type;
import com.jcohy.scis.service.DeptService;
import com.jcohy.scis.service.ProjectService;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:50 2018/5/30
 * Email: jia_chao23@126.com
 * ClassName: GraphController
 * Description:
 **/
@RestController
@RequestMapping("/graph")
public class GraphController {


    @Autowired
    private ProjectService projectService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private DeptService deptService;


    @PostMapping("/type")
    public JsonResult graph(){
        Graph graph = new Graph();
        List<Project> projects = projectService.findAll();
        List<Type> types = typeService.findAll();
        List<Dept> depts = deptService.findAll();
        Map<String,Integer> typeMap = new HashMap<>();
        Map<String,Integer> deptMap = new HashMap<>();
        Map<String,Integer> yearMap = new HashMap<>();
        for(Type type:types){
            List<Project> service = projectService.findByType(type);
            if(service.size() == 0){
                typeMap.put(type.getName(),0);
            }else{
                typeMap.put(type.getName(),(projects.size()/service.size()));
            }
        }

        for(Project project:projects){
            int count = 0;
            for(Dept dept:depts){

                if(project.getStudent().getMajor().getDept().getName().equals(dept.getName())){
                    count++;
                }
                if(count == 0){
                    deptMap.put(dept.getName(),0);
                }else{
                    deptMap.put(dept.getName(),(projects.size()/count));
                }

            }
        }

//        for(Project project:projects){
//            int count = 0;
//            if(project.getCreateDate().indexOf("2016") == 0){
//
//            }
//        }
        graph.setType(typeMap);
        graph.setDeptMap(deptMap);
        System.out.println(graph);

        return JsonResult.ok("msg",graph);
    }
}
