# 补充

adivisor传入lambda表达式修改Advisor属性 chatConversationId

advisors.(adspec -> adspec.param("chat_memory_conversation_id", chatId))

![image-20260402130914569](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402130914569.png)

# 大模型接入

## 调用大模型的四种方式

1和2 最直接

3和4调用框架

![image-20260330173611118](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330173611118.png)



# 3. AI应用开发

## 应用方案设计



![image-20260330214139287](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330214139287.png)

### 1.系统提示词 

系统预设，few-shot策略

### 2. 多轮对话实现

使用Spring AI框架的 对话记忆功能，在这之前认识一下Spring AI的一些API和特性：

#### ChatClient, Advisor

![image-20260330214541915](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330214541915.png)

![image-20260330214634871](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330214634871.png)



#### ChatMemoryAdvisor 和 ChatMemory

![image-20260330214912687](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330214912687.png)

![image-20260330215211105](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260330215211105.png)



#### 实际代码

新版本的Spring AI的ChatMemory集成了内存记忆的功能：

第一步：配置ChatMemory，为Bean

![image-20260331175533817](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260331175533817.png)

第二步：编写App

![image-20260331175306349](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260331175306349.png)



## 扩展知识

### 1. 自定义Advisor (用到的时候再学，教程的Spring AI版本和现在不同，有些代码不一样)

最佳实践

![image-20260331210703318](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260331210703318.png)

### 2.结构化输出

FormatProvider  和 Converter

![image-20260331212715507](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260331212715507.png)

代码示例：

```java
 record LoveReport(String title, List<String> suggestions) {
    }

public LoveReport doChatWithReport(String message, String chatId) {
    LoveReport loveReport = chatClient
            .prompt()
            .system(SYSTEM_PROMPT + "每次对话后都要生成恋爱结果，标题为{用户名}的恋爱报告，内容为建议列表")
            .user(message)
            .advisors(spec -> spec.param(CHAT_MEMORY_CONVERSATION_ID_KEY, chatId)
                    .param(CHAT_MEMORY_RETRIEVE_SIZE_KEY, 10))
            .call()
            .entity(LoveReport.class); // 传入对象
    log.info("loveReport: {}", loveReport);
    return loveReport;
}

```

### 3. 对话记忆持久化

编写基于文件持久化的对话记忆类 FileBaseChatMemory

![image-20260401200357002](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260401200357002.png)

在ChatClient传入自定义的ChatMemory实现类(FileBaseChatMemory)

![image-20260401200507428](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260401200507428.png)

# 4. RAG 基础

![image-20260401195934711](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260401195934711.png)



## RAG 实战：Spring AI + 本地知识库

### 一 . 文档准备

读取本地md文件

### 二.文档读取

![image-20260401215223684](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260401215223684.png)

### 三.向量转换和存储

通过Embedding模型将文档转换成向量

Spring AI 内置的 VectorStore 基于内存读写的向量数据库

![image-20260402124939294](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402124939294.png)



### 四.查询增强

![image-20260402160555292](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402160555292.png)

![image-20260402160610512](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402160610512.png)

上面的那个代码已经过期，用 RetrievalAugmentationAdvisor

![image-20260402173110917](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402173110917.png)

## Spring AI + 云知识库

第一步准备云知识库：

![image-20260402170606526](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402170606526.png)

第二步：Rag开发

创建RagCloud配置类，生成云知识库的Bean

DashScopeApi的创建用builder模式，符合版本规范

DocumentRetriever 类用Spring AI Alibaba

![image-20260402170654450](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402170654450.png)

在开发中使用Advisor的Bean

![image-20260402171003286](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402171003286.png)



# 5.RAG进阶

## ETL 中 Transform的组件



![image-20260402232348929](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402232348929.png)



![image-20260402232909034](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402232909034.png)

## 向量转换和存储

存储到PostgreSQL的向量数据库中：

配置信息：

![image-20260403160926142](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260403160926142.png)

VectorStore注入

![image-20260402233246432](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260402233246432.png)

## 文档过滤和检索

## 查询增强和关联



# 5. RAG最佳实践和调优

## 文档收集和切割

### 1.优化原始文档：内容结构化等等

### 2.文档切片：一般用云知识库的智能算法切片 

### 3.元数据标注

 1.手动标注（单个文档

![image-20260403184149924](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260403184149924.png)

  2.代码标注

![image-20260403184044531](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260403184044531.png)

#### 3.Keyword增强器

KeywordMetadataEnricherl类 实现了 DocumentTransformer接口

![image-20260403185349083](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260403185349083.png)

​		应用：

![image-20260404200703882](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260404200703882.png)



## 向量转换和存储

## 文档过滤和检索

### 1.	多查询扩展

### 2. 查询重写

查询重写效果演示

![image-20260404203126684](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260404203126684.png)



# 6.工具调用

![image-20260405161711338](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405161711338.png)



![image-20260405161720000](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405161720000.png)



![image-20260405162125071](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405162125071.png)

![image-20260405162442892](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405162442892.png)



![image-20260405162711474](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405162711474.png)

![image-20260405162726625](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405162726625.png)



# 7.MCP服务

Spring AI从 mcpServer.json配置文件读取mcp服务器，自动注入



![image-20260405222453751](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260405222453751.png)



## MCP开发，具体看编程导航的笔记



# 9.AI服务化

## 后端开发：

