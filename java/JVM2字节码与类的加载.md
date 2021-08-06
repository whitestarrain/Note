[java8文档](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)

# 1. 学习路线回顾

![course-2](./image/course-2.png)

![course-1](./image/course-1.png)

- 最终目的是性能的监控和调优(下篇)
- 所以学会使用性能监测工具(下篇)
- 要想看得懂可视化工具显示的数据，就要懂内存的分配和回收(上篇)
- 想懂内存的分配和回收，就要懂内存的结构(上篇)
- 为了更好得理解内存结构，分配位置，内存中的数据是干什么的等等，要
  - 学习类的加载器(上篇简单谈一下，中篇深入)
  - 执行引擎(上篇)
  - class文件结构和字节码指令(中篇)
    > 在上篇也会简单涉及

# 2. class文件结构

## 2.1. 概述

### 2.1.1. 字节码文件的跨平台性

> 前面就有讲过，`跨平台的语言，跨语言的平台`，此处不再重复说明

### 2.1.2. java前端编译器

Java源代码的编译结果是字节码，那么肯定需要有一种编译器能够将Java源码编译为字节码，承担这个重要责任的就是配置在path环境变量中的**javac编译器**

javac是一种能够将Java源码编译为字节码的**前端编译器**。

HotSpot VM并没有强制要求前端编译器只能使用javac来编译字节码，其实只要编译结果符合JVM规范都可以被JVM所识别即可。在Java的前端编译器领域，除了javac之外，还有一种被大家经常用到的前端编译器，那就是内置在Eclipse中的**ECJ （EclipseCompiler for Java）编译器**。和Javac的全量式编译不同，ECJ是一种增量式编译器。

在Eclipse中，当开发人员编写完代码后，使用“Ctrl+S”快捷键时，ECJ编译器所采取的编译方案是把未编译部分的源码逐行进行编译，而非每次都全量编译。因此ECJ的编译效率会比javac更加迅速和高效，当然编译质量和javac相比大致还是一样的。

ECJ不仅是Eclipse的默认内置前端编译器，在Tomcat中同样也是使用ECJ编译器来编译jsp文件。由于ECJ编译器是采用
GPLv2的开源协议进行源代码公开，所以，大家可以登录eclipse官网下载EC]编译器的源码进行二次开发。

默认情况下， IntelliJ IDEA 使用 javac编译器。（还可以自己设置为AspectJ编译器ajc）

前端编译器并不会直接涉及编译优化等方面的技术，而是将这些具体优化细节移交给HotSpot的JIT编译器负责。


<br /><br />

复习：AOT

#### 2.1.2.1. 示例：透过字节码查看代码细节

#### 2.1.2.2. 面试题

下面的面试题学完之后再答

```
BAT 面试题

类文件结构有几个部分

知道字节码吗？Integer x=5;int y = 5;比较x==y都经过哪些步骤
```

#### 2.1.2.3. 示例1--Integer间的==

通过字节码查看代码细节示例：

```java
Integer i1 = 10;
Integer i2 = 10;
System.out.println(i1==i2);//true

Integer i1 = 128;
Integer i2 = 128;
System.out.println(i1==i2);//false
```

<details>
<summary style="color:red;">解析</summary>

```
invokestatic #2 <java/lang/Integer.valueOf>
```

查看字节码可以发现调用了Integer的valueOf方法。如果是-128~127之间，就会存在与IntegerCache中，不会创建新的Integer，因此引用相同。

```java
public static Integer valueOf(int i) {
    if (i >= IntegerCache.low && i <= IntegerCache.high)
        return IntegerCache.cache[i + (-IntegerCache.low)];
    return new Integer(i);
}
```
</details>

#### 2.1.2.4. 示例2--String间的==

该示例在上面面试题中已经说明过，不再重复说明。涉及

- `new String()+new String()`实现原理
- `StringBuilder` 的 `toString()`方法

```java
String str = new String("hello") + new String("world");
String str1 = "helloworld";
System.out.println(str == str1);
```

#### 2.1.2.5. 示例3(难)--继承

下面代码的输出是什么

```java
class Father {
    int x = 10;

    public Father() {
        this.print();
        x = 20;
    }
    public void print() {
        System.out.println("Father.x = " + x);
    }
}

class Son extends Father {
    int x = 30;
//    float x = 30.1F;
    public Son() {
        this.print();
        x = 40;
    }
    @Override
    public void print() {
        System.out.println("Son.x = " + x);
    }
}

public class SonTest {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);
    }
}
```


<details>
<summary style="color:red;">答案与解析</summary>

结果：

```
Son.x = 0
Son.x = 30
20  // 属性不存在多态性
```

<br /><br />

Son类的字节码：

```
 0 aload_0 // 把this放到局部变量表中
 1 invokespecial #1 <com/atguigu/java/Father.<init>> // 调用父类的init方法，init方法中调用了print,但是因为子类重写了父类的print方法，
                                                     // 所以执行子类的print方法。因为在下面才给Son里面的x赋值为30，因此此处打印为0
 4 aload_0 
 5 bipush 30  // 对应 int x=30
 7 putfield #2 <com/atguigu/java/Son.x>  // 对应this.print()
10 aload_0
11 invokevirtual #3 <com/atguigu/java/Son.print>
14 aload_0
15 bipush 40
17 putfield #2 <com/atguigu/java/Son.x>
20 return
```

</details>

> （复习）属性初始化方式：1.默认初始化；2.显式初始化；3.代码块中初始化；4.构造器中初始化；5.对象.属性 初始化

## 2.2. 虚拟机的基石：Class文件

- 字节码文件里是什么--字节码定义
  - 源代码经过编译器编译之后便会生成一个字节码文件
  - 字节码是一种二进制的类文件,它的内容是JVM的指令,而不像C、C++经由编译器直接生成机器码。

- 什么是字节码指令(byte code)--字节码指令构成
  - Java虚拟机的指令由一个字节长度的、代表着某种特定操作含义的**操作码( opcode)**，
  - 以及跟随其后的零至多个代表此操作所需参数的**操作数(operand)**所构成。
  - 虚拟机中许多指令并不包含操作数,只有一个操作码。

- 解读字节码方式
  - jclasslib
  - javap反编译
  - Notepad++搭配HEX-Editor插件。或者Binary Viewer

## 2.3. class文件结构

- Class类的本质
  - 任何一个C1ass文件都对应着唯一一个类或接口的定义信息,
  - 但反过来说,C1ass文件实际上它并不一定以磁盘文件的形式存在，也能以网络等的方式传输过来
  - Class文件是一组以8位字节为基础单位的**二进制流**

- Class文件格式
  - 要求
    - Class的结构不像XML等描述语言
    - 由于它没有任何分隔符号，所以在其中的数据项,无论是字节顺序还是数量,都是被严格限定的
    - 哪个字节代表什么含义,长度是多少,先后顺序如何,都不允许改变
  - 组成: Class文件格式采用一种类似于C语言结构体的方式进行数据存储,这种结构中只有两种数据类型:**无符号数**和**表**
    - **无符号数**:
      - 属于基本的数据类型
      - 以u1、u2、u4、u8来分别代表1个字节、2个字节、4个字节和8个字节的无符号数
      - 无符号数可以用来描述**数字、索引引用、数量值或者按照UTF-8编码构成字符串值**
    - **表**:
      - 是由**多个无符号数或者其他表**作为数据项构成的**复合数据类型**
        > 类似java中的数组
      - 所有表都习惯性地以"_info"结尾。
      - 表用于描述有层次关系的复合结构的数据,**整个C1ass文件本质上就是一张表**。
      - 由于表没有固定长度,所以通常会在其前面加上个数说明

- class文件结构
  <details>
  <summary style="color:red;">官网定义</summary>

  ```
  ClassFile {
      u4             magic;
      u2             minor_version;
      u2             major_version;
      u2             constant_pool_count;
      cp_info        constant_pool[constant_pool_count-1];
      u2             access_flags;
      u2             this_class;
      u2             super_class;
      u2             interfaces_count;
      u2             interfaces[interfaces_count];
      u2             fields_count;
      field_info     fields[fields_count];
      u2             methods_count;
      method_info    methods[methods_count];
      u2             attributes_count;
      attribute_info attributes[attributes_count];
  }
  ```
  </details>

  <details>
  <summary style="color:red;">详细说明</summary>

    | 类型           | 名称                | 说明                   | 长度    | 数量                  |
    | -------------- | ------------------- | ---------------------- | ------- | --------------------- |
    | u4             | magic               | 魔数,识别Class文件格式 | 4个字节 | 1                     |
    | u2             | minor_version       | 副版本号(小版本)       | 2个字节 | 1                     |
    | u2             | major_version       | 主版本号(大版本)       | 2个字节 | 1                     |
    | u2             | constant_pool_count | 常量池计数器           | 2个字节 | 1                     |
    | cp_info        | constant_pool       | 常量池表               | n个字节 | constant_pool_count-1 |
    | u2             | access_flags        | 访问标识               | 2个字节 | 1                     |
    | u2             | this_class          | 类索引                 | 2个字节 | 1                     |
    | u2             | super_class         | 父类索引               | 2个字节 | 1                     |
    | u2             | interfaces_count    | 接口计数器             | 2个字节 | 1                     |
    | u2             | interfaces          | 接口索引集合           | 2个字节 | interfaces_count      |
    | u2             | fields_count        | 字段计数器             | 2个字节 | 1                     |
    | field_info     | fields              | 字段表                 | n个字节 | fields_count          |
    | u2             | methods_count       | 方法计数器             | 2个字节 | 1                     |
    | method_info    | methods             | 方法表                 | n个字节 | methods_count         |
    | u2             | attributes_count    | 属性计数器             | 2个字节 | 1                     |
    | attribute_info | attributes          | 属性表                 | n个字节 | attributes_count      |

  </details>

  - 魔数:
    - magic
    - 用来识别为一个class文件的标识
  - C1ass文件版本
    - minor_version
    - major_version
  - 常量池
    - constant_pool_count
      > 由于表没有固定长度,所以通常会在其前面加上个数说明
    - constant_pool。首索引没有分配，所以长度为constant_pool_count-1
  - 访问标志
    - access_flags
    - 是类，还是接口，权限是什么等
  - 类索引,父类索引,接口索引集合
    - this_class：当前类是什么
    - super_class：父类是什么
    - interfaces_count + interfaces: 接口数组
      > 由于表没有固定长度,所以通常会在其前面加上个数说明
  - 字段表集合
    - fields_count;
      > 由于表没有固定长度,所以通常会在其前面加上个数说明
    - fields[fields_count];
  - 方法表集合
    - methods[methods_count];
    - attributes_count;
  - 属性表集合
    > 注意，field和attribute不同。日常总是称类中的字段为属性，但是JVM中属性有其他含义。比如
    - attributes_count;
    - attributes[attributes_count];

### 2.3.1. 使用javap指令解析Class文件

### 面试题

```
类文件结构有几个部分
```

```
知道字节码吗？字节码都有哪些？Integer x = 5;int y = 5;比较x==y要经过哪些步骤
```



