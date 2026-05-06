# Docker

## 下载镜像：sudo docker pull nginx

![image-20251221214052448](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221214052448.png)



## 运行容器(创建容器)

run创建新容器

![image-20251221213918208](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221213918208.png)

**后台运行** -d

**端口映射：将宿主机和容器内的网络接口进行映射 **-p

![image-20251221214415923](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221214415923.png)

## 挂载卷

**-v 宿主机文件:容器内文件** (绑定挂载)

![屏幕截图(1723)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1723).png)

**命名卷挂载**

docker创建命名卷

![image-20251221220900894](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221220900894.png)

查看命名卷的文件的位置

![屏幕截图(1725)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1725).png)



删除命名卷：sudo docker volume rm 命名卷名字 



![屏幕截图(1726)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1726).png)



## --name 自定义容器名字

![image-20251221221927650](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221221927650.png)

## -it 和 --rm 

-it 使控制台进入容器

--rm 在容器停止后就把容器删除

![image-20251221222116745](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221222116745.png) 

## --restart always 



![image-20251221222332491](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221222332491.png)



## sudo docker ps -a 查看已有的不管是否停止的容器



## 启动容器和停止容器

sudo docker start peaceful_yalow

sudo docker stop 容器ID:容器名字



## 查看容器日志：sudo docker logs 容器ID -f (-f)是滚动追踪

![image-20251221224146868](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221224146868.png)



## 容器调试

进入容器的运行环境

![image-20251221230344258](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251221230344258.png)