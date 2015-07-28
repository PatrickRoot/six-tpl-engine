#SixTemplateEngine

    竟然被推荐了，好开心
    
初步设想是使用Juicer和Java中的js引擎来生成文本。

只要定义好Juicer支持的模板再加上Json格式的数据，就能生成文本。

原来挺简单的，目前已经完成，不知道有没有什么问题。

###已完成：
1. 使用Java和Juicer开发，从模板和json数据生成文本。
2. 重构代码，现在有两个方法可以调用。一种是从String生成String，第二种是从文件生成文件。

###使用方法：
1. 使用String生成String。
```
    String generateFromString(String tpl,String json)

    tpl - 模板字符串。
    
    json - json格式的字符串。
    
    返回值 - 生成的字符串。
```
2. 从文件生成文件。
```
    String generateFromString(String tplFileName, String dataFileName, String resultFilePath)
    tplFileName - 模板文件路径
    dataFileName - json数据文件路径
    resultFilePath - 保存结果的文本文件的路径
    返回值 - 生成的字符串。
```

###以后的计划：
1. 添加main方法，打成jar包可以启动。
2. 做个带界面的，初步想着用JavaFx，如果以后nw.js会用了，可能改为nw.js完全不用Java代码。

###其他：
1. 项目使用了GPL v3授权协议。
1. 项目使用了开源项目[Juicer][Juicer]，Juicer使用了Apache授权协议。感谢Juicer。
>Juicer 是一个高效、轻量的前端 (Javascript) 模板引擎，使用 Juicer 可以是你的代码实现数据和视图模型的分离(MVC)。 除此之外，它还可以在 Node.js 环境中运行。

[Juicer]: http://juicer.name/  "Juicer 官方网站"
[JuicerDoc]: http://juicer.name/docs/docs_zh_cn.html  "Juicer 中文文档"