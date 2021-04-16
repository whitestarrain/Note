# 1. 总括

## 设计模式间的关系

![design-patterns-1](./image/design-patterns-1.png)

## 设计模式的六大原则
- 1、开闭原则（Open Close Principle）
  > 开闭原则的意思是：对扩展开放，对修改关闭。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

- 2、里氏代换原则（Liskov Substitution Principle）
  > 里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

- 3、依赖倒转原则（Dependence Inversion Principle）
  > 这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

- 4、接口隔离原则（Interface Segregation Principle）
  > 这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

- 5、迪米特法则，又称最少知道原则（Demeter Principle）
  > 最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

- 6、合成复用原则（Composite Reuse Principle）
  > 合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。

# 2. 创建型模式

## 2.1. 概述

这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。
工厂模式（Factory Pattern）

## 2.2. 抽象工厂模式（Abstract Factory Pattern）

## 2.3. 单例模式（Singleton Pattern）

- 优点：
  - 1、在内存里只有一个实例，减少了内存的开销，尤其是频繁的创建和销毁实例（比如管理学院首页页面缓存）。
  - 2、避免对资源的多重占用（比如写文件操作）。
- 缺点：没有接口，不能继承，与单一职责原则冲突，一个类应该只关心内部逻辑，而不关心外面怎么样来实例化。

---

- 使用场景：
  - 1、要求生产唯一序列号。
  - 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
  - 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。

---

- 【可用】懒汉式，线程不安全
- 【可用】懒汉式，线程安全（静态代码块）
- 懒汉式，线程安全（静态常量）
- 饿汉式（静态常量）
- 饿汉式（静态代码块）
- doubleCheckLock
- 登记式/静态内部类
- 枚举


## 2.4. 建造者模式（Builder Pattern）

## 2.5. 原型模式（Prototype Pattern）

# 3. 结构型模式

## 3.1. 概述

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式。

## 3.2. 适配器模式（Adapter Pattern）

## 3.3. 桥接模式（Bridge Pattern）

## 3.4. 过滤器模式（Filter、Criteria Pattern）

## 3.5. 组合模式（Composite Pattern）

## 3.6. 装饰器模式（Decorator Pattern）

## 3.7. 外观模式（Facade Pattern）

## 3.8. 享元模式（Flyweight Pattern）

## 3.9. 代理模式（Proxy Pattern）

# 4. 行为型模式

## 4.1. 概述

这些设计模式特别关注对象之间的通信。

## 4.2. 责任链模式（Chain of Responsibility Pattern）

## 4.3. 命令模式（Command Pattern）

## 4.4. 解释器模式（Interpreter Pattern）

## 4.5. 迭代器模式（Iterator Pattern）

## 4.6. 中介者模式（Mediator Pattern）

## 4.7. 备忘录模式（Memento Pattern）

## 4.8. 观察者模式（Observer Pattern）

## 4.9. 状态模式（State Pattern）

## 4.10. 空对象模式（Null Object Pattern）

## 4.11. 策略模式（Strategy Pattern）

## 4.12. 模板模式（Template Pattern）

- 最经典的JDK应用的就是AQS

## 4.13. 访问者模式（Visitor Pattern）

# 5. J2EE 模式

## 5.1. 概述

这些设计模式特别关注表示层。这些模式是由 Sun Java Center 鉴定的。

## 5.2. MVC 模式（MVC Pattern）

## 5.3. 业务代表模式（Business Delegate Pattern）

## 5.4. 组合实体模式（Composite Entity Pattern）

## 5.5. 数据访问对象模式（Data Access Object Pattern）

## 5.6. 前端控制器模式（Front Controller Pattern）

## 5.7. 拦截过滤器模式（Intercepting Filter Pattern）

## 5.8. 服务定位器模式（Service Locator Pattern）

## 5.9. 传输对象模式（Transfer Object Pattern）
