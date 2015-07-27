#SixTemplateEngine
初步设想是使用Juicer和Java中的js引擎来生成文本。

原来挺简单的，目前已经完成，不知道有没有什么问题。

###使用方法：
1. 模板文件请定义为tpl.txt 放到resources目录，模板语法参照[Juicer][JuicerDoc]。
2. 数据定义为json格式放到放到resources目录的data.json中。
3. 运行TemplateEngine类，生成result.txt文件。

###以后的计划：
1. 目录优化一下，目前的目录好像没办法打成jar包用。
1. 做个带界面的，初步想着用JavaFx，如果以后nw.js会用了，可能改为nw.js完全不用Java代码。

###其他：
1. 项目使用了GPL v3授权协议。
1. 项目使用了开源项目[Juicer][Juicer]，Juicer使用了Apache授权协议。
>Juicer 是一个高效、轻量的前端 (Javascript) 模板引擎，使用 Juicer 可以是你的代码实现数据和视图模型的分离(MVC)。 除此之外，它还可以在 Node.js 环境中运行。

[Juicer]: http://juicer.name/  "Juicer 官方网站"
[JuicerDoc]: http://juicer.name/docs/docs_zh_cn.html  "Juicer 中文文档"