package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageJson;
import com.jcohy.scis.model.Experts;
import com.jcohy.scis.service.ExpertsService;
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
@RequestMapping("/admin/expert")
public class AdminExpertsController {

    @Autowired
    private ExpertsService expertsService;


    @GetMapping("/list")
    @ResponseBody
    public PageJson<Experts> all(ModelMap map){
        List<Experts> experts = expertsService.findAll();
        PageJson<Experts> page = new PageJson<>();
        page.setCode(0);
        page.setMsg("成功");
        page.setCount(experts.size());
        page.setData(experts);
        return page;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){

        if(id != null){
            Experts experts = expertsService.findById(id);
            map.put("expert",experts);
        }
        return "admin/expert/form";
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(Experts experts){
        try {
            expertsService.saveOrUpdate(experts);
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
            expertsService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.fail("删除失败");
        }
        return JsonResult.ok();
    }
}
