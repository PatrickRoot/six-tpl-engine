#SixTemplateEngine

    竟然被 git@osc 推荐了，好开心
    
初步设想是使用Juicer和Java中的js引擎来生成文本。

只要定义好Juicer支持的模板再加上Json格式的数据，就能生成文本。

使用JavaFx套了一个GUI界面，可以界面化操作。

###已完成：
1. 使用Java和Juicer开发，从模板和json数据生成文本。
2. 重构代码，现在有两个方法可以调用。一种是从String生成String，第二种是从文件生成文件。
3. 使用JavaFx编写界面，可以使用界面方式运行。

###GUI的使用方法：
1. 下载[SixTemplateEngineBin.jar][jar]，运行。

###库的使用方法：
1. 下载[SixTemplateEngine.jar][jar]，引用。
1. 使用String生成String，调用`generateFromString`方法。
```
    String generateFromString(String tpl,String json)
    
    tpl - 模板字符串。
    
    json - json格式的字符串。
    
    返回值 - 生成的字符串。
```
2. 从文件生成文件，调用`generateFromString`方法。
```
    String generateFromString(String tplFileName, String dataFileName, String resultFilePath)
    
    tplFileName - 模板文件路径
    
    dataFileName - json数据文件路径
    
    resultFilePath - 保存结果的文本文件的路径
    
    返回值 - 生成的字符串。
```

###以后的计划：
1. 分成两个maven module，库那一部分单独一个module，其他人可以引用，GUI界面的一部分一个module，调用库。
2. 看看maven 中央仓库能不能上传这个东西。


###可能的计划:
1. 如果以后nw.js会用了，可能改为nw.js完全不用Java代码。

###其他：
1. 项目使用了GPL v3授权协议。
1. 项目使用了开源项目[Juicer][Juicer]，Juicer使用了Apache授权协议。感谢Juicer。
>Juicer 是一个高效、轻量的前端 (Javascript) 模板引擎，使用 Juicer 可以是你的代码实现数据和视图模型的分离(MVC)。 除此之外，它还可以在 Node.js 环境中运行。

[Juicer]: http://juicer.name/  "Juicer 官方网站"
[JuicerDoc]: http://juicer.name/docs/docs_zh_cn.html  "Juicer 中文文档"
[jar]: http://git.oschina.net/nianqinianyi/SixTemplateEngine/attach_files  "jar 包下载"