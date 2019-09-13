
## **JBlog** - 基于 Spring Boot 开发的文章发布系统

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
+ [x] 邮箱密码找回
+ [x] 文章管理
+ [x] 文章标签
+ [ ] 站内消息
+ [ ] 文章评论
+ [x] 文章喜欢
+ [ ] SEO定时提交
+ [ ] 数据库定时备份
+ [ ] 多驱动图片上传(Native/阿里云oss/腾讯cos)
+ [x] 多驱动邮件发送(SMTP/阿里云)
+ [x] 图形验证码
+ [x] CSRF/XSS
+ [ ] 单元测试
+ [ ] 全文搜索

#### 技术栈

+ Tailwind / Webpack / React
+ Spring Boot / Thymeleaf / Hibernate
+ Git / Mysql / Redis / Quartz / Algolia
+ Shiro / Markdown / Xss Clean

#### 扩展包

| 扩展包 | 应用 |
| --- | --- |
| `org.apache.shiro` | 用户的Authentication，Authorization支持 |
| `spring-boot-starter-data-redis` | 用于session存储 |
| `aliyun-java-sdk-dm` | 阿里云邮件发送，用于用户密码找回的邮件发送，欢迎邮件等 |
| `spring-boot-starter-security` | 用于用户的password字段加密（`BCryptPasswordEncoder`）|
| `org.jsoup` | xss安全过滤 |
| `com.youbenzi.MDTool` | Markdown解析，用于支持用户简介的Markdown语法 |
| `com.github.whvcse.easy-captcha` | 图片验证码 |

#### 注解

| 注解 | 作用 |
| --- | --- |
| `@LoginRedirect` | 已登录检测，已登录完成之后跳转到首页 |
| `@PostReadNumInc` | 文章阅读量+1 |
| `@CaptchaImageVerify` | 图片验证码校验 |

#### 前端样式

| 组件 | class |
| --- | --- |
| 主背景色 | `bg-gray-800` |
| 次背景色 | `bg-gray-900` |
| 主字体颜色 | `text-gray-500` |
| 突出字体颜色 | `test-gray-400` |
| 按钮 | `block w-full py-2 text-gray-500 border-2 border-gray-700 rounded-lg hover:bg-gray-700 hover:border-gray-600` |
| 输入框 | `w-full p-2 mt-2 mb-2 bg-gray-500 border-2 border-gray-700 rounded-lg focus:outline-none text-gray-800 placeholder-gray-800` |

#### License

**JBlog** 采用 MIT 开源许可证，你可以在商业项目中免费使用 **JBlog** 或者基于 **JBlog** 二次 开发而不必支付费用。

#### 联系我

<p>
<img src="https://user-images.githubusercontent.com/12671205/64419962-a1edc500-d0d0-11e9-8662-e57ab8382078.jpg" width=300>
<img src="https://s1.ax1x.com/2018/09/29/ilrhKP.png" width=300>
</p>
