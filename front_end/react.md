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

## 4.1. 定义组件

### 4.1.1. 基本定义

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

### 4.1.2. 合成组件

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

## 4.2. 组件核心属性

### 4.2.1. props与不可变

#### 4.2.1.1. props说明

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

#### 4.2.1.2. props不可变性

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

#### 4.2.1.3. 类型限制与使用

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

### 4.2.2. state与生命周期

#### 4.2.2.1. state

- 说明：
  - state是组件对象最重要的属性, 值是对象(可以包含多个key-value的组合)
  - 组件被称为"状态机", 通过更新组件的state来更新对应的页面显示(重新渲染组件)

- 注意：
  - 不要直接修改 state(状态)
  - state(状态) 更新可能是异步的
    - 出于性能考虑，React 可能会把多个 setState() 调用合并成一个调用。
    - 因为 this.props 和 this.state 可能会异步更新，所以你不要依赖他们的值来更新下一个状态。
    - 要解决这个问题，可以让 setState() 接收一个函数而不是一个对象。
      > 这个函数用上一个 state 作为第一个参数，将此次更新被应用时的 props 做为第二个参数

    ```javascript
    // Correct
    this.setState((state, props) => ({
      counter: state.counter + props.increment
    }));
    ```
  - state(状态)更新会被合并

#### 4.2.2.2. 生命周期与相关方法

##### 旧版生命周期

![react-2](./image/react-2.png)

> React 的生命周期主要可分为: 
> - 初始化阶段
> - 挂载阶段
> - 更新阶段
> - 卸载阶段。

- 初始化阶段
  - 发生在 constructor 中的内容，在 constructor 中进行 state、props 的初始化
  - 在这个阶段修改 state，不会执行更新阶段的生命周期，可以直接对 state 赋值。

- 挂载阶段，对应的生命周期为：
  - 1.componentWillMount :发生在 render 函数之前，还没有挂载 Dom
  - 2.render 
  - 3.componentDidMount :发生在 render 函数之后，已经挂载 Dom

- 更新阶段:更新阶段分为由 state 更新引起和 props 更新引起
  - props
    - 1. componentWillReceiveProps(nextProps,nextState)
      - 这个生命周期主要为我们提供对 props 发生改变的监听
      - 如果需要在 props 发生改变后，相应改变组件的一些 state，可以使用这个方法
      - 在这个方法中改变 state 不会二次渲染，而是直接合并 state。
    - 2. shouldComponentUpdate(nextProps,nextState)
      - 这个生命周期需要返回一个 Boolean 类型的值，判断是否需要更新渲染组件
      - 优化 react 应用的主要手段之一
      - 当返回 false 就不会再向下执行生命周期了，在这个阶段 **不可以 setState()** ，会导致循环调用。
    - 3. componentWillUpdate(nextProps,nextState)
      - 这个生命周期主要是给我们一个时机能够处理一些在 Dom 发生更新之前的事情
      - 如获得 Dom 更新前某些元素的坐标、大小等
      - 在这个阶段 **不可以 setState()** ，会导致循环调用。
    - *一直到这里 this.props 和 this.state 都还未发生更新*
    - 4. render
      - 执行 render 函数。
    - 5. componentDidUpdate(prevProps, prevState) 
      - 在此时已经完成渲染，Dom 已经发生变化，State 已经发生更新
      - prevProps、prevState 均为上一个状态的值
  - state（具体同上）
    - 1. shouldComponentUpdate
    - 2. componentWillUpdate
    - 3. render
    - 4. componentDidUpdate
- 卸载阶段，对应的生命周期为
  - componentWillUnmount
    - componentWillUnmount 会在组件卸载及销毁之前直接调用
    - 在此方法中执行必要的清理操作
      - 例如，清除 timer，取消网络请求或清除在 componentDidMount  中创建的订阅等
    - componentWillUnmount 中不应调用 setState，因为该组件将永远不会重新渲染
      - 组件实例卸载后，将永远不会再挂载它。

- 注意：
  - 根据上面的生命周期可以理解所谓的 setState 是“异步”的并非 setState 函数插入了新的宏任务或微任务
  - 而是在进行到 componentDidUpdate 这个生命周期之前，React 都不会更新组件实例的 state 值。

  ```
  引发问题： setState 在 setTimeout 和原生事件回调中却可以同步更新（ this.state 立即获得更新结果）是为什么呢？
  答案： 
      在 React 中，如果是由 React 引发的事件处理（比如：onClick 引发的事件处理）或在钩子函数中，调用 setState 不会同步更新 this.state，
      除此之外的 setState 调用会同步执行this.setState。
      “除此之外”指的是：绕过 React 通过 addEventListener 直接添加的事件处理函数和 setTimeout/setInterval 产生的异步调用。
  解释： 
      每次 setState 产生新的state会依次被存入一个队列，然后会根据isBathingUpdates 变量判断是直接更新 this.state 还是放进 dirtyComponent 里回头再说。
      isBatchingUpdates 默认是 false，也就表示 setState 会同步更新 this.state。
      但是，当 React 在调用事件处理函数之前就会调用 batchedUpdates，这个函数会把 isBatchingUpdates 修改为 true，
      造成的后果就是由 React 控制的事件处理过程 setState 不会同步更新 this.state。
  解决方法：
      当我们想要依据上一个 state 的值来 setState 时，可以使用函数式 setState。

  function increment(state, props) {
    return {count: state.count + 1};
  }
  function incrementMultiple() {
    this.setState(increment);
    this.setState(increment);
    this.setState(increment);
  }
  ```

##### react16及以后

- React 16 中删除了如下三个生命周期。
  - componentWillMount
  - componentWillReceiveProps
  - componentWillUpdate

  ```
  官方给出的解释是 react 打算在17版本推出新的 Async Rendering，提出一种可被打断的生命周期，
  而可以被打断的阶段正是实际 dom 挂载之前的虚拟 dom 构建阶段，也就是要被去掉的三个生命周期。
  本身这三个生命周期所表达的含义是没有问题的，但 react 官方认为我们（开发者）也许在这三个函数中编写了有副作用的代码，
  所以要替换掉这三个生命周期，因为这三个生命周期可能在一次 render 中被反复调用多次。
  ``` 
- 取代这三个生命周期的是两个新生命周期
  - `static getDerivedStateFromProps(nextProps,nextState)`
    - 在 React 16.3.0 版本中：在组件实例化、接收到新的 props 时会被调用
    - 在 React 16.4.0 版本中：在组件实例化、接收到新的 props 、组件状态更新时会被调用

    ```
    该方法可以返回一个对象，将会和 state 发生合并，且不会触发 re-render。
    这个生命周期主要为我们提供了一个可以在组件实例化或 props、state 发生变化后根据 props 修改 state 的一个时机，
    用来替代旧的生命周期中的 componentWillMount、ComponentWillReceiveProps。
    因为是一个静态方法，this 指向不是组件实例。
    ```
  - `getSnapshotBeforeUpdate（prevProps,prevState）`
    - 在 render 函数调用之后，实际的 Dom 渲染之前，在这个阶段我们可以拿到上一个状态 Dom 元素的坐标、大小的等相关信息，用于替代旧的生命周期中的 componentWillUpdate。
    - 该函数的返回值将会作为 componentDidUpdate 的第三个参数出现。

#### 4.2.2.3. 数据的向下流动(state->props)

- 说明
  - 不管是父组件或是子组件都无法知道某个组件是有状态的还是无状态的，并且它们也并不关心它是函数组件还是 class 组件。
  - 这就是为什么称 state 为局部的或是封装的的原因
  - 除了拥有并设置了它的组件，其他组件都无法访问。

- 组件可以选择把它的 state 作为 props 向下传递到它的子组件中：

  ```javascript
  <FormattedDate date={this.state.date} />
  ```
  - FormattedDate 组件会在其 props 中接收参数 date
  - 但是组件本身无法知道它是来自于 Clock 的 state，或是 Clock 的 props，还是手动输入的：

  ```javascript
  function FormattedDate(props) {
    return <h2>It is {props.date.toLocaleTimeString()}.</h2>;
  }
  ```

### 4.2.3. ref

## 4.3. 事件处理

> 通过 React 元素处理事件跟在 DOM 元素上处理事件非常相似。但是有一些语法上的区别：

### 4.3.1. 与html上事件语法区别

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

### 4.3.2. 类组件事件与bind

- 注意：
  - 组件中render方法中的this为组件实例对象
  - 组件自定义的方法中this为undefined(作为事件的回调使用)，

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
    - 然而， **如果这个回调被作为 prop(属性) 传递给下级组件，这些组件可能需要额外的重复渲染** 。
    - 通常建议在构造函数中进行绑定，以避免这类性能问题。

### 4.3.3. 事件的参数传递

- 示例：
  - 在循环内部，通常需要将一个额外的参数传递给事件处理程序
  - 例如，如果 id 是一个内联 ID，则以下任一方式都可以正常工作：

  ```jsx
  <button onClick={(e) => this.deleteRow(id, e)}>Delete Row</button>
  <button onClick={this.deleteRow.bind(this, id)}>Delete Row</button>
  ```

  - 上述两行代码是等价的，分别使用 [arrow functions](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/Arrow_functions) 和 [Function.prototype.bind](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_objects/Function/bind) 。
  - 上面两个例子中，参数 e 作为 React 事件对象将会被作为第二个参数进行传递。
  - 通过箭头函数的方式，事件对象 **必须显式** 的进行传递
  - 但是通过 bind 的方式， **事件对象以及更多的参数将会被隐式的进行传递** 。

## 4.4. 渲染方式

### 4.4.1. 条件渲染

#### 4.4.1.1. 基本说明

- 说明：
  - React 中的条件渲染就和在 JavaScript 中的条件语句一样。
  - 使用 JavaScript 操作符如 if 或者条件操作符来创建渲染当前状态的元素，并且让 React 更新匹配的 UI 。
- 示例：

  ```javascript
  function Greeting(props) {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
      return <UserGreeting />;
    }
    return <GuestGreeting />;
  }

  ReactDOM.render(
    // 修改为 isLoggedIn={true} 试试:
    <Greeting isLoggedIn={false} />,
    document.getElementById('root')
  );
  ```

#### 4.4.1.2. 元素变量

- 说明：可以用变量来存储元素
- 目的：有条件地渲染组件的一部分，而输出的其余部分不会更改。
- 示例：

  ```javascript
  function LoginButton(props) {
    return (
      <button onClick={props.onClick}>
        Login
      </button>
    );
  }

  function LogoutButton(props) {
    return (
      <button onClick={props.onClick}>
        Logout
      </button>
    );
  }
  ```

  ```javascript
  class LoginControl extends React.Component {
    constructor(props) {
      super(props);
      this.handleLoginClick = this.handleLoginClick.bind(this);
      this.handleLogoutClick = this.handleLogoutClick.bind(this);
      this.state = {isLoggedIn: false};
    }

    handleLoginClick() {
      this.setState({isLoggedIn: true});
    }

    handleLogoutClick() {
      this.setState({isLoggedIn: false});
    }

    render() {
      const isLoggedIn = this.state.isLoggedIn;
      let button;

      if (isLoggedIn) {
        button = <LogoutButton onClick={this.handleLogoutClick} />;
      } else {
        button = <LoginButton onClick={this.handleLoginClick} />;
      }

      return (
        <div>
          <Greeting isLoggedIn={isLoggedIn} />
          {button}
        </div>
      );
    }
  }

  ReactDOM.render(
    <LoginControl />,
    document.getElementById('root')
  );
  ```

#### 4.4.1.3. 内联条件写法

> 声明一个变量并使用 if 语句进行条件渲染是不错的方式，但有时你可能会想使用更为简洁的语法。

##### 与运算符 &&

- 之所以能这样做，是因为在 JavaScript 中
- true && expression 总是会返回 expression
- 而 false && expression 总是会返回 false。

```javascript
function Mailbox(props) {
  const unreadMessages = props.unreadMessages;
  return (
    <div>
      <h1>Hello!</h1>
      {unreadMessages.length > 0 &&
        <h2>
          You have {unreadMessages.length} unread messages.
        </h2>
      }
    </div>
  );
}

const messages = ['React', 'Re: React', 'Re:Re: React'];

const root = ReactDOM.createRoot(document.getElementById('root')); 
root.render(<Mailbox unreadMessages={messages} />);
```

##### 三目运算符

```javascript
render() {
  const isLoggedIn = this.state.isLoggedIn;
  return (
    <div>
      The user is <b>{isLoggedIn ? 'currently' : 'not'}</b> logged in.
    </div>
  );
}
```
```javascript
render() {
  const isLoggedIn = this.state.isLoggedIn;
  return (
    <div>
      {isLoggedIn
        ? <LogoutButton onClick={this.handleLogoutClick} />
        : <LoginButton onClick={this.handleLoginClick} />
      }
    </div>
  );
}
```

### 4.4.2. 阻止渲染

- 在极少数情况下，可能希望能隐藏组件，即使它已经被其他组件渲染
  - 若要完成此操作， **可以让 render 方法直接返回 null，而不进行任何渲染** 。

- 下面的示例中，`<WarningBanner />` 会根据 prop 中 warn 的值来进行条件渲染
  - 如果 warn 的值是 false，那么组件则不会渲染:

  ```javascript
  function WarningBanner(props) {
    if (!props.warn) {
      return null;
    }

    return (
      <div className="warning">
        Warning!
      </div>
    );
  }

  class Page extends React.Component {
    constructor(props) {
      super(props);
      this.state = {showWarning: true};
      this.handleToggleClick = this.handleToggleClick.bind(this);
    }

    handleToggleClick() {
      this.setState(state => ({
        showWarning: !state.showWarning
      }));
    }

    render() {
      return (
        <div>
          <WarningBanner warn={this.state.showWarning} />
          <button onClick={this.handleToggleClick}>
            {this.state.showWarning ? 'Hide' : 'Show'}
          </button>
        </div>
      );
    }
  }

  const root = ReactDOM.createRoot(document.getElementById('root')); 
  root.render(<Page />);
  ```

- 注意：
  - 在组件的 render 方法中 **返回 null 并不会影响组件的生命周期** 
  - 例如，上面这个示例中，componentDidUpdate 依然会被调用。。

### 4.4.3. 列表渲染与key

- react可以渲染多个组件，其中列表中的react元素应该设置key属性

  ```javascript
  function NumberList(props) {
    const numbers = props.numbers;
    const listItems = numbers.map((number) =>
      <li key={number.toString()}>
        {number}
      </li>
    );
    return (
      <ul>{listItems}</ul>
    );
  }
  ```

- key 帮助 React 识别哪些元素改变了，比如被添加或删除
  - 因此你应当给数组中的每一个元素赋予一个确定的标识。
  - 一个元素的 key 最好是这个元素在列表中拥有的一个独一无二的字符串
  - 当元素没有确定 id 的时候，万不得已你可以使用元素索引 index 作为 key
  - 如果列表项目的顺序可能会变化，我们不建议使用索引来用作 key 值，因为这样做会导致性能变差，还可能引起组件状态的问题。

  > [深度解析使用索引作为 key 的负面影响](https://medium.com/@robinpokorny/index-as-a-key-is-an-anti-pattern-e0349aece318)
  >
  > [深入解析为什么 key 是必须的](https://zh-hans.reactjs.org/docs/reconciliation.html#recursing-on-children)

- key注意：
  - 元素的 key 只有放在就近的数组上下文中才有意义。

    ```javascript
    function ListItem(props) {
      const value = props.value;
      return (
        // 错误！你不需要在这里指定 key：
        <li key={value.toString()}>
          {value}
        </li>
      );
    }

    function NumberList(props) {
      const numbers = props.numbers;
      const listItems = numbers.map((number) =>
        // 错误！元素的 key 应该在这里指定：
        <ListItem value={number} />
      );
      return (
        <ul>
          {listItems}
        </ul>
      );
    }
    ```
  - key 值在兄弟节点之间必须唯一

## 4.5. 表单数据处理与受控组件

### 4.5.1. 受控组件

- 在 HTML 中，表单元素如 `<input>`，`<textarea>` 和 `<select>` 表单元素通常保持自己的状态，并根据用户输入进行更新。
- 而在 React 中，可变状态一般保存在组件的 `state(状态)` 属性中，并且只能通过 `setState()` 更新。
- 可以通过使 React 的 state 成为 “单一数据源” 来结合这两个形式
  - 渲染表单的 React 组件也可以控制在用户输入之后的行为
  - 这种形式，其值由 React 控制的表单元素称为“受控组件”。


### 4.5.2. 与html有区别的受控组件

#### 4.5.2.1. `<input type="text">`

```javascript
class NameForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {value: ''};

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    alert('A name was submitted: ' + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Name:
          <input type="text" value={this.state.value} onChange={this.handleChange} />
        </label>
        <input type="submit" value="Submit" />
      </form>
    );
  }
}
```

- 设置表单元素的 value 属性之后，其显示值将由 this.state.value 决定，以满足 React 状态的同一数据理念
- 每次键盘敲击之后会执行 handleChange 方法以更新 React 状态，显示值也将随着用户的输入改变。
- 对于受控组件来说，每一次 state(状态) 变化都会伴有相关联的处理函数。
  - 这使得 **可以直接修改或验证用户的输入** 
  - 比如，如果我们希望强制 name 的输入都是大写字母，可以这样来写 handleChange 方法

  ```javascript
  handleChange(event) {
    this.setState({value: event.target.value.toUpperCase()});
  }
  ```

#### 4.5.2.2. textare

- 在 HTML 中，`<textarea>` 元素通过它的子节点定义了它的文本值：
- 在 React 中，`<textarea>` 的赋值使用 value 属性替代。这样一来，表单中 <textarea> 的书写方式接近于单行文本输入框。

  ```javascript
  class ControlledTextArea extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        // 注意，this.state.value 在构造函数中初始化，所以这些文本一开始就出现在文本域中
        value: "the value of textarea",
      };

      this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
      console.log(event.target.value);
      this.setState({ value: event.target.value });
    }
    render() {
      return <textarea value={this.state.value} onChange={this.handleChange} />;
    }
  }
  ```

#### 4.5.2.3. select

- 在 HTML 中，`<select>` 创建了一个下拉列表
  - 里面使用`<option>`表示可选项
  - 并用`selected`属性进行选中
  ```html
  <select>
    <option value="grapefruit">Grapefruit</option>
    <option value="lime">Lime</option>
    <option selected value="coconut">Coconut</option>
    <option value="mango">Mango</option>
  </select>
  ```
- React 中，并不使用这个 selected 属性，而是在根 select 标签中使用了一个 value 属性。

  ```javascript
  class ControlledSelect extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        value: "1value",
      };
      this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
      this.setState({
        value: event.target.value,
      });
    }
    render() {
      const options = [1, 2, 3, 4, 5, 6];
      return (
        <select value={this.state.value} onChange={this.handleChange}>
          {options.map((num) => (
            <option key={num.toString()} value={num.toString() + "value"}>
              {num.toString() + "value" + "-content"}
            </option>
          ))}
        </select>
      );
    }
  }
  ```

### 4.5.3. 不受控组件

- 在HTML中， 
  - `<input type="file">` 可以让用户从设备存储器中选择一个或多个文件上传到服务器
  - 或者通过 JavaScript 使用 [File API](https://developer.mozilla.org/en-US/docs/Web/API/File_API/Using_files_from_web_applications) 操作。

- 因为它的值是只读的，所以它是 Reac t中的  **不受控 组件** 
  > React深入中会进行详细说明

### 4.5.4. 处理多个输入元素

- 加个name做区分就行

  ```javascript
  class Reservation extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        isGoing: true,
        numberOfGuests: 2
      };

      this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(event) {
      const target = event.target;
      const value = target.type === 'checkbox' ? target.checked : target.value;
      const name = target.name;

      this.setState({
        // es6 语法。取变量name的值作为key
        [name]: value
      });
    }

    render() {
      return (
        <form>
          <label>
            Is going:
            <input
              name="isGoing"
              type="checkbox"
              checked={this.state.isGoing}
              onChange={this.handleInputChange} />
          </label>
          <br />
          <label>
            Number of guests:
            <input
              name="numberOfGuests"
              type="number"
              value={this.state.numberOfGuests}
              onChange={this.handleInputChange} />
          </label>
        </form>
      );
    }
  }
  ```
- 想为每一个组件写一个`handleChange`也拦不住你

### 4.5.5. 受控组件的null值

- 下面这种情况，因为已经给定了固定值，并且没有设置`onChange`处理处理方法，input输入框可以聚焦，但是无法修改值

  ```javascript
  <input type="text" value="hi" />
  ```
- 若将value改为`null`则可以输入值，这种情况下可以看作input组件已经不受react控制了。

### 4.5.6. 受控组件的替代方案

- 有时使用受控组件有些乏味，因为需要为每一个可更改的数据提供事件处理器，并通过 React 组件管理所有输入状态。
- 当将已经存在的代码转换为 React 时，或将 React 应用程序与非 React 库集成时，这可能变得特别烦人。
- 在这些情况下，可能需要使用 **不受控的组件** ，用于实现输入表单的替代技术。
- 这将在React深入章节进行讨论

### 4.5.7. 完全成熟的解决方案

- 如果正在寻找一个完整的解决方案，包括验证、跟踪访问的字段以及处理表单提交
- 那么 Formik 是最受欢迎的选择之一
- 它建立在受控组件和管理状态的相同原则之上。

## 4.6. 状态提升

> 如果已经做过了井字棋的入门案例，那么这一节简单看看即可。

### 4.6.1. 基本说明

- 通常情况下，同一个数据的变化需要几个不同的组件来反映。
- 建议提升共享的状态到它们最近的祖先组件中。

### 4.6.2. 示例

```javascript
const scaleNames = {
  c: 'Celsius',
  f: 'Fahrenheit'
};

function toCelsius(fahrenheit) {
  return (fahrenheit - 32) * 5 / 9;
}

function toFahrenheit(celsius) {
  return (celsius * 9 / 5) + 32;
}

function tryConvert(temperature, convert) {
  const input = parseFloat(temperature);
  if (Number.isNaN(input)) {
    return '';
  }
  const output = convert(input);
  const rounded = Math.round(output * 1000) / 1000;
  return rounded.toString();
}

function BoilingVerdict(props) {
  if (props.celsius >= 100) {
    return <p>The water would boil.</p>;
  }
  return <p>The water would not boil.</p>;
}

class TemperatureInput extends React.Component {
  constructor(props) {
    super(props);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(e) {
    this.props.onTemperatureChange(e.target.value);
  }

  render() {
    const temperature = this.props.temperature;
    const scale = this.props.scale;
    return (
      <fieldset>
        <legend>Enter temperature in {scaleNames[scale]}:</legend>
        <input value={temperature}
              onChange={this.handleChange} />
      </fieldset>
    );
  }
}

class Calculator extends React.Component {
  constructor(props) {
    super(props);
    this.handleCelsiusChange = this.handleCelsiusChange.bind(this);
    this.handleFahrenheitChange = this.handleFahrenheitChange.bind(this);
    this.state = {temperature: '', scale: 'c'};
  }

  handleCelsiusChange(temperature) {
    this.setState({scale: 'c', temperature});
  }

  handleFahrenheitChange(temperature) {
    this.setState({scale: 'f', temperature});
  }

  render() {
    const scale = this.state.scale;
    const temperature = this.state.temperature;
    const celsius = scale === 'f' ? tryConvert(temperature, toCelsius) : temperature;
    const fahrenheit = scale === 'c' ? tryConvert(temperature, toFahrenheit) : temperature;

    return (
      <div>
        <TemperatureInput
          scale="c"
          temperature={celsius}
          onTemperatureChange={this.handleCelsiusChange} />
        <TemperatureInput
          scale="f"
          temperature={fahrenheit}
          onTemperatureChange={this.handleFahrenheitChange} />
        <BoilingVerdict
          celsius={parseFloat(celsius)} />
      </div>
    );
  }
}

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<Calculator />);
```

### 4.6.3. 经验总结

- 在一个 React 应用中，对于任何可变的数据都应该循序“单一数据源”原则
  - 通常情况下，state 首先被添加到需要它进行渲染的组件
  - 然后，如果其它的组件也需要它，可以提升状态到它们最近的祖先组件。
  - 应该依赖 从上到下的数据流向 ，而不是试图在不同的组件中同步状态。

- 提升状态相对于双向绑定方法需要写更多的“模板”代码
  - 但是有一个好处，它可以更方便的找到和隔离 bugs
  - 由于任何 state(状态) 都 “存活” 在若干的组件中，而且可以分别对其独立修改，所以发生错误的可能大大减少。
  - 另外，可以实现任何定制的逻辑来拒绝或者转换用户输入。

- 如果某个东西可以从 props(属性) 或者 state(状态) 中计算得到，那么它可能不应该在 state(状态) 中
  - 例如，我们只保存最后编辑的 temperature 和它的 scale，
  - 而不是保存 celsiusValue 和 fahrenheitValue ， 另一个输入框的值总是在 render() 方法中计算得来的。
  - 这使我们对其进行清除和四舍五入到其他字段同时不会丢失用户输入的精度。

- 当你看到 UI 中的错误，你可以使用 React 开发者工具来检查 props 
  - 并向上遍历树，直到找到负责更新状态的组件。
  - 这使你可以跟踪到 bug 的源头


## 4.7. 组合(Composition) VS 继承(Inheritance)

### 4.7.1. 说明

- React 拥有一个强大的组合模型，建议使用组合而不是继承以实现代码的重用。
- 在 Facebook ，还没有发现任何用例，值得建议用继承层次结构来创建组件。
- 使用 props(属性) 和 组合已经足够灵活来明确、安全的定制一个组件的外观和行为。
- 切记，组件可以接受任意的 props(属性) ，包括原始值、React 元素，或者函数。
- 如果要在组件之间重用非 U I功能
  - 建议将其提取到单独的 JavaScript 模块中
  - 组件可以导入它并使用该函数，对象或类，而不扩展它。

### 4.7.2. 组合方式

- 使用`props.children`进行组合

  ```javascript
  function FancyBorder(props) {
    console.log(props.children)
    return (
      <div className={'FancyBorder FancyBorder-' + props.color}>
        {props.children} // h1 和 p两个React对象
      </div>
    );
  }

  function WelcomeDialog() {
    return (
      <FancyBorder color="blue">
        <h1 className="Dialog-title">
          Welcome
        </h1>
        <p className="Dialog-message">
          Thank you for visiting our spacecraft!
        </p>
      </FancyBorder>
    );
  }

  const root = ReactDOM.createRoot(document.getElementById('root'));
  root.render(<WelcomeDialog />);
  ```

- 使用自定义属性传React对象

  ```javascript
  function SplitPane(props) {
    return (
      <div className="SplitPane">
        <div className="SplitPane-left">
          {props.left}
        </div>
        <div className="SplitPane-right">
          {props.right}
        </div>
      </div>
    );
  }

  function App() {
    return (
      <SplitPane
        left={
          <Contacts />
        }
        right={
          <Chat />
        } />
    );
  }
  ```

- 使用普通变量配置React元素

  ```javascript
  function Dialog(props) {
    return (
      <FancyBorder color="blue">
        <h1 className="Dialog-title">
          {props.title}
        </h1>
        <p className="Dialog-message">
          {props.message}
        </p>
      </FancyBorder>
    );
  }

  function WelcomeDialog() {
    return (
      <Dialog
        title="Welcome"
        message="Thank you for visiting our spacecraft!" />

    );
  }
  ```

## 4.8. React编程思想

> [示例跳转](https://react.html.cn/docs/thinking-in-react.html)

- 将 UI 拆解到组件层次结构中
- 用 React 构建一个静态版本
- 确定 UI state(状态) 的最小（但完整）表示
- 确定 state(状态) 的位置
  - 确定每个基于这个 state(状态) 渲染的组件。
  - 找出公共父级组件（一个单独的组件，在组件层级中位于所有需要这个 state(状态) 的组件的上面。
  - 公共父级组件 或者 另一个更高级组件拥有这个 state(状态) 。
  - 如果找不出一个拥有该 state(状态) 的合适组件，可以创建一个简单的新组件来保留这个 state(状态) ，并将其添加到公共父级组件的上层即可。
- 添加反向数据流(回调函数)

# 5. React深入

## 5.1. 虚拟DOM与真实DOM diff算法

# 6. 常用相关套件

## 6.1. React 脚手架

## 6.2. React 路由

## 6.3. React Ajax、axios

## 6.4. react-redux

## 6.5. CRACO

**C** **r**eate **R**eact **A**pp **C**onfiguration **O**verride 

# 7. 参考资料

- [x] [官方入门项目](https://react.html.cn/tutorial/tutorial.html)
- [ ] [中文文档18.2.0](https://react.html.cn/docs/hello-world.html)
- [ ] [中文文档16.6.3](https://react.html.cn/docs/hello-world.html)
- [ ] [react全家桶学习笔记](https://juejin.cn/post/6979132493333004319)
  - [补充](https://github.com/IgnorantCircle/myStudy)
- [ ] [用React、Redux、Immutable做俄罗斯方块](https://github.com/chvin/react-tetris)
- [ ] [Immutable 详解及 React 中实践](https://github.com/camsong/blog/issues/3)
- [ ] [从 1 到完美，用 js 和 react-native 写一个 APP](https://segmentfault.com/a/1190000016272845)
- [ ] [React 进阶](https://www.jianshu.com/p/0b2da7a6b337)
  - [ ] [React模块化与组件化](https://www.jianshu.com/p/ffd4101cee4b)
<!-- https://www.bilibili.com/video/BV1wy4y1D7JT/ -->
- [ ] [js class支持定义属性/属性初始化器语法](https://www.jianshu.com/p/d80c30cb4d71)
