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

    ```
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

> Junit5需要Java11及以上

# 4. Mockito

# 5. 正确示例

## 5.1. 项目结构

## 5.2. 单元测试方法

# 6. 参考资料

- [如何写好单元测试：Mock脱离数据库+不使用@SpringBootTest](https://blog.csdn.net/qq_36688143/article/details/97393949)
- [Java-Mock简化单元测试](https://segmentfault.com/a/1190000038317849)
- [Mockito 简明教程](https://waylau.com/mockito-quick-start/)
- [使用强大的 Mockito 来测试你的代码](https://www.jianshu.com/p/f6e3ab9719b9)
- [JUnit 5 tutorial - Learn how to write unit tests](https://www.vogella.com/tutorials/JUnit/article.html)
- [Unit Testing with JUnit 4 - Tutorial](https://www.vogella.com/tutorials/JUnit4/article.html)

