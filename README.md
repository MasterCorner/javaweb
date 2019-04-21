# javaweb
a practice web project.

## 课程要求
- 按要求编写课程设计的README.md文档

## 项目名称
待定

## 项目简介
主要跟随谷粒学院教学做一个Javaweb

## 基本信息
- No: 222015325012065
- Name: 王睿敏
- web: [一个登录界面](http://47.102.203.124:8080/javaweb)

## 第一周完成内容
- 模拟老师设置的开发环境
- 仿照老师的代码写一个登录页面
- 登陆页面可以返回结果，正确账号为：admin，密码为：123456
- 网页可以记录访问者填写的用户名，密码，和访问页面时真实的ip地址，保存默认相对路径中，然而默认地址是在tomcat/bin/下面

## 第二周完成内容
- 考虑能否使用mysql数据库代替原有写在xml文件里的默认值
- 在保持上周功能的前提下完成数据库的连接，可以使用本地服务器和远程服务器
- 测试账户更改为为"admin"-"12345"和“tom”-“1234”

## 第三周完成内容
- 保持之前的功能并增加记录访问者填写表单的时间
- 在学习了JSP后通过请求转发将原来的验证返回转发到一个新的JSP文件中
- 在返回的JSP文件中调用request得到用户输入的用户名
- 解决转发时的中文乱码问题，但是在注释掉PrintWriter out=response.getWriter();后就好了，没找到其他方法

## 第四周完成内容
- 以为把测试账号写在md里老师会看，没想到老师并不看，难受，这次更新在了网页上，同时使网页安全性降低
- 在学习了MVC后使用MVC原本想在登陆成功界面写一个MVC，但发现课程讲了两种，所以准备写两种MVC，注册一个，登陆成功界面一个，本周只写注册的，在花去大量时间，克服各项困难后终于能用了。那就这样了，清明找个周末完善了注册就可以了
- 现在只有连接，查看，删除，等待完善添加

## 第五周完成内容
- 完成DAO层设计和实现
- 两种方法实现多个请求对应一个Servlet

## 第六周完成内容
- 实现insert
- 实现update
