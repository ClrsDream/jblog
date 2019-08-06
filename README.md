
## 基于 Spring Boot 开发的博客系统

#### 安装

下载代码：

```
git clone https://github.com/Qsnh/jblog.git
```

编译：

```
cd jblog
mvn clean install
java -jar -Dspring.profiles.active=prod target/blog-0.0.1-SNAPSHOT.jar
```

> 注意，请根据自己本地的环境配置好 `application-prod.yaml` 配置文件。

#### 技术栈

+ [x] Spring Boot
+ [x] 视图继承
+ [ ] 定时备份
+ [ ] SEO定时提交
+ [ ] Redis全站缓存