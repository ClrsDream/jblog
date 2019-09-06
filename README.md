
## 基于 Spring Boot 开发的文章发布系统

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

#### 主要功能

+ [x] 邮箱登录/注册
+ [ ] 邮箱密码找回
+ [x] 文章管理
+ [x] 文章标签
+ [ ] 站内消息
+ [ ] 文章评论
+ [ ] 文章收藏
+ [ ] SEO定时提交
+ [ ] 数据库定时备份
+ [ ] 多驱动图片上传
+ [ ] 图形验证码
+ [ ] CSRF安全防范
+ [ ] 单元测试
+ [ ] 全文搜索

#### 技术栈

+ Spring Boot / Thymeleaf / Hibernate
+ Mysql / Redis / Quartz
+ Shiro / Makrdown / XssClean
+ Git

#### 扩展包

#### 协议

**MIT**

#### 联系我

