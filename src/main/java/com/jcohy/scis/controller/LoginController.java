package com.jcohy.scis.controller;

import com.jcohy.lang.StringUtils;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.model.Admin;
import com.jcohy.scis.model.Experts;
import com.jcohy.scis.model.Student;
import com.jcohy.scis.model.Teacher;
import com.jcohy.scis.service.AdminService;
import com.jcohy.scis.service.ExpertsService;
import com.jcohy.scis.service.StudentService;
import com.jcohy.scis.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName  : LoginController
 * Description  :登录模块处理
 */
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ExpertsService expertsService;


    /**
     * 登录处理
     * @param num
     * @param password
     * @param role
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(Integer num, String password,
                            @RequestParam(required = false) String role, ModelMap map){

        try {
            if(num == null || StringUtils.isEmpty(password)){
                return JsonResult.fail("用户名或者密码不能为空");
            }
            map.put("role",role);
            logger.error("name:{}  password:{}  type:{}",num,password,role);
            if(StringUtils.trim(role).equals("student")){
                Student login = studentService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                return JsonResult.ok().set("returnUrl", "/admin/student/index");

            }else if(StringUtils.trim(role).equals("teacher")){
                Teacher login = teacherService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                return JsonResult.ok().set("returnUrl", "/admin/teacher/index");
            }else if(StringUtils.trim(role).equals("expert")){
                Experts login = expertsService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                return JsonResult.ok().set("returnUrl", "/admin/expert/index");
            }else if(StringUtils.trim(role).equals("admin")){
                Admin login = adminService.login(num, password);
                if(login == null){
                    return JsonResult.fail("登录失败,用户名不存在");
                }
                if(!login.getPassword().equals(password)){
                    return JsonResult.fail("登录失败,用户名账号密码不匹配");
                }
                return JsonResult.ok().set("returnUrl", "/admin/index");
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.fail("角色不存在");
    }


    /**
     * 注销用户
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        return "redirect:/";
    }
}
