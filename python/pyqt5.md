# 介绍

- PyQt5 
  - 是Digia的一套Qt5应用框架与python的结合，同时支持2.x和3.x
  - Qt库由Riverbank Computing开发，是最强大的GUI库之一 ，官方网站：www.riverbankcomputing.co.uk/news。
  - PyQt5是由一系列Python模块组成。超过620个类，6000函数和方法。能在诸如Unix、Windows和Mac OS等主流操作系统上运行
  - PyQt5有两种证书，GPL和商业证书。

- PyQt5类分为很多模块，主要模块有：
  - QtCore 包含了核心的非GUI的功能。主要和时间、文件与文件夹、各种数据、流、URLs、mime类文件、进程与线程一起使用。
  - QtGui 包含了窗口系统、事件处理、2D图像、基本绘画、字体和文字类。
  - QtWidgets类包含了一系列创建桌面应用的UI元素
  - QtMultimedia包含了处理多媒体的内容和调用摄像头API的类。
  - QtBluetooth模块包含了查找和连接蓝牙的类。
  - QtNetwork包含了网络编程的类，这些工具能让TCP/IP和UDP开发变得更加方便和可靠
  - QtPositioning包含了定位的类，可以使用卫星、WiFi甚至文本
  - Engine包含了通过客户端进入和管理Qt Cloud的类
  - QtWebSockets包含了WebSocket协议的类
  - QtWebKit包含了一个基WebKit2的web浏览器
  - QtWebKitWidgets包含了基于QtWidgets的WebKit1的类
  - QtXml包含了处理xml的类，提供了SAX和DOM API的工具
  - QtSvg提供了显示SVG内容的类
    - Scalable Vector Graphics (SVG)是一种是一种基于可扩展标记语言（XML）
    - 用于描述二维矢量图形的图形格式（这句话来自于维基百科）。 
  - QtSql提供了处理数据库的工具。 
  - QtTest提供了测试PyQt5应用的工具。

# 组件

# pyqt5 事件机制

## 信号与槽

## 事件循环处理机制

> 基于widget的应用程序都是由事件event驱动的，像鼠标单击、按下某个按键、重回组件、最小化窗口等都会产生相应的事件。

- app启动代码

  ```python
  app = QApplication(sys.argv)
  form = QWidget()
  form.show()
  sys.exit(app.exec_())
  ```

  - 最后执行的app.exec_()开启了 **应用程序的事件处理循环** 。
  - 应用程序会对事件队列中 **排队的事件** 进行处理，还可以 **对相同事件进行合并处理** 。

## 事件类型与默认的事件处理函数

- QEvent
  - PyQt5中，事件是一种对象，事件的基类是抽象类QEvent。
  - QEvent有众多子类表示具体的事件，例如:
    - QKeyEvent表示按键事件
    - QMouseEvent表示鼠标事件
    - QPaintEvent表示窗体绘制事件。
- QEvent类定义了三个接口函数：
  - accept() 
    - 表示事件接收者接受此事件
    - 被接受的事件不会再继续向上传播给上层容器组件。
  - ignore() 表示事件接收者忽略此事件
    - 被忽略的事件会继续向上传播给上层容器组件。
  - type() 返回事件的类型
    - 事件类型是枚举，每一个枚举值都对应一个PyQt5的具体事件类型
    - 例如QMouseEvent的type枚举值为5
    - 不同事件类型对应的枚举值可以参考[官方文档](https://www.riverbankcomputing.com/static/Docs/PyQt5/api/qtcore/qevent.html#)。

- 当一个事件发生时
  - PyQt5会根据事件的具体类型用QEvent相应的子类创建一个事件对象
  - 然后 **传递给产生事件的对象(各种组件)的event()函数进行处理** 。

    ```python
    event(self,e)
    # event函数的参数e就是PyQt5调用event函数时传入的事件对象。
    ```
  - 事件会优先发送给触发事件对象(也就是组件)的event函数
  - 但是event函数默认是不做额外的具体处理，而是将事件转派给触发事件对象的各种事件的默认处理函数
  - 例如：
    - event函数会将type为QMouseEvent事件会转派给触发事件对象的mouseMoveEvent()函数
    - 会将type为QMouseButtonDblClick事件会转派给触发事件对象的mouseDoubleClickEvent()函数。
  - 每一个QWidget都定义了很多这样的默认事件处理函数，都会接受一个具体的event事件对象
  - 每一个具体的event对象除了实现基础QEvent对象的接口之外，还会提供很多其它的与事件相关的函数
    - 例如QMouseEvent对象还提供了返回鼠标位置的pos()/localPos()等函数。

- 自定义事件处理函数
  - 用户在继承QWidget或者其子类的自定义类型中可以重新实现这些默认的事件处理函数，从而实现一些需要的功能
  - 例如:
    - 某些组件没有clicked信号，那么就不能通过信号与槽的方式实现对鼠标单击的处理
    - 但是可以重新实现mousePressEvent()或mouseReleaseEvent()函数对鼠标事件进行处理。

## 信号与槽

### 什么是信号与槽

- 信号与槽机制
  > 用更通俗易懂的例子来说明，可以把信号与槽看做一种通知机制
  - 适用于每一个QObject实例，它是实现GUI编程中对象之间通信的重要方法
  - 信号与槽是相互匹配的关系
    - 一个信号可以绑定多个槽
    - 一个槽也可以监听多个信号
  - 当某一信号被触发时，与之绑定的槽的函数会自动执行
  - **这一机制类似于C/C++语言中的回调函数** 
    - 回调函数通过把需要调用的函数指针传递给调用函数，来对特定事件做出响应。

- 在PyQt5中，信号与槽机制具有如下特点：
  - 一个信号可以连接多个槽
  - 一个槽可以监听多个信号
  - 一个信号可以连接另一个信号
  - 信号参数可以是任何Python类型
  - 信号与槽的连接可以跨线程
  - 信号可以断开

### 定义信号与槽

- 使用pyqtsignal类来定义信号
  - 该信号只能在QObject的子类中进行定义，且必须在创建类时进行定义，不可以进行动态添加

  ```python
  # __init__方法
  class pyqtSignal:
      def __init__(self, *types, name: str = ..., \
      revision:int = 0, arguments = []) -> None: ...
  ```
  - *types可以接受多个Python基本数据类型，表示该信号的参数类型
  - name接受自定义的信号名，默认为使用该信号类的属性名

- 槽函数同样需要在QObject的子类中定义
  - 可以是任意函数
  - revision与arguments参数在连接QML文件时使用，本文暂时不做探讨
  - 定义后的signal将被自动添加到QObject的QMetaObject中。

  ```python
  class MyWidget(QWidget):  
      def setValue_NoParameters(self):   
          '''无参数的槽函数'''  
          pass  

      def setValue_OneParameter(self,nIndex):   
          '''带一个参数(整数)的槽函数'''  
          pass

  def setValue_OneParameter_String(self,szIndex):   
          '''带一个参数(字符串)的槽函数'''  
          pass 

      def setValue_TwoParameters(self,x,y):   
          '''带两个参数(整数,整数)的槽函数'''  
          pass  

      def setValue_TwoParameters_String(self,x,szY):   
          '''带两个参数(整数,字符串)槽函数'''  
          pass
  ```

- 示例:

  ```python
  from PyQt5.QtCore import *
  
  class SignalClass(QObject):
      # 定义一个notification信号，接受一个list作为参数，信号名为printprice
      notification = pyqtSignal(int,name = "printprice")
  
      # 定义一个槽函数
      def send_pushmsg(self, price):
          print("Current Price:", price)
  ```

### 连接，断开信号与槽

- 在确定信号与槽后，需要对信号与槽进行绑定，以对特定事件做出反应
  ```python
  QObject.signal.connect(slotFunction, type, bool: no_receiver_check)
  ```
  ```python
  # 扩展

  app = QApplication(sys.argv)   
  widget = MyWidget()   
  # 连接无参数的信号
  widget.Signal_NoParameters.connect(self.setValue_NoParameters )                                          
  
  # 连接带一个整数参数的信号
  widget.Signal_OneParameter.connect(self.setValue_OneParameter)                                         
  
  # 连接带一个整数参数，经过重载的信号
  widget.Signal_OneParameter_Overload[int].
      connect(self.setValue_OneParameter)                              
  
  # 连接带一个整数参数，经过重载的信号
  widget.Signal_OneParameter_Overload[str].
      connect(self.setValue_OneParameter_String )                     
  
  # 连接一个信号，它有两个整数参数
  widget.Signal_TwoParameters.connect(self.setValue_TwoParameters )                                        
  
  # 连接带两个参数(整数,整数)的重载版本的信号
  widget.Signal_TwoParameters_Overload[int,int].
      connect(self.setValue_TwoParameters )                      
  
  # 连接带两个参数(整数,字符串)的重载版本的信号
  widget.Signal_TwoParameters_Overload[int,str].
      connect(self.setValue_TwoParameters_String )              
  widget.show()  
  ```
  - type默认为自动连接方式
  - no_receiver_check为True时会忽视槽是否存在，强制发送信号；

- 解绑信号与槽

  ```python
  QObject.singal.disconnect([slotFunctions])
  ```
  - 可以同时解绑多个槽

### 信号发射和参数传递

- 语法
  ```python
  signal.emit(*args)
  ```

  ```python
  # 扩展

  # 发射无参数的信号
  self.Signal_NoParameters.emit() 
  # 发射带一个参数(整数)的信号
  self.Signal_OneParameter.emit(1) 
  # 发射带一个参数(整数)的重载版本的信号
  self.Signal_OneParameter_Overload.emit(1)
  # 发射带一个参数(字符串)的重载版本的信号
  self.Signal_OneParameter_Overload.emit("abc")
  # 发射带两个参数(整数,字符串)的信号
  self.Signal_TwoParameters.emit(1,"abc")
  # 发射带两个参数(整数,整数)的重载版本的信号
  self.Signal_TwoParameters_Overload.emit(1,2)
  # 发射带两个参数(整数,字符串)的重载版本的信号
  self.Signal_TwoParameters_Overload.emit (1,"abc") 
  ```
- 示例
  ```python
  # 示例
  from PyQt5.QtCore import *
    
  class SignalExample(QObject):
      # 定义一个notification信号，接受一个list作为参数，信号名为printprice
      notification = pyqtSignal(int,name = "printprice")
    
      # 定义一个槽函数
      def send_pushmsg(self, price):
          print("Current Price:", price)
    
  signal = SignalExample()
    
  # 将降价信号连接到推送消息函数上
  signal.notification.connect(self.send_pushmsg)
  # 此处也可以使用信号的别名
  # signal.printprice.connect(self.send_pushmsg)
    
  # 发射信号
  signal.notification.emit(18)
  Output: Current Price: 18
  ```
  ```python
  # 扩展
  from PyQt5.QtCore import QObject , pyqtSignal
  
  class CustSignal(QObject):
  
      #声明无参数的信号
      signal1 = pyqtSignal()
  
      #声明带一个int类型参数的信号
      signal2 = pyqtSignal(int)
  
      #声明带int和str类型参数的信号
      signal3 = pyqtSignal(int,str)
  
      #声明带一个列表类型参数的信号
      signal4 = pyqtSignal(list)
  
      #声明带一个字典类型参数的信号
      signal5 = pyqtSignal(dict)
  
      #声明一个多重载版本的信号，包括带int和str类型参数的信号和带str类型参数的信号
      signal6 = pyqtSignal([int,str], [str])
  
      def __init__(self,parent=None):
          super(CustSignal,self).__init__(parent)
  
          #将信号连接到指定槽函数
          self.signal1.connect(self.signalCall1)
          self.signal2.connect(self.signalCall2)
          self.signal3.connect(self.signalCall3)
          self.signal4.connect(self.signalCall4)
          self.signal5.connect(self.signalCall5)
          self.signal6[int,str].connect(self.signalCall6)
          self.signal6[str].connect(self.signalCall6OverLoad)
  
          #发射信号
          self.signal1.emit()
          self.signal2.emit(1)
          self.signal3.emit(1,"text")
          self.signal4.emit([1,2,3,4])
          self.signal5.emit({"name":"wangwu","age":"25"})
          self.signal6[int,str].emit(1,"text")
          self.signal6[str].emit("text")
  
      def signalCall1(self):
          print("signal1 emit")
  
      def signalCall2(self,val):
          print("signal2 emit,value:",val)
  
      def signalCall3(self,val,text):
          print("signal3 emit,value:",val,text)
  
      def signalCall4(self,val):
          print("signal4 emit,value:",val)
  
      def signalCall5(self,val):
          print("signal5 emit,value:",val)
  
      def signalCall6(self,val,text):
          print("signal6 emit,value:",val,text)
  
      def signalCall6OverLoad(self,val):
          print("signal6 overload emit,value:",val)
  
  if __name__ == '__main__':  
      custSignal = CustSignal()

      # 运行结果如下：
      # 
      # signal1 emit
      # signal2 emit,value: 1
      # signal3 emit,value: 1 text
      # signal4 emit,value: [1, 2, 3, 4]
      # signal5 emit,value: {'name': 'wangwu', 'age': '25'}
      # signal6 emit,value: 1 text
      # signal6 overload emit,value: text
  ```

### 使用lambda表达式与自定义参数

- 背景：
  - 对于clicked信号来说，它是没有参数的
  - 但对于槽函数来说，可能希望它可以接收参数

- 解决方案1：lambda表达式
  > 比较推荐，比较清晰灵活

  ```python
  from PyQt5.QtWidgets import QMainWindow, QPushButton , QWidget , QMessageBox, QApplication, QHBoxLayout
  import sys 

  class WinForm(QMainWindow):  
      def __init__(self, parent=None):  
          super(WinForm, self).__init__(parent)  
          button1 = QPushButton('Button 1')  
          button2 = QPushButton('Button 2')  

          button1.clicked.connect(lambda: self.onButtonClick(1)) 
          button2.clicked.connect(lambda: self.onButtonClick(2))

          layout = QHBoxLayout()  
          layout.addWidget(button1)  
          layout.addWidget(button2)  

          main_frame = QWidget()  
          main_frame.setLayout(layout)       
          self.setCentralWidget(main_frame)  

      def onButtonClick(self, n):  
          print('Button {0} 被按下了'.format(n))  
          QMessageBox.information(self, "信息提示框", 'Button {0} clicked'.format(n))

  if __name__ == "__main__":  
      app = QApplication(sys.argv)  
      form = WinForm()  
      form.show()  
      sys.exit(app.exec_())
  ```

- 解决方案2：使用functools中的partial函数

  ```python
  button1.clicked.connect(partial(self.onButtonClick, 1))           
  button2.clicked.connect(partial(self.onButtonClick, 2))
  ```

### 使用槽装饰器

- 槽装饰器`@pyqtSlot`
  - 说明
    - 通过装饰器的方法来定义信号和槽函数
  - 作用
    - 在一般情况下，它会轻微降低内存占用，并轻微提升运行速度
      > 由于在装饰器中直接声明了接收参数的类型，Python到C++接口的mapping会变得直接，而不需要程序去自动检测
    - 更为重要的一点是，槽装饰器允许对某一个槽进行重复的overload，并且声称不同的C++签名。
  - 使用方式：

    ```python
    @PyQt5.QtCore.pyqtSlot(参数)
    def on_发送者对象名称_发射信号名称(self, 参数):
            pass
    ```
    - **这种方法有效的前提是下面的函数已经执行** ：

      ```python
      QMetaObject.connectSlotsByName(QObject)
      ```

    - 在上面代码中，“发送者对象名称”就是使用setObjectName函数设置的名称
    - 因此自定义槽函数的命名规则也可以看成
      - on + 使用setObjectName设置的名称 + 信号名称
  - 使用示例

    ```python
    from PyQt5 import QtCore 
    from PyQt5.QtWidgets import QApplication  ,QWidget ,QHBoxLayout , QPushButton
    import sys    

    class CustWidget( QWidget ):

        def __init__(self, parent=None):
            super(CustWidget, self).__init__(parent)

            self.okButton = QPushButton("OK", self)
            #使用setObjectName设置对象名称
            self.okButton.setObjectName("okButton")
            layout = QHBoxLayout()
            layout.addWidget(self.okButton)
            self.setLayout(layout)
            QtCore.QMetaObject.connectSlotsByName(self)

        @QtCore.pyqtSlot()    
        def on_okButton_clicked(self):
            print( "单击了OK按钮")

    if __name__ == "__main__":        
        app =  QApplication(sys.argv)
        win = CustWidget()
        win.show()
        app.exec_()
    ```

- 实际应用：在跨线程操作时，尤其涉及到以lambda函数作为槽时
  - 需要注意PyQt会为lambda函数生成一个代理来满足信号/槽机制的需求
  - 并且在新版的PyQt内（4.4+），信号与槽的连接方式直到信号被emit前才会确定
  - 在连接lambda函数时，生成的代理会被相应地移动到接受对象所在的线程内：

    ```cpp
    if (receive_qobj)
        proxy->moveToThread(receive_qobj->thread());
    ```

  - 如果信号的接受对象(QObject)已经被移动到了正确的线程时，则不会产生问题，反之，可能会导致Proxy残留在主线程，导致无法正常工作
  - 使用@pyqtSlot装饰器可以避免生成代理，直接生成一个相应的签名，从而避免这一问题。

    ```python
    PyQt5.QtCore.pyqtSlot(*types, name, result, revision=0)
    ```
    - *types接受传入的参数类型
    - name可以对槽进行重命名
    - result指定返回值的数据类型
    - revision同样用于导出槽函数到QML文件中，本文不做讨论。

### 实例：多线程中的信号与槽

- 背景说明
  - 本例中，搭建前面提及到的价格监视器的最简易示例，使用两个线程
  - 主进程为前台的GUI界面，一个线程作为后台的价格监控
  - 主程序窗体上的价格将实时更新，当达到目标价格时将会进行提示
  - 为方便测试，我们将初始价格设置为1000，目标价格设置为900。

- 示例代码：
  ```python
  from PyQt5.QtCore import *
  from PyQt5.QtWidgets import *
  import sys
  import time
   
  # 主程序窗体
  class MainWindow(QWidget):
      def __init__(self, parent = None):
          super(MainWindow, self).__init__(parent)
          self.setWindowTitle("Price Monitor")
          self.resize(400, 200)
   
          # 创建价格文本框
          self.price = QLineEdit(self)
          self.price.setReadOnly(True)
          self.price.resize(400, 100)
          self.price.move(0, 20)
   
          # 创建退出按钮
          self.exitbutton = QPushButton("Exit",self)
          self.exitbutton.resize(self.exitbutton.sizeHint())
          self.exitbutton.move(135, 150)
   
          # 将退出按钮连接到QApplication的退出动作
          self.exitbutton.clicked.connect(QCoreApplication.instance().quit)
          self.loadUI()
   
      # 创建终止信号，用于控制终止后台监控线程
      terminal_sig = pyqtSignal()
   
      @pyqtSlot(str) # 更新价格信号槽，用于更新GUI的价格显示
      def update_price(self, price):
          self.price.setText(price)
   
      @pyqtSlot() # 通知信号槽，用于发出“达到目标价格”通知，并结束监控线程
      def notification(self):
          self.moniterThread.quit()
          self.price.setText(self.price.text() + "Reached Target Price!")
          # 向监控线程发出终止信号
          self.terminal_sig.emit()
   
      def loadUI(self):
          # 创建价格监控线程
          self.moniterThread = MonitorThread(1000, 900)
          # 连接监控线程的槽函数
          self.moniterThread.update_price.connect(self.update_price)
          self.moniterThread.notification.connect(self.notification)
          self.terminal_sig.connect(self.moniterThread.terminate)
          # 启动监控线程
          self.moniterThread.start()
   
  # 价格监控线程
  class MonitorThread(QThread):
      
      # 声明两个信号，更新价格信号与提醒信号
      update_price = pyqtSignal(str)
      notification = pyqtSignal()
   
      def __init__(self, initPrice, targetPrice):
          super().__init__()
          self.init_price = initPrice
          self.target_price = targetPrice
   
      def run(self):
          while True:
              # 为方便测试，价格每0.5秒减少10
              self.init_price -= 10
              # 向主进程发送通知，更新价格
              self.update_price.emit(str(self.init_price))
              if self.init_price == self.target_price:
                  # 当达到目标价格时，向主进程发送通知
                  self.notification.emit()
              time.sleep(0.5)
   
  if __name__ == "__main__":
      app = QApplication(sys.argv)
      monitor = MainWindow()
      monitor.show()
      sys.exit(app.exec_())

  ```

- 当达到目标价格时，效果如图：

  ![pyqt5-1](./image/pyqt5-1.png)

## 事件与信号的关系

- 事件与信号是有区别的，但是也有关联
  - Qt为某个界面组件定义的信号通常是对某个事件的封装
  - 在早期的GUI编程中使用的是回调机制，在Qt中则使用一种新机制——信号与槽
  - 例如QPushButton有clicked信号，就可以看做是对QPushButton的QMouseReleaseEvent事件的封装
  - 通过编写与信号关联的槽函数可以实现当信号发射时做的事情

  ![pyqt5-2](./image/pyqt5-2.png)

- 通过创建PyQt5.QtCore.pyqtSignal对象可以实现自定义信号
  - 调用自定义信号的emit函数就可以将信号发射出去，触发与信号关联的槽函数。

  ```python
  class QmyLabel(QLabel):
      doubleClicked = pyqtSignal()
      def mouseDoubleClickEvent(self,event):
          self.doubleClicked.emit()
  
  class QmyWidget(Qwidget):
      def __init__(self,parent=None):
          super().__init__(parent)
          self.resize(300,300)
          self.setWindowTitle('自定义信号')
          
          myLab = QmyLabel(self)
          myLab.setText('I am Label')
          font = myLab.font()
          font.setPointSize(14)
          font.setBold(True)
          myLab.setFont(font)
          size = myLab.sizeHint()
          myLab.setGeometry(70,60,size.width(),size.height())
          myLab.doubleClicked.connect(self.do_doubleClicked)
      
      def do_doubleClicked(self):
          print('Label is DoubleClicked!')
      
      def mouseDoubleClickEvent(self,event):
          print('Window is DoubleClicked')
  
  if __name__=='__main__':
      app = QApplication(sys.argv)
      form = QmyWidget()
      form.show()
      sys.exit(app.exec_())
  ```

  - QmyLabel自定义了一个doubleClicked信号
    - 当QmyLabel上发生了鼠标双击事件时，会将鼠标双击事件委派给QmyLabel的mouseDobleClickEvent函数处理
    - 处理的方式就是发射自定义信号doubleClicked。
  - QmyWidget
    - doubleClicked函数的槽函数是QmyWidget的do_doubleClicked函数
    - 该函数一旦检测到doubleClicked信号，槽函数就会执行，在控制台输出Label is DoubleClicked。

## 事件过滤

- 说明：
  - 通过使用PyQt5的事件过滤器（eventfilter）
  - 可以将一个对象上发生的事件委托给另一个对象来检测并处理。 
- 实现事件过滤功能需要完成以下两项操作：
  - 被监测对象使用installEvenFilter()函数将自己注册给监测对象。
  - 监测对象实现eventFilter()函数，对监测对象的事件进行处理。

  ```python
  import sys
  from PyQt5.QtWidgets import QApplication, QLabel,QWidget,QLabel
  from PyQt5.QtCore import Qt,QEvent

  class QmyWidget(QWidget):
      def __init__(self, parent=None) -> None:
          super().__init__(parent)
          self.resize(400,400)
          self.setWindowTitle('事件委托')
          self.laba = QLabel(self)
          self.laba.setText('I am Label A')
          font = self.laba.font()
          font.setPointSize(10)
          font.setBold(True)
          self.laba.setFont(font)
          self.laba.setGeometry(20,20,300,60)
          self.laba.installEventFilter(self)
          self.labb = QLabel(self)
          self.labb.setText('I am Label B')
          font = self.labb.font()
          font.setPointSize(10)
          font.setBold(True)
          self.labb.setFont(font)
          self.labb.setGeometry(20,100,300,60)
          self.labb.installEventFilter(self)
      def eventFilter(self, w, e) -> bool:
          if w == self.laba:
              if e.type()==QEvent.Enter:
                  self.laba.setText('鼠标来啦')
                  self.laba.setStyleSheet('background-color:rgb(170,255,255);')
              if e.type()==QEvent.Leave:
                  self.laba.setText('I am Label A')
                  self.laba.setStyleSheet('')  
          if w == self.labb:
              if e.type()==QEvent.Enter:
                  self.labb.setText('点我！')
                  self.labb.setStyleSheet('background-color:rgb(85,255,127);')
              if e.type()==QEvent.MouseButtonPress:
                  self.labb.setText('还真点啊！')
                  self.labb.setStyleSheet('background-color:rgb(85,255,127);')
              if e.type()==QEvent.MouseButtonRelease:
                  self.labb.setText('点我！')
                  self.labb.setStyleSheet('background-color:rgb(85,255,127);')
              if e.type()==QEvent.Leave:
                  self.labb.setText('I am Label B')
                  self.labb.setStyleSheet('')  
          return super().eventFilter(w, e)
  
  app = QApplication(sys.argv)
  form = QmyWidget()
  form.show()
  sys.exit(app.exec_())
  ```

  - QmyWidget充当窗口，里面有两个QLabel，laba和labb
  - 它们将事件处理全部委托给了QmyWidget
  - 这样，一旦有发生在laba和labb上的事件，QmyWidget的eventFilter函数就会被触发
  - 当然QmyWidget也并不会处理laba和labb的所有事件，所以会通过条件语句判定事件源和事件类型
  - 最后，也是最重要的，因为并不是对laba和labb的事件都做处理，所以 **一定要使用QWidget的eventFilter函数做善后处理** 。

# 参考资料

- [ ] [各种各样的PyQt测试和例子](https://github.com/PyQt5/PyQt)
- [ ] [PyQt中文教程](https://maicss.gitbook.io/pyqt-chinese-tutoral/)
- [ ] [15 个开源示例手把手带你用 PyQt 做小型桌面应用](https://github.com/pythonguis/15-minute-apps)
- [ ] [PyQt中MainWindow, QWidget以及Dialog的区别和选择](https://blog.csdn.net/Mengwei_Ren/article/details/71305885)
- [ ] [pyqt5 doc](https://www.riverbankcomputing.com/static/Docs/PyQt5/index.html)
- [x] [PyQt 5信号与槽的几种高级玩法](https://www.cnblogs.com/lsgxeva/p/12636756.html)
- [x] [PyQt5 – 信号与槽机制详解](https://nmgit.net/2020/94/)
