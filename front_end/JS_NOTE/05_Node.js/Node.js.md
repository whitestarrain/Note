# 安装配置

## 常用 Window 命令

- 打开应用
	- notepade 记事本
	- mspaint 画图
	- calc 计算机
	- write 写字板
	- sysdm.cpl 环境变量设置窗口

- 文件操作
	- cd 切换目录
	- ls 列出文件
	- md 创建文件夹
	- rmdir(rd) 删除文件夹（不加参数只能删除空目录）
		- rd /s/q 递归删除所有
			- /s 子目录 子目录
			- /q quite，表示不显示日志
	- echo on > a.txt 创建文件
	- echo 内容 > a.txt 覆盖写内容
	- echo 内容 >> a.txt 追加写内容
	- cat a.txt 展示内容
	- rm a.txt 删除文件
	- cat > a.txt 覆盖式阻塞写内容，ctrl+c输入完成
	- cat >> a.txt 追加式阻塞写内容，ctrl+c输入完成

- 其他
	- cls 清空控制台
	- start 同目录下打开一个cmd

## nvm

- 概念：Node.js 版本管理工具

- 安装：
	- 步骤
		- 解压到一个地方
		- 以管理员身份运行install.cmd
		- 回车
		- 将生成的配置文件存到nvm目录中
		- 修改配置文件
			- root为nvm路径
			- path为所有Node.js版本目录
		- 添加NVM_HOME（为root路径）,NVM_SYMLINK（为path路径）环境变量
	- 例：
		- 路径：
			> ![](./image/安装-1.jpg)
		- 配置文件
			```
			root: D:\learn\nodejs_nvm\nvm
			arch: 64
			proxy: none
			originalpath: .
			originalversion: 
			node_mirror: https://npm.taobao.org/mirrors/node/
			npm_mirror: https://npm.taobao.org/mirrors/npm/
			```


- nvm 所有命令
	```
		nvm arch                     : Show if node is running in 32 or 64 bit mode.
		nvm install <version> [arch] : The version can be a node.js version or "latest" for the latest stable version.
																	Optionally specify whether to install the 32 or 64 bit version (defaults to system arch).
																	Set [arch] to "all" to install 32 AND 64 bit versions.
																	Add --insecure to the end of this command to bypass SSL validation of the remote download server.
		nvm list [available]         : List the node.js installations. Type "available" at the end to see what can be installed. Aliased as ls.
		nvm on                       : Enable node.js version management.
		nvm off                      : Disable node.js version management.
		nvm proxy [url]              : Set a proxy to use for downloads. Leave [url] blank to see the current proxy.
																	Set [url] to "none" to remove the proxy.
		nvm node_mirror [url]        : Set the node mirror. Defaults to https://nodejs.org/dist/. Leave [url] blank to use default url.
		nvm npm_mirror [url]         : Set the npm mirror. Defaults to https://github.com/npm/cli/archive/. Leave [url] blank to default url.
		nvm uninstall <version>      : The version must be a specific version.
		nvm use [version] [arch]     : Switch to use the specified version. Optionally specify 32/64bit architecture.
																	nvm use <arch> will continue using the selected version, but switch to 32/64 bit mode.
		nvm root [path]              : Set the directory where nvm should store different versions of node.js.
																	If <path> is not set, the current root will be displayed.
		nvm version                  : Displays the current running version of nvm for Windows. Aliased as v.
	```
	- 常用：
		- nvm install
		- nvm uninstall
		- nvm list
		- nvm use


# 基本概念

- 看看最近写的Node.js的论文

- 基础使用：
	- cmd中输入代码：
		- cmd中输入node，进入Node.js代码执行环境（REPL）
			- REPL
				- read：读取代码
				- eval：执行代码
				- print：打印结果
				- loop：循环以上过程
		- 特殊特征：
			- _ 表示上一次执行得出的结果
		- .exit退出执行环境
	- 执行js文件
		- 在cmd中执行：node hello.js

# 模块化

- global，全局变量，可以省略。**详情先了解模块系统**
	> 所有模块都提供这些对象。 global中的对象变量虽然看起来是全局的，但其实并不是。 它们仅存在于模块范围内。

- 模块化开发
	- 非模块化开发缺点：
		- 命名冲突
		- 文件依赖
	- 标准模块化规范：
		- 前端-模块加载一般为异步
			- AMD - requirejs实现		国外使用较多
			- CMD - seajs实现				淘宝使用
		- 服务端-模块加载一般为同步：
			- CommonJS - Node.js实现
	- 如何定义模块：
		- 一个js文件就是一个模块，模块内部成员相互独立
	- 导入模块原理：
		- 就是将模块加载入内存
		- 根据路径标识模块
		- 重复模块添加会被忽略
	- 模块文件后缀
		> 后缀不写也没关系，会按照后缀名为 js json node的顺序查找
		- .js：就是普通js文件
		- .json：导入模块所获得的就是一个对象，用来存储数据
		- .node：C或者C++语言开发的模块，二进制文件，导入后获得一个对象，可以调用里面方法
	- 模块划分：
		- 自定义模块
		- 系统自带模块
	- 模块成员的导出和引入
		- 一个模块一个方法
			```js
			//demo.js文件

			var sum = function ( a , b ) {
				return parseInt(a)+parseInt(b);
			}

			//整个模块就只能导出一个方法，相当于整个模块就是一个方法
			module.exports=sum;
			```
			```js
			// 引入其他模块（其实可以去掉 .js）
			var module = require("./demo.js")
			//调用
			module(1,1);
			```
		- 一个模块多个方法
			```js
			//demo.js文件

			var sum = function ( a , b ) {
				return parseInt(a)+parseInt(b);
			}

			//导出（或者说暴露）模块成员，不导出没法儿给别的模块用
			exports.sum=sum;
			```

			```js
			// 引入其他模块，
			var module = require("./demo.js")

			// 调用其他模块中暴露出来的方法
			var a=module.sum(1,1);
			```
		- 不常用方法
			> global就只有一个还常用，不要乱加东西
			```js
			//demo.js
			var flag =123;
			global.flag=flag;
			```
			```js
			require('./demo.js')
			console.log(global.flag);
			```
	- 相关方法
		- global.exports
		- global.module
		- global.require()
	

# es6常用语法

## 变量声明

- 变量声明
	- let
		- 不会进行预解析，只能先声明再使用
		- 不允许在同一个作用域内重复声明
		- 会受到块级作用域限制
			```js
			// i只在循环体中有效，推荐这样写
			for(let i=0;i<=3;i++){
				console.log(i);
			}
			```
	- const
		- 声明常量，声明时就要初始化，不允许重新赋值

## 变量赋值

- 数组的解构赋值
	```js
	// 按照顺序赋值
	//默认值			赋值
	var [a=1,b,c]=[,2,];
	let [c,e,f]=[4,5,'f']
	```
- 对象的解构赋值
	> 从右侧对象中取出左侧列出的属性值
	```js
	//根据对象名称赋值，与顺序无关。
	let {name,value}={'name':'name1','value':'value1'}
	let {name,value}={'value':'value1','name':'name1'}
	
	console.log(name,value)

	// 属性起别名、此时只能用别名访问
	let {name:nameName,value}={'name':'name1','value':'value1'}
	
	//例
	let {con,sin,random}=Math;
	```
- 字符串的解构赋值
	```js
	//一个变量对应一个字符
	let [a,b,c,d,e]="hello";
	console.log(a,b,c,d,e);//hello
	
	let [f,g]="hello";
	console.log(f,g)//he
	```