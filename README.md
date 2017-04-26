# 说明

## 类

### controller

* MessageServlet 交互的全局控制器
* BackendServlet 后台（可选）

### dao

* AppConfDao 全局配置信息存取，目前能想到的只有个access_token
* CourierDao 快递员数据库访问
* QrCodeDao 二维码生成/存取
* SimulatedSession 模拟session接口
* UserDao 用户数据库访问

### global

* UserState 状态机状态常量

### service

* CourierService 快递员业务接口
* InitService 绑定分组前的业务逻辑接口
* UserService 用户业务接口

### util

* AccessUtil 公众号接入测试/获取accessToken等操作工具类
* NetworkIOUtil 和网络IO相关的辅助工具类

### src/main/resources

三个按钮布局json文件

## 注意

1. 觉得包不够，可以自己再加包
2. 注意代码规范，写完ctrl+shift+f
3. 不要改项目的目录结构，maven的web项目结构百度上有
4. git上传注意.gitignore
5. 注意编码统一utf8，尤其是windows下的eclipse
6. 需要改动别人代码时通知一声

## 重要！！

### 4-02

* domain.Session修改，添加了dataMap属性，删除了validate_tempstr属性
* SimulatedSession接口添加了removeSession方法
* dao.SimulatedSession接口修改，生成二维码函数添加了字符串属性（根据字符串生成二维码）
* 三个Service接口修改，sendMessage函数全部忘了一个String参数

解释：session中的临时变量不止有validate_temp，可能随着业务需求增加，还会有其他的需要添加，所以把此类临时变量以键值对形式全部存入dataMap，DAO实现上可以选用类序列化。

### 4-26

* Courier实体类修改，我忘了写name属性
