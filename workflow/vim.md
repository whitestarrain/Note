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

- /pattern 搜索，n往下搜索，N往上搜索。?pattern倒序搜索
- /\cpattern 搜索，同时忽略大小写
- ?pattern 向上搜索
- :/pattern 定位到查找字符所在行的第一个非空白字符
- `* #` 查询匹配当前光标所在单词，前后移到查到的单词

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
- 注意：也支持正则表达式
  - 例子：为非空行添加双引号  `:% s/^\(\S+\)$/"\1"/g`
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
- :g 相当于linux中的grep
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

## 2.2. 替换模式(Replace Mode)

## 2.3. 可视化模式 (Visual Mode)

## 2.4. 选择模式 (Select Mode)

## 2.5. 命令行模式 (Command-line Mode)

## 2.6. Ex模式 (Ex Mode)

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

## 3.2. 全局命令(Global Command)

- `:g` 全局命令, `help :g`

  ```vim
  " 全局删除
  :g/pattern/d
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
  - `:%xxd` 转换为hex dump
  - `:%xxd -r` hex dump 转换为二进制

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

# 5. 自定义系统

## 5.1. mapping, leader

## 5.2. modeline

将以下模式行放置到文件开头，将在打开该文件时设置制表符为4个空格：

```
/* vim:set tabstop=4: */
```

## 5.3. autocmd

## 5.4. wildmenu

## 5.5. 缩写(Abbreviations)

# 6. 外部系统交互

## 6.1. 作业 job

## 6.2. 定时器(timeer)

## 6.3. 通道(channel)

# 7. 代码开发支持

## 7.1. 模板(Template)

## 7.2. 配色方案(Color Scheme)

## 7.3. 色彩测试(colortest)

## 7.4. 语法高亮文件 (Syntax)

## 7.5. 语法高亮度(Syntax Highlight)

## 7.6. 语法高亮度-日志文件(Syntax Logfile)

## 7.7. 折叠(Fold)

## 7.8. 非可见字符(Listchars)

## 7.9. 缩进(Indent)

## 7.10. 多重色彩括号(Parentheses)

## 7.11. Zeavim离线文档查看器

# 8. Tag标签

## 8.1. 生成标签文件(Generates Tags File)

## 8.2. 匹配单个标签(Matching Single Tag)

## 8.3. 匹配多个标签(Matching Multiple Tags)

## 8.4. 标签选项(Tag Option)

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

## vsc 插件

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
