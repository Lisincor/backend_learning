## 部门查询

controller层

```java
package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //该注解包含了ResponseBody注解,会将相应返回的Result对象转为json格式返回
public class DeptController {

//    private static Logger logger = LoggerFactory.getLogger(DeptController.class);

    @RequestMapping("/depts") //
    public Result list(){
        log.info("全部部门信息");
        return Result.success();
    }

}

```

## 部门增加,部门删除看代码即可





## 分页查询

ps:重点是PageHelper插件的应用

![dd](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251006172301485.png)



## 分页条件查询

大致流程：不重要，具体看代码 ：项目代码在：

C:\Users\19776\Desktop\Backend_learn_note\CodeManagement\Mybatis_springboot_pra\tlias_prac

![image-20251007150408767](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251007150408767.png)



## 删除员工 （动态sql的批量删除）

具体看代码





## 文件上传

大小限制：在application的properities中配置

![image-20251007181114452](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251007181114452.png)



```java
package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.pojo.Result;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/upload")
    //在方法形参中传入文件：MultipartFile image
    //MultipartFile是string提供的API
    public Result upload(String name, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}", name, age, image);

        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（不能重复）-uuid（通用唯一识别码）

        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID().toString() + extname;
        log.info("新的文件名：{}", newFileName);
        //存文件到磁盘中
        image.transferTo(new File("E:\\images\\" +  newFileName ));

        return Result.success();
    }
}

```



## 阿里云OSS

脑子记住就行，云存储不算重要





 ## @Value注解



![image-20251008162658290](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251008162658290.png)

```properties
package com.projectprac.tlias_prac.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

/**
 * 阿里云 OSS 工具类
 */
//交给IOC容器管理
@Component
public class AliOSSUtils {

    private String endpoint = "https://oss-cn-shenzhen.aliyuncs.com";
    private String accessKeyId = "。。。";
    private String accessKeySecret = "。。。";
    private String bucketName = "web-practice1007";

    /**
     * 实现上传图片到OSS
     */
    public String upload(MultipartFile file) throws IOException {
        // 获取上传的文件的输入流
        InputStream inputStream = file.getInputStream();

        // 用uuid避免文件覆盖
        String originalFilename = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //上传文件到 OSS
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);

        //文件访问路径
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + fileName;
        // 关闭ossClient
        ossClient.shutdown();
        return url;// 把上传到oss的路径返回
    }

}

```



## yml文件(大多数项目都是用)

![image-20251008164044913](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251008164044913.png)

![image-20251008183149340](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251008183149340.png)



## @ConfigurationProperties注解

看CodeManagement的application.yml ，AliOSSUtils.java  和 AliOSSProperities.java

