package com.jcohy.scis.controller;

import com.google.gson.Gson;
import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by jiac on 2018/4/3.
 * ClassName  : com.jcohy.perfectteaching.controller
 * Description  :
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @Autowired
    private TeacherService teacherService;



    @GetMapping
    public void index(){

    }

    /**
     * 用户登陆接口
     * @param num 学号
     * @param password 密码
     * @return
     */
    @GetMapping("/login")
    public JsonResult login(Integer num,String password){
        Student login = null;

        try {
            if(num == null || StringUtils.isEmpty(password)){
                return JsonResult.fail("用户名或者密码不能为空");
            }

            login = studentService.login(num, password);

            if(login == null){
                return JsonResult.fail("登录失败,用户名不存在");
            }
            if(!login.getPassword().equals(password)){
                return JsonResult.fail("登录失败,用户名账号密码不匹配");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonResult.ok("登录成功").set("data",login);
    }

    /**
     * 注册接口
     * @param num 学号 必填
     * @param phone 电话 必填
     * @param password 密码 必填
     * @param name 姓名 必填
     * @return
     */
    @GetMapping("/register")
    public JsonResult register(Integer num, String phone, String password, String name){
        if(num == null||phone == null || StringUtils.hashEmpty(name,password)){
            return JsonResult.fail("参数不能为空");
        }
        boolean exist = studentService.checkUser(num);
        if(exist){
            return JsonResult.fail("用户已存在");
        }
        Student student = new Student();
        student.setNum(num);
        student.setPassword(password);
        student.setName(name);
        student.setPhone(phone);
        studentService.saveOrUpdate(student);
        return JsonResult.ok("注册成功").set("data",student);
    }

    /**
     * 更新学生信息
     * @param student
     * @return
     */
    @GetMapping("/update")
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
