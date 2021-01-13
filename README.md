# fast-java-plugin
fast-java-plugin is a fast code generation tool.Contains domains, Controllers, and commonly used service directories.And rely on the fast-java library.   
_Tips: For now, only MongoDB is supported_
## Matches artillery
![](/gif/example.gif)
## fast-java-plugin goals
* Save tedious and repetitive creation operation;
* Quickly create entity objects from JSON strings;
* Eliminate the tedious creation of basic business logic functions.
    > Tips: To support the basic operational functionality of the business scenario, you must rely on the Fast-Java library.   
    [Fast-Java](https://github.com/ve-agui/fast-java)
## code structure
The premise for using the fast-java-plugin is that you should agree with the plugin's directory structure design.   
```
|—— cn.anseon
    |—— controller
       |—— TestController.java
    |—— domain
        |—— db
            |—— TestDO.java
        |—— dto
            |—— TestDTO.java
        |—— vo
            |—— TestVO.java
    |—— service
        |—— impl
            |—— TestServiceImpl.java
        |—— ITestService.java
```
## Plugin Usage
Search the IDEA plugin Fast-Java or download the ZIP plugin.After the installation is complete, right-click the path you want to generate, click New-Fast Class Java, and then enter the name of the business entity Class you want to generate. Click OK. You can also enter the JSON of the entity object you want to generate