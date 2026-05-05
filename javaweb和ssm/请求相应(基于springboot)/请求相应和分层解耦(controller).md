

# 起步依赖

包含了web开发或测试常见的依赖：

web开发启动依赖就包括了tomcat(及内嵌tomcat)

![image-20251011194503061](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251011194503061.png)

# Tomcat介绍以及Web服务器

![image-20251011194315057](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251011194315057.png)

![image-20251011194237231](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251011194237231.png)

# 请求





### 前端控制器（或者叫核心控制器）

1.Spring的DispatcherServlet实现了Servlet规范中的Servlet接口

2.前端发送请求数据，tomcat将数据进行解析并封装到HttpServiceRequest的对象中

3.响应对象HttpServiceResponse同理

![image-20251011212748366](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251011212748366.png)

## 请求头

GET 方法 （Get，Delete等方法一般没有请求体

```http
GET /products/123 HTTP/1.1
Host: api.example.com
User-Agent: Mozilla/5.0 (compatible; MyApp/1.0)
Accept: application/json
Accept-Language: en-US
Connection: keep-alive
Cookie: session_token=abcde12345

```

POST方法

```http
POST /users HTTP/1.1
Host: api.example.com
Content-Type: application/json
Content-Length: 58
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

{"name": "Alice", "email": "alice@example.com"}

```



### 简单参数（springboot方式）

![屏幕截图(1034)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1034).png)

### 实体参数

![屏幕截图(1037)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1037).png)

### JSon参数 @RequestBody

![屏幕截图(1040)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1040).png)

### 路径参数 @PathVariable

![屏幕截图(1042)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1042).png)



## ## [@PathVariable 和 @RequestParam 的区别](https://blog.csdn.net/a15028596338/article/details/84976223)

[1](https://blog.csdn.net/a15028596338/article/details/84976223)[2](https://blog.csdn.net/Trista_1999/article/details/118929719)[3](https://blog.csdn.net/weixin_47872288/article/details/125641885)

在 Spring MVC 中，@PathVariable 和 @RequestParam 是两种常用的注解，用于从请求中获取参数。它们虽然都可以接收参数，但在使用场景和方式上有所不同。

@PathVariable

**@PathVariable** 注解用于从 URL 路径中提取参数。例如，对于 URL *http://localhost:8887/test1/id1/name1*，可以使用以下方式获取参数：

@RequestMapping("test1/{id}/{name}")

public String testPathVariable(@PathVariable String id, @PathVariable String name) {

return "id=" + id + ", name=" + name;

}

![复制](data:image/svg+xml;base64,77u/PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTkiIHZpZXdCb3g9IjAgMCAxOCAxOSIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4NCiAgICA8cGF0aCBkPSJNNC4xMTM2MyAzLjU0MTc0TDQuMTExNTMgNS4xNjY0NVYxMy4yMDU0QzQuMTExNTMgMTQuNTc5IDUuMTk5MjUgMTUuNjkyNiA2LjU0MTAyIDE1LjY5MjZMMTIuOTgyIDE1LjY5MjlDMTIuNzUxIDE2LjM2MTUgMTIuMTI4MSAxNi44NDA2IDExLjM5NTggMTYuODQwNkg2LjU0MTAyQzQuNTc5OTggMTYuODQwNiAyLjk5MDIzIDE1LjIxMzEgMi45OTAyMyAxMy4yMDU0VjUuMTY2NDVDMi45OTAyMyA0LjQxNTkxIDMuNDU5MjcgMy43Nzc1MiA0LjExMzYzIDMuNTQxNzRaTTEzLjI2ODggMS41MzEyNUMxNC4xOTc3IDEuNTMxMjUgMTQuOTUwOCAyLjMwMjE5IDE0Ljk1MDggMy4yNTMxOVYxMy4yMDIyQzE0Ljk1MDggMTQuMTUzMSAxNC4xOTc3IDE0LjkyNDEgMTMuMjY4OCAxNC45MjQxSDYuNTQxMDJDNS42MTIxIDE0LjkyNDEgNC44NTkwNyAxNC4xNTMxIDQuODU5MDcgMTMuMjAyMlYzLjI1MzE5QzQuODU5MDcgMi4zMDIxOSA1LjYxMjEgMS41MzEyNSA2LjU0MTAyIDEuNTMxMjVIMTMuMjY4OFpNMTMuMjY4OCAyLjY3OTIxSDYuNTQxMDJDNi4yMzEzOCAyLjY3OTIxIDUuOTgwMzcgMi45MzYxOSA1Ljk4MDM3IDMuMjUzMTlWMTMuMjAyMkM1Ljk4MDM3IDEzLjUxOTIgNi4yMzEzOCAxMy43NzYxIDYuNTQxMDIgMTMuNzc2MUgxMy4yNjg4QzEzLjU3ODQgMTMuNzc2MSAxMy44Mjk1IDEzLjUxOTIgMTMuODI5NSAxMy4yMDIyVjMuMjUzMTlDMTMuODI5NSAyLjkzNjE5IDEzLjU3ODQgMi42NzkyMSAxMy4yNjg4IDIuNjc5MjFaIiBmaWxsPSIjNzY3Njc2IiAvPg0KPC9zdmc+)

在这个例子中，*@PathVariable* 注解会将 URL 路径中的 *id* 和 *name* 部分提取出来，并赋值给方法参数。

@RequestParam

**@RequestParam** 注解用于从请求参数中提取参数。例如，对于 URL *http://localhost:8887/test2?id=id2&name=name2*，可以使用以下方式获取参数：

@RequestMapping("test2")

public String testRequestParam(@RequestParam("id") String id, @RequestParam("name") String name) {

return "id=" + id + ", name=" + name;

}

![复制](data:image/svg+xml;base64,77u/PHN2ZyB3aWR0aD0iMTgiIGhlaWdodD0iMTkiIHZpZXdCb3g9IjAgMCAxOCAxOSIgZmlsbD0ibm9uZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIj4NCiAgICA8cGF0aCBkPSJNNC4xMTM2MyAzLjU0MTc0TDQuMTExNTMgNS4xNjY0NVYxMy4yMDU0QzQuMTExNTMgMTQuNTc5IDUuMTk5MjUgMTUuNjkyNiA2LjU0MTAyIDE1LjY5MjZMMTIuOTgyIDE1LjY5MjlDMTIuNzUxIDE2LjM2MTUgMTIuMTI4MSAxNi44NDA2IDExLjM5NTggMTYuODQwNkg2LjU0MTAyQzQuNTc5OTggMTYuODQwNiAyLjk5MDIzIDE1LjIxMzEgMi45OTAyMyAxMy4yMDU0VjUuMTY2NDVDMi45OTAyMyA0LjQxNTkxIDMuNDU5MjcgMy43Nzc1MiA0LjExMzYzIDMuNTQxNzRaTTEzLjI2ODggMS41MzEyNUMxNC4xOTc3IDEuNTMxMjUgMTQuOTUwOCAyLjMwMjE5IDE0Ljk1MDggMy4yNTMxOVYxMy4yMDIyQzE0Ljk1MDggMTQuMTUzMSAxNC4xOTc3IDE0LjkyNDEgMTMuMjY4OCAxNC45MjQxSDYuNTQxMDJDNS42MTIxIDE0LjkyNDEgNC44NTkwNyAxNC4xNTMxIDQuODU5MDcgMTMuMjAyMlYzLjI1MzE5QzQuODU5MDcgMi4zMDIxOSA1LjYxMjEgMS41MzEyNSA2LjU0MTAyIDEuNTMxMjVIMTMuMjY4OFpNMTMuMjY4OCAyLjY3OTIxSDYuNTQxMDJDNi4yMzEzOCAyLjY3OTIxIDUuOTgwMzcgMi45MzYxOSA1Ljk4MDM3IDMuMjUzMTlWMTMuMjAyMkM1Ljk4MDM3IDEzLjUxOTIgNi4yMzEzOCAxMy43NzYxIDYuNTQxMDIgMTMuNzc2MUgxMy4yNjg4QzEzLjU3ODQgMTMuNzc2MSAxMy44Mjk1IDEzLjUxOTIgMTMuODI5NSAxMy4yMDIyVjMuMjUzMTlDMTMuODI5NSAyLjkzNjE5IDEzLjU3ODQgMi42NzkyMSAxMy4yNjg4IDIuNjc5MjFaIiBmaWxsPSIjNzY3Njc2IiAvPg0KPC9zdmc+)

在这个例子中，*@RequestParam* 注解会将请求参数中的 *id* 和 *name* 部分提取出来，并赋值给方法参数。



# 相应

## 响应实例

返回一个前端界面

```http
HTTP/1.1 200 OK
Date: Tue, 15 Aug 2023 14:28:00 GMT
Server: Apache/2.4.41
Content-Type: text/html; charset=UTF-8
Content-Length: 1234
Cache-Control: max-age=3600
Connection: keep-alive

<!DOCTYPE html>
<html>
<head><title>Example Page</title></head>
<body><h1>Hello, World!</h1></body>
</html>

```

3

### ResponseBody

Spring会默认认为你返回的是一个需要跳转的目的文件名   加入@ResponseBody是告诉Spring返回值是回写的数据不是跳转的文件名

方法注解，将方法返回值直接相应，如果返回值类型是 实体对象或集合，将会转换为JSON格式

![image-20250928202021836](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250928202021836.png)

### 统一相应结果（设计模式之一）

所有的增删改查操作都要响应一个结果Result(及统一响应结果)

![屏幕截图(1044)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1044).png)

```java
package com.projectprac.tlias_prac.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 代表成功; 0 代表失败
    private String msg;  //响应信息 描述字符串
    private Object data; //返回的数据

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}

```



# 分层解耦

三层架构

![image-20250929174830039](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250929174830039.png)

## IOC&DI介绍

![image-20250930092249825](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250930092249825.png)

IOC&&DI实际操作：

Service和DAO层用@Component实现IOC，将对象加载到Bean容器中，

Controller和service层用@Autowired注解实现DI，将Bean容器的对象赋值给

![image-20250930092933307](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250930092933307.png)

## Bean的声明

![屏幕截图(1058)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1058).png)

bean组件扫描：

将三层组件防止启动类所在包及其子包下

![image-20250930095056299](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250930095056299.png)

依赖注入的注解：

<img src="https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250930095646969.png" alt="image-20250930095646969" style="zoom:33%;" />