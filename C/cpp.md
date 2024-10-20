# C到C++

- cpp 代码可以编译为 c，但通过纯c实现cpp特性，代码可读性会很差。
- 如果有 c 的基础，在阅读 cpp 时，可以思考下转换成 c 如何实现，这对理解 cpp 的工作原理很有帮助。
- 可实现的编译器详见：[convert cpp to c](https://isocpp.org/wiki/faq/compiler-dependencies#convert-to-c)

## namespace

## 引用

### 引用变量

一种语法糖: 本质就是一个指针，不过会自动 dereference ，使用起来像直接操作变量对应的值一样

```cpp
int a = 5;
// 引用
int &a_ref = a;
a_ref = 10;
cout << a << "\n" << a_ref << "\n";
// 指针
int *a_ptr = &a;
*a_ptr = 20;
cout << a << "\n" << *a_ptr << "\n";
```

### 引用形参

同理，指针的语法糖

### 返回引用

同理，指针的语法糖

## 函数

### 默认形参

### 函数重载

## 面向对象

### 类的声明

> c 里面也可以通过结构体和函数指针来模拟 cpp 中的类，进行面向对象编程

- cpp 中，class 和 struct 都可以声明类
  - struct 的成员默认都是 public 的
  - class 的成员默认都是 private 的

- 类，实际就是 `struct + 全局函数`
  - 成员变量: struct 中的成员
  - 成员函数，静态成员函数 等函数: 全局函数
  - this 指针: 就是当前对象的指针

```cpp
class HelloC {
  int i;
  int j;
  int k;

public:
  void hello() { cout << "hello" << endl; }
};
```

也可以写为：

```cpp
struct HelloC{
  int i;
  int j;
  int k;
  void hello() { cout << "hello" << endl; }
};
```

或者：

```cpp
struct HelloC{
  int i;
  int j;
  int k;
  void hello(); // 函数声明
};
void HelloC::hello() { cout << "hello" << endl; } // 函数实现
```

### this 指针

### 生命周期函数

#### 构造函数

如果不定义，会默认会生成一个默认形式、隐藏着的构造函数，不具备任何功能

#### 拷贝构造函数

如果不定义，系统会自动生成一个，进行两个对象成员之间对应的简单赋值 (浅拷贝)

#### 析构函数

如果不定义，会默认生成一个空的析构函数

### 访问控制

c++ 访问控制原理是编译阶段实现的

访问控制是不是编译时检查的，所有成员函数应该就是全局函数，在编译的时候根据成员的访问控制类型，进行检查？
可以编译为 c 代码看看，尤其是友元函数，是不是没有额外的代码，就是告诉编译器，能访问通过？

#### 成员种类

#### 友元

即把外部的函数声明为友元类型，赋予它可以访问类内私有成员的权利，
友元的对象，它可以是全局的一般函数，也可以是其他类里的成员函数，这种叫做友元函数。不仅如此，友元还可以是一个类，这种叫做友元类。

- 友元关系不能被继承
- 友元关系是单向的，不具有交换性。若类B是类A的友元，类A不一定是类B的友元，要看在类中是否有相应的声明
- 友元关系不具有传递性。若类B是类A的友元，类C是B的友元，类C不一定是类A的友元，同样要看类中是否有相应的声明

##### 友元函数

- 友元函数并不是类的成员函数，
  - 因此在类外定义的时候不能加上class::function name
  - 也不能使用 `this` 指针

##### 友元类

#### const 成员

被const修饰的成员则必须进行初始化，并且不能被更改，而初始化的方式则是在类的构造函数的初始化列表里进行的。

### 运算符重载

可以和友元函数和常函数一起定义，

- 友元函数: 访问 private 和 projected 成员
- 常函数: 限制运算符修改实例对象

重载的运算符是带有特殊名称的函数，函数名是由关键字 operator 和其后要重载的运算符符号构成的。与其他函数一样，重载运算符有一个返回类型和一个参数列表。

声明加法运算符用于把两个 Box 对象相加，返回最终的 Box 对象:

```cpp
Box operator+(const Box&);
```

大多数的重载运算符可被定义为普通的非成员函数或者被定义为类成员函数。
如果我们定义上面的函数为类的非成员函数，那么我们需要为每次操作传递两个参数，如下所示：

```cpp
Box operator+(const Box&, const Box&);
```

## 可重载运算符/不可重载运算符

下面是可重载的运算符列表：


- 双目算术运算符: `+ (加)，-(减)，*(乘)，/(除)，% (取模)`
- 关系运算符: `==(等于)，!= (不等于)，< (小于)，> (大于)，<=(小于等于)，>=(大于等于)`
- 逻辑运算符: `||(逻辑或)，&&(逻辑与)，!(逻辑非)`
- 单目运算符: `+ (正)，-(负)，*(指针)，&(取地址)`
- 自增自减运算符: `++(自增)，--(自减)`
- 位运算符: `| (按位或)，& (按位与)，~(按位取反)，^(按位异或),，<< (左移)，>>(右移)`
- 赋值运算符: `=, +=, -=, *=, /= , % = , &=, |=, ^=, <<=, >>=`
- 空间申请与释放: `new, delete, new[ ] , delete[]`
- 其他运算符: `()(函数调用)，->(成员访问)，,(逗号)，[](下标)`


下面是不可重载的运算符列表：

- `.`：成员访问运算符
- `.*`, `->*`：成员指针访问运算符
- `::`：域运算符
- `sizeof`：长度运算符
- `?:`：条件运算符
- `#`： 预处理符号

### 继承与派生

#### 继承方式

- 公有继承：
  - (1)基类中的公有成员，在派生类中仍然为公有成员，当然无论派生里的成员函数还是派生类对象都可以访问。
  - (2)基类中的私有成员，无论在派生类的成员还是派生类对象都不可以访问。
  - (3)基类中的保护成员，在派生类中仍然是保护类型，可以通过派生类的成员函数访问，但派生类对象不可以访问。

- 私有继承：
  > 如果为私有派生，则基类的私有成员在派生类甚至再派生出的子类中，都无法再使用，没有什么存在意义，故这种使用情况比较少。
  - (1)基类的公有和受保护类型，被派生类私有继承吸收后，都变为派生类的私有类型，即在类的成员函数里可以访问，不能在类外访问。
  - (2)而基类的私有成员，在派生类无论类内还是类外都不可以访问。

- 保护继承：
  - (1)基类的公有成员和保护类型成员在派生类中为保护成员。
  - (2)基类的私有成员在派生类中不能被直接访问。

|          | 公有继承 | 公有继承 | 保护继承 | 保护继承 | 私有继承 | 私有继承 |
| -------- | -------- | -------- | -------- | -------- | -------- | -------- |
| 访问位置 | 类内     | 类外     | 类内     | 类外     | 类内     | 类外     |
| 公有成员 | 可以     | 可以     | 可以     | 不可以   | 可以     | 不可以   |
| 保护成员 | 可以     | 不可以   | 可以     | 不可以   | 可以     | 不可以   |
| 私有成员 | 不可以   | 不可以   | 不可以   | 不可以   | 不可以   | 不可以   |

#### 构造和析构

在创建一个派生类的时候，会先创建一个基类。
需要注意的是， **派生类会吸纳基类的全部成员，但并不包括构造函数及析构函数** ，

- 构造函数：
  - 调用顺序是先调用基类的构造函数再调用派生类的构造函数。
  - 如果构造函数存在参数，就需要显式得调用基类的构造函数。
- 析构函数：
  - 先调用派生类，再调用基类

#### 虚基类

在多继承关系中，如果一个派生类的从两个父类那里继承过来，并且这两个父类又恰恰是从一个基类那里继承而来，
通过虚基类只维护一份一个基类对象，避免二义性。

如果不适用虚继承，也可以显示写出调用哪个父类里面的属性。

```
obj.SuperClass::attr = value;
```

### 多态性

多态性是面向对象程序设计的重要特性之一，在面向对象程序设计中，指同样的方法被不同对象执行时会有不同的执行效果。

具体来说，多态的实现又可以分为两种：

- 编译时多态
  - 编译和链接的时候就确定了具体的操作过程
  - 联编在编译和链接时确认的，叫做静态联编，前面我们学习的函数重载、函数模板的实例化就属于这一类。
- 运行时多态
  - 后者是在程序运行过程中才确定的操作过程。
  - 是在运行的时候，才能确认执行哪段代码的，叫做动态联编，这种情况是编译的时候，还无法确认具体走哪段代码，而是程序运行起来之后才能确认。

两者相比之下，静态联编由于编译时候就已经确定好怎么执行，因此执行起来效率高；
而动态联编想必虽然慢一些，但优点是灵活。有各自不同的使用场景。

#### 虚函数

虚函数 是在基类中使用关键字 virtual 声明的函数。在派生类中重新定义基类中定义的虚函数时，会告诉编译器不要静态链接到该函数。

我们想要的是在程序中任意点可以根据所调用的对象类型来选择调用的函数，这种操作被称为 **动态链接** ，或 **后期绑定** 。

虚函数，在调用的时候，根据的不是左值指针的类型，而是右值指针的类型。

```cpp
class Shape {
   protected:
      int width, height;
   public:
      Shape( int a=0, int b=0)
      {
         width = a;
         height = b;
      }
      virtual int area()
      {
         cout << "Parent class area :" <<endl;
         return 0;
      }
};

int main( )
{
   Shape *shape;
   Rectangle rec(10,7);
   Triangle  tri(10,5);
   shape = &rec;
   shape->area();
   shape = &tri;
   shape->area();
   return 0;
}
```

注意：

1. 虚函数不能是静态成员函数，或友元函数，因为它们不属于某个对象。
2. 内联函数不能在运行中动态确定其位置，即使虚函数在类的内部定义，编译时，仍将看作非内联。
3. 构造函数不能是虚函数，析构函数可以是虚函数，而且通常声明为虚函数。

#### 虚析构函数

在C++中，不能把构造函数定义为虚构造函数，因为在实例化一个对象时才会调用构造函数，
且虚函数的实现，其实本质是通过一个虚函数表指针来调用的，还没有对象更没有内存空间当然无法调用了，
故没有实例化一个对象之前的虚构造函数没有意义也不能实现。

但析构函数却是可以为虚函数的，且大多时候都声明为虚析构函数。
这样就可以在用基类的指针指向派生类的对象在释放时，可以根据实际所指向的对象类型动态联编调用子类的析构函数，实现正确的对象内存释放。

#### 抽象类、纯虚函数 (接口)

纯虚函数，就是没有函数体的虚函数。什么叫没有函数体？就是这样定义的函数：

```
virtual 返回值 函数名(形参)=0;
```

可以看到，前面virtual与虚函数定义一样，后面加了一个=0。表示没有函数体，这就是一个纯虚函数。

包含纯虚函数的类就是抽象类，一个抽象类至少有一个纯虚函数。

抽象类的存在是为了提供一个高度抽象、对外统一的接口，然后通过多态的特性使用各自的不同方法，是C++面向对象设计以及软件工程的核心思想之一。

抽象类的特点总结如下：

1. 抽象类无法实例出一个对象来，只能作为基类让派生类完善其中的纯虚函数，然后再实例化使用。
2. 抽象类的派生来依然可以不完善基类中的纯虚函数，继续作为抽象类被派生。直到给出所有纯虚函数的定义，则成为一个具体类，才可以实例化对象。
3. 抽象类因为抽象、无法具化，所以不能作为参数类型、返回值、强转类型。
4. 接着第三条，但抽象类可以定义一个指针、引用类型，指向其派生类，来实现多态特性。

## 内存分配、实例化

对象、结构体的实例化方式：

- 方式1: 直接声明创建:
  - 分配在栈中，就是声明一个结构体。
  - 声明的为一个对象本身，而不是指针。
  - 不可作为返回值。
  - 栈帧弹出时对象失效。
- 方式2: 通过new创建:
  - 分配在堆中，相当于调用 malloc 为结构体分配内存。
  - 返回一个对象指针。
  - 可作为返回值。
  - 最后需要del，否则会有内存泄漏。

```cpp
int main(){
  // 栈上分配内存创建对象。
  HelloC h;
  // 堆上分配内存创建对象。
  // 等价于 c 中的: struct HelloC = *malloc(sizeof(struct HelloC));
  HelloC *h_ptr = new HelloC();
  del h_ptr;
}
```

## 方法调用

### 传值方式

比c多了一种传引用的方式，但基本上也就是传指针的语法糖

- 传值
- 传指针
- 传引用： 传指针的语法糖

```cpp
#include <iostream>
#include <string>

using namespace ::std;

class Student {
public:
  int age;
  int height;
  int weight;
  string name;
};

void print_student_obj(Student s) {
  cout << "size: " << sizeof(s) << endl;
  cout << "student" << s.age << "  " << s.name << endl;
}

void print_student_ptr(Student *s) {
  cout << "size: " << sizeof(*s) << endl;
  cout << "student" << s->age << "  " << s->name << endl;
}

void print_student_ref(Student &s) {
  cout << "size: " << sizeof(s) << endl;
  cout << "student" << s.age << "  " << s.name << endl;
}

int main(int argc, char *argv[]) {
  Student s1;
  s1.name = "s1";
  s1.age = 10;
  s1.height = 140;
  s1.weight = 30;

  Student *s2 = new Student;
  s2->name = "s2";
  s2->age = 10;
  s2->height = 140;
  s2->weight = 30;

  print_student_obj(s1);
  print_student_ptr(&s1);
  print_student_obj(*s2);
  print_student_ptr(s2);
  print_student_ref(s1);
  print_student_ref(*s2);
  return 0;
}
```

### 方法模版下的调用

## 模版

### 函数模版

### 对象模版

## 异常处理

### 示例

```cpp
#include <iostream>
using namespace std;
int main()
{
    int a,b;
    cin>>a>>b;
    try
    {
        if(b==0)
            throw "error! b<0";
    }
    catch(const char *str)
    {
        cout<<str<<endl;
    }
    catch(int)
    {
        cout<<"throw int "<<endl;
    }
    return 0;
}
```

### exception 类

![cpp-20241021001535-179756.png](./image/cpp-20241021001535-179756.png)

使用C++自带的标准异常类，需要包含对应的头文件:

- exception、bad_exception类在头文件exception中定义，
- bad_alloc类在头文件new中定义，
- bad_typeid类在头文件typeinfo中定义，
- ios_base::failure类在头文件ios中定义，
- 其他异常类在stdexcept中定义。

# c++ 基础

## 字符串与数组

```
1.字符串
1.1 初始化
1.2 大小
1.3 常见操作
2.数组
2.1 初始化
2.2 大小
2.3 遍历
2.4 auto与decltype
2.5 多维数组
```

## 函数

```
1.函数参数
1.1 形参
1.2 默认实参
2.函数返回
2.1 状态码
2.2 数组与函数的指针
3.函数重载
3.1 判断标准
3.2 函数匹配步骤
4.内联函数
5.constexpr函数
6.函数指针
6.1 函数类型与函数指针
6.2 如何赋值
6.3 如何调用
6.4 作为形参与返回值
```

## 类

```
1.关键字
2.向前声明
3.组成
3.1 友元声明
3.2 访问说明符
3.3 类型别名成员
3.4 静态成员
3.5 成员变量
3.6 成员函数
4.初始化
4.1 显示初始化
4.2 默认初始化
4.3 值初始化
4.4 成员的初始化
5.作用域与名字查找
5.1 作用域
5.2 名字查找
6.类型转换
6.1 隐式类型转换
7.类对象移动
7.1 右值引用
```

## 重载运算与类型转换

```
1.重载运算
1.1 重载为成员函数
1.2 重载为非成员函数
1.3 不应重载的运算符
1.4 可被重载的运算符
2.二义性类型转换
2.1 转换二义性
2.2 避免转换出现二义性
```

## 继承体系

```
1.虚函数
1.1 动态绑定
1.2 函数覆盖
1.3 纯虚函数与抽象基类
2.派生类的构造与拷贝控制
2.1 构造
2.2 拷贝控制
3.防止继承与防止覆盖
4.static与继承
5.类型转换与继承
5.1 指针或引用的转换
5.2 对象之间不存在转换
6.访问权限与继承
6.1 using修改继承自基类成员的权限
7.继承中的作用域
7.1 名字查找
7.2 名字继承与冲突
8.虚继承
8.1 重复继承
8.2 成员可见性
8.3 构造与拷贝控制
```

## 容器

```
1.容器通用操作
1.1 类型别名
1.2 构造
1.3 赋值与swap
1.4 大小
1.5 添加与删除元素
1.6 关系运算符
1.7 获取迭代器
2.顺序容器
2.1 种类
2.2 操作
2.3 迭代器失效
3.关联容器
3.1 有序关联容器
3.2 无序关联容器
3.3 pair
3.4 操作
容器适配器
1.通用的容器适配器操作
2.三个顺序容器适配器
2.1 stack
2.2 queue
2.3 priority_queue
```

## 泛型算法

```
1.常用算法
1.1 读容器(元素)算法
1.2 写容器(元素)算法
1.3 for_each算法
2.迭代器
2.1 按类型划分
2.2 按操作级别划分
3.调用对象
3.1 谓词
3.2 lambda
3.3 bind参数绑定
4.链表的算法
```

## 模板与泛型编程

```
1.模板函数
1.1 模板参数
1.2 函数形参
1.3 成员模板
2.类模板
2.1 与模板函数的区别
2.2 模板类名的使用
2.3 类模板的成员函数
2.4 类型成员
2.5 类模板和友元
2.6 模板类型别名
2.7 类模板的static成员
3.模板编译
3.1 实例化声明
3.2 实例化定义
4.模板参数
4.1 默认模板实参
4.2 模板实参推断
5.重载与模板
6.可变参数模板
7.模板特例化
```

## 内存管理

```
1.new和delete
1.1 new
1.2 delete
2.智能指针
2.1 通用操作
2.2 shared_ptr
2.3 unique_ptr
2.4 weak_ptr
```

## 输入输出

```
1.I/O流
2.文件流
2.1 文件模式
2.2 创建文件流
2.3 打开文件流
2.4 关闭文件流
3.字符串流
3.1 创建string流
3.2 返回string流
3.3 将string拷贝到string流
4.四个常用I/O对象
5.流状态
5.1 条件状态
5.2 格式状态
6.流操作
6.1 关联输入输出流
6.2 未格式化I/O操作
7.缓冲区管理
7.1 刷新缓冲区
```

## 输入输出

```
1.I/O流
2.文件流
2.1 文件模式
2.2 创建文件流
2.3 打开文件流
2.4 关闭文件流
3.字符串流
3.1 创建string流
3.2 返回string流
3.3 将string拷贝到string流
4.四个常用I/O对象
5.流状态
5.1 条件状态
5.2 格式状态
6.流操作
6.1 关联输入输出流
6.2 未格式化I/O操作
7.缓冲区管理
7.1 刷新缓冲区
```

# STL

## 什么是 STL

> STL 提供了六大组件，彼此之间可以组合套用
> 这六大组件分别是容器、算法、迭代器、仿函数、适配器、空间配置器
> 其中，在算法竞赛中用到最多的为容器、算法与迭代器。

- 容器(Container)：
  - STL 容器为各种数据结构，如 vector、stack、queue、map、set 等，用来存放数据，
  - 从实现角度来看，STL 容器是一种 class template。
- 算法(Algorithm)
  - STL 的算法多数定义在`<algorithm>`头文件中，其中包括了各种常用的算法，如 sort、find、copy、reverse 等
  - 从实现角度来看，STL 算法是一种 function template。
- 迭代器(Iterator)
  - STL 迭代器扮演了容器与算法之间的胶合剂，共有五种类型
  - 从实现角度来看，迭代器是一种将 opetator\*、opetator->、operator++等指针相关操作予以重载的 class template。所有 STL 容器都附带有自己专属的迭代器，只有容器的设计者才知道如何遍历自己的元素。
- 仿函数(Functor)：
  - 行为类似函数，可作为算法的某种策略
  - 从实现角度来看，仿函数是一种重载了 operator()的 class 或者 class template。
- 适配器(Adaptor)
  - 一种用来修饰容器或仿函数或迭代器接口的东西。
- 空间配置器(Allocator)
  - 负责空间的配置与管理
  - 从实现角度来看，配置器是一个实现了动态空间配置、空间管理、空间释放的 class template。

## 容器

### vector

- vector

  - 又称变长数组，定义在`<vector>`头文件中
  - vector 容器是动态空间，随着元素的加入，它的内部机制会自动扩充空间以容纳新的元素
  - 因此 vector 的运用对于内存的合理利用与运用的灵活性有很大的帮助。

- vector 的定义方式

  ```cpp
  vector<int> v;//定义一个vector，其中的元素为int类型
  vector<int> v[N];//定义一个vector数组，其中有N个vector
  vector<int> v(len);//定义一个长度为len的vector
  vector<int> v(len, x);//定义一个长度为len的vector，初始化每个元素为x
  vector<int> v2(v1);//用v1给v2赋值，v1的类型为vector
  vector<int> v2(v1.begin(), v1.begin() + 3);//将v1中第0~2三个元素赋值给v2
  ```

- vector 的常用内置函数

  ```cpp
  //vector中的常用内置函数
  vector<int> v = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
  vector<int>::iterator it = v.begin();//定义vector的迭代器，指向begin()

  v.push_back(4);//在vector的尾部插入元素4，v:{1, 2, 3, 4}
  v.pop_back();//删除vector的最后一个元素，v:{1, 2, 3}
  //注意使用lower_bound()与upper_bound()函数时vector必须是有序的，upper_bound()在<algorithm>中
  lower_bound(v.begin(), v.end(), 2);//返回第一个大于等于2的元素的迭代器v.begin() + 1，若不存在则返回v.end()
  upper_bound(v.begin(), v.end(), 2);//返回第一个大于2的元素的迭代器v.begin() + 2，若不存在则返回v.end()
  v.size();//返回vector中元素的个数
  v.empty();//返回vector是否为空，若为空则返回true否则返回false
  v.front();//返回vector中的第一个元素
  v.back();//返回vector中的最后一个元素
  v.begin();//返回vector第一个元素的迭代器
  v.end();//返回vector最后一个元素后一个位置的迭代器
  v.clear();//清空vector
  v.erase(v.begin());//删除迭代器it所指向的元素，即删除第一个元素
  v.erase(v.begin(), v.begin() + 2);//删除区间[v.begin(), v.begin() + 2)的所有元素
  v.insert(v.begin(), 1);//在迭代器it所指向的位置前插入元素1，返回插入元素的迭代器
  ```

- vector 遍历

  ```cpp
  //根据下标进行遍历
  for (int i = 0; i < v.size(); i++)
      cout << v[i] << ' ';
  //使用迭代器遍历
  for (vector<int>::iterator it = v.begin(); it != v.end(); it++)
      cout << *it << ' ';
  //for_each遍历(C++11)
  for (auto x : v)
      cout << x << ' ';
  ```

### stack

- stack

  - 又称栈，是一种后进先出(Last In First Out，LIFO)的数据结构，定义在`<stack>`头文件中
  - stack 容器允许新增元素、移除元素、取得栈顶元素
  - 但是除了最顶端以外，没有任何方法可以存取 stack 的其它元素
  - **换言之，stack 不允许有遍历行为**

- stack 的定义方式

  ```cpp
  stack<int> stk;//定义一个stack，其中元素的类型为int
  stack<int> stk[N];//定义一个stack数组，其中有N个stack
  ```

- stack 的常用内置函数

  ```cpp
  //stack中的常用内置函数
  stack<int> stk;
  stk.push(x);//在stack中插入元素x
  stk.pop();//弹出stack的栈顶元素
  stk.top();//返回stack的栈顶元素
  stk.size();//返回stack中元素的个数
  stk.empty();//返回stack是否为空，若为空则返回true否则返回false
  ```

### string

- string：

  - 又称字符串，定义在`<string>`头文件中
  - C 风格的字符串(以空字符结尾的字符数组)太过复杂难于掌握，因此 C++标准库定义了一种 string 类
  - string 和`vector<char>`在数据结构、内存管理等方面都是相同的
  - 但是，`vector<char>`只是单纯的一个“charchar 元素的容器”，而 string 不仅是一个“charchar 元素的容器”，它还扩展了一些针对字符串的操作
    - 例如 string 可以使用 c_str()函数转换为 C 风格的字符串
    - vector 中并未对输入输出流操作符进行重载，因此无法直接对`vector<char>`进行 cin 或者 cout 这样的操作，但是 string 可以
    - `vector<char>`并不能直接实现字符串的拼接，但是 string 可以，string 中重载了+,+=+,+=运算符。

- string 的定义方式
  ```cpp
  string str;//定义一个空的字符串
  string str[N];//定义一个string数组，其中有N个string
  string str(5, 'a');//使用5个字符'a'初始化
  string str("abc");//使用字符串初始化
  ```
- string 的常用内置函数

  ```cpp
  //string中的常用内置函数
  string str("abcabc");
  str.push_back('d');//在string尾部插入字符，"abcabcd"
  str.pop_back();//删除string尾部的字符，"abcabc"
  str.length();//返回string中字符的个数
  str.size();//作用与length()相同
  str.empty();//返回string是否为空，若为空返回true否则返回false
  str.substr(1);//返回string中从下标为1开始至末尾的子串，"bcabc"
  str.substr(0, 2);//返回string中从下标为0开始长度为2的子串，"ab"
  str.insert(1, 2, 'x');//在下标为1的字符前插入2个字符'x'，"axxbcabc"
  str.insert(1, "yy");//在下标为1的字符前插入字符串"yy"，"ayyxxbcabc"
  str.erase(1, 4);//删除从位置1开始的4个字符，"abcabc"
  str.find('b');//返回字符'b'在string中第一次出现的位置，返回1，若不存在则返回-1
  str.find('b', 2);//返回从位置2开始字符'b'在string中第一次出现的位置，返回4
  str.find("bc");//同上，返回字符串第一次出现的位置，返回1，若不存在则返回-1
  str.find("bc", 2);//返回4
  str.rfind('b');//反向查找，原理同上，返回4，若不存在则返回-1
  str.rfind('b', 3);//返回1
  str.rfind("bc");//返回4，若不存在则返回-1
  str.rfind("bc", 3);//返回1
  stoi(str);//返回str的整数形式
  to_string(value);//返回value的字符串形式，value为整型、浮点型等
  str[0];//用下标访问string中的字符
  cout << (str == str) << endl;//string可比较大小，按字典序
  ```

- string 的 erase()与 remove()函数的用法

  ```cpp
  //string中erase()与remove()的用法
  string str1, str2, str3, str4, str5;
  str1 = str2 = str3 = str4 = str5 = "I love AcWing! It's very funny!";
  str1.erase(15);//删除[15,end())的所有元素，"I love AcWing!"
  str2.erase(6, 11);//从第6个元素(包括)开始往后删除11个元素，"I love's very funny!"
  str3.erase(str3.begin() + 2);//删除迭代器所指的元素，"I ove AcWing! It's very funny!"
  str4.erase(str4.begin() + 7, str4.end() - 11);//删除[str4.begin()+7,str4.end()-11)的所有元素，"I love very funny!"
  str5.erase(remove(str5.begin(), str5.end(), 'n'), str5.end());//删除[str5.begin(),str5.end())中所有字符'n'，"I love AcWig! It's very fuy!"
  ```

### queue and priority_queue

- queue

  - 又称队列，是一种先进先出(First In First Out，FIFO)的数据结构，定义在`<queue>`头文件中
  - queue 容器允许从一端(称为队尾)新增元素(入队)，从另一端(称为队头)移除元素(出队)。

- priority_queue

  - 又称优先队列，同样定义在`<queue>`头文件中
  - 与 queue 不同的地方在于我们可以自定义其中数据的优先级，优先级高的排在队列前面，优先出队
  - priority_queue 具有 queue 的所有特性，包括基本操作，只是在这基础上添加了内部的一个排序
  - 它的本质是用堆实现的，因此可分为小根堆与大根堆，小根堆中较小的元素排在前面，大根堆中较大的元素排在前面
  - 注意：创建 priority_queue 时 **默认是大根堆**

- queue 的定义方式
  ```cpp
  queue<int> que;//定义一个queue，其中元素的类型为int
  queue<int> que[N];//定义一个queue数组，其中有N个queue
  priority_queue<int> bigHeap;//定义一个大根堆
  priority_queue<int, vector<int>, greater<int> > smallHeap;//定义一个小根堆
  ```
- queue 的常用内置函数
  ```cpp
  //queue中的常用内置函数
  queue<int> que;
  que.push(x);//在queue的队尾插入元素x
  que.pop();//出队queue的队头元素
  que.front();//返回queue的队头元素
  que.back();//返回queue的队尾元素
  que.size();//返回stack中元素的个数
  que.empty();//返回stack是否为空，若为空则返回true否则返回false
  ```

### deque

- deque

  - 又称双端队列，定义在`<deque>`头文件中
  - vector 容器是单向开口的连续内存空间，deque 则是一种双向开口的连续线性空间
  - 所谓的双向开口，意思是可以在头尾两端分别做元素的插入和删除操作
  - 当然，vector 也可以在头尾两端插入元素，但是在其头部进行插入操作效率奇差，无法被接受
  - deque 和 vector 最大的差异
    - 一是在于 deque 允许使用常数项时间在头部进行元素的插入和删除操作
    - 二是在于 deque 没有容量的概念，因为它是动态的以分段连续空间组合而成，随时可以增加一段新的空间并链接起来。

- deque 的定义方式

  ```cpp
  deque<int> deq;//定义一个deque，其中的元素为int类型
  deque<int> deq[N];//定义一个deque数组，其中有N个deque
  deque<int> deq(len);//定义一个长度为len的deque
  deque<int> deq(len, x);//定义一个长度为len的deque，初始化每个元素为x
  deque<int> deq2(deq1);//用deq1给v2赋值，deq2的类型为deque
  deque<int> deq2(deq1.begin(), deq1.begin() + 3);//将deq1中第0~2三个元素赋值给deq2
  ```

- deque 的常用内置函数

  ```cpp
  //deque中的常用内置函数
  deque<int> deq = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
  deque<int>::iterator it = deq.begin();//定义vector的迭代器，指向begin()

  deq.push_back(4);//在deque的尾部插入元素4，v:{1, 2, 3, 4}
  deq.pop_back();//删除deque的尾部元素，v:{1, 2, 3}
  deq.push_front(4);//在deque的头部插入元素4，v:{4, 1, 2, 3}
  deq.pop_front();//删除deque的头部元素，v:{1, 2, 3}
  deq.size();//返回deque中元素的个数
  deq.empty();//返回deque是否为空，若为空则返回true否则返回false
  deq.front();//返回deque中的第一个元素
  deq.back();//返回deque中的最后一个元素
  deq.begin();//返回deque第一个元素的迭代器
  deq.end();//返回deque最后一个元素后一个位置的迭代器
  deq.clear();//清空deque
  deq.erase(deq.begin());//删除迭代器it所指向的元素，即删除第一个元素
  deq.erase(deq.begin(), deq.begin() + 2);//删除区间[v.begin(), v.begin() + 2)的所有元素
  deq.insert(deq.begin(), 1);//在迭代器it所指向的位置前插入元素1，返回插入元素的迭代器
  ```

- 遍历
  ```cpp
  //根据下标进行遍历
  for (int i = 0; i < deq.size(); i++)
  cout << deq[i] << ' ';
  //使用迭代器遍历
  for (deque<int>::iterator it = deq.begin(); it != deq.end(); it++)
  cout << *it << ' ';
  //for_each遍历(C++11)
  for (auto x : deq)
  cout << x << ' ';
  ```

### map/multimap

- map/multimap

  - 又称映射，定义在`<map>`头文件中，map 和 multimap 的底层实现机制都是红黑树
  - map 的功能是能够将任意类型的元素映射到另一个任意类型的元素上，并且所有的元素都 **会根据元素的键值自动排序**
  - map 所有的元素都是 pair，同时拥有实值和键值
    - pair 的第一元素被视为键值，第二元素被视为实值，map 不允许两个元素有相同的键值
  - multimap 和 map 的操作类似，唯一区别是 multimap 的键值允许重复。

- map/multimap 的定义方式

  ```cpp
  map<string, int> mp;//定义一个将string映射成int的map
  map<string, int> mp[N];//定义一个map数组，其中有N个map
  multimap<string, int> mulmp;//定义一个将string映射成int的multimap
  multimap<string, int> mulmp[N];//定义一个multimap数组，其中有N个multimap
  ```

- map/multimap 的常用内置函数

  ```cpp
  //map/multimap中的常用内置函数
  map<string, int> mp;
  mp["abc"] = 3;//将"abc"映射到3
  mp["ab"]++;//将"ab"所映射的整数++
  mp.insert(make_pair("cd", 2));//插入元素
  mp.insert({ "ef", 5 });//同上
  mp.size();//返回map中元素的个数
  mp.empty();//返回map是否为空，若为空返回true否则返回false
  //mp.clear();//清空map
  mp.erase("ef");//清除元素{"ef", 5}
  mp["abc"];//返回"abc"映射的值
  mp.begin();//返回map第一个元素的迭代器
  mp.end();//返回map最后一个元素后一个位置的迭代器
  mp.find("ab");//返回第一个键值为"ab"的迭代器，若不存在则返回mp.end()
  mp.find({ "abc", 3 });//返回元素{"abc", 3}的迭代器，若不存在则返回mp.end()
  mp.count("abc");//返回第一个键值为"abc"的元素数量1，由于map元素不能重复因此count返回值只有0或1
  mp.count({ "abc", 2 });//返回第一个键值为"abc"的元素数量1，注意和find不一样，count只判断第一个键值
  mp.lower_bound("abc");//返回第一个键值大于等于"abc"的元素的迭代器，{"abc", 3}
  mp.upper_bound("abc");//返回第一个键值大于"abc"的元素的迭代器，{"cd", 2}
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (map<string, int>::iterator it = mp.begin(); it != mp.end(); it++)
  cout << (*it).first << ' ' << (*it).second << endl;
  //for_each遍历(C++11)
  for (auto x : mp)
  cout << x.first << ' ' << x.second << endl;
  //扩展推断范围的for_each遍历(C++17)
  for (auto &[k, v] : mp)
  cout << k << ' ' << v << endl;
  ```

### set/multiset

- set/multiset：

  - 又称集合，定义在`<set>`头文件中
  - set 的特性是所有元素都会根据元素的键值自动被排序，set 的元素不像 map 那样可以同时拥有实值和键值
  - set 的元素既是键值又是实值，set 不允许两个元素有相同的键值
  - 因此总结来说就是 set 中的元素是有序且不重复的
  - multiset 的特性和用法和 set 完全相同，唯一的区别在于 multiset 允许有重复元素
  - set 和 multiset 的底层实现都是红黑树。

- set/multiset 的定义方式

  ```cpp
  set<int> st;//定义一个set，其中的元素类型为int
  set<int> st[N];//定义一个set数组，其中有N个set
  multiset<int> mulst;//定义一个multiset
  multiset<int> mulst[N];//定义一个multiset数组，其中有N个multiset
  ```

- set/multiset 的常用内置函数

  ```cpp
  //set/multiset中的常用内置函数
  set<int> st;
  st.insert(5);//插入元素5
  st.insert(6);//同上
  st.insert(7);//同上
  st.size();//返回set中元素的个数
  st.empty();//返回set是否为空，若为空返回true否则返回false
  st.erase(6);//清除元素6
  st.begin();//返回set第一个元素的迭代器
  st.end();//返回set最后一个元素后一个位置的迭代器
  st.clear();//清空set
  st.find(5);//返回元素5的迭代器，若不存在则返回st.end()
  st.count(5);//返回元素5的个数1，由于set元素不会重复，因此count返回值只有0或1
  st.lower_bound(5);//返回第一个键值大于等于5的元素的迭代器，返回元素5的迭代器
  st.upper_bound(5);//返回第一个键值大于5的元素的迭代器，返回元素7的迭代器
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (set<int>::iterator it = st.begin(); it != st.end(); it++)
      cout << (*it) << ' ';
  //for_each遍历(C++11)
  for (auto x : st)
      cout << x << ' ';
  ```

### unordered_map/unordered_set

- unordered_map/unordered_set

  - 分别定义在`<unordered_map>`与`<unordered_set>`头文件中
  - 内部采用的是 hash 表结构，拥有快速检索的功能
  - 与 map/set 相比最大的区别在于 unordered_map/unordered_set 中的元素是无序的
  - 增删改查的时间复杂度为 O(1)(map/set 增删改查的时间复杂度为 O(logn))
  - 但是不支持 lower_bound()/upper_bound()函数。

- unordered_map/unordered_set 的定义方式

  ```cpp
  unordered_set<int> st;//定义一个unordered_set，其中的元素类型为int
  unordered_set<int> st[N];//定义一个unordered_set数组，其中有N个unordered_set
  unordered_map<int, int> mp;//定义一个unordered_map
  unordered_map<int, int> mp[N];//定义一个unordered_map数组，其中有N个unordered_map
  ```

- unordered_map/unordered_set 的常用内置函数

  ```cpp
  //unordered_map/unordered_set中的常用内置函数
  unordered_set<int> st;
  unordered_map<int, int> mp;
  st.insert(5);//插入元素5
  st.insert(6);//同上
  st.insert(7);//同上
  st.size();//返回unordered_set中元素的个数
  st.empty();//返回unordered_set是否为空，若为空返回true否则返回false
  st.erase(6);//清除元素6
  st.begin();//返回unordered_set第一个元素的迭代器
  st.end();//返回unordered_set最后一个元素后一个位置的迭代器
  st.clear();//清空unordered_set
  mp.insert(make_pair(1, 2));//插入元素{1, 2}
  mp.insert({ 3, 4 });//同上
  mp.size();//返回unordered_map中元素的个数
  mp.empty();//返回unordered_map是否为空，若为空返回true否则返回false
  mp.erase(3);//清除元素{3, 4}
  mp.begin();//返回unordered_map第一个元素的迭代器
  mp.end();//返回unordered_map最后一个元素后一个位置的迭代器
  mp.clear();//清空unordered_map
  ```

- 遍历

  ```cpp
  //使用迭代器遍历
  for (unordered_set<int>::iterator it = st.begin(); it != st.end(); it++)
      cout << (*it) << ' ';
  //for_each遍历(C++11)
  for (auto x : st)
      cout << x << ' ';

  //使用迭代器遍历
  for (unordered_map<int, int>::iterator it = mp.begin(); it != mp.end(); it++)
      cout << (*it).first << ' ' << (*it).second << endl;
  //for_each遍历(C++11)
  for (auto x : mp)
      cout << x.first << ' ' << x.second << endl;
  //扩展推断范围的for_each遍历(C++17)
  for (auto &[k, v] : mp)
      cout << k << ' ' << v << endl;
  ```

## 算法

- 说明

  - C++标准库定义了一组泛型算法
  - 之所以称为泛型指的是它们可以操作在多种容器上
  - 不但可以作用于标准库类型，还可以用在内置数组类型甚至其它类型的序列上
  - 泛型算法定义在`<algorithm>`头文件中
  - 标准库还定义了一组泛化的算术算法(Generalized Numeric Algorithm)，定义在`<numeric>`头文件中。

- 示例

  ```cpp
  #include <iostream>
  #include <algorithm>
  #include <numeric>
  using namespace std;

  int main()
  {
      //使用STL容器时将数组地址改为迭代器即可

      int a[5] = { 1, 2, 3, 4, 5 };

      //排序算法
      sort(a, a + 5);//将区间[0, 5)内元素按字典序从小到大排序
      sort(a, a + 5, greater<int>());//将区间[0, 5)内元素按字典序从大到小排序
      reverse(a, a + 5);//将区间[0, 5)内元素翻转
      nth_element(a, a + 3, a + 5);//将区间[0, 5)中第a + 3个数归位，即将第3大的元素放到正确的位置上，该元素前后的元素不一定有序

      //查找与统计算法
      find(a, a + 5, 3);//在区间[0, 5)内查找等于3的元素，返回迭代器，若不存在则返回end()
      binary_search(a, a + 5, 2);//二分查找区间[0, 5)内是否存在元素2，若存在返回true否则返回false
      count(a, a + 5, 3);//返回区间[0, 5)内元素3的个数

      //可变序列算法
      copy(a, a + 2, a + 3);//将区间[0, 2)的元素复制到以a+3开始的区间，即[3, 5)
      replace(a, a + 5, 3, 4);//将区间[0, 5)内等于3的元素替换为4
      fill(a, a + 5, 1);//将1写入区间[0, 5)中(初始化数组函数)
      unique(a, a + 5);//将相邻元素间的重复元素全部移动至末端，返回去重之后数组最后一个元素之后的地址
      remove(a, a + 5, 3);//将区间[0, 5)中的元素3移至末端，返回新数组最后一个元素之后的地址

      //排列算法
      next_permutation(a, a + 5);//产生下一个排列{ 1, 2, 3, 5, 4 }
      prev_permutation(a, a + 5);//产生上一个排列{ 1, 2, 3, 4, 5 }

      //前缀和算法
      partial_sum(a, a + 5, a);//计算数组a在区间[0, 5)内的前缀和并将结果保存至数组a中

      // vector sort
      vector<int> v = { 1, 2, 3 };//初始化vector，v:{1, 2, 3}
      sort(vector.begin(),vector.end());

      return 0;
  }
  ```

# Effective C++

TODO

```
[已过时内容](https://book.douban.com/review/14560548/)

这本书（Effective C++）在2011年出版，里面的代码讲解都是基于C++98/03的内容，但目前C++20已经发布，C++23也在制定之中。就本书里面的55条条款（items）而言，还是很实用的，只是部分举例有点过时。
部分（个人认为）已经过时的内容以及补充，还请各位指正：

条款54：如今已经将扩展库tr1的内容并入到了C++标准程序库中了，因此可以直接使用，不需要加tr1。std::...即可。

条款05/06：在C++11中，除了默认构造函数、拷贝构造函数、析构函数和拷贝赋值运算符之外，编译器还会“默默编写”下面两个函数:
ClassName(ClassName&&); // 移动构造函数
ClassName& operator=(ClassName&&); // 移动赋值运算符

条款06/14，目前不再需要书中的方法，只需要使用=delete阻止拷贝类对象

条款07/36/39：C++11中已经可以通过定义类为final来阻止继承；虚函数也可以有override和final指示符。

条款13：auto_ptr已被废弃，取而代之的是unique_ptr。

条款29/51：表示函数不会抛出异常的动态异常声明throw()已经被新的noexcept异常声明所取代。在C++98中，new可能会包含一些抛出的std::bad_alloc异常，而在C++11中，则使用noexcept(false)来进行替代。

条款37：C++11已经允许类内初始化。（不过按照这一条款说法还是不建议用hhh）

条款38：如果担心标准库中set的开销，可以用unordered_set。

条款42：新标准已经允许为类模板定义一个类型别名。（其实新标准后，类型别名就大行其道了）

条款47：（我选择快乐的auto类型推导。）traits和函数模板参数推导的技巧，利用C++11才出现的auto和decltype似乎可以很轻松的解决。C++14甚至引入decltype(auto) 来自动推导精确类型。除此之外返回值不确定时还有尾置返回这种东西，C++14连尾置返回都可以省了。

条款06：C++11后引入了delete关键字，现在想阻止编译器默认的合成行为只需在函数声明的尾部写上 = delete 即可。无需再采用书中的方式。

条款17：C++11后，更推荐直接使用 make_shared<T>(args) 来一次性完成“new对象并放入智能指针”的工作
```

# 参考资料

- 《Essential c++》
- 《现代C++教程》
- [CMake、CMakeLists.txt、GCC、Clang、LLVM、MinGW、交叉编译](https://zhupite.com/program/CMake-GCC-Clang-LLVM-MinGW-CrossCompile.html)
- [gcc与clang对比](https://blog.csdn.net/sinat_36629696/article/details/79979274)
- gcc,LLVM,clang,VC 关系：
  - [Clang、LLVM与GCC介绍](https://blog.csdn.net/weichuang_1/article/details/48632321)
  - [LLVM,Clang,GCC](https://blog.csdn.net/ShockYu/article/details/102793708)
  - [VC, GCC, Clang/LLVM区别 ](https://www.cnblogs.com/xuesu/p/14542821.html)
- [cstdio和stdio.h的区别](https://blog.csdn.net/Abigial_/article/details/54799629)
- [Google的C++开源代码项目以及经典C++库](https://www.cnblogs.com/zhoug2020/p/5812578.html)
- [C/C++中关于静态链接库(.a)、动态链接库（.so）的编译与使用](https://blog.csdn.net/qq_27825451/article/details/105700361)
- [从C到C++](https://nu-ll.github.io/2020/06/23/%E4%BB%8EC%E5%88%B0C++/)
- [C++入门教程](https://www.dotcpp.com/course/cpp/)
- [gnu libstdc++ doc](https://gcc.gnu.org/)
- [重述《Effective C++》](https://normaluhr.github.io/2020/12/31/Effective-C++/)

