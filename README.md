#### 🌈 介绍
🎉🎉🔥Fast-Java-Plugin是一个快速代码生成工具的IDEA 插件，支持MySql、MongoDB数据库。
> 为什么会有这个插件：
>> 试想一下，开发过程中，是不是避免不了增删改查，新增一个功能，往往要重复性的不停的去创建save、update、delete、list、page等等接口。   
>> 
>> 难吗？不难，恶心吗？恶心。
>>
>> 所以，也就有了<a target="_blank" href="https://gitee.com/wxson/easy-cloud-admin.git">Fast-Java-Plugin</a>插件。
#### 💒 优势
* 节省繁琐和重复的创建操作;
* 免去建表创建对象的繁琐逻辑，通过JSON字符串就可实现整个功能的业务逻辑;
* 效率提高，打个比方，创建一个用户管理的功能，实现传统的增删改查逻辑功能，调试。快的话10分钟要吧？使用<a target="_blank" href="https://gitee.com/wxson/easy-cloud-admin.git">Fast-Java-Plugin</a>插件，1分钟搞定，且逻辑基本正确，不符合的逻辑部分轻微改动适配即可。
#### 💝️ 预览
![](/gif/example.gif)
#### ⛱️ 操作
> 请注意：如果要使用该插件，那么，您必须要认同插件所默认的业务层级结构以及命名方式。
* 1、Idea搜索插件Fast-Java安装或下载安装包进行离线安装；
* 2、点击要生成的业务逻辑目录，比如com.easy.cloud.upms.biz，右键——>新建——Fast Java Class；
* 3、在弹出框中输入实体类名称，如：User，内容框中输入实体类的JSON字符串，点击确定，即可得到以下目录结构代码；
```
|—— com.easy.cloud.upms.biz
    |—— controller
       |—— UserController.java
    |—— domain
        |—— db
            |—— UserDO.java
        |—— dto
            |—— UserDTO.java
        |—— vo
            |—— UserVO.java
    |—— converter
        |—— UserConverter.java
    |—— repository
        |—— UserRepository.java
    |—— service
        |—— impl
            |—— UserServiceImpl.java
        |—— IUserService.java
```
#### 💌 支持作者
如果觉得框架不错，或者已经在使用了，希望你可以去<a target="_blank" href="https://gitee.com/wxson/easy-cloud-admin.git">Gitee</a> 或 <a target="_blank" href="https://github.com/wxson/easy-cloud-service.git">GitHub</a> 帮我点个 ⭐
Star，这将是对我极大的鼓励与支持。也可以一起完善该插件，让他更完善。