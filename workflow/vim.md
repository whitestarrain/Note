> 历史vim学习笔记记录，当前[个人dotfiles](https://github.com/whitestarrain/dotfiles)试用的插件和配置与下方记录可能有较大不同

# 1. 基础操作

## 1.1. 查看相关信息

- 版本：`:version`
    > 同时也可以查看支持

- 查看功能键映射字符`:h key-notation`
- 查看keymap设置代码：`verbose map s`
  > 查看为s设置map的代码位置
- `:scriptnames`输出`source`或者`runtime`过的所有脚本
- `gQ`:进入Ex模式

## 1.2. 移动

- hjkl:移动
- w:下一个单词开头，e：下一个单词末尾。W下一个空格开头，E下一个空格末尾
- % 括号匹配移动
- gg移动到开头，G移动到末尾，数字+gg移动到指定行
- \$ 到行末;0到行首;^ 到第一个不是空格的位置(可以使用0w代替)
- M(middle)移动到屏幕中间位置。H(Head)移动到屏幕顶部位置。L(Lower)移动到屏幕底部位置
- zz将编辑行移动到屏幕中间,zt将编辑行移动到屏幕顶部，zb将编辑行移动到屏幕底部
- t, 到逗号前的一个字符，逗号可以变成其他字符。T同理，反方向。然后通过分号;跳转到下一个，逗号，跳转到上一个(until)
- f,到达第一个逗号的位置，逗号可以变成其他字符。F同理，反方向。然后通过分号;跳转到下一个，逗号，跳转到上一个(find)
- C-i C-o新位置，旧位置
- -(不按shift)到上一行第一个非空格字符处。+(按着shift)到下一行第一个非空字符处
- C-b,C-f(forword)上下翻页
- C-u(upword),C-d 半页
- gi跳转到最后一编辑位置并进入插入模式
- g_到最后一个不是空格的位置,可以代替\$,
- ()在句子间移动。{}在段落间移动。（不用记，之后有easy-motion插件）
  > 在可视行模式，选择一块时挺好用的
      - ma 在当前位置做标记，通过'a可以回来
- L:最后一行行首
- c-y: 向上滚动屏幕
- c-e: 向下滚动屏幕
- `;`: 重复搜索 `f`, `t`, `F`, `T` 时搜索的字符

## 1.3. 搜索

> `help pattern`

- /pattern 搜索，n往下搜索，N往上搜索。?pattern倒序搜索
- /\cpattern 搜索，同时忽略大小写
- ?pattern 向上搜索
- :/pattern 定位到查找字符所在行的第一个非空白字符
- `* #` 查询匹配当前光标所在单词，前后移到查到的单词
- magic 模式
  - 说明： 不同模式之间的区别，在于哪些特殊字符需要使用反斜杠（\）进行转义。例如星号（*），在magic和very magic模式下视为特殊修饰符；而在no magic和very nomagic模式下则被视为普通字符，必须使用“\*”恢复其特殊作用。
  - pattern：
    - magic模式，使用`\m`前缀，其后模式的解释方式为'magic'选项。^，`$`，.，*和[]等字符含有特殊意义；而+、?、()、和{}等其它字符则按字面意义解释。magic为默认设置，表达式中的\m前缀可以省略；
    - no magic模式，使用`\M`前缀，其后模式的解释方式为'nomagic'选项。除了^和`$`之外的特殊字符，都将被视为普通文本；
    - very magic模式，使用`\v`前缀，其后模式中除 '0'-'9'，'a'-'z'，'A'-'Z' 和 '_' 之外的字符都当作特殊字符解释；
      > very magic 和日常正则比较相似
    - very nomagic模式，使用`\V`前缀，其后模式中只有反斜杠（\）具有特殊意义。
- `.*` 非贪婪模式：
  - magic: `.\{-}`
  - very magic: `.{-}`

## 1.4. 可视化

- 选择模式
  - v
  - V
  - C-v
- 可视化命令
  - o	切换高亮选区的活动端，
  - gv 重选上一次的高亮选区
- `w! >> file` 将选中内容写入到一个文件中

## 1.5. 编辑

- vim +10 file 打开一个文件并定位到某行
- vim + file 打开文件并定位到最后
- vim +/aaaaa 打开文件并定位到搜索到的aaaaa的第一个的位置
- i,a,I,A插入。A可以代替\$,I可以代替^
- o 在光标后插入一行，O在光标前插入一行
- x删除一个，X往左删除
- c删除后面指定范围并切到插入模式，cc从开头一直删除到行末并进入插入模式，C一直删到行末并切到插入模式(change)
- s删除一个并切换到插入模式(xi)，S删除一行并切换到插入模式(和cc功能相同)(substitute)
- d删除后面指定范围，D一直删除到行末
  > 比如dt,一直删除到逗号那里,逗号不会删
- daw(delete around word)删除当前光标所在单词,同理还有caw。详情请看后面区域选择及其原理
- r替换一个，R不断切换后面字符
- u回退，U回退一行的操作;C-r取消回退，C-R取消回退一行
- dd删除一行
- J把后面一行和移到当前所在行
- = 自动给缩进，相当于格式化
- yy 复制一行，p粘贴
- 0y\$ 从行开头复制到行末尾；y2/help复制查到的两个help之间的内容
- gU选中内容变大写,gu选中内容变小写
- `<M-o>`: 在下方新加一行，相当于`<Esc>o`

## 1.6. 插入模式下的编辑操作：

- 仅终端使用：
  > 终端中编辑使用
  - C-a快速移动到开头
  - C-e快速移动到结尾
  - C-b前移
  - C-f后移

- 删除操作
  - `ctrl-w`删除一个单词
  - `ctrl-h`删除至航首
  - `ctrl-h`相当于退格
- 调整缩进
  - `ctrl-t`增加缩进
  - `ctrl-d`减少缩进
- 复制
  - `ctrl-y`复制上一行相同列的字符
  - `ctrl-e`复制下一行相同列的字符
- indent
  - `ctrl-f`，插入模式调整indent，输入太多空格时可以使用
    - `:help i_CTRL-F`
  - `ctrl-t` `ctrl-d` 调整 indent
- 粘贴寄存器内容
  - `ctrl-r` + `寄存器名称`:插入指定寄存器中的内容，会自动调整缩进
  - `ctrl-r ctrl-p` + `寄存器名称`: 插入指定寄存器中的内容，按原义插入文本，并不会修正不必要的缩进
- 使用表达式寄存器
- 输入非常用字符
- 自动补全
- 替换模式相关

## 1.7. 替换

> 本质上就是 substitute这个命令
- 格式[range]s/pattern/string/[flags]
  - 临近s的第一个字符为边界符号
  - / 也可以换成 **#或:或@等** 在替换时如果包含/可以用别的
- 注意：默认支持正则表达式
  - 为非空行添加双引号  `:%s/^\(\S+\)$/"\1"/g`
  - 如果只需要纯文本替换，可以使用: `:%sno/text1/text2/g`
- 范围：
  - n:行号
  - .:当前光标行
  - +n 偏移n行
    > .,+4d  删除从当前行开始四行的内容
  - $ 末尾航 $-3倒数第四行
  - % 全文
- 选项：
  - g:一行所有
  - i:忽略大小写
  - c:进行确认
- 例：
  - 在一行内替换 **头一个** 字符串 old 为新的字符串 new，请输入  :s/old/new
  - 忽略大小写进行查找替换      :%s/old/new/i
  - 在一行内替换所有的字符串 old 为新的字符串 new，请输入  :s/old/new/g
  - 在1,6行内替换所有的字符串 old 为新的字符串 new，请输入  :1,6s/old/new/g
  - 在文件内替换所有的字符串 old 为新的字符串 new，请输入  :%s/old/new/g
  - 进行全文替换时询问用户确认每个替换需添加 c 标志        :%s/old/new/gc
- 选项：
  - y - yes 替换
  - n - no 不替换
  - a - all 替换所有
  - q - quit 退出替换
  - l - last 最后一个，并把光标移动到行首

## 1.8. 折叠展开

- za:折叠展开切换
- zM:折叠所有
- zR:展开所有

## 1.9. 多文件操作

- :Vexplore 纵向展示目录树（）
- :e path/file 打开指定文件
- 缓冲区常用命令：每打开一个文件就是一个缓冲区，用来存储文件修改
  - :ls 列举缓冲区。
  - :b2 跳转到第2个缓冲区。:bpre :bnext :bfirst :blast 也可以进行跳转
  - :bf 跳转到第一个缓冲区
  - :bl 跳转到最后一个缓冲区
  - :b name 进行tab补全，跳转到缓冲区
  - :bd2 删除编号为2的缓冲区
- 缓冲区状态:
  > ls 所看到的
  - u     列表外缓冲区 | unlisted-buffer|。
  - %      当前缓冲区。
  - \#      轮换缓冲区。
  - a     激活缓冲区，缓冲区被加载且显示。
  - h     隐藏缓冲区，缓冲区被加载但不显示。
  - =    只读缓冲区。
  - \-    不可改缓冲区， 'modifiable' 选项不置位。
  - \+   已修改缓冲区。
- 窗口：一个缓冲区可以分割为多个窗口,一个窗口也可以打开不同的缓冲区
  - :sp横向增加分屏(或者C-w s)，:vsp纵向增加分屏(或者C-w v)。后面也可以跟路径和文件名，用来指定分屏窗口打开哪个文件
  - C-w w切换到下一个窗口，C-w r互换窗口，C-w c 关闭当前窗口，C-w o 关闭其他窗口
  - C-w hjkl 左下上右选择窗口。C-w w窗口间循环切换
  - C-w HJKL 将当前窗口移动到左下上右侧
  - `C-w <> 左右移动分割线`
  - `C-w -+ 上下移动分割线`
  - `C-w =平分空间`
  - `C-w _最大化活动窗口高度`
  - `C-w |最大化互动窗口宽度`
- tab:容纳一系列窗口的容器。类似工作区。用起来比较少
  - 这里不多说了，看后面的插件

## 1.10. 窗口跳转，移动

- `CTRL-W w` 在各个窗口之间来回切换。每输入一次，切换一个窗口。不停输入，可以遍历所有窗口。在只有两个窗口时很方便来回切换。
- `CTRL-W j` 光标切换到下一个窗口。
- `CTRL-W k` 光标切换到上一个窗口。
- `CTRL-W h` 光标切换到左边窗口。如果左边没有窗口，保持在当前窗口不变。
- `CTRL-W l` 光标切换到右边窗口。
- `CTRL-w r` 上下或左右交换窗口
- `CTRL-w x` 交换同列或同行的窗口的位置
  - vim默认交换当前窗口的与它下一个窗口的位置
  - 如果下方没有窗口，则试着与上一个窗口交换位置
  - 亦可在此命令前加上数量，与制定的窗口交换位置。

## 1.11. 区域选择和文本对象

- 区域选择 `<action>a<object>` 或 `<action>i<object>`
	> action可以是任何的命令，如 d (删除), y (拷贝), v (可以视模式选择)。c(修改)，s(替代)<br>
	> object 可能是： w 一个单词， W 一个以空格为分隔的单词， s 一个句字， p 一个段落。也可以是一个特别的字符："、 '、 )、 }、 ]。<br>
	> i 表示不会选中分界，a表示会选中分界，t表示直到，而不会选择前面的
	- 假设你有一个字符串 (map (+) ("foo")).而光标键在第一个 o 的位置。
	- vi" → 会选择 foo.
	- va" → 会选择 "foo".
	- vt" → 会选择 oo
	- vi) → 会选择 "foo".
	- va) → 会选择("foo").
	- v2i) → 会选择 map (+) ("foo")
	- v2a) → 会选择 (map (+) ("foo"))
	- v2t) → 会选择 oo")

> 推荐使用文本对象来提高编辑速度，而不是单纯的退格键※

- 文本对象（区域选择命令原理解析）
	- `[number]<command>[text object]`
		- number 表示次数
		- command表示命令 d c y v
		- text object 表示要操作的文本对象 如单词w，句子s，段落p
	- 区域选择文本对象示例：
		- iw 表示inner word ，会选中当前单词(也就是说 i 不会包含边界)
		- aw 表示 a word，不但会选中当前单词，也会包含单词后的空格(也就是说 a 会包含边界)
		- a" 表示双引号里面的内容，包含双引号 - i" 表示双引号里面的内容，不好寒双引号
		- 2a" 表示选择两层双引号，包括双引号里面的内容
	- 完整示例:
		- daw 删除一个单词
		- das 删除一个句子
		- dap 删除一个段落

## 1.12. 复制粘贴，寄存器

- 简单命令
  - 普通模式下：
    - y (yank) 复制
    - p(put)粘贴
    - yy 复制一行 yiw复制一个单词
  - 插入模式下
    - 可以通过C-v粘贴剪切板内容
    - C-r 寄存器名  粘贴指定寄存器内容
    - 复制还是到普通模式下吧
- 剪切版复制代码缩进混乱解决办法：
  - 方式1 平时不推荐,往服务器上复制时使用
    - :set autoindent 设置自动缩进
    - :set paste 这样就能复制代码了，但会导致自动缩进失效
    - 复制代码
    - :set nopaste 恢复自动缩进
  - 方式2 通过寄存器 推荐
    - normal模式下输入 "+p
    - 注意：服务器上的vim的话，该方式就无法使用了，就要使用方式1
- 每次删除或者赋值的内容默认都会放到无名寄存器中
- 可以通过`"名称`指定寄存器。
  - 寄存器：
    - 无名寄存器是默认的。非要指定名称的话就是`""`
    - 常用名称是 a-z
    - 复制时除了会存到无名寄存器中外，也会存到`"0`寄存器
    - 系统剪切板 `"+`寄存器。可以复制到系统剪切板，或者从系统剪切板粘贴
      > echo has('clipboard')查看是否支持该特性。1的话就支持
    - `"%`存储当前文件名
    - `".`存储上次插入模式下插入的文本
    - set clipboard=unnamed 把系统剪切板作为默认的无名寄存器(注意，不要加多余空格)
    - `:registers`查看所有寄存器中的内容
  - 示例
    - "ayiw 把一个单词存储到a寄存器中
    - "byy 把当前所在行复制到b寄存器中
    - "ap 把寄存器a中的内容赋值
- 宏
  - 说明：宏可以看成一系列命令的集合，可以将操作进行录制后，再进行回放
  - 基础操作：
    - q开始录制宏，q结束录制宏
    - qa 将录制内容存储到寄存器a中
    - @a 调用a寄存器中的宏
  - 调用宏
    - V 进行选择要调用的内容
    - 输入 `:normal @a` (表示再normal模式下每行执行a寄存器里的宏)
      - 注意，也可以再normal后直接输入命令操作，@a本身就是调用存储在寄存器中的命令
- 补全（这里只说主要用的，不再系统讲）：
  - C-n,C-p 补全单词
  - C-x C-f **补全文件名**。
  - C-x C-o 代码补全，需要开启文档类型检查，安装插件
    - filetype on 开启文档类型检查。 set filetype 查看文档类型
    - 插件设置看后面

## 1.13. 命令

- . 重复上次命令
- :help <command> 查看帮助
- :wq 存储退出(:x,ZZ同样效果)
- :q! 不保存退出
- :w! 强行保存
- :qa 退出所有窗口
- :saveas path/file 另存为
- :命令组合 (\$在插入模式表示行末，在末行模式表示最后一行)
  > `:4,$-1d`    删除4到倒数第二行,包含第四行和倒数第二行
- :r file 将file中的内容放在当前编辑文件中
  - 文件相关
      - :new 文件名.后缀    新建文件。
      - :e 文件名    打开文件。
      - :w 文件名.txt 　保存文件。
      - :wq    保存并退出。
      - :x    退出，如果文件更改则保存。
- :g 全局命令
- :st 最小化窗口

## 1.14. 更换主题

- :colorscheme 显示当前配色方案，默认是default
- :colorscheme C-d 可以显示所有配色方案
- 可以去 https://github.com/flazz/vim-colorschemes 安装其他配色方案
- 持久化的话就写到 vimrc中

## 1.15. 其他

- ^E 向下滚屏
- ^Y 向上滚屏
- gf 查看光标所在字符串对应文件
- gd 查看定义位置
- C-p C-n 代码提示上下浏览
- dh 相当于将鼠标移动到那里（vscode专属）
- C-w h 光标移动到左侧目录树（vscode专属）
- q: 显示命令历史
- ssh-keygen -p 重新设置ssh的密码
- Q: 进入Ex模式

## 1.16. 代码跳转操作

- %
  - 跳转到光标所在括号的另一个配对括号上，适用于小括号()、大括号{}、方括号[]。
  - 例如当前光标在左大括号 { 上，输入 % 命令，光标会跳转到配对的右大括号 } 上。
  - 这个命令也适用于C语言的条件编译宏，可以在配对的 #if、#ifdef、#else、#elif、 #endif 之间快速跳转光标。
- 方法跳转 (`[  ]`与`m M`的组合)
  - [m
    > [ 一定往左走
    - 跳转到当前光标往上的最近一个函数开头，停在左大括号上。
    - 如果光标在函数内，就是跳转到当前函数的开头左大括号。
    - 如果光标在函数外，则跳转到上面最近一个函数的开头左大括号。
  - [M
    > [ 一定往左走
    - 跳转到当前光标往上的最近一个函数结尾，停在右大括号上。
    - 无论当前光标在函数内、还是函数外，都是跳转到上面最近一个函数的末尾右大括号。
  - ]m
    > ] 一定往右走
    - 跳转到当前光标往下的最近一个函数开头，停在左大括号上。
    - 无论当前光标在函数内、还是函数外，都是跳转到下面最近一个函数的开头左大括号。
  - ]M
    > ] 一定往右走
    - 跳转到当前光标往下的最近一个函数结尾，停在右大括号上。
    - 如果光标在函数内，就是跳转到当前函数的末尾右大括号。
    - 如果光标在函数外，则跳转到下面最近一个函数的末尾右大括号。
- 括号跳转(`[  ]`与`{  } (  )` 的组合)
  - [{
    - 跳转到当前光标往上最近一个没有匹配的左大括号，停在左大括号上。
    - 例如光标在 if 语句内，则跳转到 if 语句开头左大括号。
  - ]}
    - 跳转到当前光标往下最近一个没有匹配的右大括号，停在右大括号上。
    - 例如光标在 if 语句内，则跳转到 if 语句末尾右大括号。
  - [(
    - 跳转到当前光标前面最近一个没有匹配的左小括号，停在左小括号上。
    - 例如光标在一个小括号 () 内，则跳转到左边最近的左小括号。
  - ])
    - 跳转到当前光标后面最近一个没有匹配的右小括号，停在右小括号上。
    - 例如光标在一个小括号 () 内，则跳转到右边最近的右小括号。

- 这些命令都可以在前面加上数字,表示跳转级数. 例如:
  - 3[m
    - 将跳转到当前光标往上的第三个函数开始处，停在左大括号上。
    - 如果光标在函数内，则当前函数是第一个函数，会再往上跳两个函数。
  - 3[{
    - 将跳转到当前光标往上的第三级大括号开始处，停在左大括号上。

- 在文件内跳转到变量定义处(`ctrl+]`)
  - gd
    - 在当前函数内查找当前光标下的单词，如果找到，就跳转到该单词在函数内第一次出现的地方。
    - 对局部变量来说，也就是跳转到变量定义处。
    - 如果光标下的单词对应全局变量，这个命令不会跳转到全局变量定义处，而是会跳转到函数内第一次用到该变量的地方。
    - 这个命令本身没有进行语法解析，只是简单的执行字符串搜索并跳转。在实际使用时可用来查找任意单词，不限于查找变量。
  - gD
    - 在当前文件内查找当前光标下的单词，如果找到，就跳转到该单词在文件内第一次出现的地方。
    - 对全局变量来说，也就是跳转到全局变量定义处。
    - 对局部变量来说，也能跳转到局部变量定义处。
    - 如果变量是在文件外定义，就只能跳转到当前文件内第一次用到该变量的地方。
    - 实际测试，gd 命令只能在函数内搜索，不会跳转到全局变量定义处。gD 命令是在整个文件内搜索，可以跳转到全局变量定义处，也能跳转到函数内的局部变量定义处。
    - 如果不确认要搜索的单词是全局变量，还是局部变量，统一用 gD 命令即可。如果变量是在当前文件内定义，gD 命令一定能跳转到文件内的变量定义处，比 gd 命令要方便。

- terminal: vim支持terminal模式
  - 打开：
    - `term cmd`,`term poewrshell`等
    - 或者：`split term://cmd`
  - `ctrl-\ ctrl-n`，可以切换至普通模式，在其他模式上也可以
  - `:startinsert` 或者`a`可以进入命令输入模式
  - 详细可以看`help terminal`

- help
  - 可以使用 :help [( 命令查看 [( 的说明。% 的说明在 [( 的上面。
  - 可以使用 :help [m 命令查看 [m 的说明。
  - 其他命令的说明可以类似查看。
  - 可以用 :help gd 和 :help gD 命令来查看帮助说明，这两个命令的帮助说明是相邻的。

# 2. 模式说明

## 2.1. 插入模式 (Insert Mode)

- 通过 `i`, `a`, `o`, `O`, `I`, `A` 等进入
- 在此模式下可以输入文本，大部分按键直接插入字符
- `<Esc>` 或 `<C-[>` 退出插入模式回到普通模式
- `<C-o>` 临时执行一个普通模式命令后自动返回插入模式
- `:help Insert-mode` 查看完整文档

## 2.2. 替换模式(Replace Mode)

- 通过普通模式按 `R` 进入替换模式
- 输入的字符会替换光标下的字符，而不是插入
- `<Backspace>` 可以恢复被替换的原始字符
- 虚拟替换模式 `gR`：按屏幕显示宽度替换，对 Tab 等特殊字符更友好
- `r` 单字符替换不进入替换模式，替换一个字符后自动回到普通模式

## 2.3. 可视化模式 (Visual Mode)

- `v` 字符可视模式：按字符选择
- `V` 行可视模式：按行选择
- `<C-v>` 块可视模式：按矩形块选择
- 选中后可以使用操作符命令（`d`, `y`, `c`, `>`, `<` 等）对选中区域操作
- `gv` 重新选择上一次的可视区域
- `o` 切换选区活动端（光标跳到选区另一端）

## 2.4. 选择模式 (Select Mode)

- 类似于 Windows 下的选择行为，输入任意可打印字符会替换选中内容
- 通过 `gh`（字符选择）、`gH`（行选择）、`g<C-h>`（块选择）进入
- 也可以在可视模式下按 `<C-g>` 切换到选择模式
- 主要用于某些插件或 IDE 兼容场景
- `:help Select-mode` 查看详情

## 2.5. 命令行模式 (Command-line Mode)

- 通过 `:`, `/`, `?`, `!` 进入命令行模式
- `:` 执行 Ex 命令，`/` 和 `?` 用于搜索，`!` 用于过滤
- `<C-r>` + 寄存器名：在命令行中插入寄存器内容
- `<C-f>` 打开命令行窗口，可用普通模式编辑命令
- 支持 Tab 补全、历史浏览（上下箭头或 `<C-p>`, `<C-n>`）
- `q:` 打开命令历史窗口，`q/` 打开搜索历史窗口

## 2.6. Ex模式 (Ex Mode)

- 通过 `Q` 或 `gQ` 进入 Ex 模式
- 类似于命令行模式，但执行完命令后不会自动退出，而是继续等待下一个命令
- 适合批量执行多条 Ex 命令的场景
- `visual` 命令退出 Ex 模式回到普通模式
- `gQ` 进入的 Ex 模式比 `Q` 更接近 vi 兼容模式

# 3. 进阶

## 3.1. 批量操作

### 3.1.1. quickfix 和 location

`help quickfix`

- `vimgrep /content/ *.* | copen`
  > help: `grep` `lgrep` `vimgrep`

  ```
  vim[grep][!] /{pattern}/[g][j] {file} ...
  vim[grep][!] {pattern} {file} ...
  ```

  - vim 可作为 vimgrep 的缩写
  - ! 可紧随 vimgrep 之后，表示强制执行该命令
  - 索引的关键字 pattern 放在了两个 “/” 中间，并且支持正则表达式
  - g, j 可选。 如果添加 g，将显示重复行， 如果添加 j，vim 将不会自动跳转到第一个匹配的行（可能是别的文件）
  - file 可以是正则文件名，也可以是多个确定的文件名

  ```
  例：
    1. 只搜索当前文件 vimgrep /main/ % | copen
    2. 只搜索当前目录 vimgrep /main/ * | copen
    3.  搜索上级目录下，并递归 vimgrep /main/ ../** | copen
    4. 可以在多个路径中搜索  vimgrep // path1/** path2/** | copen
    5. 所有指定类型文件中搜索 vimgrep // ./**/*.md | copen
  ```

- cdo, cfdo, ldo

  ```vim
  :grep foo "%:p"
  :copen
  :cdo lua print(vim.inspect(vim.api.nvim_win_get_cursor(0)))
  :cdo lua print(vim.fn.expand("cfile"))
  :cdo execute "normal! f]D"
  ```

### 3.1.2. 参数列表(arglist)

- `args` 指定参数列表内容
  - `args: temp.html`
  - `args: *.md`
  - `args: **/*.js **/*.css`
- `argdo`: 对arglist中的每个每个buffer进行操作
  - `:argdo normal @a`

### 3.1.3. bufdo, windo, tabdo

- `:bufdo` 对所有缓冲区执行命令
  - `:bufdo %s/foo/bar/ge | update` 对所有缓冲区进行替换并保存
  - `:bufdo normal @a` 对所有缓冲区执行宏
- `:windo` 对当前 tab 所有可见窗口执行命令
  - `:windo diffthis` 对所有窗口开启 diff 模式
  - `:windo set wrap` 对所有窗口设置自动换行
- `:tabdo` 对所有标签页执行命令
  - `:tabdo windo set number` 对所有标签页的所有窗口显示行号
- 注意：配合 `| update` 可以自动保存修改，避免切换 buffer 时提示未保存

## 3.2. 全局命令(Global Command)

- `:g` 全局命令, `help :g`

  ```vim
  " 删除符合pattern的
  :g/pattern/d
  " 删除不符合pattern的
  :g!/pattern/d
  :g/^$/d "删除所有空行

  " 全局替换
  :g/microsoft antitrust/s/judgment/ripoff/

  " 全局移动：
  " 将所有的行按相反的顺序排列。其中，查找模式.*将匹配所有行，m0命令将每一行移动到0行之后：
  :g/.*/m0
  "将指定标记（Mark）之间的行按相反的顺序排列：
  :'a,'bg/^/m'b
  " 将文本中的“DESCRIPTION”部分，上移到“SYNTAX”之前：
  :g /SYNTAX/.,/DESCRIPTION/-1 move /PARAMETERS/-1

  " 全局复制
  " 使用以下命令，可以重复每一行。其中:t或:copy为复制命令：
  :g/^/t.
  " 将包含模式pattern的行，复制到文件末尾：
  :g/pattern/t$
  "重复每一行，并以“print ''”包围：
  :g/./yank|put|-1s/'/"/g|s/.*/Print '&'/
  ```

## 3.3. 过滤器(filter)

- 调用外部命令(`help filter`, `help w_c`)
  - 调用命令并replace
    - `:%!sort -k2` 整个文件的内容作为stdin，调用sort程序，参数为`-k2`，输出到buffer
    - `:'<,'>!awk '{print $1}'` 选定内容作为stdin，调用awk程序，输出到buffer
    - `:'<,'>!sh` 执行选中的命令，输出到buffer

      ```bash
      # 多行命令也支持，多行命令+多行stdin的可以用这种 here doc 的形式
      tr [:lower:] [:upper:] \
      <<_exit
        sdfsdf
        sdfsdsdf
        sdfsdd
      _exit
      # :%!sh
      ```
  - 调用命令并输出到message
    - `:w !ls` 执行指定命令，并写入到message
    - `:'<,'>w !sh` 执行选中的命令(将选中的数据作为stdin传给sh)，输出到message
  - 调用命令并追加
    > :read 不支持读取buffer作为stdin
    - `:r !ls` 执行指定命令，并写入到buffer。

- 二进制编辑
  - `:%!xxd` 转换为hex dump
  - `:%!xxd -r` hex dump 转换为二进制

## 3.4. 编码处理

- set encoding	设置Vim的内部编码方式
- set termencoding	设置Vim的屏幕显示编码
- set fileencoding	设置文件的编码方式
- set fileencodings	设置Vim的解码列表

指定编码打开文件:

```vim
" 打开新文件
:e ++encoding=gbk file_name
" 已其他编码加载当前文件
:e ++encoding=gbk
```

- excel 打开 csv 中文乱码

  ```vim
  " help encoding-names
  set fileencoding=prc
  set fileencoding=cp936
  ```

## 3.5. 自动补全

`help popmenu-keys`

- 关键字补全：`<C-N>`, `<C-p>`
- ^X模式（Ctrl-X Mode） 补全
  - Ctrl-]: 标签(tags)补全
  - Ctrl-D: 定义补全
  - Ctrl-E: 向上滚动文本
  - Ctrl-F: 文件名补全
  - Ctrl-I: 当前文件以及包含进来的文件补全
  - Ctrl-K: 字典补全
  - Ctrl-L: 整行补全
  - Ctrl-N: 当前文件内的关键字补全，向下选择匹配项
  - Ctrl-O: 全能补全
  - Ctrl-P: 当前文件内的关键字补全，向上选择匹配项
  - Ctrl-S: 拼写建议补全
  - Ctrl-U: 用户自定义补全
  - Ctrl-V: Vim命令补全
  - Ctrl-Y: 向下滚动文本


# 4. 命令 tips

- `<c-a>` `<c-x>`:数字加一减一
- `g<c-a>`编号，`sort`排序
  > help v_g_CTRL-A 查看帮助
  ```
  原始：
    aaa
    aaa
    aaa

  添加初始编号
    0aaa
    0aaa
    0aaa

  g<c-a>
    1aaa
    2aaa
    3aaa

  sort sort默认使用字典序，数字排序的话，使用 sort n
  ```
- `<c-a>`:increase number
- `:v/./.,/./-1join` 压缩空行(多行空行合并为一行)
- `:g/^$/,/./-j`  压缩空行(多行空行合并为一行)
- `echo expand("%:p")`: 查看当前文件路径
- visual模式下`g+<c-g>`:查看选中字符个数
- `ctrl-a` 数字加一。`ctrl-x` 数字减一
- 将输出写出到文件

  ```
  :redir > highlight.txt
  :highlight
  :redir END
  ```
- 输出所有highlight到buffer: `:runtime syntax/hitest.vim`
- 检查语法高亮花费的时间

  ```vim
  syntime on
  syntime report
  ```
- 正则替换

  ```
  将 `## 标题 ##` 替换为 `## 标题`

  %s/\(#\+\)\(.\{-}\) \1/\1\2/g

  .\{-} 是 .* 的非贪婪模式
  ```
- visual block 模式下的 replace

  ```
  :'<,'>s/\%Vpattern1/pattern2/g
  或者
  :'<,'>s/\%Vpattern1\%V/pattern2/g
  ```
- 打出'^M':
  - 插入模式下，`<c-v><c-m>`
  - 关于^M: [脱字符表示法](https://zh.wikipedia.org/wiki/脱字符表示法)

# 5. 自定义系统

## 5.1. mapping, leader

- 映射基本命令：
  - `map` 递归映射（所有模式）
  - `nmap`, `vmap`, `imap` 分别对应普通、可视、插入模式的递归映射
  - `nnoremap`, `vnoremap`, `inoremap` 非递归映射（推荐始终使用）
  - `nunmap`, `iunmap` 取消映射
- Leader 键：
  - `let mapleader = ","` 设置 leader 键为逗号
  - `let maplocalleader = "\\"` 设置 local leader
  - `nnoremap <leader>w :w<CR>` leader+w 保存文件
  - `nnoremap <leader>q :q<CR>` leader+q 退出
- 特殊标记：
  - `<silent>` 不在命令行显示映射内容
  - `<buffer>` 仅对当前缓冲区生效
  - `<expr>` 映射内容作为表达式求值
  - `<nowait>` 不等待更长的键序列
- 查看映射：
  - `:map` 查看所有映射
  - `:verbose nmap <leader>w` 查看某个映射的定义位置

## 5.2. modeline

将以下模式行放置到文件开头，将在打开该文件时设置制表符为4个空格：

```
/* vim:set tabstop=4: */
```

## 5.3. autocmd

- 自动命令在特定事件发生时自动执行命令
- 基本语法：`autocmd {event} {pattern} {command}`
- 常用事件：
  - `BufRead`, `BufNewFile` 读取/新建文件时
  - `BufWritePre`, `BufWritePost` 写文件前后
  - `FileType` 文件类型被设置时
  - `VimEnter`, `VimLeave` 进入/退出 vim 时
  - `InsertEnter`, `InsertLeave` 进入/退出插入模式时
- 推荐使用 augroup 避免重复注册：
  ```vim
  augroup MyGroup
    autocmd!
    autocmd BufWritePre * :%s/\s\+$//e
    autocmd FileType python setlocal shiftwidth=4
  augroup END
  ```
- `autocmd!` 清除当前组内所有自动命令
- `:help autocmd-events` 查看所有可用事件

## 5.4. wildmenu

- wildmenu 提供命令行模式下 Tab 补全时的增强菜单
- 开启：`set wildmenu`
- 补全模式设置：
  - `set wildmode=longest:full,full` 先补全最长匹配，再完整匹配
  - `set wildmode=list:longest` 列出所有匹配并补全最长公共部分
- 忽略文件：
  ```vim
  set wildignore+=*.o,*.obj,*.pyc,*.class
  set wildignore+=*/.git/*,*/node_modules/*
  ```
- `set wildoptions=pum` (neovim) 使用弹出菜单显示补全选项
- 在补全菜单中用 `<Tab>`, `<S-Tab>`, `<C-n>`, `<C-p>` 切换选项

## 5.5. 缩写(Abbreviations)

- 缩写是一种输入时自动展开的快捷方式
- 定义缩写：
  - `:iab teh the` 插入模式下输入 teh 后自动替换为 the
  - `:ab ff Firefox` 所有模式下的缩写
  - `:cab` 仅命令行模式
- 触发条件：输入缩写后跟一个非关键字字符（空格、回车、标点等）
- 取消缩写：`:unabbreviate teh` 或 `:iunab teh`
- 查看所有缩写：`:ab`
- 常用场景：
  - 纠正常见拼写错误：`:iab adn and`
  - 输入代码模板：`:iab #i #include`
  - 插入个人信息：`:iab myemail user@example.com`

# 6. 外部系统交互

## 6.1. 作业 job

- Vim 8+ 支持异步作业（job），可以在后台运行外部命令
- 启动作业：
  ```vim
  let job = job_start('command', {'out_cb': 'MyHandler'})
  ```
- 回调函数处理输出：
  ```vim
  function! MyHandler(channel, msg)
    echom a:msg
  endfunction
  ```
- 常用函数：
  - `job_start(cmd, options)` 启动异步作业
  - `job_stop(job)` 停止作业
  - `job_status(job)` 查看状态：`run`, `dead`, `fail`
  - `job_info(job)` 获取作业详细信息
- neovim 使用 `jobstart()` 和 `jobstop()`，API 略有不同

## 6.2. 定时器(timeer)

- 定时器允许在指定延迟后执行函数
- 基本用法：
  ```vim
  let timer = timer_start(2000, 'MyCallback')
  " 2000ms 后执行 MyCallback
  ```
- 重复执行：
  ```vim
  let timer = timer_start(1000, 'MyCallback', {'repeat': -1})
  " -1 表示无限重复，正数表示重复次数
  ```
- 停止定时器：`call timer_stop(timer)`
- 查看所有定时器：`call timer_info()`
- 停止所有定时器：`call timer_stopall()`

## 6.3. 通道(channel)

- Channel 用于 Vim 与外部进程之间进行双向通信
- 支持的模式：
  - `raw` 原始数据
  - `nl` 按行分隔（newline）
  - `json` JSON 格式通信
  - `lsp` LSP 协议
- 打开通道：
  ```vim
  let channel = ch_open('localhost:8765', {'mode': 'json'})
  ```
- 发送数据：`call ch_sendexpr(channel, {'cmd': 'hello'})`
- 关闭通道：`call ch_close(channel)`
- Channel 是许多 Vim 插件（如 LSP 客户端）实现异步通信的基础

# 7. 代码开发支持

## 7.1. 模板(Template)

- 通过 autocmd 在新建文件时自动插入模板内容：
  ```vim
  autocmd BufNewFile *.py 0r ~/.vim/templates/python.tpl
  autocmd BufNewFile *.sh 0r ~/.vim/templates/bash.tpl
  ```
- `0r` 表示在第 0 行后读入文件内容
- 也可以通过插件管理模板，如 vim-template, vim-skeleton
- 模板中可以使用占位符，配合 UltiSnips 等代码片段插件实现动态模板

## 7.2. 配色方案(Color Scheme)

- 配色方案文件位于 `colors/` 目录下，后缀为 `.vim`
- 切换配色：`:colorscheme <name>`
- 查看当前配色：`:colorscheme`
- 列出可用配色：`:colorscheme <Tab>` 或 `:colorscheme <C-d>`
- 自定义配色基本结构：
  ```vim
  set background=dark
  highlight Normal guifg=#ffffff guibg=#1e1e1e
  highlight Comment guifg=#6a9955 gui=italic
  highlight Keyword guifg=#569cd6 gui=bold
  ```
- 使用 `termguicolors` 启用真彩色：`set termguicolors`

## 7.3. 色彩测试(colortest)

- 内置色彩测试脚本：`:runtime syntax/colortest.vim`
- 该命令在新 buffer 中展示所有终端颜色组合
- 检测终端是否支持 256 色：`:echo &t_Co`
- 测试真彩色支持：
  ```vim
  :set termguicolors
  :highlight TestColor guifg=#ff0000 guibg=#00ff00
  ```
- 输出所有 highlight 组到 buffer：`:runtime syntax/hitest.vim`

## 7.4. 语法高亮文件 (Syntax)

- 语法文件位于 `syntax/` 目录下，文件名与文件类型一致
- 基本结构：
  ```vim
  " syntax/mylang.vim
  syntax match myComment /\/\/.*/
  syntax keyword myKeyword if else while for
  syntax region myString start=/"/ end=/"/
  highlight link myComment Comment
  highlight link myKeyword Keyword
  highlight link myString String
  ```
- 加载顺序：先加载系统 syntax 文件，再加载用户 `after/syntax/` 下的文件
- `:syntax on` 开启语法高亮，`:syntax off` 关闭

## 7.5. 语法高亮度(Syntax Highlight)

- highlight 命令用于定义高亮组的颜色和样式
- 基本语法：`highlight GroupName key=value ...`
- 常用属性：
  - `ctermfg`, `ctermbg` 终端前景/背景色
  - `guifg`, `guibg` GUI 前景/背景色
  - `gui`, `cterm` 样式：bold, italic, underline, reverse, NONE
- 链接高亮组：`highlight link MyGroup Comment`
- 清除高亮：`highlight clear MyGroup`
- 查看当前高亮定义：`:highlight` 或 `:hi GroupName`

## 7.6. 语法高亮度-日志文件(Syntax Logfile)

- 为日志文件创建自定义语法高亮便于阅读
- 常见做法：
  ```vim
  " ~/.vim/syntax/log.vim
  syntax match logError /\c\<error\>/
  syntax match logWarn /\c\<warn\(ing\)\?\>/
  syntax match logInfo /\c\<info\>/
  syntax match logDate /\d\{4}-\d\{2}-\d\{2}/
  highlight logError ctermfg=red guifg=#ff0000
  highlight logWarn ctermfg=yellow guifg=#ffff00
  highlight logInfo ctermfg=green guifg=#00ff00
  highlight logDate ctermfg=cyan guifg=#00ffff
  ```
- 关联文件类型：`autocmd BufRead *.log setfiletype log`

## 7.7. 折叠(Fold)

- 折叠方法 `set foldmethod=xxx`：
  - `manual` 手动折叠
  - `indent` 基于缩进
  - `syntax` 基于语法
  - `expr` 基于表达式 `foldexpr`
  - `marker` 基于标记（默认 `{{{` 和 `}}}`）
  - `diff` 折叠未改变的行
- 常用命令：
  - `zf` 创建折叠（manual 模式）
  - `za` 切换折叠，`zo` 打开，`zc` 关闭
  - `zR` 打开所有折叠，`zM` 关闭所有折叠
  - `zj`, `zk` 在折叠之间移动
- `set foldlevel=99` 默认打开所有折叠

## 7.8. 非可见字符(Listchars)

- `set list` 显示非可见字符
- 自定义显示方式：
  ```vim
  set listchars=tab:>-,trail:~,extends:>,precedes:<,space:.
  set listchars+=eol:$,nbsp:+
  ```
- `set nolist` 关闭非可见字符显示
- 常用于检查混用 Tab/空格、行末空格等问题
- `:help listchars` 查看所有可配置项

## 7.9. 缩进(Indent)

- 基本缩进设置：
  ```vim
  set tabstop=4       " Tab 显示宽度
  set shiftwidth=4    " 自动缩进宽度
  set expandtab       " Tab 转空格
  set softtabstop=4   " 编辑时 Tab 的宽度
  set autoindent      " 自动缩进
  set smartindent     " 智能缩进
  ```
- 文件类型缩进：`filetype indent on`
- 缩进文件位于 `indent/` 目录下
- 手动调整缩进：`>>` 增加，`<<` 减少，`==` 自动缩进
- `:retab` 将文件中的 Tab 按当前设置重新转换

## 7.10. 多重色彩括号(Parentheses)

- 通过插件实现不同层级括号使用不同颜色，便于辨别嵌套关系
- 常用插件：
  - rainbow (luochen1990/rainbow)
  - rainbow-parentheses.vim
  - nvim-ts-rainbow (基于 treesitter)
- 基本配置（rainbow 插件）：
  ```vim
  let g:rainbow_active = 1
  ```
- 支持自定义颜色列表和括号类型

## 7.11. Zeavim离线文档查看器

- Zeavim 是 Zeal 的 Vim 集成插件
- Zeal 是一个离线文档浏览器，支持 200+ 文档集（类似 macOS Dash）
- 安装插件：`Plug 'KabbAmine/zeavim.vim'`
- 常用命令：
  - `<leader>z` 查询光标下单词的文档
  - `<leader>Z` 指定 docset 进行查询
  - `:Zeavim` 手动输入查询内容
- 需要先安装 Zeal 应用：`pacman -S zeal` 或 `apt install zeal`

# 8. Tag标签

## 8.1. 生成标签文件(Generates Tags File)

- 使用 ctags 生成标签文件：
  - `ctags -R .` 递归生成当前目录的 tags
  - `ctags -R --languages=python .` 仅生成 Python 的标签
  - `ctags --exclude=node_modules -R .` 排除目录
- 使用 universal-ctags（推荐，ctags 的维护更新版本）
- 标签文件默认名为 `tags`，vim 通过 `set tags=./tags;,tags` 查找
- 也可以使用 gutentags 插件自动管理 tags 文件的生成和更新

## 8.2. 匹配单个标签(Matching Single Tag)

- `<C-]>` 跳转到光标下标识符的定义（第一个匹配）
- `:tag {name}` 跳转到指定标签
- `<C-t>` 或 `:pop` 返回跳转前的位置
- `:tag /pattern` 使用正则匹配标签名
- `g<C-]>` 如果有多个匹配，显示列表供选择；仅一个则直接跳转

## 8.3. 匹配多个标签(Matching Multiple Tags)

- `:tselect {name}` 列出所有匹配的标签供选择
- `:tnext`, `:tprev` 在多个同名标签间前后跳转
- `:tfirst`, `:tlast` 跳转到第一个/最后一个匹配
- `:tags` 显示标签栈（跳转历史）
- 预览标签：`:ptag {name}` 在预览窗口中打开标签定义

## 8.4. 标签选项(Tag Option)

- `set tags=./tags;,tags` 向上查找 tags 文件
- `set tagcase=match` 大小写匹配（`ignore` 忽略大小写）
- `set tagstack` 启用标签栈
- `set showfulltag` 在补全时显示标签的完整信息
- `set tagbsearch` 使用二分查找（要求 tags 文件已排序）
- `set cscopetag` 同时使用 cscope 和 ctags

# 9. 性能排查

## 9.1. profile

```vim
profile start temp_profile.txt
profile func *
profile file *

" after file ...

profile dump
" or
profile stop
```

## 9.2. syntime

- 用于分析语法高亮的性能瓶颈
- 使用流程：
  ```vim
  :syntime on        " 开始记录
  " ... 进行正常编辑操作 ...
  :syntime report    " 查看报告
  :syntime off       " 停止记录
  ```
- 报告按耗时排序，显示每个语法规则的匹配次数和耗时
- 适用于调试大文件编辑时的卡顿问题
- 如果某个正则匹配耗时过高，考虑优化或禁用对应的语法规则

# 10. vim插件(已过时)

**详细插件配置：[dotfiles](https://github.com/whitestarrain/dotfiles)**

## 10.1. 插件

## 10.2. 插件安装示例

- 插件管理器：vim-plug
	> 更多其实看github上的文档就行
	- 下载vim文件
	- 将文件放到autoload文件夹
- 安装插件vim-startify：
	- 去vimrc中进行配置：
		```vim
		call plug#begin('~/vimfiles/plugin') " 这里写的是插件放的位置

		Plug 'mhinz/vim-startify'
		" 这里写的是需要的插件
		call plug#end()
		```
	- 输入:PlugInstall
	- windows报错解决：
		- 将插件文件夹下的文件夹（仅文件夹）和vimfiles下的文件夹 合并
		- 不要替换tag，把所有插件的tag放到一个文件夹中

## 10.3. 寻找插件

- 大多数插件都托管在github上，google关键词搜索
- 使用网站： http://vimawesome.com/ (十分推荐)
- 浏览网上开源的vimrc配置。

## 10.4. 美化插件

- 启动界面：vim-startify
- 状态栏：vim-airline
- 代码缩进线条：indentline

- 配色：
	- hybird 黑色，类似one dark
	- solarized 对眼睛较好，亮暗两种主题
	- gruvbox 两种主题

- 配色安装：
	- 方式和插件安装相同
	- 要持久化的话要在配置中加上 colorscheme XXX
	- 简单点也可以把hybird.vim下载后放到安装目录的colors下

## 10.5. 实用插件

- 文件树安装：
	- 安装:Plug 'scrooloose/nerdtree'
	- 配置
	```vim
	" auto turn on
	" autocmd vimenter * NERDTree
	" ctrl+n to toggle file tree
	noremap <C-n> :NERDTreeToggle<CR>
	" ignore file
	let NERDTreeIgnore=[
		\ '\.pyc$','\~$','\.swp','\.git$','\.pyo$','\.svn$','\.swp$','__pycache__'
		\ ]
	nnoremap ,v :NERDTreeFind<cr>
	" and ctrl w p  to back
	```
- 模糊搜索：
	- 安装：Plug 'ctrlpvim/ctrlp.vim'
	- 配置
		```
		" ctrlp config
		let g:ctrlp_map = '<C-p>'
		```
- 快速跳转
	- 目的：能够实现窗口任意区域的跳转
	- 安装：Plug 'easymotion/vim-easymotion'
	- 配置：
		```
		" easymotion config

		nmap ss <Plug>(easymotion-s2)
		" 使用ss时会调用s2查询

		```
- 成对编辑
	- 安装：Plug 'tpope/vim-surround'
	- 使用：
		- ds：delete a surrounding
		- cs: change a surrounding
		- ys: you add a surrounding
    - vS: add a surrounding for highlight
	- 使用示例：
    ```
    "Hello *world!"           ds"         Hello world!
    [123+4*56]/2              cs])        (123+456)/2
    "Look ma, I'm *HTML!"     cs"<q>      <q>Look ma, I'm HTML!</q>
    if *x>3 {                 ysW(        if ( x>3 ) {
    my $str = *whee!;         vllllS'     my $str = 'whee!';
    ```
	- 配置：不用

- 多个文件模糊搜索：
	- 安装： 挑一个
		- Ag.vim
		- fzf.vim
      <details>
      <summary style="color:red;">fzf命令</summary>

        | `:Files [path]`  | 列出path路径下的所有文件 (功能等价于 `:FZF` 命令)            |
        | ---------------- | ------------------------------------------------------------ |
        | `:Buffers`       | 文件缓冲区切换                                               |
        | `:Colors`        | 选择Vim配色方案                                              |
        | `:Tags [QUERY]`  | 当前项目中的Tag (等价于：`ctags -R`)                         |
        | `:BTags [QUERY]` | 当前活动缓冲区的标记                                         |
        | `:Marks`         | 所有Vim标记                                                  |
        | `:Windows`       | 窗口                                                         |
        | `:Lines [QUERY]` | 在所有加载的文件缓冲区里包含目标词的所有行                   |
        | `BLines [QUERY]` | 在当前文件缓冲区里包含目标词的行                             |
        | `Locate PATTERN` | `locate` command output                                      |
        | `History`        | `v:oldfiles` and open buffers                                |
        | `History:`       | 命令行命令历史                                               |
        | `History/`       | 搜索历史                                                     |
        | `Commands`       | Vim命令列表                                                  |
        | `Maps`           | 普通模式下的按键映射                                         |
        | `Snippets`       | Snippets ([UltiSnips][us])                                   |
        | `Commits`        | Git commits (requires [fugitive.vim][f])                     |
        | `BCommits`       | Git commits for the current buffer                           |
        | `GFiles [OPTS]`  | Git files (`git ls-files`)                                   |
        | `GFiles?`        | Git files (`git status`)                                     |
        | `Ag [PATTERN]`   | [ag][ag] search result (`ALT-A` to select all, `ALT-D` to deselect all) |
        | `Rg [PATTERN]`   | [rg][rg] search result (`ALT-A` to select all, `ALT-D` to deselect all) |
        | `Filetypes`      | File types                                                   |
      </details>
	- 完全可以提到 ctrlp
	- 常用命令:
		- Files
		- Ag

- 批量搜索替换
	- 安装：Plug `brooth/far.vim`
	- 使用：
		- `Far AAA XXXX **/*.py`
		- 展示预览，没问题的话；
		- `Fardo`进行替换

- golang插件
	- 安装： `Plug 'fatih/vim-go', { 'do': ':GoUpdateBinaries' }`
	- 教程看官方文档
	- ctrl+] 查看定义
	- 自动导入，重构，格式化，运行等功能都有集成
	- 使用python和php的都转向go了，推荐学学

- python-mode插件
	- 安装： Plug 'python-mode/python-mode', { 'for': 'python', 'branch': 'develop' }
	- 注意：
		- 安装时确保网络
		- vim要支持python3

- 大纲插件；
	- 安装 Plug 'majutsushi/tagbar'
	- 注意：查看文档，需要安装ctags生成tag文件

- 高亮指定单词：
	- 安装：Plug 'lfv89/vim-interestingwords'
	- 使用：
		- 默认在指定单词处`<leader>k`开启高亮，
		- 使用`<leader>K`清除所有高亮
		- n和N进行跳转

- 强大的代码补全插件
	- deoplete.nvm
		- 内容：通用补全，和之前的全家桶有区别
		- 安装:查看github文档，有一些vimscript,项目名称为:shougo/deoplete.nvim
		- 支持vim8异步和模糊补全
		- 注意：注意文档提出的各种要求，比如neovim，python版本
		- 配置：
			```
			" 补全时默认会有文档预览，设置这条关闭预览
			set completeopt-=preview
			```
	- coc.vim
		- 支持vim8异步。 主要特色是支持 LSP(Language Serve Propocol)
		- 使vim像vscode一样！ 我个人更喜欢

- 代码格式化和静态检查：
	- 异步检查：vim8和neovim支持，在检查时也能进行编写，不会卡住
	- 无论写哪种语言都要加上这两种，重要！！！
	- 格式化：
		- nsbdchd/neoformat
			- 注意依赖,需要安装对应的库。插件本身就起调用作用
				- 比如，python需要 pip install autopep8
			- 使用：输入Neoformat命令,更多推荐查文档
		- autoformat
	- 静态检查；
		- w0rp/ale
			- 注意依赖,需要安装对应的库。插件本身就起调用作用
				- python需要 pip install pylint
			- 安装：Plug 'w0rp/ale'
			- 一开始会有很多提示，可以根据需要配置忽略项
			- 使用：自动就给你检查了
		- neomake

- 快速注释代码插件：
	- 安装：Plug 'tpope/vim-commentary'
	- 会根据不同语言使用不同注释
	- 常用命令：
		- gc 设置取消注释切换（后面来基本不怎么用）
		- gcc:注释
		- gcgc:取消注释

- git结合
	- tpope/Fugitive
		- 安装：Plug `tpope/vim-fugitive`
		- 作用：git的包装器,可以通过内置命令调用git
		- 不喜欢看后面的tmux开一个窗口用git
		- 使用：查文档，有具体命令
	- airblade/vim-gitgutter
		- 安装：Plug `airblade/vim-gitgutter`
		- 作用：会在每行左侧显示每行是新增的，删除的，等等
		- 使用：每行都会显示，不用主动调用
	- gv.vim
		- 注意：要安装了fugitive才行
		- 安装：Plug 'junegunn/gv.vim'
		- 作用：浏览代码提交变更,和git里面打 git log --ongline --graph --decorate 效果差不多基本相同
		- 使用：GV

- 思维导图
  - markmap:使用markdown来画思维导图

- 格式化工具：
  - Prettier:支持全局格式化和局部格式化的好插件

# 11. 其他工具

## tmux

- tmux 与 vim 配合使用，实现终端多路复用 + 编辑的工作流
- vim-tmux-navigator 插件：使用 `Ctrl+h/j/k/l` 在 vim 和 tmux pane 间无缝切换
- 在 tmux 中使用 vim 复制模式：`set -g mode-keys vi`
- 从 vim 向 tmux pane 发送命令：vim-slime 或 vimux 插件
  ```vim
  " vimux: 向 tmux pane 发送命令
  map <Leader>vp :VimuxPromptCommand<CR>
  map <Leader>vl :VimuxRunLastCommand<CR>
  ```

## vsc 插件

- VSCode Neovim 扩展（asvetliakov.vscode-neovim）：在 VSCode 中使用真正的 Neovim 后端
- 比 VSCodeVim 性能更好，完整支持 vim 插件和配置
- 配置方式：在 settings.json 中指定 neovim 路径
  ```json
  {
    "vscode-neovim.neovimExecutablePaths.linux": "/usr/bin/nvim",
    "vscode-neovim.neovimInitVimPaths.linux": "~/.config/nvim/init.lua"
  }
  ```
- 支持 vim 的全部模式、宏录制、文本对象等特性

## neovide

连接远程nvim实例

参考文档：[neovide-features](https://neovide.dev/features.html)

- tcp socket:
  - remote

    ```bash
    nvim --headless --listen 0.0.0.0:6666
    ```
  - local

    ```bash
    ssh -L 6666:localhost:6666 ip.of.remote.machine
    ```
    ```bash
    neovide  --server=localhost:6666
    ```

- unix domain socket
  - remote

    ```bash
    nvim --headless --listen /path/to/remote/socket
    ```
  - local

    ```bash
    ssh -L /path/to/local/socket:/path/to/remote/socket ip.of.remote.machine
    ```

# 12. vimscript

## 12.1. 前言

尽管都转到了lua，vimscript还是稍微学一下吧

## 12.2. 基本vim配置

- 目的：
	- 持久化配置，比如行号，高亮等
- vim常用设置
	- 到vimrc里面看看吧
- 位置：
	- 目的：把一个操作映射到另一个操作
- 普通映射：(仅限普通模式)
	- map option1 option2 把option1映射到option2
		- `map <space> viw` 按空格就会选中一个单词  `<C-d> dd` ctrl+d就会删除一行
	- 取消映射：unmap
- 指定模式映射
	- normal:nmap
	- visual:vmap
	- insert:imap
		- 例：`imap <C-d> <Esc>ddi` 插入模式下ctrl+d就会 返回normal模式，删除一行，再进入插入模式
- 禁用递归映射
	> 任何时候都要使用非递归映射
	- 原因：上面的映射都会进行递归映射，一直映射到没有映射的按键。同时使用插件时也会导致很多冲突
	- 方法：
		- nnoremap
		- vnnoremp
		- inoremap
- 设置leader
	```vim
	let mapleader=','
	inoremap <leader>w <Esc>:w<cr>
	" 插入模式下逗号+w  相当于  退回普通模式，输入:w 再回车
	```

- 工作路径相关：
    - cd: 改变vim的当前工作路径
    - lcd： 改变当前窗口的工作路径
    - pwd: 查看当前的工作路径
    - set autochdir: 自动设当前编辑的文件所在目录为当前工作路径 ，这个可以加入到_vimrc文件中去
- 查看配置项配置位置：
  - `verbose set ...`，如`verbose set number`

# 13. 其他

## 13.1. Vim中的^M

Unix uses 0xA for a newline character. Windows uses a combination of two characters: 0xD 0xA. 0xD is the carriage return character. `^M` happens to be the way vim displays 0xD (0x0D = 13, M is the 13th letter in the English alphabet).

You can remove all the `^M` characters by running the following:
/bin/bash: line 1: ctrans: command not found

```vim
:%s/^M//g
```

Where `^M` is entered by holding down Ctrl and typing v followed by m, and then releasing Ctrl. This is sometimes abbreviated as `^V^M`, but note that you must enter it as described in the previous sentence, rather than typing it out literally.

This expression will replace all occurrences of `^M` with the empty string (i.e. nothing). I use this to get rid of `^M` in files copied from Windows to Unix (Solaris, Linux, OSX).

- window上，如果有^M符号，编译不会报错，但会导致lsp各种毛病。命令行模式Ctrl-v-m 可以打出这个符号

# 14. vim启动过程

# 15. 参考资料

- **help**
- **[VIM学习笔记](https://yyq123.github.io/learn-vim/)**
- [Vim-一些实用技巧](https://tkstorm.com/posts-list/os/linux/vim-usages/)
- 《笨方法学vimscript》
- [What does ^M character mean in Vim?](https://stackoverflow.com/questions/5843495/what-does-m-character-mean-in-vim)
- [VIM插入模式下的几个命令](https://blog.csdn.net/champwang/article/details/47656463)
