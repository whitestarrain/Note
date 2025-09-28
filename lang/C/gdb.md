- 源码&汇编码
  - list（或 l） 列出源代码，接着上次的位置往下列，每次列 10 行
  - list 行号 列出从第几行开始的源代码
  - list 函数名 列出某个函数的源代码

- 运行
  - start 开始执行程序，停在 main 函数第一行语句前面等待命令
  - next（或 n） 执行下一行语句
  - step（或 s） 执行下一行语句，如果有函数调用则进入到函数中
  - finish 连续运行到当前函数返回为止，然后停下来等待命令
  - continue（或 c） 从当前位置开始连续运行程序，直到断点
  - quit（或 q） 退出 gdb 调试环境
  - run（或 r） 从头重新开始运行程序, restart and debug

- 变量
  - print（或 p） 打印表达式的值，通过表达式可以修改变量的值或者调用函数
  - set var 修改变量的值
  - display 变量名 跟踪查看某个变量，每次停下来都显示它的值

- 状态
  - where 当前执行位置
  - backtrace（或 bt） 查看各级函数调用及参数
  - frame（或 f） 帧编号 选择栈帧
  - info（或 i） locals 查看当前栈帧局部变量的值

- 跟踪
  - undisplay 跟踪显示号 取消跟踪显示

- 断点
  - break（或 b） 行号 在某一行设置断点
  - break 函数名 在某个函数开头设置断点
  - break ... if ... 设置条件断点
  - delete breakpoints 断点号 删除断点
  - disable breakpoints 断点号 禁用断点
  - enable 断点号 启用断点
  - info（或 i） breakpoints 查看当前设置了哪些断点

- 观察点：监视指定地址的变化
  - watch 设置观察点
  - info（或 i） watchpoints 查看当前设置了哪些观察点

- 二进制内容查看
  - x 从某个位置开始打印存储单元的内容，全部当成字节来看，而不区分哪个字节属于哪个变量
    - `x/{format}`
    - 文档：[x command](https://visualgdb.com/gdbreference/commands/x): 使用指定的格式显示给定地址的内存内容

- 汇编级别（来自后续章节）
  - `disassemble` 可以反汇编当前函数或者指定的函数，
    - 单独用 `disassemble` 命令是反汇编当前函数，
    - 如果 `disassemble` 命令后面跟函数名或地址则反汇编指定的函数。
  - `si` 命令可以一条指令一条指令地单步调试
  - `info registers` 可以显示所有寄存器的当前值。
    - 在 `gdb` 中表示寄存器名时前面要加个 `$`，
    - 例如 `p $esp` 可以打印 `esp` 寄存器的值，
    - 在上例中 `esp` 寄存器的值是 `0xbff1c3f4`，所以 `x/20 $esp` 命令查看内存中从 `0xbff1c3f4` 地址开始的 20 个 32 位数。
      - [x command](https://visualgdb.com/gdbreference/commands/x): 使用指定的格式显示给定地址的内存内容
      - 见gdb章节 `x/7b`


- tui
  - tui disable -- Disable TUI display mode.
  - tui enable -- Enable TUI display mode.
  - tui focus, fs, focus -- Set focus to named window or next/prev window.
  - tui layout, layout -- Change the layout of windows.
  - tui new-layout -- Create a new TUI layout.
  - tui refresh, refresh -- Refresh the terminal display.
  - tui reg -- TUI command to control the register window.
  - tui window -- Text User Interface window commands.

- 在执行程序时，操作系统为进程分配一块栈空间来保存函数栈帧，
  - **`esp` 寄存器总是指向栈顶**
    > 64位系统上，为`rsp`，见 《深入理解操作系统》p120
  - 在 x86 平台上这个栈是从高地址向低地址增长的，
  - 我们知道每次调用一个函数都要分配一个栈帧来保存参数和局部变量

