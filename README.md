---------------------------------------------------------------
# bsweb-common-core常用工程代码 
主要功能包括:
	1. 1 常见常用日期、字符串、数字、文件操作等工具类
	2. 2 配置文件属性工具读取工具类
	3. 3 代码生成器工具，可以自定义代码模板，根据模板文件生成代码
--------------------------------------------------------------

# 1、maven使用
 `
 	<depenency>com.lj.app.bsweb.core</depency>
  	<artictId>bsweb-common-core</artictId>
  	<version>0.0.1</version> `
# 2、运行脚本
执行src/main/script/db_script.sql，在数据库中建立表

# 3、项目集成

## 添加sqlMap.xml文件到工程中

# 4 项目发布到nexus
查看插件  
	 ` 
	 mvn help:describe -Dplugin=deploy  -Ddetail -X
	  `
执行命令 : 
`
 mvn deploy
  `
	
	 
# 贡献代码
 1. fork代码
 2. 创建分支
 3. 修改代码，提交分支
 4. 发送pull request
