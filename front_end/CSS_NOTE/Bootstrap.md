# 概念

- 概念：前端开发的框架
  - 框架：一个半成品软件，可以在框架基础上再进行开发
- 好处：
  - 定义了许多 css 和 js 插件，可以直接使用，可以得到丰富的页面效果
  - 响应式布局
    - 同一套页面可以兼容不同分辨率的设备
    - 比如 www.apple.com

# 快速入门

1. 下载 Bootstrap
   - 其中带-min 的是压缩版
   - 阅读源码看不带 min 的
2. 复制模版（）

   > 官网上就有，这里改了下资源路径，改为本地的了

   ```html
   <!DOCTYPE html>
   <html lang="zh-CN">
     <head>
       <meta charset="utf-8" />
       <meta http-equiv="X-UA-Compatible" content="IE=edge" />
       <meta name="viewport" content="width=device-width, initial-scale=1" />
       <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
       <title>Bootstrap 101 Template</title>

       <!-- Bootstrap -->
       <link href="./css/bootstrap.min.css" rel="stylesheet" />

       <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
       <script src="./js/jquery-3.5.1.min.js"></script>

       <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
       <script src="./js/bootstrap.min.js"></script>
     </head>
     <body>
       <h1>你好，世界！</h1>
     </body>
   </html>
   ```

# 响应式布局

## 栅格系统

> 同一套页面可以兼容不同分辨率的设备
> **自己去看文档更快**

- 栅格系统
  - 将一行平均分为 12 个格子，可以指定元素占几个格子
  - 比如可以设置 pc 上一个 div 占 4 个格子，pe 上占 12 个格子（也就是一行）
- 实现：
  > 指定 class 即可
  1. 定义容器 相当于 table
  - containeer
  - container-fluid
  2. 定义行 相当于 tr
  - row
  3. 定义元素，指定元素在不同设备上所占格子数目 相当于 td
  - col-设备代号-占据格子数目
    - 设备代数：
      - xs
      - sm
      - md
      - lg
  4. 注意
  - 如果一行中格子数目超过 12，超出部分会自动换行
  - 栅格类属性可以向上兼容。栅格类适用于与屏幕宽度大于或等于分界点大小的设备
  - 如果设备宽度小于栅格类属性的设备代码的最小值，会一个元素占满一整行

# css 和 js 插件

## 全局 css 样式

- 按钮

  ```html
  <!-- Standard button -->
  <button type="button" class="btn btn-default">（默认样式）Default</button>

  <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
  <button type="button" class="btn btn-primary">（首选项）Primary</button>

  <!-- Indicates a successful or positive action -->
  <button type="button" class="btn btn-success">（成功）Success</button>

  <!-- Contextual button for informational alert messages -->
  <button type="button" class="btn btn-info">（一般信息）Info</button>

  <!-- Indicates caution should be taken with this action -->
  <button type="button" class="btn btn-warning">（警告）Warning</button>

  <!-- Indicates a dangerous or potentially negative action -->
  <button type="button" class="btn btn-danger">（危险）Danger</button>

  <!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
  <button type="button" class="btn btn-link">（链接）Link</button>
  ```

- 图片

  ```html
  <img src="..." class="img-responsive" alt="..." /><!-- 百分百占满 -->
  <img src="..." alt="..." class="img-rounded" />
  <img src="..." alt="..." class="img-circle" />
  <img src="..." alt="..." class="img-thumbnail" />
  ```

- 表格
- 表单

## 组件

- 导航条
- 分页条

## 插件

- 轮播图

```

```
