# hutool

引入依赖

```java
         <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.7.17</version>
        </dependency>
```



## 1. 拷贝不同对象的属性：BeanUtil.copyProperties(..,T.class)

![image-20251107141526807](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251107141526807.png)



## 2. 将JSON字符串数据转化为对象

```
//3.如果存在，直接返回
// 将json字符串数据，转换为Shop对象
Shop shop = JSONUtil.toBean(shopJson, Shop.class);
```



# CountDownLatch

## 代码整体逻辑

这是一个测试 Redis ID 生成器性能的代码，模拟**300个线程**并发生成 ID，每个线程生成**100个ID**，总共生成 **30,000个ID**。

## CountDownLatch 详解

### 1. 创建 CountDownLatch

java

```
CountDownLatch latch = new CountDownLatch(300);
```



- 创建计数器，初始值为 **300**
- 表示需要等待 **300个任务** 完成

### 2. CountDownLatch 工作原理

java

```
// 在每个任务完成时调用
latch.countDown();  // 计数器减1

// 在主线程等待
latch.await();      // 阻塞直到计数器为0
```



# ExecutorService线程池

## 代码结构分析

java

```
private ExecutorService es = Executors.newFixedThreadPool(500);
```



### 1. `ExecutorService` - 线程池接口

- Java 并发编程的核心接口
- 用于管理和执行多个线程任务
- 提供了提交任务、关闭线程池等方法

### 2. `Executors.newFixedThreadPool(500)` - 创建固定大小线程池

- `Executors` 是线程池的工厂类
- `newFixedThreadPool(500)` 创建一个固定包含 **500个线程** 的线程池
- 线程数量固定，不会自动扩容或缩容

## 完整示例代码

java

```
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    // 创建包含500个线程的固定大小线程池
    private ExecutorService es = Executors.newFixedThreadPool(500);
    
    public void processTasks() {
        // 提交任务到线程池
        for (int i = 0; i < 1000; i++) {
            final int taskId = i;
            es.submit(() -> {
                // 任务逻辑
                System.out.println("执行任务: " + taskId + ", 线程: " + Thread.currentThread().getName());
            });
        }
    }
    
    public void shutdown() {
        // 优雅关闭线程池
        es.shutdown();
    }
}
```



## 线程池工作原理

### 任务执行流程：

1. **提交任务** → `es.submit(Runnable task)`
2. **线程分配** - 如果有空闲线程，立即执行
3. **队列等待** - 如果所有线程都在忙，任务进入等待队列
4. **执行完成** - 线程空闲后从队列获取新任务



# String

转换成字符串String.valueOf(long)



# Boolean

```
Boolean.TRUE.equals(success) //如果success是null，那么自动拆箱后会出错，调用api后如果success是null，也会返回fasle
```



# @PostConstruct

在 Java 中，*@PostConstruct* 是一个注解，通常用于标记一个方法，该方法会在类实例化之后（通过构造函数创建对象之后）立即执行。这个注解在 Spring 框架中非常常见，用于确保在对象的所有依赖项都已经注入完成之后执行一些初始化操作