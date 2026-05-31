# 基础

- Python是解释型、动态类型的高级编程语言，使用缩进表示代码块
- 基本数据类型：int, float, str, bool, None
- 容器类型：list(可变序列), tuple(不可变序列), dict(键值对), set(无序不重复集合)
- 流程控制：if/elif/else, for/while, break/continue, try/except/finally
- 函数定义使用def关键字，支持默认参数、可变参数(*args, **kwargs)
- 列表推导式：`[x*2 for x in range(10) if x > 3]`
- 字符串格式化：f-string(推荐)、format()、%操作符
- 文件操作：`with open('file.txt', 'r') as f: content = f.read()`
- 异常处理：try/except捕获异常，raise抛出异常，自定义异常继承Exception
- 虚拟环境：venv或virtualenv隔离项目依赖，pip管理包

# 常用模块

## 内建

<!-- https://www.liaoxuefeng.com/wiki/1016959663602400/1017642838127488 -->

## 第三方

<!-- https://www.liaoxuefeng.com/wiki/1016959663602400/1017785364772448 -->

# socket编程

- socket是网络通信的基础，Python通过socket模块提供底层网络接口
- 通信模型：服务端bind/listen/accept，客户端connect
- TCP socket提供可靠的面向连接的字节流传输
- UDP socket提供无连接的数据报传输，速度快但不可靠
- 服务端基本流程：

  ```python
  import socket
  server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  server.bind(('0.0.0.0', 8080))
  server.listen(5)
  conn, addr = server.accept()
  data = conn.recv(1024)
  conn.send(b'response')
  conn.close()
  ```

- 客户端基本流程：

  ```python
  client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  client.connect(('127.0.0.1', 8080))
  client.send(b'hello')
  response = client.recv(1024)
  client.close()
  ```

- AF_INET表示IPv4，SOCK_STREAM表示TCP，SOCK_DGRAM表示UDP
- 常见问题：端口占用(SO_REUSEADDR)、粘包(自定义协议头标明数据长度)
- 实际项目中通常使用更高级的框架(如asyncio、Twisted)而非直接操作socket

# 多任务

## 基本说明

- 多任务是指操作系统同时运行多个任务的能力
- 实现方式：多线程、多进程、协程(异步IO)
- 并发：在一个时间段内交替执行多个任务(单核CPU通过时间片轮转实现)
- 并行：在同一时刻同时执行多个任务(需要多核CPU)
- Python中由于GIL的存在，多线程适合IO密集型任务，多进程适合CPU密集型任务
- 选择策略：IO密集型用多线程/协程，CPU密集型用多进程

## 线程

### 基本概念

- 线程是操作系统调度的最小单位，同一进程内的线程共享内存空间
- 主线程：程序启动时默认创建的线程
- 子线程：由主线程创建的新线程，用于执行并发任务
- 线程的生命周期：新建、就绪、运行、阻塞、终止
- 守护线程(daemon)：主线程结束时自动销毁，适合后台服务任务
- Python线程受GIL限制，同一时刻只有一个线程执行Python字节码

### 基本使用

- 使用threading模块创建和管理线程
- 两种创建方式：传入target函数，或继承Thread类重写run方法

  ```python
  import threading

  def task(name):
      print(f'{name} is running')

  t = threading.Thread(target=task, args=('thread-1',))
  t.start()  # 启动线程
  t.join()   # 等待线程结束
  ```

- 继承方式：

  ```python
  class MyThread(threading.Thread):
      def run(self):
          print(f'{self.name} is running')

  t = MyThread()
  t.start()
  ```

- t.setDaemon(True)设置为守护线程，必须在start()之前调用
- threading.enumerate()查看当前活跃的线程列表
- threading.current_thread().name获取当前线程名

### 全局变量

- 同一进程内的多个线程共享全局变量，无需额外传参
- 共享带来便利的同时也引入了数据竞争问题
- 多个线程同时修改全局变量可能导致数据不一致

  ```python
  import threading
  num = 0
  def add():
      global num
      for _ in range(1000000):
          num += 1  # 非原子操作，结果不可预期

  t1 = threading.Thread(target=add)
  t2 = threading.Thread(target=add)
  t1.start(); t2.start()
  t1.join(); t2.join()
  print(num)  # 结果可能小于2000000
  ```

- 解决方案：使用锁(Lock/RLock)、队列(queue.Queue)等同步机制

### 同步，互斥与死锁

- 互斥锁(Lock)：保证同一时刻只有一个线程访问共享资源

  ```python
  lock = threading.Lock()
  def safe_add():
      global num
      for _ in range(1000000):
          lock.acquire()
          num += 1
          lock.release()
  ```

- 推荐使用with语句自动管理锁：`with lock: num += 1`
- 可重入锁(RLock)：同一线程可多次acquire，避免自身递归调用死锁
- 死锁：两个或多个线程互相等待对方释放锁，导致程序永久阻塞
- 死锁产生条件：互斥、持有并等待、不可抢占、循环等待
- 避免死锁：按固定顺序加锁、使用超时参数(lock.acquire(timeout=5))、尽量减小锁粒度
- 信号量(Semaphore)：限制同时访问资源的线程数量
- 条件变量(Condition)：线程间通知机制，wait()等待通知，notify()发送通知

## 进程

### 状态

- 进程是资源分配的基本单位，每个进程有独立的内存空间
- 进程状态：新建(New)、就绪(Ready)、运行(Running)、阻塞(Blocked)、终止(Terminated)
- 就绪态：进程已获得除CPU外的所有资源，等待调度
- 阻塞态：进程因等待IO或其他事件而暂停执行
- 使用multiprocessing模块创建进程：

  ```python
  from multiprocessing import Process
  def worker(name):
      print(f'Process {name} running, pid={os.getpid()}')

  p = Process(target=worker, args=('p1',))
  p.start()
  p.join()
  ```

- 进程间内存隔离，全局变量不共享，需要通过IPC机制通信
- os.getpid()获取当前进程ID，os.getppid()获取父进程ID

### 进程间通信-Queue

- multiprocessing.Queue是进程安全的队列，用于进程间数据传递
- 基于管道和锁实现，自动处理序列化/反序列化
- 主要方法：put(item)放入数据，get()取出数据(阻塞式)

  ```python
  from multiprocessing import Process, Queue

  def producer(q):
      q.put({'name': 'test', 'age': 18})

  def consumer(q):
      data = q.get()
      print(f'received: {data}')

  q = Queue()
  p1 = Process(target=producer, args=(q,))
  p2 = Process(target=consumer, args=(q,))
  p1.start(); p2.start()
  p1.join(); p2.join()
  ```

- q.full()判断队列是否已满，q.empty()判断队列是否为空
- q.qsize()返回队列中的数据个数
- Queue(maxsize)可指定队列最大容量，满时put会阻塞

### 进程池Pool

- 进程池用于管理和复用固定数量的工作进程，避免频繁创建销毁进程的开销
- Pool(n)创建包含n个进程的进程池，默认为CPU核心数

  ```python
  from multiprocessing import Pool

  def task(n):
      return n * n

  with Pool(4) as pool:
      results = pool.map(task, range(10))
      print(results)  # [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
  ```

- pool.apply_async(func, args)异步提交单个任务，返回AsyncResult对象
- pool.map(func, iterable)批量提交任务，阻塞直到所有结果返回
- pool.close()关闭进程池(不再接受新任务)，pool.join()等待所有任务完成
- 注意：进程池中使用Queue需用multiprocessing.Manager().Queue()

## 异步IO

### 迭代器

- 迭代器是实现了__iter__()和__next__()方法的对象
- __iter__()返回迭代器自身，__next__()返回下一个元素，无元素时抛出StopIteration
- 可迭代对象(Iterable)：实现了__iter__()方法的对象(list, dict, str等)
- iter(iterable)将可迭代对象转为迭代器

  ```python
  class Counter:
      def __init__(self, max_val):
          self.max_val = max_val
          self.current = 0
      def __iter__(self):
          return self
      def __next__(self):
          if self.current >= self.max_val:
              raise StopIteration
          self.current += 1
          return self.current

  for i in Counter(5):
      print(i)  # 1 2 3 4 5
  ```

- 迭代器是惰性求值的，节省内存，适合处理大数据集
- for循环的本质：先调用iter()获取迭代器，再不断调用next()直到StopIteration

### 生成器

- 生成器是一种特殊的迭代器，使用yield关键字定义，更简洁
- 函数中包含yield语句即变为生成器函数，调用时返回生成器对象
- 每次调用next()执行到yield处暂停并返回值，再次调用从暂停处继续

  ```python
  def fibonacci(n):
      a, b = 0, 1
      for _ in range(n):
          yield a
          a, b = b, a + b

  gen = fibonacci(6)
  print(list(gen))  # [0, 1, 1, 2, 3, 5]
  ```

- 生成器表达式：`(x*2 for x in range(10))`，类似列表推导但使用圆括号
- send(value)方法可以向生成器发送值，赋给yield表达式的结果
- 生成器自动实现了迭代器协议，是实现协程的基础

### 协程-yield

- 协程是用户态的轻量级线程，通过yield实现协作式多任务
- 利用send()方法向生成器传值，实现双向通信

  ```python
  def consumer():
      while True:
          data = yield
          print(f'processing: {data}')

  def producer(c):
      next(c)  # 启动生成器(预激)
      for i in range(3):
          c.send(i)
      c.close()

  c = consumer()
  producer(c)  # processing: 0, 1, 2
  ```

- yield from语句用于委托子生成器，简化嵌套生成器的写法
- yield from iterable 等价于 for item in iterable: yield item
- 基于yield的协程是Python早期协程实现方式，Python 3.5后推荐使用async/await

### asyncio

- asyncio是Python标准库提供的异步IO框架，基于事件循环(event loop)
- 核心概念：事件循环(调度协程)、协程(async def)、Task(协程的封装)、Future(异步结果)
- 事件循环不断检查并执行就绪的回调和协程，遇到IO等待时切换到其他任务

  ```python
  import asyncio

  async def fetch_data(url, delay):
      print(f'start fetching {url}')
      await asyncio.sleep(delay)  # 模拟IO操作
      return f'data from {url}'

  async def main():
      tasks = [fetch_data('url1', 1), fetch_data('url2', 2)]
      results = await asyncio.gather(*tasks)
      print(results)

  asyncio.run(main())
  ```

- asyncio.run()创建事件循环并运行入口协程(Python 3.7+)
- asyncio.gather()并发运行多个协程，等待所有完成
- asyncio.create_task()将协程包装为Task并调度执行

### await/async 语法糖

- async def定义协程函数，await挂起当前协程等待异步操作完成
- await只能在async函数内使用，await的对象必须是awaitable(协程/Task/Future)
- async with用于异步上下文管理器(实现__aenter__和__aexit__)
- async for用于异步迭代器(实现__aiter__和__anext__)

  ```python
  import asyncio

  async def fetch(session, url):
      async with session.get(url) as response:
          return await response.text()

  async def count():
      for i in range(3):
          await asyncio.sleep(1)
          yield i  # async generator

  async def main():
      async for val in count():
          print(val)
  ```

- await让出控制权给事件循环，事件循环可调度其他协程执行
- 与回调模式相比，async/await保持了同步代码的可读性

### aiohttp

- aiohttp是基于asyncio的异步HTTP客户端/服务端库
- 适合高并发HTTP请求场景，如爬虫、API调用
- 使用ClientSession管理连接池，支持连接复用

  ```python
  import aiohttp, asyncio

  async def fetch(url):
      async with aiohttp.ClientSession() as session:
          async with session.get(url) as resp:
              return await resp.text()

  async def main():
      urls = ['http://example.com'] * 10
      tasks = [fetch(url) for url in urls]
      results = await asyncio.gather(*tasks)

  asyncio.run(main())
  ```

- 支持GET/POST/PUT/DELETE等HTTP方法
- 也可作为异步Web服务器使用：aiohttp.web模块
- 配合asyncio.Semaphore可控制并发数量，避免对目标服务器造成压力

### 协程框架

#### greenlet

- greenlet是一个轻量级的协程库，提供手动切换协程的能力
- 不依赖yield，通过switch()方法显式切换执行上下文
- 每个greenlet是一个独立的执行栈，切换时保存/恢复栈状态

  ```python
  from greenlet import greenlet

  def func1():
      print('func1 start')
      gr2.switch()  # 切换到gr2
      print('func1 end')

  def func2():
      print('func2 start')
      gr1.switch()  # 切回gr1
      print('func2 end')

  gr1 = greenlet(func1)
  gr2 = greenlet(func2)
  gr1.switch()  # 输出: func1 start, func2 start, func1 end
  ```

- greenlet本身不提供IO自动切换，需要手动管理调度逻辑
- 是gevent等高级协程框架的底层实现基础

#### gevent

- gevent是基于greenlet和libev的高性能协程框架
- 核心特性：遇到IO操作时自动切换协程(隐式切换)，无需手动管理
- monkey patch：通过猴子补丁将标准库的阻塞调用替换为异步版本

  ```python
  from gevent import monkey; monkey.patch_all()
  import gevent, requests

  def fetch(url):
      resp = requests.get(url)
      print(f'{url}: {len(resp.text)}')

  jobs = [gevent.spawn(fetch, url) for url in [
      'http://example.com', 'http://example.org'
  ]]
  gevent.joinall(jobs)
  ```

- gevent.spawn()创建greenlet并调度执行
- gevent.joinall()等待所有greenlet完成
- gevent.sleep()主动让出CPU，触发协程切换
- 适合IO密集型任务，如网络爬虫、Web服务器(gunicorn gevent worker)

## Local,LocalStack,LocalProxy

- 这三个类来自werkzeug库(Flask的依赖)，用于实现线程/协程安全的全局状态管理
- Local：类似threading.local，但同时支持线程和greenlet协程隔离
  - 内部使用字典存储数据，key为线程/greenlet的id
  - 每个线程/协程访问到的都是各自独立的数据副本
- LocalStack：基于Local实现的栈结构，支持push/pop操作
  - Flask用它维护请求上下文栈和应用上下文栈
  - 支持嵌套上下文(如内部测试请求)

  ```python
  from werkzeug.local import LocalStack
  stack = LocalStack()
  stack.push('request_ctx_1')
  print(stack.top)  # 'request_ctx_1'
  stack.pop()
  ```

- LocalProxy：Local对象的代理，延迟求值
  - 每次属性访问时动态查找被代理的对象，而非绑定固定对象
  - Flask的request、g、current_app都是LocalProxy实例
  - `request = LocalProxy(partial(_lookup_req_object, 'request'))`

# 深入

## GIL

- GIL(Global Interpreter Lock)是CPython解释器中的全局互斥锁
- 作用：保证同一时刻只有一个线程执行Python字节码，简化内存管理(引用计数)
- 影响：多线程无法利用多核CPU并行执行计算密集型任务
- IO操作时GIL会被释放，因此多线程仍能加速IO密集型任务(网络请求、文件读写)
- 绕过GIL的方案：
  - 多进程(multiprocessing)：每个进程有独立的GIL
  - C扩展：在C代码中手动释放GIL(Py_BEGIN_ALLOW_THREADS)
  - 使用其他解释器：Jython、PyPy(有STM版本)
- GIL释放时机：每执行一定数量字节码(Python3中约5ms)后释放让其他线程竞争
- Python 3.13开始实验性支持free-threaded模式(无GIL)

## 深拷贝，浅拷贝

- 赋值操作只是引用绑定，不会创建新对象
- 浅拷贝(copy.copy)：创建新对象，但内部元素仍是原对象元素的引用

  ```python
  import copy
  a = [[1, 2], [3, 4]]
  b = copy.copy(a)
  b[0].append(5)
  print(a[0])  # [1, 2, 5]  内层对象被共享
  ```

- 深拷贝(copy.deepcopy)：递归复制所有层级的对象，完全独立

  ```python
  c = copy.deepcopy(a)
  c[0].append(6)
  print(a[0])  # [1, 2, 5]  互不影响
  ```

- 浅拷贝方式：copy.copy()、list.copy()、切片[:]、dict.copy()
- 不可变对象(int, str, tuple)的拷贝返回同一对象(优化)
- 注意循环引用：deepcopy内部使用memo字典处理循环引用

## 私有化

- Python没有严格的访问控制，通过命名约定实现私有化
- _var：约定为"内部使用"，from module import * 不会导入
- __var：名称修饰(name mangling)，解释器将其改为_ClassName__var
  - 防止子类意外覆盖父类属性，但仍可通过_ClassName__var访问
- __var__：魔法方法/属性，由Python系统使用，用户不应自定义
- var_：用于避免与Python关键字冲突，如class_、type_

  ```python
  class MyClass:
      def __init__(self):
          self.public = 'anyone'
          self._protected = 'internal'
          self.__private = 'mangled'

  obj = MyClass()
  print(obj._MyClass__private)  # 'mangled' 仍可访问
  ```

- 模块级别：_开头的变量不会被from module import *导入，但import module后仍可访问

## import模块搜索路径

- import时Python按以下顺序搜索模块：
  1. 当前目录(脚本所在目录)
  2. 环境变量PYTHONPATH指定的目录
  3. 标准库目录
  4. 第三方包目录(site-packages)
- sys.path列表存储了所有搜索路径，可动态修改

  ```python
  import sys
  sys.path.append('/custom/path')
  print(sys.path)  # 查看所有搜索路径
  ```

- .pth文件：放在site-packages中，每行一个路径，自动添加到sys.path
- 包(package)：含有__init__.py的目录，支持层级化组织模块
- 相对导入：from . import module (同包), from .. import module (上级包)
- 绝对导入(推荐)：from package.subpackage import module

## 多继承与MRO

- Python支持多继承，一个类可以继承多个父类
- MRO(Method Resolution Order)：方法解析顺序，决定多继承时方法查找顺序
- Python3使用C3线性化算法计算MRO，保证：
  - 子类优先于父类
  - 多个父类按定义顺序排列
  - 保持单调性(不破坏局部优先序)

  ```python
  class A: pass
  class B(A): pass
  class C(A): pass
  class D(B, C): pass
  print(D.__mro__)  # D -> B -> C -> A -> object
  ```

- super()按MRO顺序调用下一个类的方法，而非直接调用父类
- 菱形继承问题：C3算法确保公共基类只被调用一次
- 查看MRO：ClassName.__mro__ 或 ClassName.mro()

## @property

- @property装饰器将方法转为属性访问，实现getter/setter/deleter
- 用途：对属性访问添加验证逻辑、实现只读属性、计算属性

  ```python
  class Circle:
      def __init__(self, radius):
          self._radius = radius

      @property
      def radius(self):
          return self._radius

      @radius.setter
      def radius(self, value):
          if value < 0:
              raise ValueError('radius must be >= 0')
          self._radius = value

      @property
      def area(self):  # 只读计算属性
          return 3.14159 * self._radius ** 2

  c = Circle(5)
  print(c.area)  # 78.53975
  c.radius = 10  # 触发setter验证
  ```

- 不定义setter则为只读属性，赋值时抛出AttributeError
- 底层原理：property是一个描述符(descriptor)类，实现了__get__/__set__/__delete__

## 魔法属性

- 魔法属性/方法(dunder methods)是Python的特殊协议接口
- 常用魔法方法：
  - __init__：构造函数，初始化实例
  - __str__/__repr__：字符串表示(print用str，调试用repr)
  - __len__：len()调用时触发
  - __getitem__/__setitem__：支持obj[key]语法
  - __call__：使实例可被调用，obj()
  - __enter__/__exit__：上下文管理器(with语句)
  - __eq__/__lt__/__gt__：比较运算符重载
  - __add__/__mul__：算术运算符重载

  ```python
  class Vector:
      def __init__(self, x, y):
          self.x, self.y = x, y
      def __repr__(self):
          return f'Vector({self.x}, {self.y})'
      def __add__(self, other):
          return Vector(self.x + other.x, self.y + other.y)

  v = Vector(1, 2) + Vector(3, 4)
  print(v)  # Vector(4, 6)
  ```

- __dict__：实例的属性字典
- __class__：实例所属的类
- __slots__：限制实例属性，减少内存占用

# 进阶

## 闭包

- 闭包：内函数引用了外函数的局部变量，外函数返回内函数，形成闭包
- 闭包的三个条件：函数嵌套、内函数引用外函数变量、外函数返回内函数
- 闭包使得函数可以"记住"创建时的环境状态

  ```python
  def make_counter(start=0):
      count = [start]  # 使用列表避免nonlocal(Python2兼容)
      def counter():
          count[0] += 1
          return count[0]
      return counter

  c = make_counter()
  print(c(), c(), c())  # 1 2 3
  ```

- Python3使用nonlocal关键字声明修改外层变量：`nonlocal count`
- __closure__属性可查看闭包捕获的变量
- 注意循环中的闭包陷阱：循环变量延迟绑定，需用默认参数固定

  ```python
  # 错误：所有函数都引用同一个i
  funcs = [lambda: i for i in range(3)]
  # 正确：用默认参数固定
  funcs = [lambda x=i: x for i in range(3)]
  ```

## 装饰器与functools.wraps

- 装饰器本质是接收函数并返回新函数的高阶函数，用@语法糖简化调用
- 作用：在不修改原函数代码的前提下，增加额外功能(日志、计时、权限校验)

  ```python
  import functools, time

  def timer(func):
      @functools.wraps(func)  # 保留原函数的元信息
      def wrapper(*args, **kwargs):
          start = time.time()
          result = func(*args, **kwargs)
          print(f'{func.__name__} took {time.time()-start:.3f}s')
          return result
      return wrapper

  @timer
  def slow_func():
      time.sleep(1)
  ```

- functools.wraps的作用：保留被装饰函数的__name__、__doc__等属性
- 带参数的装饰器：再嵌套一层函数接收参数
- 类装饰器：实现__call__方法的类也可以作为装饰器
- 多个装饰器叠加时从下往上装饰，从上往下执行

## 元类

- 元类(metaclass)是创建类的类，类是元类的实例
- Python中一切皆对象，类本身也是对象，由type创建
- type是所有类的默认元类：`MyClass = type('MyClass', (base,), {'attr': val})`
- 自定义元类通过继承type并重写__new__或__init__实现

  ```python
  class SingletonMeta(type):
      _instances = {}
      def __call__(cls, *args, **kwargs):
          if cls not in cls._instances:
              cls._instances[cls] = super().__call__(*args, **kwargs)
          return cls._instances[cls]

  class Database(metaclass=SingletonMeta):
      pass

  db1 = Database()
  db2 = Database()
  print(db1 is db2)  # True
  ```

- 元类的执行时机：类定义时(import阶段)，而非实例化时
- 应用场景：ORM框架、API注册、单例模式、接口约束检查
- __init_subclass__(Python 3.6+)可替代简单的元类场景

## 元类实现ORM

- ORM(Object-Relational Mapping)将类映射到数据库表，属性映射到字段
- 元类在类定义时自动收集字段信息，生成表结构和SQL语句

  ```python
  class Field:
      def __init__(self, column_type):
          self.column_type = column_type

  class ModelMeta(type):
      def __new__(mcs, name, bases, attrs):
          if name == 'Model':
              return super().__new__(mcs, name, bases, attrs)
          fields = {k: v for k, v in attrs.items() if isinstance(v, Field)}
          attrs['_fields'] = fields
          attrs['_table'] = name.lower()
          return super().__new__(mcs, name, bases, attrs)

  class Model(metaclass=ModelMeta):
      def save(self):
          cols = ', '.join(self._fields.keys())
          vals = ', '.join([f"'{getattr(self, k)}'" for k in self._fields])
          print(f"INSERT INTO {self._table} ({cols}) VALUES ({vals})")
  ```

  ```python
  class User(Model):
      name = Field('varchar(50)')
      age = Field('int')

  u = User()
  u.name = 'Alice'
  u.age = 25
  u.save()  # INSERT INTO user (name, age) VALUES ('Alice', '25')
  ```

- Django ORM、SQLAlchemy等框架的核心原理就是基于元类/描述符实现字段收集和SQL生成

# web服务器

## Django

- Django是Python最流行的全栈Web框架，遵循MTV(Model-Template-View)架构
- 核心组件：ORM、模板引擎、URL路由、Admin后台、中间件、表单处理
- 项目创建与启动：

  ```python
  # django-admin startproject myproject
  # python manage.py startapp myapp
  # python manage.py runserver 0.0.0.0:8000
  ```

- URL路由配置(urls.py)：

  ```python
  from django.urls import path
  from . import views
  urlpatterns = [
      path('users/<int:pk>/', views.user_detail, name='user_detail'),
  ]
  ```

- 视图(views.py)处理请求并返回响应，支持FBV(函数视图)和CBV(类视图)
- Model定义数据模型，通过makemigrations/migrate同步数据库
- Django自带Admin后台、认证系统、CSRF防护、ORM等开箱即用功能
- 适合中大型Web应用，RESTful API可配合DRF(Django REST Framework)

## Flask

- [跳转：flask](./flask.md)

## WSGI

- WSGI(Web Server Gateway Interface)是Python Web服务器与Web应用之间的标准接口
- 定义了一个简单的调用约定：应用是一个可调用对象，接收environ和start_response
- WSGI应用最简实现：

  ```python
  def application(environ, start_response):
      status = '200 OK'
      headers = [('Content-Type', 'text/plain')]
      start_response(status, headers)
      return [b'Hello World']
  ```

- environ：包含HTTP请求信息的字典(PATH_INFO, REQUEST_METHOD等)
- start_response：用于发送HTTP状态码和响应头的回调函数
- WSGI服务器(如Gunicorn, uWSGI)负责解析HTTP请求并调用WSGI应用
- Flask/Django等框架本质上都是符合WSGI规范的application可调用对象
- 中间件(Middleware)：包装application，在请求/响应流程中添加处理逻辑
- 生产部署：Nginx(反向代理) + Gunicorn/uWSGI(WSGI服务器) + Flask/Django(应用)

# Python爬虫

- [跳转：python爬虫](./python爬虫.md)

# 界面开发

- [跳转：pyqt5](./pyqt5.md)

# 常用工具

- [跳转：常用工具](./lib.md)

# 参考资料

- [闭包，装饰器，functools.wraps](https://zhuanlan.zhihu.com/p/78500405)
- [本地：笔记随笔](./_PythonNote.md)

