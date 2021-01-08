package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UploadController {

    @Value("${name}")
    private String path;

    @RequestMapping("/helloUpload")
    public String hello(){
        System.out.println(path);
        return path;
    }


    /**
     * 单文件上传
     * @param srcFile 目标文件参数
     */
    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile srcFile) {

        if(srcFile.isEmpty()){
            System.out.println("文件为空");
            return;
        }
        //文件上传保存路径
        String path = "C:\\Users\\Administrator\\Desktop\\uploadTest";
        File file = new File(path);
        //当该目录不存在时创建
        if(!file.exists()){
            file.mkdirs();
        }
        //获取原文件名
        String fileName = srcFile.getOriginalFilename();
        //获取扩展名
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        //设置新文件名
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." +extension;
        File destFile = new File(path,newFileName);
        try{
            //上传
            srcFile.transferTo(destFile);
            System.out.println("文件上传成功...");
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @PostMapping("/uploads")
    public void uploads(@RequestParam("files")MultipartFile[] files) throws IOException {
        if(files.length == 0){
            System.out.println("当前上传文件数量为空");
        }
        //文件上传路径
        String path = "C:\\Users\\Administrator\\Desktop\\uploadsTest";
        File file = new File(path);
        //当该目录不存在时创建
        if(!file.exists()){
            file.mkdirs();
        }
        int i = 0;

        for (MultipartFile f : files){
            //获取原文件名
            String oldFileName = f.getOriginalFilename();
            //获取文件扩展名
            String extension = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
            //设置新文件名
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "." + extension;
            File dest = new File(path,newFileName);
            try{
                //上传
                f.transferTo(dest);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
            i++;
        }

        System.out.println("文件上传成功...");
        System.out.println(i);

    }
}
