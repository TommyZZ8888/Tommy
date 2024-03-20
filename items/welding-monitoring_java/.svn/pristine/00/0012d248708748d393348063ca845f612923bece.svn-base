package com.vren.weldingmonitoring_java.controller;

import com.vren.weldingmonitoring_java.exception.ErrorException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    // 从配置文件中获取上传文件保存的路径
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    @Value("${login.upload}")
    private String upload;

    // 处理文件上传请求
    @PostMapping("/upload")
    @ApiOperation("上传头像")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 检查上传文件是否为空
        if (file.isEmpty()) {
            throw new ErrorException("文件为空");
        }
        String imagePath = upload;
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //获取后缀
            String name = fileName.substring(fileName.lastIndexOf("."));
            //用UUID代替文件名防止重复
            fileName = UUID.randomUUID() + name;
            imagePath = upload + fileName;

/*            // 获取当前工作目录
            Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
            // 拼接文件在服务器上保存的完整路径
            String filePath = currentWorkingDirectory + uploadPath + "/" + fileName;*/
            // 获取当前工作目录
            String imgPath = System.getProperty("user.dir") + uploadPath;
            String filePath = imgPath + fileName;
            // 将文件保存到服务器指定路径
            file.transferTo(new File(filePath));


        } catch (IOException e) {
            e.printStackTrace();

        }
        return imagePath;

    }


}