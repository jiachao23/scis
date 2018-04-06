package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Teacher;
import com.jcohy.scis.service.DeptService;
import com.jcohy.scis.service.MajorService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiac on 2018/4/2.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */
@Controller
@RequestMapping("/admin/teacher")
public class AdminTeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    @ResponseBody
    public PageJson<Teacher> all(ModelMap map){
        List<Teacher> labs = teacherService.findAll();
        PageJson<Teacher> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(labs.size());
        page.setData(labs);
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){

        if(id != null){
            Teacher teacher = teacherService.findById(id);
            map.put("teacher",teacher);
        }
        return "admin/teacher/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(Teacher teacher){
        try {
            teacherService.saveOrUpdate(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            teacherService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }
}
