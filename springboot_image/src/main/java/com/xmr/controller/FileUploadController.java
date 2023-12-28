package com.xmr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by xmr on 2020/3/29.
 */
@Controller
public class FileUploadController {
    /*
     * 获取file.html页面
     */
    @RequestMapping("/file")
    public String file() {
        return "file";
    }

    /**
     * 实现文件上传
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "D:/test";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }
    public static final String host = "http://127.0.0.1:8080/image/";
    @PostMapping(value = "/upload")
    @ResponseBody
    public String insertOrderImg(@RequestParam("file") MultipartFile file) {
        // 文件上传路径
        String location = "F:\\image\\";

        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //设置允许上传文件类型
        String suffixList = ".jpg,.png,.ico,.bmp,.jpeg";
        String imgUrl=null;
        // 判断是否包含
        if (suffixList.contains(extName.trim().toLowerCase())) {
            // 保存文件的路径
            String path = location + originalFilename;
            //  spring的transferTo保存文件方法
            try {
                file.transferTo(new File(path));
                imgUrl = host + originalFilename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgUrl;
    }
}
