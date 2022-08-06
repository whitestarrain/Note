# 1. 前置知识

- this 指向
- ES6
- npm(webpack)
- 原型，原型链
- js 数组常用方法
- 模块化

# 2. 入门

## 2.1. 为什么要使用React

- **开发迅速**
  - React组件化的特性使得React可以在项目中大量复用封装好的组件，提高代码的复用率
  - 减少了重复写相同代码的繁琐而无聊的工作

  ```
  原生的JavaScript操作DOM繁琐，效率低（DOM-API操作UI）
  使用JavaScript，包括jQuery直接操作DOM，浏览器会进行大量的重绘和重排（虽然jQuery简化了操作DOM的步骤，但依然效率低下）
  原生的JavaScript没有组件化编码方案，代码复用率低
  ```

- **生态相对完善**
  - React 起源于 Facebook 的内部项目，具有相对稳定的维护，周边生态相对完善
  - 像各种的UI库，路由库等，可以在更少的时间内完成更多的工作。
- **有大公司作为背书**
  - 除了React的开发公司Faceboook大量使用React外，国内外还有很多大公司也广泛应用React
  - 在国外有Paypal，Airbnb等，在国内有阿里，腾讯，字节跳动等。
- **有强大的开源社区**
  - 开源项目的社区非常重要，在社区开发者的贡献下会让一些开源项目变得越来越好
  - 项目的issue的解决速度也会得到提升，同时还会提供大量的周边技术工具和技术博客。

## 2.2. React的定义

- React 的定义：用于 **构建用户界面** 的 **JavaScript库** 。
  -  **构建用户界面**
    -  说明React专注于视图的构建，既不是一个请求库，也不是一个打包工具，而是主要提供UI层面的解决方案。
  -  **JavaScript库**
    -  这说明React并不是一个框架，并不能解决项目中的所有问题
    -  为了在项目中使用它，需要结合其他的库，例如Redux/React-router等来协助提供完整的解决方案
    -  在这些周边生态的配合下才能组合成一个框架

- 换句话来说，React所做的有三步
  - 发送请求获得数据
  - 处理数据（过滤，整理格式等）
  - 操作DOM呈现页面

  > 也就是说React也可以定义为一个将数据渲染为HTML视图的开源JavaScript库。


## 2.3. React的三大特性

### 2.3.1. 声明式编程

- 命令式编程 VS 声明式编程：
  - 简单来说，命令式编程就是通过代码来告诉计算机去做什么。
  - 而声明式编程是通过代码来告诉计算机你想要做什么，让计算机想出如何去做。

  ```
  如果我要在界面上展示一个按钮，并且点击按钮后会改变该按钮的class。
  用DOM编程写的代码就是命令式编程：
    首先你要指挥浏览器，第一步先要找到id为container的节点，
    然后创建一个button element，接着给这个button添加一个class name
    然后添加一个点击事件
    最后将button添加到container节点里
    这整个过程每一步操作都是命令式的，一步一步告诉浏览器要做什么。
  ```

  ```javascript
  const container = document. getElementById ( "container" );
  const btn = document.createElement ("button");

  btn.className = "btn red " ;
  btn.textContent = "Demo" ;

  btn.onclick = function ( ) {
      if ( this.classList.contains ( "red" ) ) {
        this.classList.remove( "red" );
        this.classList.add ( "blue" );
      }else {
        this.classList.remove( "blue" );
        this.classList.add ( "red" );
      }
  };
  container.appendChild( btn);
  ```

  ```
  而要实现相同功能，采用声明式编程的React就简单得多了
    首先我们定义一个Button组件，在render函数里通过返回一个类似HTML的数据结构，告诉React我要渲染一个Button，它是id为container的子节点
    Button上的ClassName是动态变化的，当点击按钮时class要改变，这样就可以了
    至于render什么时候被执行，是如何渲染到页面上的，点击按钮之后classname是如何更新的，这些都不需要你关心
    只需要告诉React你希望当前的UI应该是一个什么样的状态就可以了。
  ```

  ```javascript
  class Button extends React. Component {
      state = { color: "red" };
      handleChange =()=> {
          const color = this.state.color == "red" ? "blue" : "red" ;this.setState({ color });
      };
      render( ) {
          return (
          <div id="container">
              <button
                  className={ `btn ${this.state.color}` }
                  onclick={this.handleChange}
              >
                  Demo
              </button>
          </div>
      );
      }
  }
  ```

### 2.3.2. 组件化

- React提供了一种全新的语法扩展，JSX。
- JSX创造性地将渲染逻辑和UI逻辑结合在了一起，而这个结合体在React中就被称为组件。
- 一个页面由多个组件组成，甚至整个应用都可以视为一个组件，只不过是最大的组件。
- 组件可以层层嵌套，一个组件可以由多个组件组成，一个大的组件由很多个小组件组成，这些小组件也有可能由更小的组件组成。
- 同一个组件可能会被使用在不同的地方。
- 组件化的出现大幅度地提升了代码地复用率，同时也改变了前端开发人员的一个编程思维

### 2.3.3. 一次学会，随处编写

- 这句话的意思不是学会了想写什么就可以写什么，也不是说写一次想在哪里跑就在哪里跑，
- 而是说学会后可以在很多地方使用React的语法来写代码，比如配合React DOM来编写web端的页面，配合React Native来写手机客户端APP，配合React 360开发VR界面等。
- React的灵活性是由于它自身的定位决定的。
- React是一个用于构建用户界面的JS库，对于React来说，这里的用户界面是一个抽象的虚拟的用户界面，其实就是一个描述页面状态的数据结构。
- web页面，移动客户端页面，VR界面都是用户界面，只要配合相应的渲染器就能在不同的平台中展示正确的UI界面。
- 通俗来说，我们可以把React的执行结果想象成一个视频文件数据，在不同的播放器设备，我们通过转换器将视频编译成不同的格式来让他们在不同的播放器上正常地播放。
- 所以在写web端React时我们要额外引入React DOM来做渲染。

**此外，React使用虚拟DOM+优秀的Diffing算法，尽量减少与真实DOM的交互，最小化页面重绘**

# 3. react入门

## 3.1. 入门项目

[官方入门项目](https://react.html.cn/tutorial/tutorial.html)

## 3.2. JSX简介

- 说明：
  - 全称: JavaScript XML
  - react定义的一种类似于XML的JS扩展语法: JS + XML，本质上还是JavaScript

- 作用：
  - 用来简化创建虚拟DOM

- 示例：

  ```javascript
  const element = (
    <h1 className="greeting">
      Hello, world!
    </h1>
  );

  // 等价于
  const element = React.createElement(
    'h1',
    {className: 'greeting'},
    'Hello, world!'
  );

  // 其中React.createElement创建的对象的简化结构为：
  const element = {
    type: 'h1',
    props: {
      className: 'greeting',
      children: 'Hello, world'
    }
  };
  ```

  - 这些对象被称作“React元素”
  - 可以把他们想象成为你想在屏幕上显示内容的一种描述
  - React会读取这些对象，用他们来构建DOM，并且保持它们的不断更新。

- 本质：
  - 是`React.createElement(component, props, ...children)`方法的语法糖
  - 最终会被解析为一个对象。
  - JSX是一个表达式，可以正常在`if`与`for`中使用
  - 在JSX中，可以 **使用大括号嵌入js表达式** 

  ```javascript
  function getGreeting(user) {
    if (user) {
      return <h1>Hello, {formatName(user)}!</h1>;
    }
    return <h1>Hello, Stranger.</h1>;
  }
  ```

- 安全性：
  - 默认情况下， 在渲染之前, React DOM 会格式化(escapes) JSX中的所有值. 从而保证用户无法注入任何应用之外的代码
  - 在被渲染之前，所有的数据都被转义成为了字符串处理。 以避免 XSS(跨站脚本) 攻击。

- jsx解析：babel.js
  - 浏览器不能直接解析JSX代码, 需要babel转译为纯JS的代码才能运行
  - 只要用了JSX，都要加上type="text/babel", 声明需要babel来处理

- 注意：
  - 注释需要写在花括号{}中
  - 样式的类名指定不要写class，要写className
  - 内联样式要用style={{key:value}}的形式写
    - 第一个{}表示里面是一个js表达式，第二个{}表示里面是一个键值对
    - 里面要写小驼峰的形式， 比如font-size要写成fontSize

      ```javascript
      <span style={{color:'#e0e0e0', fontSize:18} }> myData
      ```
  - 虚拟DOM只能有一个根标签，有多个标签时，可用一个div包起来
  - 标签必须闭合

## 3.3. react的元素与虚拟DOM

- React 元素是 **不可变对象** 
  - 一旦被创建，你就无法更改它的子元素或者属性
  - 一个元素就像电影的单帧：它代表了某个特定时刻的 UI。
  - 更新 UI 唯一的方式是创建一个全新的元素，并将其传入 root.render()。

- 虚拟DOM对象(React元素)创建：

  ```javascript
  //依次为标签名，标签属性和标签内容
  const VDOM = React.createElement('xx',{id:'xx'},'xx')

  // 创建对象的简化结构为：
  const vdom_object = {
    type: "xx",
    props:{
      id: "xx",
      children: "xx"
    }
  }
  ```

  - 本质是Object类型的对象（一般对象）
  - 虚拟DOM比较“轻”，真实DOM比较“重”，因为虚拟DOM是React内部在用，无需真实DOM上那么多的属性
  - 虚拟DOM对象最终都会被React转换为真实DOM，呈现在页面上

- 渲染虚拟DOM到真实DOM上

  ```javascript
  const element = <h1>Hello, world</h1>;
  ReactDOM.render(element, document.getElementById('root'));
  ```

## 3.4. 模块化与组件化

- 模块
  - 理解：向外提供特定功能的js程序，一般就是一个js文件
  - 为什么要拆成模块：因为随着业务逻辑增加，代码越来越多且复杂
  - 作用：服用js，简化js的编写，提高js运行效率

- 组件
  - 理解：用来实现局部功能的代码和资源的集合（html/css/js/image等等）
  - 为什么一个界面的功能很复杂，不可能写成一整块，要分成一块块写，然后拼起来
  - 作用：复用编码，简化项目编码，提高运行效率

- 模块化
  - 当一个应用的js都是以模块来编写，这个应用就是一个模块化的应用

- 组件化
  - 当应用是以多组件的方式实现，这个应用就是一个组件化的应用

# 4. 面向组件化编程

## 定义组件

### 基本定义

- 最简单的定义组件的方法是写一个 JavaScript 函数:

  ```javascript
  function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
  }
  ```
  - 这个函数是一个有效的 React 组件，因为它接收一个 props 参数, 并返回一个 React 元素
  - 我们把此类组件称为” **函数式(Functional)** “组件， 因为从字面上看来它就是一个 JavaScript 函数。

- 也可以用一个 ES6 的 class 来定义一个组件:

  ```javascript
  class Welcome extends React.Component {
    render() {
      return <h1>Hello, {this.props.name}</h1>;
    }
  }
  ```

- 上面两个组件从 React 的角度来看是等效的。
  - 组件其实有一些额外的特性，比如`state`，之后再继续说明
  - 在此之前, 我们先用函数式组件，因为它们更加简洁。

### 合成组件

- 组件可以在它们的输出中引用其它组件
  - 可以使用同样的组件来抽象到任意层级
  - 一个按钮，一个表单，一个对话框，一个屏幕：在 React 应用中，所有这些都通常描述为组件。

- 示例：创建一个 App 组件，并在其内部多次渲染 Welcome：

  ```javascript
  function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
  }

  function App() {
    return (
      <div>
        <Welcome name="Sara" />
        <Welcome name="Cahal" />
        <Welcome name="Edite" />
      </div>
    );
  }

  ReactDOM.render(
    <App />,
    document.getElementById('root')
  );
  ```

## 4.1. 组件核心属性

### 4.1.1. props与不可变

#### props说明

- 说明：
  - 当 React 遇到一个代表用户定义组件的元素时，它将 JSX 属性以一个单独对象props的形式传递给相应的组件
  - 通过标签属性从组件外向组件内传递变化的数据

  ```javascript
  // 比如, 以下代码在页面上渲染 “Hello, Sara” ：
  function Welcome(props) {
    return <h1>Hello, {props.name}</h1>;
  }

  const element = <Welcome name="Sara" />;
  ReactDOM.render(
    element,
    document.getElementById('root')
  );
  ```
  - 调用了 `ReactDOM.render()` 方法并向其中传入了 `<Welcome name="Sara" />` 元素。
  - React 调用 `Welcome` 组件，并向其中传入了 `{name: 'Sara'}` 作为 props 对象。
  - `Welcome` 组件返回 `<h1>Hello, Sara</h1>`。
  - React DOM 迅速更新 DOM ，使其显示为 `<h1>Hello, Sara</h1>`。

- 注意：
  - **组件名称总是以大写字母开始** 
  - 就像类名需要大写一样。
  - 不过，funciton类型的组件也需要大写首字母
  - 举例来说
    - `<div />` 代表一个 DOM 标签
    - 而 `<Welcome />` 则代表一个组件
    - 并且需要在作用域中有一个 `Welcome` 组件。


#### props不可变性

- 纯函数与非纯函数

  ```javascript
  // 无论你用函数或类的方法来声明组件, 它都无法修改其自身 props. 思考下列 sum (求和)函数:
  // 这种函数称为 “纯函数” ，因为它们不会试图改变它们的输入，并且对于同样的输入,始终可以得到相同的结果。
  function sum(a, b) {
    return a + b;
  }

  // 反之， 以下是非纯函数， 因为它改变了自身的输入值：
  function withdraw(account, amount) {
    account.total -= amount;
  }
  ```

- React 很灵活，但是它有一条严格的规则：  **所有 React 组件都必须是纯函数，并禁止修改其自身 props** 

#### 类型限制与使用

- 类型限制写法

  ```javascript
  // 15.5弃用
  Person.propTypes = {
  name: React.PropTypes.string.isRequired,
  age: React.PropTypes.number
  }
  ```
  ```javascript
  Person.propTypes = {
    name: PropTypes.string.isRequired,
    age: PropTypes.number. 
  }
  ```

- eslint检查：
  - 默认进行props检查，强制要求写上类型限制
  - 可以在配置文件中关闭

  ![react-1](./image/react-1.png)

  ```javascript
  rules: {
    "react/prop-types": 0
  }
  ```

#### 使用方式

### 4.1.2. state与生命周期

#### state

- 不要直接修改 state(状态)
- state(状态) 更新可能是异步的
- state(状态)更新会被合并

#### 生命周期方法

#### 数据的向下流动(state->props)

### 4.1.3. ref

## 4.2. 事件处理

> 通过 React 元素处理事件跟在 DOM 元素上处理事件非常相似。但是有一些语法上的区别：

### 与html上事件语法区别

- React 事件使用驼峰命名，而不是全部小写。
- 通过 JSX , 你传递一个函数作为事件处理程序，而不是一个字符串。

  ```html
  <!--html-->
  <button onclick="activateLasers()">
    Activate Lasers
  </button>
  ```
  ```javascript
  // jsx
  <button onClick={activateLasers}>
    Activate Lasers
  </button>
  ```

- 在 React 中你 **不能通过返回 false来阻止默认行为** 
  - 必须明确调用 `preventDefault` 
  - 例如，对于纯 HTML ，要阻止链接打开一个新页面的默认行为，可以这样写：

    ```html
    <a href="#" onclick="console.log('The link was clicked.'); return false">
      Click me
    </a>
    ```
  - 在 React 中, 应该这么写:

    ```javascript
    function ActionLink() {
      function handleClick(e) {
        e.preventDefault();
        console.log('The link was clicked.');
      }

      return (
        <a href="#" onClick={handleClick}>
          Click me
        </a>
      );
    }
    ```
  - 这里， e 是一个合成的事件。 React 根据 W3C 规范 定义了这个合成事件，所以你不需要担心跨浏览器的兼容性问题
  - 查看 [SyntheticEvent](https://react.html.cn/docs/events.html) 参考指南了解更多。

### 类组件事件与bind

- 当使用一个 ES6 类 定义一个组件时，通常的一个事件处理程序是类上的一个方法
  - 例如， Toggle 组件渲染一个按钮，让用户在 “ON” 和 “OFF” 状态之间切换：

  ```javascript
  class Toggle extends React.Component {
    constructor(props) {
      super(props);
      this.state = {isToggleOn: true};

      // 这个绑定是必要的，使`this`在回调中起作用
        // bind()方法创建一个新的函数，在bind()被调用时
        // 这个新函数的this被bind的第一个参数指定，其余的参数将作为新函数的参数供调用时使用。
      this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
      this.setState(state => ({
        isToggleOn: !state.isToggleOn
      }));
    }

    render() {
      return (
        <button onClick={this.handleClick}>
          {this.state.isToggleOn ? 'ON' : 'OFF'}
        </button>
      );
    }
  }

  ReactDOM.render(
    <Toggle />,
    document.getElementById('root')
  );
  ```

  - 在JSX回调中必须注意 this 的指向
    - 在 JavaScript 中，类方法默认没有 bind 的
    - 如果你忘记使用bind并将this.handleClick 传递给onClick，那么在直接调用该函数时，this 会是 undefined 。

- 不使用bind的解决方法：
  - 使用实验性的 [属性初始化语法](https://www.jianshu.com/p/d80c30cb4d71) ，可以使用属性初始值设置来正确地 绑定(bind) 回调：
  > [《阮一峰 ECMAScript 6 (ES6) 标准入门教程》中有提到](https://www.bookstack.cn/read/es6-3rd/spilt.1.docs-class.md)
    ```javascript
    class LoggingButton extends React.Component {
      // 这个语法确保 `this` 绑定在 handleClick 中。
      // 警告：这是 *实验性的* 语法。
      // 在React App中是默认开启的
      handleClick = () => {
        console.log('this is:', this);
      }

      render() {
        return (
          <button onClick={this.handleClick}>
            Click me
          </button>
        );
      }
    }
    ```
  - 箭头函数

    ```javascript
    class LoggingButton extends React.Component {
      handleClick() {
        console.log('this is:', this);
      }

      render() {
        // This syntax ensures `this` is bound within handleClick
        return (
          <button onClick={(e) => this.handleClick(e)}>
            Click me
          </button>
        );
      }
    }
    ```
    - 每次 LoggingButton 渲染时都创建一个不同的回调。在多数情况下，没什么问题。
    - 然而，如果这个回调被作为 prop(属性) 传递给下级组件，这些组件可能需要额外的重复渲染。
    - 通常建议在构造函数中进行绑定，以避免这类性能问题。


## 4.3. 表单数据处理

## 4.4. 生命周期

## 4.5. 虚拟DOM与真实DOM diff算法

# 5. React 脚手架

# 6. React 路由

# 7. React Ajax、axios

# 8. react-redux

# 9. CRACO

**C** **r**eate **R**eact **A**pp **C**onfiguration **O**verride 

# 10. 参考资料

- [x] [官方入门项目](https://react.html.cn/tutorial/tutorial.html)
- [ ] [中文文档](https://react.html.cn/docs/hello-world.html)
- [ ] [react全家桶学习笔记](https://juejin.cn/post/6979132493333004319)
- [ ] [用React、Redux、Immutable做俄罗斯方块](https://github.com/chvin/react-tetris)
- [ ] [Immutable 详解及 React 中实践](https://github.com/camsong/blog/issues/3)
- [ ] [从 1 到完美，用 js 和 react-native 写一个 APP](https://segmentfault.com/a/1190000016272845)
- [ ] [React 进阶](https://www.jianshu.com/p/0b2da7a6b337)
  - [ ] [React模块化与组件化](https://www.jianshu.com/p/ffd4101cee4b)
<!-- https://www.bilibili.com/video/BV1wy4y1D7JT/ -->
- [ ] [js class支持定义属性/属性初始化器语法](https://www.jianshu.com/p/d80c30cb4d71)
