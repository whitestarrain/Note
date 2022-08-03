# plantuml

## 使用场景

- 需要graphviz
- 使用场景： 流程图，类图，状态图，时序图等UML图以及甘特图等图，就用这个，准没错。
- 资料:
  - [中文官网文档](https://plantuml.com/zh/)
  - [style-c4](https://github.com/xuanye/plantuml-style-c4)：一个自定义的c4风格的样式。

## 语法示例

### 时序图

### 用例图

### 类图

### 活动图

### 组件图

### 状态图

### 状态图

### 对象图

### 部署图

### 定时图

### 网络图

### 甘特图

### 架构图

### 思维导图

### 工作分解结构

### json与yaml

## 其他语法

### 样式

### skinparam 样式语法

### function与procedure

## 常用语法

# plantuml+C4

- 需要graphviz
- 画整体的架构图挺不错的
- 但是要是画那种各种组件，各种接口都体现出来的比较复杂架构图，就会箭头乱飞。阅读体验极差
- 甚至需要自己手动使用`Lay_D`等手动控制方向

## 基本概念介绍

## 使用说明

## 其他

不同的文件里面可能会有不同的方法重写。

比如C4和C4_Dynamic中都有Rel()方法，不过C4_Dynamic中每一条连线都会有标号，除此之外作用相同

```plantuml
' C4
!unquoted procedure Rel_U($from, $to, $label, $techn="", $descr="", $sprite="", $tags="", $link="")
$getRel($up("-","->>"), $from, $to, $label, $techn, $descr, $sprite, $tags, $link)
!endprocedure

' C4_Dynamic
!unquoted procedure Rel_U($from, $to, $label, $techn="", $descr="", $sprite="", $tags="", $link="")
$getRel($up("-","->>"), $from, $to, Index() + ": " + $label, $techn, $descr, $sprite, $tags, $link)
!endprocedure
```

# graphviz

## 使用场景

- 直接使用graphviz绘图
- 用来画一些数据结构图，复杂有向图比较好用。
- 可以看一些示例了解一下它的强大功能：[官方示例](https://www.graphviz.org/gallery/)
- 主要依赖rank属性对图片进行布局

## Graphviz简要语法

# diagrams

## 使用场景

- 编写python代码生成图片
- 需要graphviz
- 是一个python库，提供了大量图标，使用python脚本编写架构逻辑就能生成架构图。
- 重写了一些操作符，代码看起来也比较清晰
- 比较用来写整体系统架构部署用到的组件或者架构图

## 示例

# draw.io

```bash
draw.io --enable-plugins
# 允许添加外部插件
```

基本啥都能画。免费无限制版的process on

# process on

好像是国产软件，免费版有一些限制，但是能在线协作画图，这个功能可能会有用。

一个人画图感觉不如draw.io

# 参考资料

- [Graphviz简要语法](https://leojhonsong.github.io/zh-CN/2020/03/12/Graphviz%E7%AE%80%E8%A6%81%E8%AF%AD%E6%B3%95/)
- [使用C4-PlantUML来快速的描述软件架构](https://gowa.club/%E8%BD%AF%E4%BB%B6%E6%9E%B6%E6%9E%84/%E4%BD%BF%E7%94%A8C4-PlantUML%E6%9D%A5%E5%BF%AB%E9%80%9F%E7%9A%84%E6%8F%8F%E8%BF%B0%E8%BD%AF%E4%BB%B6%E6%9E%B6%E6%9E%84.html)
- [github:C4-PlantUML](https://github.com/plantuml-stdlib/C4-PlantUML)
- [plantuml中文文档](https://plantuml.com/zh/guide)
- [plantuml_c4网页工具](https://kroki.io/)
- [plantuml图标](https://github.com/tupadr3/plantuml-icon-font-sprites/blob/master/devicons/index.md)
- [piskel:在线画精灵图](https://www.piskelapp.com/)

