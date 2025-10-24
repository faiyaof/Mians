# SpringBoot 3.2.0 Security Demo

这是一个使用SpringBoot 3.2.0和SpringSecurity的演示项目，包含认证保护的HelloWorld API和用户登录功能。

## 功能特性

- SpringBoot 3.2.0
- SpringSecurity认证
- 受保护的HelloWorld API
- 用户名密码登录API
- 内存用户存储（用户名：test，密码：123456）

## 快速开始

### 1. 运行项目

```bash
mvn spring-boot:run
```

项目将在 http://localhost:8080 启动

### 2. API端点

#### 登录API
- **POST** `/api/login`
- 参数：`username=test&password=123456`
- 返回：JSON格式的登录结果

#### HelloWorld API（需要认证）
- **GET** `/api/hello`
- 需要先登录获取认证
- 返回：`"Hello World"`

#### 获取当前用户信息
- **GET** `/api/user`
- 需要认证
- 返回：当前用户信息

### 3. 测试API

#### 使用curl测试登录：
```bash
curl -X POST "http://localhost:8080/api/login" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=test&password=123456"
```

#### 使用curl测试HelloWorld API（需要先登录）：
```bash
# 先登录获取session
curl -c cookies.txt -X POST "http://localhost:8080/api/login" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "username=test&password=123456"

# 然后访问受保护的API
curl -b cookies.txt "http://localhost:8080/api/hello"
```

#### 使用Basic Auth测试：
```bash
curl -u test:123456 "http://localhost:8080/api/hello"
```

## 技术栈

- Java 17
- SpringBoot 3.2.0
- SpringSecurity 6.x
- Maven
- BCrypt密码编码

## 项目结构

```
src/main/java/com/example/springbootsecuritydemo/
├── SpringbootSecurityDemoApplication.java    # 主应用类
├── config/
│   └── SecurityConfig.java                   # SpringSecurity配置
└── controller/
    ├── HelloController.java                  # HelloWorld API控制器
    └── AuthController.java                   # 认证API控制器
```

