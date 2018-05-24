package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.*;
import com.jcohy.scis.repository.NoticeRepository;
import com.jcohy.scis.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by jiac on 2018/4/3.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */
@Controller
@RequestMapping("/student")
public class StudentController extends BaseController{

    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AllotService allotService;

    @Autowired
    private NoticeService noticeService;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/main")
    public String main(ModelMap map){
        List<Notice> notices = noticeService.findAll();
        map.put("size",notices.size());
        map.put("url","/notice/list");
        return "/student/main";
    }

    @GetMapping("/notice/all")
    @ResponseBody
    public PageJson<Notice> notice(@SessionAttribute("user") Student student , ModelMap map){
        List<Notice> notices = noticeService.findbyNum(student.getNum());
        List<Notice> noticeList = notices.stream().filter(x -> x.getLevel() < 3).collect(Collectors.toList());
        PageJson<Notice> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(noticeList.size());
        page.setData(noticeList);
        return page;
    }

    @DeleteMapping("/notice/{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            noticeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }

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
    @ResponseBody
    public JsonResult saveOrUpdate(@SessionAttribute("user") Student student,Project project){
        Project ret = projectService.findByName(project.getName());
        if(ret != null){
            return JsonResult.fail("此项目已经申报，请不要重复申报！");
        }
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
