package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.pojo.Result;
import com.projectprac.tlias_prac.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploaderController {

    @Autowired
    private AliOSSUtils aliOSSUtils ;
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传：{},{},{}", name, age, image);
//
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//
//        //构造唯一的文件名（不能重复）-uuid（通用唯一识别码）
//
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        log.info("新的文件名：{}", newFileName);
//        //存文件到磁盘中
//        image.transferTo(new File("E:\\images\\" +  newFileName ));
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传的文件：{}",image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功,url：{}",url);
        return Result.success(url);
    }
}
