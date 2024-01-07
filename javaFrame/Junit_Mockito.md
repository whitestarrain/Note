> 部分英文待翻译

# 1. 常见测试写法与解析

## 1.1. 常见单元测试方法

- 代码
  ```java
  @RunWith(SpringRunner.class)
  @SpringBootTest(classes = Application.class)
  @Transactional
  @Rollback(true) // 事务自动回滚，默认是true。可以不写
  public class HelloServiceTest {

      @Autowired
      private HelloService helloService;

      @Test
      public void sayHello() {
          helloService.sayHello("zhangsan");
      }
  }
  ```

- 错误点
  - @Autowired启动了Spring
  - @SpringBootTest启动了SpringBoot环境，而classes = Application.class启动了整个项目
  - 通过@Transactional可以知道调用了数据库
  - 没有Assert断言

## 1.2. 错误测试步骤

- 使用@RunWith(SpringRunner.class)声明在Spring的环境中进行单元测试，这样Spring的相关注解就会被识别并起效
- 然后使用@SpringBootTest，它会扫描应用程序的spring配置，并构建完整的Spring Context
- 通过@SpringBootTest我们可以指定启动类，或者给@SpringBootTest的参数webEnvironment赋值为SpringBootTest.WebEnvironment.RANDOM_PORT
- 这样就会启动web容器，并监听一个随机的端口，同时，为我们自动装配一个`TestRestTemplate`类型的bean来辅助我们发送测试请求。

- 劣势
  - 如果项目稍微复杂一点，像SpringCloud那样多模块
  - 还使用了缓存、分片、微服务、集群分布式等东西，然后电脑配置再差一点
  - 那每执行一次单元测试的启动-运行-测试时间简直无法忍受

## 1.3. Component注解

在SpringBoot项目启动的时候就会跟着实例化/启动

@Component也会导致测试变慢

这个@Component注解的类里有多线程方法，随着启动类中定义的ApplicationStartup类启动了，那么在你执行单元测试的时候，由于多线程任务的影响，就可能对你的数据库造成了数据修改，即使你使用了事务回滚注解@Transactional。我出现的问题是：在我运行单元测试的时候，代码里的其他类的多线程中不停接收activeMQ消息，然后更新数据库中对应的数据。跟单元测试的执行过程交叉重叠，导致单元测试失败。其他组员在操作数据库的时候，也因为我无意中带起的多线程更改了数据库，造成了开发上的困难。

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component {
    //这个值可能作为逻辑组件(即类)的名称，在自动扫描的时候转化为spring bean，
    //即相当<bean id="" class="" />中的id
    String value() default "";
}
```

## 1.4. 如何优化

## 1.5. 不应该使用@Autowired

## 1.6. 不应该使用@SpringBootTest

## 1.7. 不应该调用数据库

## 1.8. 使用Assert断言(Junit)

# 2. Junit4

## 2.1. 介绍

- Junit是一个使用注解来标注要指定测试的方法
- 开源： [Github](https://github.com/junit-team/junit).

## 2.2. 简单使用Junit

### 2.2.1. 单元测试方法与断言

- A JUnit test：
  - is **a method** 
  - contained in a class which is **only used for testing**
-  Test class:
  - To define that a certain method is a test method
  - annotate test method with the `@Test` annotation.

- assert
  - define: 
    - method used to check an expected result versus the actual result.
    - These method calls are typically called *asserts* or *assert statements*.
  - provided by: JUnit or another assert framework
  - recommend
    - You should provide meaningful messages in assert statements
    - That makes it easier for the user to identify and fix the problem
      > This is especially true if someone looks at the problem,
      > who did not write the code under test or the test code.

### 2.2.2. Junit Test示例

- what you want to test
  ```java
  package com.vogella.junit4;

  public class Calculator {

      public int multiply(int a, int b) {
          return a * b;
      }
  }
  ```

- test class
  ```java
  package com.vogella.junit4;

  import static org.junit.Assert.assertEquals;

  import org.junit.Before;
  import org.junit.Test;

  public class CalculatorTest {

      private Calculator calculator;

      @Before
      void setUp() throws Exception {
          calculator = new Calculator();
      }

      @Test
      void testMultiply() {
          assertEquals( "Regular multiplication should work", calculator.multiply(4,5), 20);
      }

      @Test
      void testMultiplyWithZero() {
          assertEquals("Multiple with zero should be zero",0,  calculator.multiply(0,5));
          assertEquals("Multiple with zero should be zero", 0, calculator.multiply(5,0));
      }
  }
  ```

### 2.2.3. 命名习惯

> There are several potential naming conventions for JUnit tests

- general rule
  - a test name **should explain what the test doess**
  - If that is done correctly, reading the actual implementation can be avoided.

- junitt naming conventions
  - 1.widely-used naming way:
    - use the "Test" suffix at the end of test classes names.
  - 2.One possible convention is to use the "should" in the test method name
    - For example, "ordersShouldBeCreated" or "menuShouldGetActive". 
    - This gives a hint what should happen if the test method is executed.
  - 3.use "Given[ExplainYourInput]When[WhatIsDone]Then[ExpectedResult]"

- JUnit naming conventions **for Maven**
  - If you are using the Maven build system, **you should use the "Test" suffix for test classes**
  - The Maven build system (via its surfire plug-in) **automatically includes such classes in its test scope**

This class can be executed like any other Java program on the command line. You only need to add the JUnit library JAR file to the classpath.

## 2.3. 使用Junit4

### 2.3.1. Defining test methods

> JUnit uses annotations to mark methods as test methods and to configure them. 
>
> The following table gives an overview of the most important annotations in JUnit for the 4.x and 5.x versions.
>
> All these annotations can be used on methods.

| JUnit 4                                | Description                                                  |
| :------------------------------------- | :----------------------------------------------------------- |
| `import org.junit.*`                   | Import statement for using the following annotations.        |
| `@Test`                                | Identifies a method as a test method.                        |
| `@Before`                              | Executed before **each test**. It is used to prepare the test environment (e.g., read input data, initialize the class). |
| `@After`                               | Executed after **each test**. It is used to cleanup the test environment (e.g., delete temporary data, restore defaults). It can also save memory by cleaning up expensive memory structures. |
| `@BeforeClass`                         | **Executed once**, before the start of all tests.  <br />It is used to perform time intensive activities, for example, to connect to a database. <br /> Methods marked with this annotation **need to be defined as `static`** to work with JUnit. |
| `@AfterClass`                          | **Executed once**, after all tests have been finished. <br /> It is used to perform clean-up activities, for example, to disconnect from a database. <br />Methods annotated with this annotation **need to be defined as `static`** to work with JUnit. |
| `@Ignore` or <br /> `@Ignore("Why disabled")` | Marks that the test should be disabled. <br /> This is useful when the underlying code has been changed and the test case has not yet been adapted. <br /> Or if the execution time of this test is too long to be included. <br />It is best practice to provide the optional description, why the test is disabled. |
| `@Test (expected = Exception.class)`   | Fails if the method does not throw the named exception.      |
| `@Test(timeout=100)`                   | Fails if the method takes longer than 100 milliseconds.      |

### 2.3.2. Assert statements

> JUnit provides static methods to test for certain conditions via the `Assert` class.
>
> These *assert statements* typically start with `assert`. 
>
> They allow you to specify the error message, the expected and the actual result. 
>
> An *assertion method* compares the actual value returned by a test to the expected value.
>
> It throws an `AssertionException` if the comparison fails.

The following table gives an overview of these methods. Parameters in [] brackets are optional and of type String.

| Statement                                            | Description                                                  |
| :--------------------------------------------------- | :----------------------------------------------------------- |
| fail([message])                                      | Let the method fail. Might be used to check that a certain part of the code is not reached or to have a failing test before the test code is implemented. The message parameter is optional. |
| assertTrue([message,] boolean condition)             | Checks that the boolean condition is true.                   |
| assertFalse([message,] boolean condition)            | Checks that the boolean condition is false.                  |
| assertEquals([message,] expected, actual)            | Tests that two values are the same. Note: for arrays the reference is checked not the content of the arrays. |
| assertEquals([message,] expected, actual, tolerance) | Test that float or double values match. The tolerance is the number of decimals which must be the same. |
| assertNull([message,] object)                        | Checks that the object is null.                              |
| assertNotNull([message,] object)                     | Checks that the object is not null.                          |
| assertSame([message,] expected, actual)              | Checks that both variables refer to the same object.         |
| assertNotSame([message,] expected, actual)           | Checks that both variables refer to different objects.       |

### 2.3.3. JUnit test suites

- test suites
  - test suites and test class:
    - if you have several test classes, you can combine them into a test suite
  - test suites and test suites
    - A test suite can also contain other test suites.
  - performance
    - running a test suite executes all test classes in that suite in the specified order
  - how to:
    - use `RunWith` and `SuiteClasses`

- example

  ```java
  package com.vogella.junit.first;

  import org.junit.runner.RunWith;
  import org.junit.runners.Suite;
  import org.junit.runners.Suite.SuiteClasses;

  @RunWith(Suite.class)
  @SuiteClasses({
          MyClassTest.class,
          MySecondClassTest.class })

  public class AllTests {

  }
  ```

  - The following example code demonstrates the usage of a test suite.
  - It contains two test classes (MyClassTest and MySecondClassTest).
  - If you want to add another test class, you can add it to the `@Suite.SuiteClasses` statement.

### 2.3.4. Disabling tests

- use @Ignore annotation
  - allow to statically ignore a test. 

- use **`Assume.assumeFalse` or `Assume.assumeTrue`** to define a condition for the test
  - `Assume.assumeFalse` marks the test as invalid, if its condition evaluates to true
  - `Assume.assumeTrue` evaluates the test as invalid if its condition evaluates to false
  - For example, the following disables a test on Linux:

    ```java
    Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
    ```

### 2.3.5. Parameterized test

- explain
  - define
    - use parameters in a tests class.
  - restrict
    - This class can contain **one** test method and this method is executed with the different parameters provided.
  - how to
    - mark a test class as a parameterized test with the `@RunWith(Parameterized.class)` annotation.
    - must contain **a static method** annotated with the `@Parameters` annotation
      - That method generates and returns a collection of arrays
      - Each item in this collection is used as parameter for the test method.
    - can use the `@Parameter` annotation on **public field**s to get the test values injected in the test.

- example
  - way-1: using the `@Parameter` annotation
    - The following code shows an example for a parameterized test.
    - It tests the `multiply()` method of the `MyClass` class which is included as inner class for the purpose of this example.

    <details>
    <summary style="color:red;">unfold</summary>

    ```java
    package testing;

    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.junit.runners.Parameterized;
    import org.junit.runners.Parameterized.Parameters;

    import java.util.Arrays;
    import java.util.Collection;

    import static org.junit.Assert.assertEquals;
    import static org.junit.runners.Parameterized.*;

    @RunWith(Parameterized.class)
    public class ParameterizedTestFields {

        // fields used together with @Parameter must be public
        @Parameter(0)
        public int m1;
        @Parameter(1)
        public int m2;
        @Parameter(2)
        public int result;

        // creates the test data
        @Parameters
        public static Collection<Object[]> data() {
            // Respectively injected into m1,m2,result
            Object[][] data = new Object[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
            return Arrays.asList(data);
        }

        @Test
        public void testMultiplyException() {
            MyClass tester = new MyClass();
            assertEquals("Result", result, tester.multiply(m1, m2));
        }

        // class to be tested
        class MyClass {
            public int multiply(int i, int j) {
                return i *j;
            }
        }

    }
    ```
    </details>

  - way-2: you can use **a constructor** in which you store the values for each test
    - The number of elements in each array provided by the method annotated with `@Parameters` must correspond to the number of parameters in the constructor of the class.
    - The class is created for each parameter and the test values are passed via the constructor to the class.

    <details>
    <summary style="color:red;">unfold</summary>

    ```java
    package de.vogella.junit.first;

    import static org.junit.Assert.assertEquals;

    import java.util.Arrays;
    import java.util.Collection;

    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.junit.runners.Parameterized;
    import org.junit.runners.Parameterized.Parameters;

    @RunWith(Parameterized.class)
    public class ParameterizedTestUsingConstructor {

        private int m1; // can be private
        private int m2;

        public ParameterizedTestUsingConstructor(int p1, int p2) {
            m1 = p1;
            m2 = p2;
        }

        // creates the test data
        @Parameters
        public static Collection<Object[]> data() {
            Object[][] data = new Object[][] { { 1 , 2 }, { 5, 3 }, { 121, 4 } };
            return Arrays.asList(data);
        }

        @Test
        public void testMultiplyException() {
            MyClass tester = new MyClass();
            assertEquals("Result", m1 * m2, tester.multiply(m1, m2));
        }

        // class to be tested
        class MyClass {
            public int multiply(int i, int j) {
                return i *j;
            }
        }

    }
    ```
    > If you run this test class, the test method is executed with each defined parameter.
    > In the above example the test method is executed three times.
    </details>

  - way-3:A more flexible and easier to write approach is provided by the JUnitParams from https://github.com/Pragmatists/JUnitParams.

### 2.3.6. JUnit Rules

- explain
  - how to:annotate fields of type `TestRule` with the `@Rule` annotation
  - do what:Via JUnit rules you can add behavior to each tests in a test class

- example-1 specify which exception message you expect during the execution of your test code.
  > like @Test(expected = NullPointerException.class)

    ```java
    package de.vogella.junit.first;

    import org.junit.Rule;
    import org.junit.Test;
    import org.junit.rules.ExpectedException;

    public class RuleExceptionTesterExample {

      @Rule
      public ExpectedException exception = ExpectedException.none();

      @Test
      public void throwsNullPointerException() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null");
        throw new NullPointerException("null");
      }
    }
    ```

- example-2:`TemporaryFolder` class allows to setup files and folders which are automatically removed after each test run.

  ```java
  package de.vogella.junit.first;

  import static org.junit.Assert.assertTrue;

  import java.io.File;
  import java.io.IOException;

  import org.junit.Rule;
  import org.junit.Test;
  import org.junit.rules.TemporaryFolder;

  public class RuleTester {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testUsingTempFolder() throws IOException {
      File createdFolder = folder.newFolder("newfolder");
      File createdFile = folder.newFile("myfilefile.txt");
      assertTrue(createdFile.exists());
    }
  }
  ```

- For more examples of existing rules see https://github.com/junit-team/junit4/wiki/Rules.

### 2.3.7. Writing custom JUnit rules

- how to: implement the `TestRule` interface.
- explain: This interface defines the `apply(Statement, Description)` method which must return an instance of `Statement`.
  - Statement represent the tests within the JUnit runtime and Statement#evaluate() run these
  - Description describes the individual test. It allows to read information about the test **via reflection**.

- example: adding a log statement to an Android application before and after test execution.

  <details>
  <summary style="color:red;">unfold</summary>

  ```java
  package testing.android.vogella.com.asynctask;

  import android.util.Log;

  import org.junit.rules.TestRule;
  import org.junit.runner.Description;
  import org.junit.runners.model.Statement;

  public class MyCustomRule implements TestRule {
      private Statement base;
      private Description description;

      @Override
      public Statement apply(Statement base, Description description) {
          this.base = base;
          this.description = description;
          return new MyStatement(base);
      }

      public class MyStatement extends Statement {
          private final Statement base;

          public MyStatement(Statement base) {
              this.base = base;
          }

          @Override
          public void evaluate() throws Throwable {
              System.
              Log.w("MyCustomRule",description.getMethodName() + "Started" );
              try {
                  base.evaluate();
              } finally {
                  Log.w("MyCustomRule",description.getMethodName() + "Finished");
              }
          }
      }
  }
  ```
  </details>

  - To use this rule, simple add a field annotated with `@Rule` to your test class.

    ```java
    @Rule
    public MyCustomRule myRule = new MyCustomRule();
    ```

### 2.3.8. Categories

- explain
  - It is possible to define categories of tests and include or exclude them based on annotations. The following example is based on the [JUnit 4.8 release notes](https://github.com/junit-team/junit/blob/master/doc/ReleaseNotes4.8.md).
  - always used with suite

- example
  ```java
  public interface FastTests { /* category marker */
  }

  public interface SlowTests { /* category marker */
  }

  public class A {
      @Test
      public void a() {
          fail();
      }

      @Category(SlowTests.class)
      @Test
      public void b() {
      }
  }

  @Category({ SlowTests.class, FastTests.class })
  public class B {
      @Test
      public void c() {
      }
  }

  @RunWith(Categories.class)
  @IncludeCategory(SlowTests.class)
  @SuiteClasses({ A.class, B.class })
  // Note that Categories is a kind of Suite
  public class SlowTestSuite {
      // Will run A.b and B.c, but not A.a
  }

  @RunWith(Categories.class)
  @IncludeCategory(SlowTests.class)
  @ExcludeCategory(FastTests.class)
  @SuiteClasses({ A.class, B.class })
  // Note that Categories is a kind of Suite
  public class SlowTestSuite {
      // Will run A.b, but not A.a or B.c
  }
  ```

### 2.3.9. JUnit static imports

- explain
  - Static import is a feature that allows fields and methods defined in a class as `public static` to be used without specifying the class in which the field is defined.
  - JUnit assert statements are typically defined as `public static` to allow the developer to write short test statements.
  - The following snippet demonstrates an assert statement with and without static imports.

- example

  ```java
  // without static imports you have to write the following statement
  Assert.assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));

  // alternatively define assertEquals as static import
  import static org.junit.Assert.assertEquals;

  // more code

  // use assertEquals directly because of the static import
  assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
  ```

### 2.3.10. @RunWith注解

- @RunWith 在JUnit中有很多个Runner，他们负责调用你的测试代码，每一个Runner都有各自的特殊功能
- 要根据需要选择不同的Runner来运行你的测试代码。一般都是使用SpringRunner.class
- 如果只是简单的做普通Java测试，不涉及Spring Web项目，你可以省略@RunWith注解，这样系统会自动使用默认Runner来运行你的代码。

# 3. Junit5

> 要求Junit5需要Java11及以上，因此暂时应该用不到

# 4. Mockito

## 4.1. 使用Mock对象进行测试

### 4.1.1. 单元测试的目标和挑战

- 单元测试的思路是在不涉及依赖关系的情况下测试代码（隔离性），所以**测试代码与其他类或者系统的关系应该尽量被消除**
- 一个可行的消除方法是**替换掉依赖类（测试替换）**，也就是说我们可以使用替身来替换掉真正的依赖对象。

### 4.1.2. 测试类的分类

- *dummy object*
  - **作为参数传递给方法但是绝对不会被使用**
  - 譬如说，这种测试类内部的方法不会被调用，或者是用来填充某个方法的参数。
- *Fake*
  > *Fake* 实现了真正的逻辑，但它的存在只是为了测试，而不适合于用在产品中
  - **是真正接口或抽象类的实现体**，但给对象内部实现很简单
  - 譬如说，它存在内存中而不是真正的数据库中。
- *stub* 
  - *stub* 类是**依赖类的部分方法实现**，而这些方法在你测试类和接口的时候会被用到，也就是说 *stub* 类在测试中会被实例化。
  - *stub* 类会回应任何外部测试的调用
  - *stub* 类有时候还会记录调用的一些信息。
- *mock object*
  - 是指 **类或者接口的*模拟实现***
  - 你可以自定义这个对象中某个方法的输出结果。

---

- 测试替代技术：
  - 测试替代技术能够**在测试中模拟测试类以外对象**。因此你可以验证测试类是否响应正常。
  - 譬如说，你可以验证在 Mock 对象的某一个方法是否被调用。这可以确保隔离了外部依赖的干扰只测试测试类。
  - 我们选择 Mock 对象的原因是因为 Mock 对象只需要少量代码的配置。

### 4.1.3. Mock 对象

- 创建方式：
  - 手动创建
  - 使用 Mock 框架来模拟这些类，Mock 框架允许你在运行时创建 Mock 对象并且定义它的行为。

- 常见使用场景
  - 一个典型的例子是把 Mock 对象模拟成数据的提供者
  - Mock 对象可以被提供来进行测试。因此，我们测试的类应该避免任何外部数据的强依赖。
  - 在正式的生产环境中它会被实现用来连接数据源

- 作用
	- <mark> Mockito 会跟踪 mock 对象里面所有的方法和变量。 </mark>
  - 通过 Mock 对象或者 Mock 框架，我们可以测试代码中期望的行为
  - 譬如说，验证只有某个存在 Mock 对象的方法是否被调用了。

### 4.1.4. 使用 Mockito 生成 Mock 对象

- **Mockito**
  - 是一个流行 mock 框架，可以和JUnit结合起来使用
  - Mockito 允许你创建和配置 mock 对象。使用Mockito可以明显的简化对外部依赖的测试类的开发。

- 一般使用 Mockito 需要执行下面三步
  - 模拟并替换测试代码中外部依赖。
  - 执行测试代码
  - 验证测试代码是否被正确的执行

  ![mock-1](./image/mock-1.png)

## 4.2. Mockito API

### 4.2.1. 静态引用

- 静态引用：如果在代码中静态引用了`org.mockito.Mockito.*;`，那就可以直接调用静态方法和静态变量而不用创建对象，譬如直接调用 mock() 方法。

### 4.2.2. 使用 Mockito 创建和使用 mock 对象

- 创建mock对象
  - Mockito.mock() 静态方法
  - `@Mock` 注解
    - 旧版本会调用`MockitoAnnotations.initMocks(this)` 来初始化该 mock 对象
    - 当前会使用`MockitoAnnotations.openMocks()` 来初始化mock对象

- 另外也可以通过使用`@RunWith(MockitoJUnitRunner.class)`来达到相同的效果。

- 示例：验证某个方法是否有执行过
  ```java
  import static org.mockito.Mockito.*;

  public class MockPreTest {

      @Mock
      IMedicalRecordMapper iMedicalRecordMapper;

      @Rule
      public MockitoRule mockitoRule = MockitoJUnit.rule();

      @Test
      public void testMock() {

        // 通过静态方法创建 创建 mock 对象
        // MyClass iMedicalRecordMapper = Mockito.mock(IMedicalRecordMapper .class);

          // 不会有空指针异常，@Mock标注的对象已经被注入
          MedicalRecord record = iMedicalRecordMapper.getMedicalRecordByNum(1);
          System.out.println(record);
          // 验证getMedicalRecordByNum是否被调用过
          verify(iMedicalRecordMapper).getMedicalRecordByNum(1); // 正常通过
          // verify(iMedicalRecordMapper).getMedicalRecordByNum(2); // 报错，参数不一致
      }
  }
  ```

  - 告诉 Mockito 模拟 IMedicalRecordMapper 实例
  - Mockito 通过 @mock 注解创建 mock 对象
  - 使用已经创建的mock初始化这个类(注入)
  - 在测试环境下，执行测试类中的代码
  - 验证 getMedicalRecordByNum 方法是否被 `IMedicalRecordMapper` 的 mock 对象调用

### 4.2.3. mock 配置返回值和异常（打桩Stub）

- 语法：两种，都支持链式调用
  - `when(….).thenReturn(….)`
    > 返回的就是一个Stub类型
  - `doReturn(…).when(…).methodCall()`
    > 返回的是methodCall()方法的返回值类型

- 两种语法的区别：
  - when在前的打桩方式无法作用到spy对象上
  - 但是do在前的打桩方式可以作用到spy对象上

- 配置返回值
  - **可以多次定义**
    - 当你多次调用函数的时候，Mockito 会**根据你定义的先后顺序来返回返回值**
  - 可以根据 **传入参数的不同** 来定义不同的返回值
    - 譬如说你的函数可以将`anyString` 或者 `anyInt`作为输入参数，然后定义其特定的放回值。

- 配置抛出异常：
  - 我们想在调用某些无返回值函数的时候抛出异常，那么可以使用`doThrow` 方法

- **注意**
  - 对于 static 和 final 方法， Mockito 无法对其 when(…).thenReturn(…) 操作。
  - 当我们连续两次为同一个方法使用 stub 的时候，他只会只用最新的一次。

---

- 示例：配置返回值

  ```java
  import static org.mockito.Mockito.*;
  import static org.junit.Assert.*;

  @Test
  public void test1()  {
          //  创建 mock
          MyClass test = Mockito.mock(MyClass.class);

          // 自定义 getUniqueId() 的返回值
          when(test.getUniqueId()).thenReturn(43);
          // 或者写成下面这种格式
          doReture(43).when(test).getUniqueId();

          // 在测试中使用mock对象
          assertEquals(test.getUniqueId(), 43);
  }

  // 返回多个值
  @Test
  public void testMoreThanOneReturnValue()  {
          Iterator i= mock(Iterator.class);
          when(i.next()).thenReturn("Mockito").thenReturn("rocks");
          String result=i.next()+" "+i.next();
          // 断言
          assertEquals("Mockito rocks", result);
  }

  // 如何根据输入来返回值
  @Test
  public void testReturnValueDependentOnMethodParameter()  {
          Comparable c= mock(Comparable.class);
          when(c.compareTo("Mockito")).thenReturn(1);
          when(c.compareTo("Eclipse")).thenReturn(2);
          // 断言
          assertEquals(1,c.compareTo("Mockito"));
  }

  // 如何让返回值不依赖于输入
  @Test
  public void testReturnValueInDependentOnMethodParameter()  {
          Comparable c= mock(Comparable.class);
          when(c.compareTo(anyInt())).thenReturn(-1);
          // 断言
          assertEquals(-1 ,c.compareTo(9));
  }

  // 根据参数类型来返回值
  @Test
  public void testReturnValueInDependentOnMethodParameter()  {
          Comparable c= mock(Comparable.class);
          when(c.compareTo(isA(Todo.class))).thenReturn(0);
          // 断言
          Todo todo = new Todo(5);
          assertEquals(todo ,c.compareTo(new Todo(1)));
  }
  ```

- 示例：没有返回值，抛出异常
  ```java
  import static org.mockito.Mockito.*;
  import static org.junit.Assert.*;

  // 下面测试用例描述了如何使用doThrow()方法

  @Test(expected=IOException.class)
  public void testForIOException() {
          // 创建并配置 mock 对象
          OutputStream mockStream = mock(OutputStream.class);
          doThrow(new IOException()).when(mockStream).close();

          // 使用 mock
          OutputStreamWriter streamWriter= new OutputStreamWriter(mockStream);
          streamWriter.close();
  }
  ```

- 注意：按顺序返回对象时，这两种语法的效果会不会叠加。顺序执行完后不会重新从头来
    ```java
    when(iMedicalRecordMapper.getMedicalRecordByNum(10)).thenReturn(new MedicalRecord()).thenReturn(null).thenReturn(new MedicalRecord()); // (1)
    doReturn(null).doReturn(null).doReturn(new MedicalRecord()).when(iMedicalRecordMapper).getMedicalRecordByNum(10); // (2)
    for (int i = 0; i < 6; i++) {
        System.out.println(iMedicalRecordMapper.getMedicalRecordByNum(10)); // object
    }
    // 分别返回 null,null,obj,obj,obj
    // (2)的设定覆盖了(1)，同时三个返回完之后，之后一直按照最后一个进行返回
    ```

### 4.2.4. 验证 mock 对象方法是否被调用(行为测试)

> 行为测试并不会检查函数的返回值，而是检查在**传入正确参数时候函数是否被调用**。

```java
import static org.mockito.Mockito.*;

@Test
public void testVerify()  {
        // 创建并配置 mock 对象
        MyClass test = Mockito.mock(MyClass.class);
        when(test.getUniqueId()).thenReturn(43);

        // 调用mock对象里面的方法并传入参数为12
        test.testing(12);
        test.getUniqueId();
        test.getUniqueId();

        // 查看在传入参数为12的时候方法是否被调用
        verify(test).testing(12);

        // 方法是否被调用两次
        verify(test, times(2)).getUniqueId();

        // 其他用来验证函数是否被调用的方法
        verify(mock, never()).someMethod("never called");
        verify(mock, atLeastOnce()).someMethod("called at least once");
        verify(mock, atLeast(2)).someMethod("called at least twice");
        verify(mock, times(5)).someMethod("called five times");
        verify(mock, atMost(3)).someMethod("called at most 3 times");
}
```

### 4.2.5. 使用 Spy 封装 java 对象

- 说明：
  - @Spy或者`spy()`方法可以被用来封装 java 对象
  -  被封装后，除非特殊声明（打桩 *stub*），否则都会真正的调用对象里面的每一个方法
    > 只有do在前的打桩方式会起作用

- spy和mock异同
  - 得到的对象同样可以进行“监管”，即验证和打桩。
  - 如果不对spy对象的methodA打桩，那么调用spy对象的methodA时，会调用真实方法。
  - 如果不对mock对象的methodA打桩，将doNothing，且返回默认值（null,0,false）。

- 示例

  ```java
  import static org.mockito.Mockito.*;

  // Lets mock a LinkedList
  List list = new LinkedList();
  List spy = spy(list);

  // 可用 doReturn() 来打桩
  doReturn("foo").when(spy).get(0);

  // 下面代码不生效
  // 真正的方法会被调用
  // 将会抛出 IndexOutOfBoundsException 的异常，因为 List 为空
  when(spy.get(0)).thenReturn("foo");

  // `verifyNoMoreInteractions()`允许你检查没有其他的方法被调用了。
  ```

### 4.2.6. 使用 @InjectMocks 在 Mockito 中进行依赖注入

- `@InjectMocks`作用
  - 生成当前类的mock对象
  - 降其他对象注入到当前类中

- 示例：
  - 有 ArticleManager 类

    ```java
    public class ArticleManager {
        private User user;
        private ArticleDatabase database;

        ArticleManager(User user) {
            this.user = user;
        }

        void setDatabase(ArticleDatabase database) { }
    }
    ```

  - 这个类会被 Mockito 构造，而类的成员方法和变量都会被 mock 对象所代替，正如下面的代码片段所示：

    ```java
    @RunWith(MockitoJUnitRunner.class)
    public class ArticleManagerTest  {

          @Mock
          ArticleCalculator calculator;
          @Mock
          ArticleDatabase database;
          @Most
          User user;

          @Spy
          private UserProvider userProvider = new ConsumerUserProvider();

          @InjectMocks
          private ArticleManager manager; // user 和 ArticleDatabase会被注入

          @Test 
          public void shouldDoSomething() {
              // 假定 ArticleManager 有一个叫 initialize() 的方法被调用了
              // 使用 ArticleListener 来调用 addListener 方法
              manager.initialize();

              // 验证 addListener 方法被调用
              verify(database).addListener(any(ArticleListener.class));
          }
    }
    ```

### 4.2.7. 捕捉参数

- `ArgumentCaptor`类允许我们在verification期间访问方法的参数。得到方法的参数后我们可以使用它进行测试。

  ```java
  import static org.hamcrest.Matchers.hasItem;
  import static org.junit.Assert.assertThat;
  import static org.mockito.Mockito.mock;
  import static org.mockito.Mockito.verify;

  import java.util.Arrays;
  import java.util.List;

  import org.junit.Rule;
  import org.junit.Test;
  import org.mockito.ArgumentCaptor;
  import org.mockito.Captor;
  import org.mockito.junit.MockitoJUnit;
  import org.mockito.junit.MockitoRule;

  public class MockitoTests {

      @Captor
      private ArgumentCaptor captor;

      @Test
      public final void shouldContainCertainListItem() {
          List asList = Arrays.asList("someElement_test", "someElement");
          final List mockedList = mock(List.class);
          mockedList.addAll(asList);

          verify(mockedList).addAll((Collection) captor.capture());
          System.out.println(captor.getValue());

          final  Collection<Object> capturedArgument = (Collection<Object>) captor.getValue();
          assertThat(capturedArgument, hasItem("someElement"));
      }
  }
  ```

### 4.2.8. 无法被测试的类型

- Mockito当然也有一定的限制。而下面三种数据类型则不能够被测试

  - final classes
  - anonymous classes
  - primitive types

## 4.3. 实例：使用 Mockito 创建一个 mock 对象

### 4.3.1. 目标

创建一个 Api，它可以被 Mockito 来模拟并做一些工作

### 4.3.2. 创建一个Twitter API 的例子

- 实现 `TwitterClient`类，它内部使用到了 `ITweet` 的实现。但是`ITweet`实例很难得到，譬如说他需要启动一个很复杂的服务来得到。

  ```java
  public interface ITweet {

          String getMessage();
  }

  public class TwitterClient {

          public void sendTweet(ITweet tweet) {
                  String message = tweet.getMessage();

                  // send the message to Twitter
          }
  }
  ```

### 4.3.3. 模拟 ITweet 的实例

- 为了能够不启动复杂的服务来得到 `ITweet`，可以使用 Mockito 来模拟得到该实例

  ```java
  @Test
  public void testSendingTweet() {
          TwitterClient twitterClient = new TwitterClient();

          ITweet iTweet = mock(ITweet.class);

          when(iTweet.getMessage()).thenReturn("Using mockito is great");

          twitterClient.sendTweet(iTweet);
  }
  ```

- 现在 `TwitterClient` 可以使用 `ITweet` 接口的实现，当调用 `getMessage()` 方法的时候将会打印 "Using Mockito is great" 信息。

### 4.3.4. 验证方法调用

- 确保 getMessage() 方法至少调用一次。

  ```java
  @Test
  public void testSendingTweet() {
          TwitterClient twitterClient = new TwitterClient();

          ITweet iTweet = mock(ITweet.class);

          when(iTweet.getMessage()).thenReturn("Using mockito is great");

          twitterClient.sendTweet(iTweet);

          verify(iTweet, atLeastOnce()).getMessage();
  }
  ```

### 4.3.5. 验证

运行测试，查看代码是否测试通过。

## 4.4. 模拟静态方法

### 4.4.1. 使用 Powermock 来模拟静态方法

- 因为 Mockito 不能够 mock 静态方法，所以需要使用 `Powermock`。
- 示例：模拟 NetworkReader 的依赖

  ```java
  import java.net.InetAddress;
  import java.net.UnknownHostException;

  public final class NetworkReader {
      public static String getLocalHostname() {
          String hostname = "";
          try {
              InetAddress addr = InetAddress.getLocalHost();
              // Get hostname
              hostname = addr.getHostName();
          } catch ( UnknownHostException e ) {
          }
          return hostname;
      }
  }
  ```

  ```java
  import org.junit.runner.RunWith;
  import org.powermock.core.classloader.annotations.PrepareForTest;

  @RunWith( PowerMockRunner.ilass )
  @PrepareForTest( NetworkReader.class )
  public class MyTest {

  // 测试代码

  @Test
  public void testSomething() {
      mockStatic( NetworkUtil.class );
      when( NetworkReader.getLocalHostname() ).andReturn( "localhost" );

      // 与 NetworkReader 协作的测试
  }
  ```

### 4.4.2. 用封装的方法代替Powermock

- 可以在静态方法周围包含非静态的方法来达到和 Powermock 同样的效果。

  ```java
  class FooWraper { 
        void someMethod() { 
            Foo.someStaticMethod() 
        } 
  }
  ```

# 5. 项目示例

## 5.1. 项目结构

## 5.2. 单元测试方法

# 6. 参考资料

- [如何写好单元测试：Mock脱离数据库+不使用@SpringBootTest](https://blog.csdn.net/qq_36688143/article/details/97393949)
- [Java-Mock简化单元测试](https://segmentfault.com/a/1190000038317849)
- [Mockito 简明教程](https://waylau.com/mockito-quick-start/)
- [使用强大的 Mockito 来测试你的代码](https://www.jianshu.com/p/f6e3ab9719b9)
- [JUnit 5 tutorial - Learn how to write unit tests](https://www.vogella.com/tutorials/JUnit/article.html)
- [Unit Testing with JUnit 4 - Tutorial](https://www.vogella.com/tutorials/JUnit4/article.html)
- [mockito doc](https://javadoc.io/static/org.mockito/mockito-core/4.1.0/org/mockito/Mockito.html)
- [AndroidUT](https://github.com/simplezhli/AndroidUT)
- [JUnit高级用法之@RunWith](https://blog.csdn.net/weixin_33742618/article/details/91799240)

