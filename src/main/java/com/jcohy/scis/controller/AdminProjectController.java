package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Allot;
import com.jcohy.scis.model.Expert;
import com.jcohy.scis.model.Project;
import com.jcohy.scis.service.AllotService;
import com.jcohy.scis.service.ExpertService;
import com.jcohy.scis.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/admin/project")
public class AdminProjectController extends BaseController{

    @Autowired
    private AllotService allotService;

    @Autowired
    private ExpertService expertService;

    @Autowired
    private ProjectService projectService;


//    @DeleteMapping("{id}/del")
//    @ResponseBody
//    public JsonResult del(@PathVariable("id") Integer id){
//        try {
//            allotService.delete(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JsonResult.fail("删除失败");
//        }
//        return JsonResult.ok();
//    }
//
//    @DeleteMapping("{id}/change")
//    @ResponseBody
//    public JsonResult change(@PathVariable("id") Integer id){
//        try {
//            projectService.AdminPass(id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JsonResult.fail("删除失败");
//        }
//        return JsonResult.ok();
//    }
}
