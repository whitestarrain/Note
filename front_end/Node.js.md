# 相关概念

## Node.js是什么

## npm 是什么

## webpack是什么

## 三者关系是什么

# nvm

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

- 引用全局模块
  - 设置系统变量，新建 NODE_PATH，指向全局node_modules

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
			```javascript
			//demo.js文件

			var sum = function ( a , b ) {
				return parseInt(a)+parseInt(b);
			}

			//整个模块就只能导出一个方法，相当于整个模块就是一个方法
			module.exports=sum;
			```
			```javascript
			// 引入其他模块（其实可以去掉 .js）
			var module = require("./demo.js")
			//调用
			module(1,1);
			```
		- 一个模块多个方法
			```javascript
			//demo.js文件

			var sum = function ( a , b ) {
				return parseInt(a)+parseInt(b);
			}

			//导出（或者说暴露）模块成员，不导出没法儿给别的模块用
			exports.sum=sum;
			```
			```javascript
			// 引入其他模块，
			var module = require("./demo.js")

			// 调用其他模块中暴露出来的方法
			var a=module.sum(1,1);
			```
		- 不常用方法
			> global就只有一个还常用，不要乱加东西
			```javascript
			//demo.js
			var flag =123;
			global.flag=flag;
			```
			```javascript
			require('./demo.js')
			console.log(global.flag);
			```
	- 相关方法
		- global.exports
		- global.module
		- global.require()
	

# es6常用语法

[ECMAScript6](./ECMAScript6.md


# 参考资料

- [ ] [躺下来聊聊前端自动化](https://zhuanlan.zhihu.com/p/28483358)
- [ ] [前端模块化开发中webpack、npm、node、nodejs之间的关系](https://blog.csdn.net/AngelLover2017/article/details/84801673)
