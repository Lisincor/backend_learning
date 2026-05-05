# maven

## maven介绍

maven的jar包管理

image-20251001175800758

maven安装和配置步骤

![image-20251001180329159](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001180329159.png)

## 导入maven

![image-20251001183254675](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001183254675.png)

![image-20251001183349778](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001183349778.png)

## 配置依赖

![image-20251001183912328](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001183912328.png)

```java


    <dependencies>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.5.18</version>
        </dependency>
    </dependencies>

```



## 依赖范围



![屏幕截图(1022)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1022).png)



## 生命周期

![image-20251001194235655](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001194235655.png)

install: 安装jar包到本地仓库，使用时在依赖标名jar包

![image-20251001194257494](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001194257494.png)



# Maven高级

## BOM

![image-20260422160807476](C:/Users/19776/AppData/Roaming/Typora/typora-user-images/image-20260422160807476.png)

## 分模块开发



![屏幕截图(1304)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1304).png)

## 可选依赖和排除依赖

可选依赖：消除依赖传递性

**在父依赖中写**

![image-20251020200249412](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020200249412.png)

![屏幕截图(1305)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1305).png)

排除依赖：

**在子依赖中写**

![image-20251020200347843](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020200347843.png)

![image-20251020195718624](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020195718624.png)



## 聚合

![image-20251020201014593](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020201014593.png)

![image-20251020200957913](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020200957913.png)



## 继承



![屏幕截图(1311)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1311).png)

![屏幕截图(1312)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1312).png)

![屏幕截图(1313)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1313).png)

![屏幕截图(1314)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1314).png)

![屏幕截图(1315)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1315).png)



## 属性

这使用的是 **Maven 属性变量**，`${lombok.version}` 是一个**占位符**，它的实际值会在其他地方定义。

### 详细解释：

### 1. **这是什么语法？**

- `${xxx}`：Maven 的**属性引用语法**
- `lombok.version`：属性名
- 实际效果：Maven 在构建时会用真实版本号替换这个占位符

### 2. **这个属性在哪里定义？**

通常会在 `pom.xml` 的以下几个位置之一：

#### 位置A：在 `<properties>` 标签中（最常见）

xml

```
<project>
    <properties>
        <!-- 这里定义属性 -->
        <lombok.version>1.18.30</lombok.version>
        <java.version>17</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>  <!-- 这里引用 -->
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```



#### 位置B：在父 POM 中（继承）

xml

```
<!-- 父pom.xml -->
<project>
    <properties>
        <lombok.version>1.18.30</lombok.version>
    </properties>
</project>

<!-- 子pom.xml -->
<project>
    <parent>
        <groupId>com.example</groupId>
        <artifactId>parent-project</artifactId>
        <version>1.0</version>
    </parent>
    
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>  <!-- 继承自父POM -->
            <scope>provided</scope>
        </dependency>
    </dependencies>
</project>
```

1.定义属性

![image-20251021103312538](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251021103312538.png)

2.引用属性

![image-20251020203540092](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251020203540092.png)



##  多环境开发



![屏幕截图(1318)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1318).png)

![image-20251021105338886](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251021105338886.png)



## 私服

![image-20251021112353167](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251021112353167.png)