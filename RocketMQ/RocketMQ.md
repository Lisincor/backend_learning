# 第一天 

## 07. RocketMQ工作原理

![image-20251209145723418](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251209145723418.png)

心跳测试，消息类型的实体类和命名服务器集群：

心跳测试：测试是否还连接

实体类消息：Message类

​                      topic和tags标签：

​													topic在broker中的分配

![image-20251209150124911](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251209150124911.png)



## 08. rocketmq的启动和测试

![image-20251209200352477](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251209200352477.png)

start mqnamesrv.cmd 

start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true

**测试**： tools.cmd org.apache.rocketmq.example.quickstart.Producer

​			tools.cmd org.apache.rocketmq.example.quickstart.Consumer

![image-20251211165656108](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211165656108.png)



## 10.简单生产者书写

![image-20251211170711259](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211170711259.png)

## 11. 简单消费者书写

![屏幕截图(1682)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1682).png)

# 第二天

## 01.多消费者模式：广播模式和集群模式

负载均衡(默认): 消息总数/消费者数量

广播模式: 每个组内的消费者都收到所有消息

![image-20251211212404476](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211212404476.png)

![image-20251211212348950](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211212348950.png)



## 03. 同步，异步，单向消息类型

同步：正常流程



异步消息：及时性较弱，但需要有回执

操作：生产者的send方法中new一个接口

![image-20251211220611273](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211220611273.png)

单向消息：单向发送，没有返回值

![image-20251211220724745](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251211220724745.png)



## 04延时消息

![image-20251213202632266](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213202632266.png)

![image-20251213202620600](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213202620600.png)

## 05.批量消息

![image-20260225153344667](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260225153344667.png)

**注意事项**

![image-20251213204204603](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213204204603.png)



## 06.tags过滤消息

 生产者：

![image-20251213205721760](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213205721760.png)

消费者

![image-20260303205612930](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303205612930.png)

## 07.属性追加和属性过滤（或者叫sql过滤

启动RocketMQ的sql过滤配置：mqadmin.cmd updateBrokerConfig -blocalhost:10911 -kenablePropertyFilter -vtrue

追加属性：key-value对，都用字符串包裹

![image-20251213224105940](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213224105940.png)

sql过滤

![image-20251213223951502](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251213223951502.png)

# 第三天

## 01.springboot整合 -消费者

Json字符串转换

![image-20251216172608766](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216172608766.png)

## 02.boot -消费者 

看代码

![image-20251216172720081](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216172720081.png)



## 03.其余设置

同步消息，异步消息，单向消息，延时消息，批量消息

![屏幕截图(1696)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1696).png)

tag过滤，sql过滤

![image-20251216183446056](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216183446056.png)



## 04.顺序消息错乱演示

![image-20251216190427495](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216190427495.png)

想要的效果

![image-20260226161247284](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260226161247284.png)

## 05 顺序消息发送演示

生产者：

在send方法中new一个queueSelector，筛选

![image-20251216193743626](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216193743626.png)

消费者

![image-20251216193708577](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216193708577.png)

![image-20251216193648003](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251216193648003.png)



## 06事务消息流程 

正常事务， 事务补偿

![image-20260302222852874](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260302222852874.png)

事务消息的状态

![image-20260302223009367](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260302223009367.png)



## 07.事务消息发送演示

看代码：

![image-20260302234637290](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260302234637290.png)



## 08.事务回滚演示

看代码

![image-20260302235203515](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260302235203515.png)

## 09.事务补偿过程

看代码

![image-20260303194317677](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303194317677.png)

# 第四天 集群

## 2. rocket集群介绍

nameserver的topic记住了broker节点的ip

![image-20260303201134666](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303201134666.png)

### 工作流程

![image-20260303201531819](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303201531819.png)



## 3.双主双从集群搭建

主节点与副本节点放在不同机子上

![image-20260303210657879](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303210657879.png)



## 4. 虚拟机配置

所属集群名字：同一个集群

![image-20260303223514972](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303223514972.png)

brokerRole：slave

![image-20260303223822558](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303223822558.png)

## 5.两台虚拟机配置 （配置，没有相关资料，没必要看，视频过一遍）

## 6. 启动集群（看截图复习）

集群控制台：

![image-20260303225640500](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303225640500.png)

## 7. 集群测试（没东西）

## 8.高级特性-消息存储特性

消息存储特性：

数据的持久化操作

![image-20260303235723851](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260303235723851.png)

## 9.高级特性-高效读写的两个特性

1.零拷贝

2.预留空间为了顺序读写，加快读写速度

![image-20260304113410959](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304113410959.png)



## 10.高级特性-消息存储的物理地址

xiaoxi

![image-20260304115639006](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304115639006.png)

commitlog :存消息

consumequeue：记录每个消费者消费到哪个队列的哪条消息中

index：记录各种东西

![image-20260304115622206](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304115622206.png)

## 11.高级特性-刷盘机制

同步刷盘

![image-20260304120918427](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304120918427.png)

异步刷盘

![image-20260304122618046](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304122618046.png)

刷盘的配置

![image-20260304122635175](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304122635175.png)

## 12.高级特性-高可用和主从数据复制

高可用

![屏幕截图(1937)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1937).png)



![image-20260304131922127](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304131922127.png)

主从数据复制：同步复制和异步复制

![image-20260304132207835](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304132207835.png)

配置：brokerRole=

![image-20260304132308908](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304132308908.png)



## 13.高级特性-负载均衡

producer负载均衡：轮巡发送

![image-20260304133443038](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304133443038.png)

consumer负载均衡：循环平均分配

![image-20260304133405283](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304133405283.png)



## 14.高级特性-消息重试

顺序消息

![image-20260304134106050](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304134106050.png)

无序消息

![image-20260304134127028](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304134127028.png)



死信队列

![屏幕截图(1945)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1945).png)

![image-20260304134258660](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304134258660.png)

## 15.高级特性-消息重复消费

消息重复消费的原因

![image-20260304135101758](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304135101758.png)

解决办法：消息幂等

![image-20260304135138764](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260304135138764.png)
