> 面向对象(oo)=>功能模块(设计模式+算法/数据结构)==放到==>框架(使用多种设计模式)=>架构(服务器集群)

**一定要好好看代码和注释**

# 1. 总括

## 1.1. 设计模式目的

编写软件过程中，程序员面临着来自 耦合性，内聚性以及可维护性，可扩展性，重用性，灵活性 等多方面的挑战，设计模式是为了让程序(软件)，具有更好的：

1. 代码重用性 (即：相同功能的代码，不用多次编写)
2. 可读性 (即：编程规范性,  便于其他程序员的阅读和理解)
3. 可扩展性 (即：当需要增加新的功能时，非常的方便，称为可维护)
4. 可靠性 (即：当我们增加新的功能后，对原来的功能没有影响)
5. 使程序呈现**高内聚，低耦合**的特性。

> 设计模式包含了面向对象的精髓，“懂了设计模式，你就懂了面向对象分析和设计（OOA/D）的精要”

## 1.2. 设计模式的七大原则

### 1.2.1. 单一职责原则

#### 1.2.1.1. 基本介绍

对类来说的，即一个类应该只负责一项职责。如类A负责两个不同职责：职责1，职责2。当职责1需求变更而改变A时，可能造成职责2执行错误， 所以需要将类A的粒度分解为A1， A2

#### 1.2.1.2. 案例

> 应用实例：以交通工具案例讲解

<details>
<summary style="color:red;">案例1:未准守单一职责原则</summary>

```java
public class SingleResponsibility1 {

	public static void main(String[] args) {
		Vehicle vehicle = new Vehicle();
		vehicle.run("摩托车");
		vehicle.run("汽车");
		vehicle.run("飞机");
	}
}

// 方式1
// 会有：飞机在公路上运行。这一错误
// 违反了单一职责原则
// 解决的方案非常的简单，根据交通工具运行方法不同，分解成不同类即可
class Vehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + " 在公路上运行....");
	}
}
```

</details>

<details>
<summary style="color:red;">案例2：完全准守单一职责原则，对类进行拆解，实现单一职责原则</summary>

```java
public class SingleResponsibility2 {

	public static void main(String[] args) {
		RoadVehicle roadVehicle = new RoadVehicle();
		roadVehicle.run("摩托车");
		roadVehicle.run("汽车");
		
		AirVehicle airVehicle = new AirVehicle();
		
		airVehicle.run("飞机");
	}

}

//方案2的分析
//1. 遵守单一职责原则
//2. 但是这样做的改动很大，即将类分解，同时修改客户端
//3. 改进：直接修改Vehicle 类，改动的代码会比较少=>方案3
class RoadVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "公路运行");
	}
}

class AirVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "天空运行");
	}
}

class WaterVehicle {
	public void run(String vehicle) {
		System.out.println(vehicle + "水中运行");
	}
}
```

1. 遵守单一职责原则
2. 但是这样做的改动很大，即将类分解，同时修改客户端
3. 改进：直接修改Vehicle 类，改动的代码会比较少=>方案3

</details>


<details>
<summary style="color:red;">案例3：没有完全准守单一职责原则，对方法进行拆解，实现单一职责原则</summary>

```java
public class SingleResponsibility3 {

	public static void main(String[] args) {
		Vehicle2 vehicle2  = new Vehicle2();
		vehicle2.run("汽车");
		vehicle2.runWater("轮船");
		vehicle2.runAir("飞机");
	}

}

//方式3的分析
//1. 这种修改方法没有对原来的类做大的修改，只是增加方法
//2. 这里虽然没有在类这个级别上遵守单一职责原则，但是在方法级别上，仍然是遵守单一职责
class Vehicle2 {
	public void run(String vehicle) {
		//处理
		System.out.println(vehicle + " 在公路上运行....");
	}
	
	public void runAir(String vehicle) {
		System.out.println(vehicle + " 在天空上运行....");
	}
	
	public void runWater(String vehicle) {
		System.out.println(vehicle + " 在水中运行....");
	}

	//方法4.
	//...
}
```

</details>

补充：**慎用if-else分支语句，耦合性过高**

#### 1.2.1.3. 注意

- **单一职责原则的注意事项和原则**
  1. 降低类的复杂度，一个类只负责一项职责
  2. 提高类的可读性，可维护性
  3. 降低变更引起的风险
  4. 通常情况下，**我们应当遵守单一职责原则**，只有逻辑足够简单，才可以在代码级违反单一职责原则；**只有类中方法数量足够少，可以在方法级别保持单一职责原则**
    > 比如方式3，因为代码比较简单，所以只把功能拆分为了方法，但是如果每个方法中都要调用类中的其他各种方法，还是分成类比较好。

### 1.2.2. 接口隔离原则

#### 1.2.2.1. 基本介绍

客户端不应该依赖它不需要的接口，即一个类对另一个类的依赖应该建立在最小的接口上

#### 1.2.2.2. 案例

1. 类A通过接口Interface1依赖类B，类C通过接口Interface1依赖类D，如果接口Interface1对于类A和类C来说不是最小接口，那么类B和类D必须去实现他们不需要的方法。
2. 按隔离原则应当这样处理：将接口Interface1拆分为独立的几个接口，类A和类C分别与他们需要的接口建立依赖关系。也就是采用接口隔离原则

![design-patterns-2](./image/design-patterns-2.png)


<details>
<summary style="color:red;">案例1：未应用接口隔离原则，问题：类 A 通过 Interface1 依赖类 B 类 C 通过 Interface1 依赖类 D，造成类 B 和 类 D 需要去实现他们无需实现的接口</summary>

```java
public class Segregation1 {

	public static void main(String[] args) {
		A a = new A();
		a.depend1(new B()); // A类通过接口去依赖B类
		a.depend2(new B());
		a.depend3(new B());
	}

}

//接口
interface Interface1 {
	void operation1();

	void operation2();

	void operation3();

	void operation4();

	void operation5();
}

class B implements Interface1 {
	public void operation1() {
		System.out.println("B 实现了 operation1");
	}

	public void operation2() {
		System.out.println("B 实现了 operation2");
	}

	public void operation3() {
		System.out.println("B 实现了 operation3");
	}

	public void operation4() {
		System.out.println("B 实现了 operation4");
	}

	public void operation5() {
		System.out.println("B 实现了 operation5");
	}
}

class D implements Interface1 {
	public void operation1() {
		System.out.println("D 实现了 operation1");
	}

	public void operation2() {
		System.out.println("D 实现了 operation2");
	}

	public void operation3() {
		System.out.println("D 实现了 operation3");
	}

	public void operation4() {
		System.out.println("D 实现了 operation4");
	}

	public void operation5() {
		System.out.println("D 实现了 operation5");
	}
}

class A { // A 类通过接口Interface1 依赖(使用) B类，但是只会用到1,2,3方法
	public void depend1(Interface1 i) {
		i.operation1();
	}

	public void depend2(Interface1 i) {
		i.operation2();
	}

	public void depend3(Interface1 i) {
		i.operation3();
	}
}

class C { // C 类通过接口Interface1 依赖(使用) D类，但是只会用到1,4,5方法
	public void depend1(Interface1 i) {
		i.operation1();
	}

	public void depend4(Interface1 i) {
		i.operation4();
	}

	public void depend5(Interface1 i) {
		i.operation5();
	}
}
```
</details>

<details>
<summary style="color:red;">案例2：分析传统方法的问题，使用接口隔离原则改进程序结构</summary>

- 类A通过接口Interface1依赖类B，类C通过接口Interface1依赖类D，如果接口Interface1对于类A和类C来说不是最小接口，那么类B和类D必须去实现他们不需要的方法
- 将接口Interface1拆分为独立的几个接口，类A和类C分别与他们需要的接口建立依赖关系。也就是采用接口隔离原则
- 接口Interface1中出现的方法，根据实际情况拆分为三个接口

![design-patterns-4](./image/design-patterns-4.png)

```java
public class Segregation1 {

	public static void main(String[] args) {

		// 使用一把
		A a = new A();
		a.depend1(new B()); // A类通过接口去依赖B类
		a.depend2(new B());
		a.depend3(new B());

		C c = new C();

		c.depend1(new D()); // C类通过接口去依赖(使用)D类
		c.depend4(new D());
		c.depend5(new D());

	}

}

// 接口1
interface Interface1 {
	void operation1();
}

// 接口2
interface Interface2 {
	void operation2();
	void operation3();
}

// 接口3
interface Interface3 {
	void operation4();
	void operation5();
}

class B implements Interface1, Interface2 {
	public void operation1() {
		System.out.println("B 实现了 operation1");
	}

	public void operation2() {
		System.out.println("B 实现了 operation2");
	}

	public void operation3() {
		System.out.println("B 实现了 operation3");
	}

}

class D implements Interface1, Interface3 {
	public void operation1() {
		System.out.println("D 实现了 operation1");
	}

	public void operation4() {
		System.out.println("D 实现了 operation4");
	}

	public void operation5() {
		System.out.println("D 实现了 operation5");
	}
}

class A { // A 类通过接口Interface1,Interface2 依赖(使用) B类，但是只会用到1,2,3方法
	public void depend1(Interface1 i) {
		i.operation1();
	}

	public void depend2(Interface2 i) {
		i.operation2();
	}

	public void depend3(Interface2 i) {
		i.operation3();
	}
}

class C { // C 类通过接口Interface1,Interface3 依赖(使用) D类，但是只会用到1,4,5方法
	public void depend1(Interface1 i) {
		i.operation1();
	}

	public void depend4(Interface3 i) {
		i.operation4();
	}

	public void depend5(Interface3 i) {
		i.operation5();
	}
}
```
</details>

### 1.2.3. 依赖倒转(倒置)原则

#### 1.2.3.1. 基本介绍

- 高层模块不应该依赖低层模块，二者都应该依赖其抽象(接口，抽象类)
- 抽象不应该依赖细节(比如实现类)，细节应该依赖抽象
- 依赖倒转(倒置)的中心思想是**面向接口编程**
- 依赖倒转原则是基于这样的设计理念：相对于细节的多变性，抽象的东西要稳定的多。以抽象为基础搭建的架构比以细节为基础的架构要稳定的多。在java中，抽象指的是接口或抽象类，细节就是具体的实现类
- **使用接口或抽象类的目的是制定好规范**，而不涉及任何具体的操作，**把展现细节的任务交给他们的实现类去完成**
 
#### 1.2.3.2. 案例


<details>
<summary style="color:red;">案例1：未遵循依赖倒置原则，Person 类与 Email 类耦合，如果我们还想获取其他消息，比如微信、短信、QQ 等、则需要添加相应的实现方法</summary>

```java
public class DependecyInversion {

	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());
	}

}

class Email {
	public String getInfo() {
		return "电子邮件信息: hello,world";
	}
}

//完成Person接收消息的功能
//方式1分析
//1. 简单，比较容易想到
//2. 如果我们获取的对象是 微信，短信等等，则新增类，同时Peron也要增加相应的接收方法
//3. 解决思路：引入一个抽象的接口IReceiver, 表示接收者, 这样Person类与接口IReceiver发生依赖
//   因为Email, WeiXin 等等属于接收的范围，他们各自实现IReceiver 接口就ok, 这样我们就符号依赖倒转原则
class Person {
	public void receive(Email email) {
		System.out.println(email.getInfo());
	}
}
```
</details>


<details>
<summary style="color:red;">案例2：引入一个抽象的接口 IReceiver，表示接收者（Email、微信、短信、QQ 等），接受者分别实现 IReceiver 接口中的方法，实现各自接收消息的逻辑，Person 类与 IReceiver 接口发生依赖，达到接收消息的功能</summary>

```java
public class DependecyInversion {

	public static void main(String[] args) {
		// 客户端无需改变
		Person person = new Person();
		person.receive(new Email());
		person.receive(new WeiXin());
	}

}

//定义接口
interface IReceiver {
	public String getInfo();
}

class Email implements IReceiver {
	public String getInfo() {
		return "电子邮件信息: hello,world";
	}
}

//增加微信
class WeiXin implements IReceiver {
	public String getInfo() {
		return "微信信息: hello,ok";
	}
}

//方式2
class Person {
	// 这里我们是对接口的依赖
	public void receive(IReceiver receiver) {
		System.out.println(receiver.getInfo());
	}
}
```
</details>

#### 1.2.3.3. 依赖关系传递的三种方式和应用案例


<details>
<summary style="color:red;">接口传递</summary>

```java
public class DependencyPass {

	public static void main(String[] args) {
		// 通过接口传递
		ChangHong changHong = new ChangHong();
		OpenAndClose openAndClose = new OpenAndClose();
		openAndClose.open(changHong);
	}

}

// 方式1： 通过接口传递实现依赖
// 开关的接口
interface IOpenAndClose {
	public void open(ITV tv); // 抽象方法,接收接口
}

// ITV接口
interface ITV {
	public void play();
}

// 长虹电视：实现 ITV 接口
class ChangHong implements ITV {
	@Override
	public void play() {
		System.out.println("长虹电视机，打开");
	}
}

// 设备播放类：实现 IOpenAndClose 接口，调用接口 IITV 的 play() 方法实现播放功能（通过接口注入）
class OpenAndClose implements IOpenAndClose {
  // 接收接口，通过接口传递依赖
	public void open(ITV tv) {
		tv.play();
	}
}
```
</details>


<details>
<summary style="color:red;">构造方法传递</summary>

```java
public class DependencyPass {

	public static void main(String[] args) {
		// 通过构造器进行依赖传递
         ChangHong changHong = new ChangHong();
		OpenAndClose openAndClose = new OpenAndClose(changHong);
		openAndClose.open();
	}

}

// 方式2: 通过构造方法依赖传递
interface IOpenAndClose {
	public void open(); // 抽象方法
}

interface ITV { // ITV接口
	public void play();
}

// 长虹电视：实现 ITV 接口
class ChangHong implements ITV {
	@Override
	public void play() {
		System.out.println("长虹电视机，打开");
	}
}

class OpenAndClose implements IOpenAndClose {
	public ITV tv; // 成员变量

  // 通过构造器注入实现了 ITV 接口的对象
	public OpenAndClose(ITV tv) {
		this.tv = tv;
	}

	public void open() {
		this.tv.play();
	}
}
```
</details>


<details>
<summary style="color:red;">setter方式传递</summary>

```java
public class DependencyPass {

	public static void main(String[] args) {
		// 通过setter方法进行依赖传递
         ChangHong changHong = new ChangHong();
		OpenAndClose openAndClose = new OpenAndClose();
		openAndClose.setTv(changHong);
		openAndClose.open();
	}

}

// 方式3 , 通过setter方法传递
interface IOpenAndClose {
	public void open(); // 抽象方法
	public void setTv(ITV tv); // 通过 setter 方法注入
}

interface ITV { // ITV接口
	public void play();
}

// 长虹电视：实现 ITV 接口
class ChangHong implements ITV {
	@Override
	public void play() {
		System.out.println("长虹电视机，打开");
	}
}

class OpenAndClose implements IOpenAndClose {
	private ITV tv;

	// 通过 setYv() 方法注入实现了 ITV 接口的对象实例
	public void setTv(ITV tv) {
		this.tv = tv;
	}

	public void open() {
		this.tv.play();
	}
}
```
</details>

**总结：无论通过什么方法，目的都是要将实现了接口的具体实现类注入到调用者类中**

#### 1.2.3.4. 注意

1. **低层模块尽量都要有抽象类或接口**，或者两者都有，程序稳定性更好
2. **变量的声明类型尽量是抽象类或接口**，这样我们的变量引用和实际对象间，就存在一个缓冲层，利于程序扩展和优化
  > 可以通过修改父类，作用到子类
3. 继承时遵循里氏替换原则

### 1.2.4. 里氏替换原则

#### 1.2.4.1. 基本介绍

- 引入
  1. 继承包含这样一层含义：父类中凡是已经实现好的方法， 实际上是在设定规范和契约，虽然它不强制要求所有的子类必须遵循这些契约，但是如果子类对这些已经实现的方法任意修改，就会对整个继承体系造成破坏。
  2. 继承在给程序设计带来便利的同时，也带来了弊端。比如使用继承会给程序带来侵入性，程序的可移植性降低，增加对象间的耦合性，如果一个类被其他的类所继承，则当这个类需要修改时，必须考虑到所有的子类，并且父类修改后，所有涉及到子类的功能都有可能产生故障
  3. 问题提出：在编程中，如何正确的使用继承? => 里氏替换原则

- 基本介绍
  1. 里氏替换原则(Liskov Substitution Principle)在1988年，由麻省理工学院的以为姓里的女士提出的。
  2. 如果对每个类型为T1的对象o1，都有类型为T2的对象o2，使得以T1定义的所有程序P在所有的对象o1都代换成o2时，程序P的行为没有发生变化，那么类型T2是类型T1的子类型。**换句话说，所有引用基类的地方必须能透明地使用其子类的对象**。
  3. 在使用继承时，遵循里氏替换原则，**在子类中尽量不要重写父类的方法**
  4. 里氏替换原则告诉我们，继承实际上让两个类耦合性增强了， **在适当的情况下，可以通过聚合，组合，依赖来解决问题**
    > 我们也可以通过提升的方法，来尽量满足里氏替换原则，假设现在有两个类，A 类和 B 类，如果 B 类继承 A 类，需要重写 A 类中的某些方法，那么，我们在 A 类 和 B 类之上，再抽取出一个更加通用的父类 CommonSuper，让 A 类和 B 类同时去继承 CommonSuper，这样 B 类就无须重写 A 类中的某些方法，达到基类的引用对子类对象透明的效果

#### 1.2.4.2. 案例


<details>
<summary style="color:red;">案例1：未遵循里氏替换原则，由于子类 B 继承父类 A 时重写了 func1() 方法，导致程序中使用多态时，本意是想调用重写前的方法，结果变成了重写后的方法，所以程序输出结果和预期不同</summary>

```java
public class Liskov {

	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("1-8=" + a.func1(1, 8));

		System.out.println("-----------------------");
		B b = new B();
		System.out.println("11-3=" + b.func1(11, 3));// 这里本意是求出11-3，结果变成了11+3
		System.out.println("1-8=" + b.func1(1, 8));// 这里本意是求出1-8，结果变成了1+8
		System.out.println("11+3+9=" + b.func2(11, 3));
	}

}

// A类
class A {
	// 返回两个数的差
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

// B类继承了A
// 增加了一个新功能：完成两个数相加,然后和9求和
class B extends A {
	// 这里，重写了A类的方法, 可能是无意识
	public int func1(int a, int b) {
		return a + b;
	}

	public int func2(int a, int b) {
		return func1(a, b) + 9;
	}
}
```

</details>

- 原因分析与解决方法
  1. 案例一中，我们发现原来运行正常的相减功能发生了错误。原因就是类B无意中重写了父类的方法，造成原有功能出现错误。在实际编程中，我们常常会通过重写父类的方法完成新的功能，这样写起来虽然简单，但整个继承体系的复用性会比较差。特别是运行多态比较频繁的时候
  2. 通用的做法是：原来的父类和子类都继承一个更通俗的基类，原有的继承关系去掉，采用依赖，聚合，组合等关系代替。见案例二

![design-patterns-5](./image/design-patterns-5.png)


<details>
<summary style="color:red;">案例2：将类 B 的级别提升至与类 A 平级，他们有一个共同的父类 Base，这样就不会出现类 B 重写类 A 中方法的问题，此时基类的引用能够透明地使用子类的对象</summary>


```java
public class Liskov {

	public static void main(String[] args) {
		
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("1-8=" + a.func1(1, 8));

		System.out.println("-----------------------");
		B b = new B();
		
		// 因为B类不再继承A类，因此调用者，不会再func1是求减法
		// 调用完成的功能就会很明确
		System.out.println("11+3=" + b.func1(11, 3));// 这里本意是求出11+3
		System.out.println("1+8=" + b.func1(1, 8));// 这里本意是求出1+8
		System.out.println("11+3+9=" + b.func2(11, 3));

		// 使用组合仍然可以使用到A类相关方法
		System.out.println("11-3=" + b.func3(11, 3));// 这里本意是求出11-3

	}

}

//创建一个更加基础的基类
class Base {
	// 把更加基础的方法和成员写到Base类
}

// A类
class A extends Base {
	// 返回两个数的差
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

// B类继承了A
// 增加了一个新功能：完成两个数相加,然后和9求和
class B extends Base {
	// 如果B需要使用A类的方法,使用组合关系
	private A a = new A();

	// 这里虽然方法名是 fun1()，但由于类 B 集成于类 Base，已和类 A 无关
	public int func1(int a, int b) {
		return a + b;
	}

	public int func2(int a, int b) {
		return func1(a, b) + 9;
	}

	// 我们仍然想使用A的方法
	public int func3(int a, int b) {
		return this.a.func1(a, b);
	}
}
```
</details>

#### 1.2.4.3. 注意

### 1.2.5. 开闭原则 ocp(核心)

#### 1.2.5.1. 基本介绍

> 核心

1. 开闭原则（Open Closed Principle） 是编程中最基础、最重要的设计原则
2. 一个软件实体如类，模块和函数应该对扩展开放(对提供方)， 对修改关闭(对使用方)。 **用抽象构建框架，用实现扩展细节**。
  > 也就是你怎么该内部代码我都不管，只要提供的API不变就行
3. 当软件需要变化时，**尽量通过扩展软件实体**的行为来实现变化，而不是通过修改已有的代码来实现变化。
  > 也就是说最好新增api，而不要乱改api
4. **编程中遵循其它原则，以及使用设计模式的目的就是遵循开闭原则**。

#### 1.2.5.2. 案例


<details>
<summary style="color:red;">案例1：未遵循开闭原则，导致新增一个图形类时，需要在【使用方 GraphicEditor】中添加很多代码</summary>

![design-patterns-6](./image/design-patterns-6.png)

```java
public class Ocp {

	public static void main(String[] args) {
		// 使用看看存在的问题
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		graphicEditor.drawShape(new Triangle());
	}

}

//这是一个用于绘图的类 [使用方，需使用图形绘图]
class GraphicEditor {
	// 接收Shape对象，然后根据type，来绘制不同的图形
	public void drawShape(Shape s) {
		if (s.m_type == 1)
			drawRectangle(s);
		else if (s.m_type == 2)
			drawCircle(s);

    // ***************新增**************
    // 给使用方新增了代码
		else if (s.m_type == 3)
			drawTriangle(s);
	}

	// 绘制矩形
	public void drawRectangle(Shape r) {
		System.out.println(" 绘制矩形 ");
	}

	// 绘制圆形
	public void drawCircle(Shape r) {
		System.out.println(" 绘制圆形 ");
	}

  // ***************新增**************
	// 绘制三角形
	public void drawTriangle(Shape r) {
		System.out.println(" 绘制三角形 ");
	}
}

//Shape类，基类
class Shape {
	int m_type;
}

// 具体的图形为提供方，提供具体的绘图流程
class Rectangle extends Shape {
	Rectangle() {
		super.m_type = 1;
	}
}

class Circle extends Shape {
	Circle() {
		super.m_type = 2;
	}
}

// ***************新增**************
//新增画三角形
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}
}
```
</details>

- 案例一的优缺点分析
  1. 优点是比较好理解，简单易操作。
  2. 缺点是违反了设计模式的ocp原则，即对扩展开放(提供方)，对修改关闭(使用方)。即当我们给类增加新功能的时候，尽量不修改代码，或者尽可能少修改代码。
  3. 比如我们这时要新增加一个图形种类：三角形，我们需要做大量的修改， 修改的地方较多


- 改进思路分析：
  1. 把创建Shape类做成抽象类，并提供一个抽象的draw方法，让子类去实现即可
  2. 这样我们有新的图形种类时，只需要让新的图形类继承Shape，并实现draw方法即可，使用方的代码就不需要修 -> 满足了开闭原则


<details>
<summary style="color:red;">案例2：改进</summary>

1. 所有的图形类都继承自公共的抽象父类 Shape，并重写父类中的 draw() 方法，之后新增图形类时，只需要重写 draw() 方法即可，在【使用方 GraphicEditor】中无需做任何修改
2. PS：和前面的接收消息例子很像，通过 IReceiver 接口定义各种接收者的行为，这样扩展新的接受者时，就无须在【使用方 Person】中修改任何代码

```java
public class Ocp {

	public static void main(String[] args) {
		// 使用看看存在的问题
		GraphicEditor graphicEditor = new GraphicEditor();
		graphicEditor.drawShape(new Rectangle());
		graphicEditor.drawShape(new Circle());
		graphicEditor.drawShape(new Triangle());
		graphicEditor.drawShape(new OtherGraphic());
	}

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
	// 接收Shape对象，调用draw方法
	public void drawShape(Shape s) {
		s.draw();
	}
}

//Shape类，基类
abstract class Shape {
	int m_type;

	public abstract void draw();// 抽象方法
}

class Rectangle extends Shape {
	Rectangle() {
		super.m_type = 1;
	}

	@Override
	public void draw() {
		System.out.println(" 绘制矩形 ");
	}
}

class Circle extends Shape {
	Circle() {
		super.m_type = 2;
	}

	@Override
	public void draw() {
		System.out.println(" 绘制圆形 ");
	}
}

//新增画三角形
class Triangle extends Shape {
	Triangle() {
		super.m_type = 3;
	}

	@Override
	public void draw() {
		System.out.println(" 绘制三角形 ");
	}
}

//新增一个图形
class OtherGraphic extends Shape {
	OtherGraphic() {
		super.m_type = 4;
	}

	@Override
	public void draw() {
		System.out.println(" 绘制其它图形 ");
	}
}
```
</details>

### 1.2.6. 迪米特法则

#### 1.2.6.1. 基本介绍

- 一个对象应该对其他对象保持最少的了解
- 类与类关系越密切，耦合度越大
- 迪米特法则(Demeter Principle)又叫**最少知道原则**，即**一个类对自己依赖的类知道的越少越好**。也就是说，对于被依赖的类不管多么复杂，都尽量将逻辑封装在类的内部。**对外除了提供的public 方法，不对外泄露任何信息**
- 迪米特法则还有个更简单的定义：只与直接的朋友通信
- 每个对象都会与其他对象有耦合关系，只要两个对象之间有耦合关系，我们就说这两个对象之间是**朋友关系**。耦合的方式很多，依赖，关联，组合，聚合等。
  - 其中，我们称出现**成员变量，方法参数，方法返回值**中的类为**直接的朋友**，
  - 而出现在**局部变量**中的类不是直接的朋友。也就是说，**陌生的类最好不要以局部变量的形式出现在类的内部**。

#### 1.2.6.2. 案例

> 应用实例：有一个学校， 下属有各个学院和总部， 现要求打印出学校总部员工ID和学院员工的id


<details>
<summary style="color:red;">案例1：CollegeEmployee 类不是 SchoolManager 类的直接朋友，而是一个陌生类，这样的设计违背了迪米特法则</summary>

```java
//客户端
public class Demeter1 {

	public static void main(String[] args) {
		// 创建了一个 SchoolManager 对象
		SchoolManager schoolManager = new SchoolManager();
		// 输出学院的员工id 和 学校总部的员工信息
		schoolManager.printAllEmployee(new CollegeManager());
	}

}

//学校总部员工类
class Employee {
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

//学院的员工类
class CollegeEmployee {
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

//管理学院员工的管理类
class CollegeManager {
	// 返回学院的所有员工
	public List<CollegeEmployee> getAllEmployee() {
		List<CollegeEmployee> list = new ArrayList<CollegeEmployee>();
		for (int i = 0; i < 10; i++) { // 这里我们增加了10个员工到 list
			CollegeEmployee emp = new CollegeEmployee();
			emp.setId("学院员工id= " + i);
			list.add(emp);
		}
		return list;
	}
}

//学校管理类
//分析 SchoolManager 类的直接朋友类有哪些 Employee、CollegeManager
//CollegeEmployee 不是 直接朋友 而是一个陌生类，这样违背了 迪米特法则 
class SchoolManager {
	// 返回学校总部的员工
	public List<Employee> getAllEmployee() {
		List<Employee> list = new ArrayList<Employee>();

		for (int i = 0; i < 5; i++) { // 这里我们增加了5个员工到 list
			Employee emp = new Employee();
			emp.setId("学校总部员工id= " + i);
			list.add(emp);
		}
		return list;
	}

	// 该方法完成输出学校总部和学院员工信息(id)
	void printAllEmployee(CollegeManager sub) {

		// 分析问题
		// 1. 这里的 CollegeEmployee 不是 SchoolManager的直接朋友
		// 2. CollegeEmployee 是以局部变量方式出现在 SchoolManager
		// 3. 违反了 迪米特法则

		// 获取到学院员工
		List<CollegeEmployee> list1 = sub.getAllEmployee();
		System.out.println("------------学院员工------------");
		for (CollegeEmployee e : list1) {
			System.out.println(e.getId());
		}
		// 获取到学校总部员工
		List<Employee> list2 = this.getAllEmployee();
		System.out.println("------------学校总部员工------------");
		for (Employee e : list2) {
			System.out.println(e.getId());
		}
	}
}
```
</details>

1. 前面设计的问题在于SchoolManager中， CollegeEmployee类并不是SchoolManager类的直接朋友
2. 按照迪米特法则，应该避免类中出现这样非直接朋友关系的耦合，我们将输出学院员工的方法封装到CollegeManager，这样在SchoolManager中就不会出现CollegeEmployee类了
3. 按照迪米特法则的意思就是：SchoolManager对输出学院员工知道得越少越好，所以我们就直接将该逻辑封装到CollegeManager中


<details>
<summary style="color:red;">案例2：改进</summary>

```java
//客户端
public class Demeter1 {

	public static void main(String[] args) {
		System.out.println("~~~使用迪米特法则的改进~~~");
		// 创建了一个 SchoolManager 对象
		SchoolManager schoolManager = new SchoolManager();
		// 输出学院的员工id 和 学校总部的员工信息
		schoolManager.printAllEmployee(new CollegeManager());
	}

}

//学校总部员工类
class Employee {
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

//学院的员工类
class CollegeEmployee {
	private String id;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}

//管理学院员工的管理类
class CollegeManager {
	// 返回学院的所有员工
	public List<CollegeEmployee> getAllEmployee() {
		List<CollegeEmployee> list = new ArrayList<CollegeEmployee>();
		for (int i = 0; i < 10; i++) { // 这里我们增加了10个员工到 list
			CollegeEmployee emp = new CollegeEmployee();
			emp.setId("学院员工id= " + i);
			list.add(emp);
		}
		return list;
	}

	// 输出学院员工的信息
	public void printEmployee() {
		// 获取到学院员工
		List<CollegeEmployee> list1 = getAllEmployee();
		System.out.println("------------学院员工------------");
		for (CollegeEmployee e : list1) {
			System.out.println(e.getId());
		}
	}
}

//学校管理类
class SchoolManager {
	// 返回学校总部的员工
	public List<Employee> getAllEmployee() {
		List<Employee> list = new ArrayList<Employee>();

		for (int i = 0; i < 5; i++) { // 这里我们增加了5个员工到 list
			Employee emp = new Employee();
			emp.setId("学校总部员工id= " + i);
			list.add(emp);
		}
		return list;
	}

	// 该方法完成输出学校总部和学院员工信息(id)
	void printAllEmployee(CollegeManager sub) {

		// 分析问题
		// 1. 将输出学院的员工方法，封装到CollegeManager
		sub.printEmployee();

		// 获取到学校总部员工
		List<Employee> list2 = this.getAllEmployee();
		System.out.println("------------学校总部员工------------");
		for (Employee e : list2) {
			System.out.println(e.getId());
		}
	}
}
```
</details>


#### 1.2.6.3. 注意

1. 迪米特法则的核心是降低类之间的耦合
2. 但是注意：由于每个类都减少了不必要的依赖，因此迪米特法则只是要求降低类间(对象间)耦合关系， 并不是要求完全没有依赖关系(也没办法做到)

### 1.2.7. 合成复用原则

原则是尽量使用合成/聚合的方式，而不是使用继承，即尽量使用 has a 的关系，而不要使用 is a 的关系

![design-patterns-7](./image/design-patterns-7.png)

## 1.3. 设计原则核心思想

- 找出应用中可能需要变化之处， 把它们独立出来， 不要和那些不需要变化的代码混在一起。
- 针对接口编程， 而不是针对实现编程。
- **为了交互对象之间的松耦合设计而努力**

# 2. UML类图复习

![design-patterns-10](./image/design-patterns-10.png)

## Dependency：表示依赖（使用）

只要是在类中用到了对方， 那么他们之间就存在依赖关系，比如：

- 类的成员属性
- 方法的返回类型
- 方法接收的参数类型
- 方法中使用的局部变量

```java
public class PersonServiceBean {
    private PersonDao personDao;//类
    
    public void save(Person person){}
    
    public IDCard getIDCard(Integer personid){
        return null;
    }
    
    public void modify(){
    	Department department = new Department();
    }
} 

public class PersonDao{}

public class IDCard{}

public class Person{}

public class Department{}
```

![design-patterns-8](./image/design-patterns-8.png)

---

## Association：表示关联

**依赖关系的特例**

- 关联关系实际上就是类与类之间的联系，他是依赖关系的特例
- 关联具有导航性：即双向关系或单向关系
- 关系具有多重性：如“1”（表示有且仅有一个），“0…”（表示0个或者多个），“0， 1”（表示0个或者一个），“n…m”(表示n到 m个都可以)，“m…*”（表示至少m个）

```java
public class Person {
	private IDCard card;
}

public class IDCard{
    
}

// 双向一对一关系
public class Person {
	private IDCard card;
}

public class IDCard{
	private Person person
}
```

![design-patterns-9](./image/design-patterns-9.png)

---

## Generalization：表示泛化（继承）

**依赖关系的特例**

- 泛化关系实际上就是继承关系
- 如果 A 类继承了 B 类， 我们就说 A 和 B 存在泛化关系

```java
public abstract class DaoSupport{
    public void save(Object entity){
        
    }
    
    public void delete(Object id){
        
    }
}

public class PersonServiceBean extends Daosupport{
    
}
```

![design-patterns-11](./image/design-patterns-11.png)

---

## Realization：表示实现

**依赖关系的特例**

- 实现关系实际上就是 A 类实现 B 接口， 他是依赖关系的特例

```java
public interface PersonService {
	public void delete(Integer id);
} 

public class PersonServiceBean implements PersonService {
	public void delete(Integer id){
        
    }
}
```

![design-patterns-14](./image/design-patterns-14.png)

---

## Aggregation：表示聚合

**关联关系的特列**

- 聚合关系（Aggregation）表示的是整体和部分的关系，整体与部分可以分开。 聚合关系是关联关系的特例，所以他具有关联的导航性与多重性。
- 如：一台电脑由键盘(keyboard)、显示器(monitor)，鼠标等组成；组成电脑的各个配件是可以从电脑上分离出来的， 使用带空心菱形的实线来表示：

```java
public class Computer {
	private Mouse mouse; // 鼠标可以和Computer分离
	private Moniter moniter;// 显示器可以和Computer分离

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public void setMoniter(Moniter moniter) {
		this.moniter = moniter;
	}
}
```

![design-patterns-12](./image/design-patterns-12.png)

---

## Composite：表示组合

**关联关系的特列**

- 组合关系：也是整体与部分的关系，但是整体与部分不可以分开。
- 在程序中我们定义实体： Person与IDCard、 Head，那么 Head 和Person 就是组合关系， IDCard 和 Person 就是聚合关系。
- 但是如果在程序中Person实体中定义了对IDCard进行级联删除，即删除Person时连同IDCard一起删除， 那么IDCard 和 Person 就是组合了

> **注意观察聚合关系和组合关系在代码中的区别**

```java
public class Person {
	private IDCard card; // 聚合关系
	private Head head = new Head(); // 组合关系
}
```

![design-patterns-13](./image/design-patterns-13.png)

# 3. 设计模式

## 设计模式概述

### 设计模式的层次

1. 第 1 层： 刚开始学编程不久， 听说过什么是设计模式
2. 第 2 层： 有很长时间的编程经验， 自己写了很多代码， 其中用到了设计模式， 但是自己却不知道
3. 第 3 层： 学习过了设计模式， 发现自己已经在使用了， 并且发现了一些新的模式挺好用的
4. 第 4 层： 阅读了很多别人写的源码和框架， 在其中看到别人设计模式， 并且能够领会设计模式的精妙和带来的好处。
5. 第 5 层： 代码写着写着， 自己都没有意识到使用了设计模式， 并且熟练的写了出来。

### 设计模式介绍

1. 设计模式是程序员在面对同类软件工程设计问题所总结出来的有用的经验， **模式不是代码， 而是某类问题的通用解决方案**， 设计模式（Design pattern） 代表了最佳的实践。 这些解决方案是众多软件开发人员经过相当长的一段时间的试验和错误总结出来的。
2. 设计模式的本质提高 软件的维护性， 通用性和扩展性， 并降低软件的复杂度。
3. 《设计模式》是经典的书， 作者是 Erich Gamma、 Richard Helm、 Ralph Johnson 和 John Vlissides Design（俗称 “四人组 GOF” ）
4. 设计模式并不局限于某种语言， java， php， c++ 都有设计模式。

### 设计模式类型与关系

> 注意： 不同的书籍上对分类和名称略有差别

设计模式分为三种类型， 共 23 种

1. 创建型模式： 单例模式、 抽象工厂模式、 原型模式、 建造者模式、 工厂模式。
2. 结构型模式： 适配器模式、 桥接模式、 装饰模式、 组合模式、 外观模式、 享元模式、 代理模式。
3. 行为型模式： 模版方法模式、 命令模式、 访问者模式、 迭代器模式、 观察者模式、 中介者模式、 备忘录模式、解释器模式（Interpreter 模式） 、 状态模式、 策略模式、 职责链模式(责任链模式)。

![design-patterns-1](./image/design-patterns-1.png)


## 3.2. 创建型模式

### 3.2.1. 概述

这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。

### 3.2.3. 单例模式（Singleton Pattern）

#### 单例设计模式介绍

1. 所谓类的单例设计模式， 就是采取一定的方法保证在整个的软件系统中， 对某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法(静态方法)。
2. 比如 `Hibernate` 的 `SessionFactory`， 它充当数据存储源的代理， 并负责创建 `Session` 对象。`SessionFactory` 并不是轻量级的， 一般情况下， 一个项目通常只需要一个 `SessionFactory` 就够，这是就会使用到单例模式。

#### 单例设计模式八种方式

单例模式有八种方式：

1. **饿汉式(静态常量)**
2. **饿汉式(静态代码块)**
3. 懒汉式(线程不安全)
4. 懒汉式(线程安全， 同步方法)
5. 懒汉式(线程安全， 同步代码块)
6. **双重锁检查**(推荐)
7. **静态内部类**(推荐)
8. **枚举**

#### 饿汉式（静态常量）

> **饿汉式（静态常量）的具体实现步骤**

1. 构造器私有化 (防止 `new`)
2. 类的内部创建对象
3. 向外暴露一个静态的公共方法： `getInstance()`

> **饿汉式（静态常量）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest01 {

   	public static void main(String[] args) {
   		// 测试
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   //饿汉式(静态变量)
   class Singleton {

   	// 1. 构造器私有化, 外部不能new
   	private Singleton() {

   	}

   	// 2.本类内部创建对象实例
   	private final static Singleton instance = new Singleton();

   	// 3. 提供一个公有的静态方法，返回实例对象
   	public static Singleton getInstance() {
   		return instance;
   	}

   }
   ```

2. 程序运行结果

   ```
   true
   instance.hashCode=366712642
   instance2.hashCode=366712642
   ```

> **饿汉式（静态常量）的优缺点说明**

1. 优点： 这种写法比较简单， 就是在类装载的时候就完成实例化。 避免了线程同步问题。
2. 缺点： 在类装载的时候就完成实例化， 没有达到 `Lazy Loading` 的效果。 如果从始至终从未使用过这个实例， 则会造成内存的浪费
3. 这种方式基于 `Classloder` 机制避免了多线程的同步问题， 不过， `instance` 在类装载时就实例化， 在单例模式中大多数都是调用 `getInstance()` 方法获取单例对象， 但是导致类装载的原因有很多种， 因此不能确定有其他的方式（或者其他的静态方法） 导致类装载， 这时候初始化单例对象，就没有达到 `lazy loading` 的效果
4. 结论： 这种单例模式可用， 可能造成内存浪费

#### 饿汉式（静态代码块）

> **饿汉式（静态代码块）的具体实现步骤**

1. 构造器私有化，外部不能 `new`
2. 在本类内部的静态代码块中，创建单例对象
3. 提供一个公有的静态方法，返回实例对象

> **饿汉式（静态代码块）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest02 {

   	public static void main(String[] args) {
   		// 测试
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   //饿汉式(静态变量)
   class Singleton {

   	// 1. 构造器私有化, 外部不能new
   	private Singleton() {

   	}

   	// 2.本类内部创建对象实例
   	private static Singleton instance;

   	static { // 在静态代码块中，创建单例对象
   		instance = new Singleton();
   	}

   	// 3. 提供一个公有的静态方法，返回实例对象
   	public static Singleton getInstance() {
   		return instance;
   	}

   }
   ```

2. 程序运行结果

   ```
   true
   instance.hashCode=366712642
   instance2.hashCode=366712642
   ```

> **饿汉式（静态代码块）的优缺点说明**

1. 这种方式和上面的方式其实类似，只不过将类实例化的过程放在了静态代码块中，也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。优缺点和上面是一样的。
2. 结论： 这种单例模式可用，但是可能造成内存浪费

#### 懒汉式（线程不安全）

> **懒汉式（线程不安全）的具体实现步骤**

1. 构造器私有化，外部不能 `new`

2. 在本类内部的 `getInstance()`:

   静态方法中，判断单例对象是否为空

   1. 如果为空，则创建单例对象并返回
   2. 如果不为空，则直接返回此对象

> **懒汉式（线程不安全）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest03 {

   	public static void main(String[] args) {
   		System.out.println("懒汉式1 ， 线程不安全~");
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   class Singleton {
   	private static Singleton instance;

   	private Singleton() {
   	}

   	// 提供一个静态的公有方法，当使用到该方法时，才去创建 instance
   	// 即懒汉式
   	public static Singleton getInstance() {
   		if (instance == null) {
   			instance = new Singleton();
   		}
   		return instance;
   	}
   }
   ```

2. 程序运行结果

  ```
  懒汉式1 ， 线程不安全~
  true
  instance.hashCode=366712642
  instance2.hashCode=366712642
  ```

> **懒汉式（线程不安全）的优缺点说明**

1. 起到了 `Lazy Loading` 的效果， 但是只能在单线程下使用。
2. 如果在多线程下， 一个线程进入了 `if (singleton == null)` 判断语句块， 还没来得及往下执行， 另一个线程也通过了这个判断语句， 这时便会产生多个实例。 所以在多线程环境下不可使用这种方式
3. 结论： 在实际开发中， 不要使用这种方式

#### 懒汉式（同步方法）

> **懒汉式（同步方法）的具体实现步骤**

1. 构造器私有化，外部不能 `new`

2. 在本类内部的

   ```
   getInstance()
   ```

   静态同步方法中，判断单例对象是否为空

   1. 如果为空，则创建单例对象并返回
   2. 如果不为空，则直接返回此对象

> **懒汉式（同步方法）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest04 {

   	public static void main(String[] args) {
   		System.out.println("懒汉式2 ， 线程安全~");
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   // 懒汉式(线程安全，同步方法)
   class Singleton {
   	private static Singleton instance;

   	private Singleton() {
   	}

   	// 提供一个静态的公有方法，加入同步处理的代码，解决线程安全问题
   	// 即懒汉式
   	public static synchronized Singleton getInstance() {
   		if (instance == null) {
   			instance = new Singleton();
   		}
   		return instance;
   	}
   }
   ```

2. 程序运行结果

   ```
   懒汉式2 ， 线程安全~
   true
   instance.hashCode=366712642
   instance2.hashCode=366712642
   ```

> **懒汉式（同步方法）的优缺点说明**

1. 解决了线程安全问题
2. 效率太低了， 每个线程在想获得类的实例时候， 执行 `getInstance()` 方法都要进行同步。 而其实这个方法只执行一次实例化代码就够了， 后面的想获得该类实例， 直接 `return` 就行了。 方法进行同步效率太低
3. 结论： 在实际开发中， 不推荐使用这种方式

#### 懒汉式（同步代码块）

> **懒汉式（同步代码块）的具体实现步骤**

1. 构造器私有化，外部不能 `new`

2. 在本类内部的

   ```
   getInstance()
   ```

   静态方法中，先判断对象是否为空

   1. 如果为空，则加锁创建单例对象，并返回
   2. 如果不为空，则直接返回此对象

> **懒汉式（同步代码块）的代码实现**

代码实现

```java
class Singleton{
    private static Singleton singleton;
    private Singleton(){
    }

    public static singleton getInstance(){
        if(singleton==null){
            synchronized(Singleton. class){
                singleton=new Singleton();
            }
        }
    }
    return singleton;
}
```

> **懒汉式（同步代码块）的优缺点说明**

1. 这种方式，本意是想对第四种实现方式的改进，因为前面同步方法效率太低，改为同步产生实例化的的代码块
2. 但是这种同步**并不能起到线程同步的作用**。跟第`3`种实现方式遇到的情形一致，假如一个线程进入了 `if (singleton == null)` 判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例
3. 结论：在实际开发中， 不能使用这种方式

#### 懒汉式（双重锁检查）(推荐)

> **懒汉式（双重检查）的具体实现步骤**

1. 构造器私有化，外部不能 `new`

2. 在本类内部的

   ```
   getInstance()
   ```

   静态方法中，先判断对象是否为空

   1. 如果为空，则先加锁，再判断此单例对象是否为空，如果还为空，才创建对象
   2. 如果不为空，则直接返回此对象

3. 注意：单例变量需要使用 `volatile` 关键字进行修饰，保证内存可见性，以及防止指令重排序

> **懒汉式（双重检查）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest06 {

   	public static void main(String[] args) {
   		System.out.println("双重检查");
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   // 懒汉式(线程安全，同步方法)
   class Singleton {
   	private static volatile Singleton instance;

   	private Singleton() {
   	}

   	// 提供一个静态的公有方法，加入双重检查代码，解决线程安全问题, 同时解决懒加载问题
   	// 同时保证了效率, 推荐使用
   	public static Singleton getInstance() {
   		if (instance == null) {
   			synchronized (Singleton.class) {
   				if (instance == null) {
   					instance = new Singleton();
   				}
   			}

   		}
   		return instance;
   	}
   }
   ```

2. 程序运行结果

   ```
   双重检查
   true
   instance.hashCode=366712642
   instance2.hashCode=366712642
   ```

> **懒汉式（双重检查）的优缺点说明**

1. `Double-Check` 概念是多线程开发中常使用到的， 如代码中所示， 我们进行了两次 `if (singleton == null)` 检查， 这样就可以保证线程安全了
2. 这样， 实例化代码只用执行一次， 后面再次访问时， 判断 `if (singleton == null)`， 直接 `return` 实例化对象， 也避免的反复进行方法同步
3. 线程安全； 延迟加载； 效率较高
4. 结论： 在实际开发中， 推荐使用这种单例设计模式

#### 懒汉式（静态内部类）

> **懒汉式（静态内部类）的具体实现步骤**

1. 构造器私有化，外部不能 `new`
2. 在本类内部新增一个静态内部类，封装一个单例对象，用于实现单例模式
3. 静态内部类的实现方式本质是利用类加载的同步机制，保证单例对象的线程安全，并且该方式能保证该单例对象的懒加载机制，因为只有用到静态内部类时，才会加载该静态内部类以及单例对象
4. 在本类内部提供一个静态方法 `getInstance()` 用于返回静态内部类中的单例对象

> **懒汉式（静态内部类）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest07 {

   	public static void main(String[] args) {
   		System.out.println("使用静态内部类完成单例模式");
   		Singleton instance = Singleton.getInstance();
   		Singleton instance2 = Singleton.getInstance();
   		System.out.println(instance == instance2); // true
   		System.out.println("instance.hashCode=" + instance.hashCode());
   		System.out.println("instance2.hashCode=" + instance2.hashCode());
   	}

   }

   // 静态内部类完成， 推荐使用
   class Singleton {
   	//构造器私有化
   	private Singleton() {}

   	//写一个静态内部类,该类中有一个静态属性 Singleton
   	private static class SingletonInstance {
   		private static final Singleton INSTANCE = new Singleton();
   	}

   	//提供一个静态的公有方法，直接返回SingletonInstance.INSTANCE
   	public static Singleton getInstance() {
   		return SingletonInstance.INSTANCE;
   	}
   }
   ```

2. 程序运行结果

   ```
   使用静态内部类完成单例模式
   true
   instance.hashCode=366712642
   instance2.hashCode=366712642
   ```

> **懒汉式（静态内部类）的优缺点说明**

1. 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
2. **静态内部类方式在`Singleton`类被装载时并不会立即实例化，而是在需要实例化时，调用`getInstance()`方法，才会装载`SingletonInstance`类，从而完成`Singleton`的实例化**。
3. 类的静态属性只会在第一次加载类的时候初始化，所以在这里， `JVM`帮助我们保证了线程的安全性(CAS,TLAB)，在类进行初始化时，别的线程是无法进入的。
4. 优点：避免了线程不安全，利用静态内部类特点实现延迟加载，效率高。
5. 结论：推荐使用。

#### 饿汉式（枚举）

> **饿汉式（枚举）的具体实现步骤**

通过枚举类实现单例模式

> **饿汉式（枚举）的代码实现**

1. 代码实现

   ```java
   public class SingletonTest08 {
   	public static void main(String[] args) {
   		Singleton instance = Singleton.INSTANCE;
   		Singleton instance2 = Singleton.INSTANCE;
   		System.out.println(instance == instance2);

   		System.out.println(instance.hashCode());
   		System.out.println(instance2.hashCode());

   		instance.sayOK();
   	}
   }

   //使用枚举，可以实现单例, 推荐
   enum Singleton {
   	INSTANCE; // 属性

   	public void sayOK() {
   		System.out.println("ok~");
   	}
   }
   ```

2. 程序运行结果

   ```
   true
   366712642
   366712642
   ok~
   ```

> **饿汉式（枚举）的优缺点说明**

1. 这借助`JDK1.5`中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
2. 这种方式是`Effective Java`作者`Josh Bloch`提倡的方式。如果用枚举去实现一个单例，属于饿汉模式。
3. 结论：推荐使用

#### 源码示例

> **Runtime 源码**。单例模式

这是典型的饿汉式啊

```java
public class Runtime {
    private static Runtime currentRuntime = new Runtime();

    /**
     * Returns the runtime object associated with the current Java application.
     * Most of the methods of class <code>Runtime</code> are instance
     * methods and must be invoked with respect to the current runtime object.
     *
     * @return  the <code>Runtime</code> object associated with the current
     *          Java application.
     */
    public static Runtime getRuntime() {
        return currentRuntime;
    }

    /** Don't let anyone else instantiate this class */
    private Runtime() {}
```

#### 单例模式注意事项

1. 单例模式保证了系统内存中该类只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能
2. 当想实例化一个单例类的时候，必须要记住使用相应的获取对象的方法，而不是使用 `new`
3. 单例模式使用的场景：需要频繁的进行创建和销毁的对象、创建对象时耗时过多或耗费资源过多(即： 重量级对象)，但又经常用到的对象、工具类对象、频繁访问数据库或文件的对象(比如数据源、 `session` 工厂等)

### 工厂模式（Factory Pattern）

### 3.2.2. 抽象工厂模式（Abstract Factory Pattern）

### 3.2.4. 建造者模式（Builder Pattern）

### 3.2.5. 原型模式（Prototype Pattern）

## 3.3. 结构型模式

### 3.3.1. 概述

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式。

### 3.3.2. 适配器模式（Adapter Pattern）

### 3.3.3. 桥接模式（Bridge Pattern）

### 3.3.4. 过滤器模式（Filter、Criteria Pattern）

### 3.3.5. 组合模式（Composite Pattern）

### 3.3.6. 装饰器模式（Decorator Pattern）

### 3.3.7. 外观模式（Facade Pattern）

### 3.3.8. 享元模式（Flyweight Pattern）

### 3.3.9. 代理模式（Proxy Pattern）

## 3.4. 行为型模式

### 3.4.1. 概述

这些设计模式特别关注对象之间的通信。

### 3.4.2. 责任链模式（Chain of Responsibility Pattern）

### 3.4.3. 命令模式（Command Pattern）

### 3.4.4. 解释器模式（Interpreter Pattern）

### 3.4.5. 迭代器模式（Iterator Pattern）

### 3.4.6. 中介者模式（Mediator Pattern）

### 3.4.7. 备忘录模式（Memento Pattern）

### 3.4.8. 观察者模式（Observer Pattern）

### 3.4.9. 状态模式（State Pattern）

### 3.4.10. 空对象模式（Null Object Pattern）

### 3.4.11. 策略模式（Strategy Pattern）

### 3.4.12. 模板模式（Template Pattern）

- 最经典的 JDK 应用的就是 AQS

### 3.4.13. 访问者模式（Visitor Pattern）

## 3.5. J2EE 模式

### 3.5.1. 概述

这些设计模式特别关注表示层。这些模式是由 Sun Java Center 鉴定的。

### 3.5.2. MVC 模式（MVC Pattern）

### 3.5.3. 业务代表模式（Business Delegate Pattern）

### 3.5.4. 组合实体模式（Composite Entity Pattern）

### 3.5.5. 数据访问对象模式（Data Access Object Pattern）

### 3.5.6. 前端控制器模式（Front Controller Pattern）

### 3.5.7. 拦截过滤器模式（Intercepting Filter Pattern）

### 3.5.8. 服务定位器模式（Service Locator Pattern）

### 3.5.9. 传输对象模式（Transfer Object Pattern）
