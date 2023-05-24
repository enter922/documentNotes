

## Linux

重载配置文件

source /etc/profile 

关闭进程

kill -9 进程pid 	

查找端口

lsof -i:端口号     
netstat -tunlp | grep 端口号

添加防火墙规则  --permanent永久生效，没有此参数重启后失效

firewall-cmd --zone=public --add-port=9200/tcp --permanent 

重新载入 防火墙

firewall-cmd --reload

查看开发的端口

firewall-cmd --zone=public --list-ports 

开启防火墙

systemctl start firewalld.service

管理员权限添加

sudo chmod -R 777  /工作目录，

后台运行jar 并输出日志

nohup java -jar sentinel-dashboard-1.8.6.jar --serve.port=8090 > mylog.log 2>&1 &

解压tar.gz到指定目录

tar -xvf nacos-server-2.2.0.tar.gz -C /software

创建目录

mkdir dist


Docker



## Sof

单点启动nacos

sh startup.sh -m standalone   

后台运行sentinel Jar包并更改端口输出日志

nohup java   -Dserver.port=8090 -jar sentinel-dashboard-1.8.6.jar > mylog.log 2>&1 &

后台运行 seate 并输出日志

nohup ./seata-server.sh >log.out 2>1 &





## Docker

删除容器 mysql

cocker rm myslq       

启动容器 		59ec

docker start 59ec      

安全停止容器		 59ec

docker stop 59ec      

直接关闭容器		 59ec

docker kill    59ec	  

进入正在运行中的容器

sudo docker attach  容器ID/容器名  

查看镜像

docker images          

查看所有正在运行的容器

sudo docker ps         

查看所有容器

sudo docker ps -a     
#查看Docker的运行状态
systemctl status docker    

启动docker 

systemctl start docker

加入开机启动

systemctl enable docker

停止docker服务

systemctl stop docker

重启docker服务

systemctl restart docker

删除镜像 df1 前需要删除容器

docker rmi df1

进入容器 

docker exec -it mysql /bin/bash



### mysql

```sh
docker run -d \
--name mysql \
-p 3306:3306 \
-v /mydata/mysql/config/mysqld.cnf:/etc/mysql/mysql.conf.d/mysqld.cnf \
-v /mydata/mysql/data/mysql:/var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123112 \
mysql:5.7

2
docker run -p 3306:3306 --name mysql \
-v /mydata/mysql/log:/var/log/mysql \
-v /mydata/mysql/data:/var/lib/mysql \
-v /mydata/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=aassdd: \
-d mysql:5.
```

跟踪docker日志

```shell
docker logs [OPTIONS] CONTAINER   

docker logs -f mysql

[OPTIONS]

-f : 跟踪日志输出

-t : 显示时间戳

--tail :仅列出最新N条容器日志

--since：显示某个日期至今的所有日志

CONTAINER
代表容器ID或容器名称

```



### redis

```sh
docker run \
--restart=always \
--log-opt max-size=100m \
--log-opt max-file=2 \
-p 6379:6379 \
--name redis \
-v /home/redis/myredis/myredis.conf:/etc/redis/redis.conf \
-v /home/redis/myredis/data:/data \
-d redis redis-server /etc/redis/redis.codnf \
--appendonly yes \
--requirepass "123112" 

```

- --name redis 给容器取名
- -v 挂载目录
- -p 端口映射
- –restart=always 在docker启动后启动

> 部分redis变量需要在后

 docker rm -f redis



