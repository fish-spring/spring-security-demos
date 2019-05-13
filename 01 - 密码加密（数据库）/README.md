## 使用手册

1、创建数据库
```
create database `security01` default character set utf8mb4 collate utf8mb4_general_ci;
```

2、执行 `user.sql`

3、执行 `mvn spring-boot:run`

4、注册账号 http://localhost:8080/register

5、登录 http://localhost:8080/login

## 相关资料

- https://docs.spring.io/spring-security/site/docs/5.1.4.RELEASE/reference/htmlsingle/#spring-security-crypto-encryption

默认就是{noop}sdfsd 的格式，我们只需要加密，

另一步是SpringSecurity 自动帮我们做的。。。这就很烦躁了
其实我不太喜欢这种隐式规则的东西