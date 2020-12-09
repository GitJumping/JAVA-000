学习笔记
```shell
docker search mysql
docker pull mysql:5.7
```

# 作业1:
## 在docker启动两个mysql数据库
```shell
docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=root  -p 3306:3306 mysql:5.7
docker run -d --name mysqlrep -e MYSQL_ROOT_PASSWORD=root  -p 3307:3306 mysql:5.7
```
```sql
create database demo_ds_0;
create database demo_ds_1;
```