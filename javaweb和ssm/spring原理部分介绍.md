## bean的管理

### 获取bean对象

![屏幕截图(1207)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1207).png)

![image-20251013142312078](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013142312078.png)



### bean的作用域

bean对象默认单例

@Lazy： 延迟初始化，延迟到第一次使用时再初始化bean对象

@Scope("prototype")

![image-20251013142542327](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013142542327.png)



### 第三方bean

如果第三方bean需要依赖其他bean对象，直接在bean定义方法中设置形参即可，容器会根据类型自动装配

![image-20251013143651772](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013143651772.png)



# Springboot原理

### 0.配置类介绍

 博客详解：[java配置类init_mob649e816a77bf的技术博客_51CTO博客](https://blog.51cto.com/u_16175524/12070522)

- *@Configuration*：声明一个类作为配置类，告诉Spring这是一个配置文件的类。
- *@Bean*：用于声明一个方法，该方法将返回一个对象，这个对象会注册为Spring应用于上下文中的bean。方法名默认就是bean的名称。

## 1.自动配置概述

@Componet注解下的类想成为IOC容器的bean，需要被Spring组件扫描到

@SpringBootApplication注解有扫描作用

spring扫描bean对象时，只扫描@SpringBootApplication当前目录的包及其子包

![image-20251013152219591](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013152219591.png)



### 解决方案一：

![image-20251013152644166](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013152644166.png)

### 解决方案四：

第四种方式就是springboot使用的自动配置方案：

在启动类上使用@EnableXxxx注解，该注解封装了@Import；

![屏幕截图(1325)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1325).png)

自定义（或者第三方）@EnableXxxx注解：

![屏幕截图(1326)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1326).png)



![image-20251013153428007](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013153428007.png)



**接下来介绍@Import注解 （也就是导入ImportSelector 接口是实现类的方式）**

一个ImportSelector接口实现类，重写方法的返回值是第三方类的全类名的字符串数组。把想加载的类都写在一个文件中，然后读取文件将文件的内容转换成字符串，就可以实现大量类的加载

<img src="https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1216).png" alt="屏幕截图(1216)" style="zoom: 200%;" />

在启动类上使用@Import导入ImportSelector实现类

![image-20251013153814307](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013153814307.png)



## 2. 自动配置原理

springboot启动类源码跟踪

![屏幕截图(1221)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1221).png)

在启动类上使用@EnableXxxx注解，该注解封装了@Import，@Import注解导入了ImportSelector接口的实现类，该实现类使用selectImports方法，该方法返回值是字符串数组，数组中封装的是要导入IOC容器的类的全类名

![image-20251013161158785](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013161158785.png)

## 3. @Conditional

![image-20251013164459576](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013164459576.png)

第三个注解的情况：

![屏幕截图(1225)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1225).png)



## 4.自定义starter（难点）

autoconfigure模块是核心

![image-20251013175141569](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//image-20251013175141569.png)