# Spring 整体框架

注意：容器，AOP，Data

![屏幕截图(1239)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1239).png)、

学习路线：

![image-20251016174754642](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251016174754642.png)



## 1.IoC和DI入门案例：

![屏幕截图(1241)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1241).png)

```java
```



DI：

![屏幕截图(1242)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1242).png)

![image-20251016191217274](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251016191217274.png)



## 2.bean的实例化

### 构造方法实例化bean

构造方法：创建bean对象时运用的是无参的构造方法，且用了反射机制

无参的构造方法如果不存在，会出现BeanCreationException

### 工厂模式（重点）

工厂bean的实现类

![image-20251016214454630](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251016214454630.png)

**配置**

![image-20251016214615828](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251016214615828.png)



## 3.bean的生命周期（不重要）

![屏幕截图(1255)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1255).png)



![屏幕截图(1256)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1256).png)



## 4.注入方式（个人开发推荐使用setter注入）

### setter注入： <property name = "xxx"

简单类型的注入用 value = ，引用类型的注入用 ref

![image-20251017133247173](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017133247173.png)

![image-20251017133227643](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017133227643.png)

### 构造器注入 

代码：

![屏幕截图(1259)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1259).png)

配置文件：

![image-20251017133601411](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017133601411.png)



### 自动装配

setter不要删除

![image-20251017134833368](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017134833368.png)

![image-20251017134841695](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017134841695.png)



### 集合注入 （了解即可，一些框架会用）

![image-20251017194711828](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017194711828.png)



## 5.数据源对象管理

![image-20251017200234154](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017200234154.png)



## 6.resouce的xml中加载properties文件（麻烦，白雪）

![image-20251017201059482](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017201059482.png)



## 7.容器 ：springframework的Bean继承图

spring

![image-20251017201703164](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017201703164.png)

## 8.注解开发定义bean

@Component 自动提交对象到IOC容器，不用在xml中配置

![image-20251017203328638](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017203328638.png)

## 9.配置类注解来喽

@Configuration 设定配置类，代替创建bean的xml文件

@ComponentScan 设定包扫描路径

![image-20251017203154148](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017203154148.png)

### 在配置类上加载其他的配置文件

![image-20251018102309606](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251018102309606.png)

![屏幕截图(1272)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1272).png)

## 10.Bean的生命周期

@PostConstruct 是在构造方法后，@PreDestroy是在销毁前

![image-20251017203644070](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017203644070.png)



## 11.注解开发的依赖注入

![image-20251017210921891](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017210921891.png)

@Qualifier 与 @Autowired一起使用，指定IoC容器中bean对象的ID

@Value 进行简单类型的注入



## 12.注解开发管理第三方Bean

在第三方的方法上加@Bean注解，方法的返回值就是Bean对象

@Import(第三方类名)导入到核心配置类中

![image-20251017213237550](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017213237550.png)

比如说Druid的配置

![image-20251017213511018](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017213511018.png)



## 13.第三方Bean注入资源

如果第三方bean需要依赖其他对象，直接在bean定义方法中声明形参即可，容器会根据类型自动装配

![image-20251017214531897](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017214531897.png)

![image-20251017214833719](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017214833719.png)



简单类型（值类型）注入：

![image-20251017214907666](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251017214907666.png)

**例子**

`@Bean("RocketMqTemplate")` 中双引号内的内容 **指定了Bean的名称**。让我详细解释：

### 1. **核心作用：Bean的标识符**

双引号内的 `"RocketMqTemplate"` 是：

- Bean在Spring容器中的**唯一名称**
- 在其他地方注入时使用的标识符
- 替代默认的Bean名称生成规则

### 2. **使用场景对比**

### 默认命名（无名称参数）

java

```
@Bean
public RocketMQTemplate rocketMQTemplate() {
    return new RocketMQTemplate();
}
// 默认名称：方法名 "rocketMQTemplate"
```



### 自定义命名

java

```
@Bean("RocketMqTemplate")  // 指定自定义名称
public RocketMQTemplate createTemplate() {
    return new RocketMQTemplate();
}
// 名称："RocketMqTemplate"（与方法名无关）
```



### 3. **注入时的对应关系**

### 按名称注入

java

```
@Component
public class MyService {
    @Autowired
    @Qualifier("RocketMqTemplate")  // 匹配@Bean指定的名称
    private RocketMQTemplate template;
    
    // 或者使用@Resource
    @Resource(name = "RocketMqTemplate")
    private RocketMQTemplate template2;
}
```



### 按类型注入（当只有一个该类型的Bean时）

java

```
@Autowired
private RocketMQTemplate rocketMQTemplate;  // Spring会自动匹配
```



### 4. **为什么需要自定义名称？**

**场景1：多个同类型Bean**

java

```
@Configuration
public class MqConfig {
    
    @Bean("orderMqTemplate")
    public RocketMQTemplate orderTemplate() {
        // 订单业务的MQ模板
    }
    
    @Bean("paymentMqTemplate")  // 需要不同名称区分
    public RocketMQTemplate paymentTemplate() {
        // 支付业务的MQ模板
    }
}

// 使用时明确指定
@Autowired
@Qualifier("orderMqTemplate")
private RocketMQTemplate orderTemplate;
```



**场景2：遵循命名规范**

- 团队规范要求特定命名
- 与第三方库命名保持一致
- 避免默认生成的不直观名称

**场景3：避免名称冲突**

java

```
@Bean("RocketMqTemplate")  // 即使方法名改变，Bean名称不变
public RocketMQTemplate createMQTemplate() {
    return new RocketMQTemplate();
}
```

## 14.Spring 整合Mybatis

没什么好说的,就是 8和9的内容整合然后加上mybatis的API

![image-20251018104827972](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251018104827972.png)

![屏幕截图(1281)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1281).png)

![屏幕截图(1282)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1282).png)



# AOP 

在javaweb的事务管理笔记里
