package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Copyright  : 2015-2033 Beijing Startimes Communication & Network Technology Co.Ltd
 * Created by jiac on 2017/12/27 16:04.
 * ClassName  : UploadController
 * Description  :
 */
@Controller
public class UploadController {


    @Value("${file.path}")
    private String filePath;

    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        //文件夹标号
        //上传到服务器的地址
        File upload = null;
        //上传到服务器的文件名
        String fileName = null;
        String url = null;
        //获取跟目录
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        if(type.equals("blog")){
//            url = "e://";
            upload = new File(filePath);
//        }
        if(!upload.exists()) upload.mkdirs();
        System.out.println("upload url:"+upload.getAbsolutePath());
        //在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
        //在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/
        //获取当前目录下的所有文件
        File[] files = upload.listFiles();
        //截取后缀，拼接新的文件名
        fileName = String.valueOf(files.length+1)+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length());
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(upload+File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok("url",url+File.separator+fileName);
    }

    @GetMapping("/download")
    public void download(@RequestParam("name") String name, HttpServletResponse response) throws IOException {


        File file = new File(filePath,name);
        System.out.println(filePath);
        if(file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment;fileName=" + name);

            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        }
    }
}
