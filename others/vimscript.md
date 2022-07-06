# vim自定义

## 预备知识

- 配置文件
  - vim下是`~/.vimrc`，该文件包含了Vimscript代码
  - 每次启动Vim时，Vim都会自动执行其中的代码。
  - nvim则是init.vim

- 获取配置文件位置：
  - 在 _任意_ 系统中，在Vim中执行:echo `$MYVIMRC`命令可以快速得知这个文件的位置和名称
  - 执行`version`获取不同位置读取的优先级
  - 文件的路径会在屏幕的底部显示。
  - 如果你的home文件夹没有这个文件，请自行创建一个。

## echo

- echo 用来输出
- echom 同样，不过可以使用`:messages`查看输出记录，用来debug，类似日志

---

- 文档阅读
  - echo
  - echom
  - messages

## 设置选项

- off/on 选项
  ```vim
  set number
  set nonumber " close

  set number! " toggle

  set number? " show the value
  set nonumber? " same as above
  ```

- 有值选项
  ```vim
  :set numberwidth=4 " set value
  :set numberwiddth " show value
  :set numberwidth? " save as above
  ```

- 可以一次性设置多个值
  ```vim
  :set number numberwidth=6
  ```

---

- 文档阅读
  - :help 'number'（注意有单引号）
  - :help relativenumber
  - :help numberwidth
  - :help wrap
  - :help shiftround
  - :help matchtime

## 映射

# Leader

# vimscript

## 变量

> :help internal-variables

- (b:) buffer-variable   :	  局部于当前缓冲区。
- (w:) window-variable   :	  局部于当前窗口。
- (t:) tabpage-variable  :	  局部于当前标签页。
- (g:) global-variable   :	  全局。
- (l:) local-variable    :	  局部于函数。
- (s:) script-variable   :	  局部于 :source 的 Vim 脚本。
- (a:) function-argument :	  函数参数 (只限于函数内使用)。
- (v:) vim-variable      :	  Vim 预定义的全局变量。

# 插件

## 文件夹说明

> 详见：`help runtimepath`

- ~/.vim/colors/
  - Vim将会查找~/.vim/colors/mycolors.vim并执行它
  - 这个文件应该包括生成你的配色方案所需的一切Vimscript命令。

  > 《笨方法学vimscript》不会谈到配色方案。
  > 如果想创造属于自己的配色方案，你应该从一个现存的配色方案上改造出来。
  > 记住，:help将与你常在。

- ~/.vim/plugin/
  - ~/.vim/plugin/下的文件将在_每次_Vim启动的时候执行
  - 这里的文件包括那些无论何时，在启动Vim之后你就想加载的代码。

- ~/.vim/ftdetect/
  - ~/.vim/ftdetect/下的文件在每次你启动Vim的时候_也会_执行。
  - ftdetect是"filetype detection"的缩写
  - 这里的文件_仅仅_负责启动检测和设置文件的filetype类型的自动命令。 这意味着它们一般不会超过一两行。

- ~/.vim/ftplugin/
  - ~/.vim/ftplugin/下的文件则各不相同。
  - 一切皆取决于它的名字!
  - 当Vim把一个缓冲区的filetype设置成某个值时， 它会去查找~/.vim/ftplugin/下对应的文件。
  - 比如：如果你执行set filetype=derp，Vim将查找~/.vim/ftplugin/derp.vim。 一旦文件存在，Vim将执行它。
  - Vim也支持在~/.vim/ftplugin/下放置文件夹。
  - 再以我们刚才的例子为例：set filetype=derp将告诉Vim去执行~/.vim/ftplugin/derp/下的全部*.vim文件
  - 这使得你可以按代码逻辑分割在ftplugin下的文件。
  - 因为每次在一个缓冲区中执行filetype时都会执行这些文件，所以它们_只能_设置buffer-local选项！
  - 如果在它们中设置了全局选项，所有打开的缓冲区的设置都会遭到覆盖！

- ~/.vim/indent/
  - ~/.vim/indent/下的文件类似于ftplugin下的文件
  - 加载时也是只加载名字对应的文件。
  - indent文件应该设置跟对应文件类型相关的缩进，而且这些设置应该是buffer-local的。
  - 当然可以把这些代码也一并放入ftplugin文件， 但最好把它们独立出来，让其他Vim用户理解你的意图
  - 这只是一种惯例，不过请尽量体贴用户并遵从它。

- ~/.vim/compiler/
  - ~/.vim/compiler下的文件非常类似于indent文件
  - 它们应该设置同类型名的当前缓冲区下的编译器相关选项。
  - 不要担心不懂什么是"编译器相关选项"。我们等会会解释。

- ~/.vim/after/
  - ~/.vim/after文件夹有点神奇
  - 其中可以含 plugin 与 ftplugin 文件夹, 加载时机位于 ~/.vim/plugin/ 或 ~/.vim/ftplugin 之后
  - 这个文件夹下的文件会在每次Vim启动的时候加载， 不过是在~/.vim/plugin/下的文件加载了之后。
  - 这允许你覆盖Vim的默认设置。
  - 实际上你将很少需要这么做，所以不用理它， 除非你有"Vim设置了选项x，但我想要不同的设置"的主意。

- ~/.vim/autoload/
  - ~/.vim/autoload文件夹就更加神奇了。事实上它的作用没有听起来那么复杂。
  - 简明扼要地说：autoload是一种延迟插件代码到需要时才加载的方法
  - 我们将在重构插件的时候详细讲解并展示它的用法。

- ~/.vim/doc/
  - 最后，~/.vim/doc/文件夹提供了一个你可以放置你的插件的文档的地方
  - Vim对文档的要求是多多益善(看看我们执行过的所有:help命令就知道)，所以为你的插件写文档是重要的。

## 加载插件

### 早期方式与问题

- 在过去，要想使用别人写好的插件，你得下载所有文件并逐一正确地放置它们。 你也可能使用zip或tar来替你做放置的工作。
- 当你想更新插件的时候怎么办？你可以覆盖旧的文件， 但如果作者删除了某个文件，你怎么知道你要手工删除对应文件？
- 假如有两个插件正好使用了同样的文件名(比如utils.vim或别的更大众的名字)呢？ 
  - 有时你可以简单地重命名掉它，但如果它位于autoload/或别的名字相关的文件夹中呢？
  - 你改掉文件名，就等于改掉插件。这一点也不好玩。
- 因此插件管理器就诞生了，早期有 `Pathogen`，`vundle`，之后有`vim-plug`，还有纯lua的`packer.nvim`

### 运行时路径

- 当Vim在特殊的文件夹，比如syntax/，中查找文件时，它不仅仅只到单一的地方上查找。
- 就像Linux/Unix/BSD系统上的PATH，Vim设置runtimepath以便查找要加载的文件。
- 在你的桌面创建colors文件夹。在这个文件夹中创建一个叫mycolor.vim的文件(在本示例中你可以让它空着)。
- 打开Vim并执行这个命令：

  ```vimscript
  :color mycolor
  ```
- Vim将显示一个错误，因为它不懂得去你的桌面查找。
- 现在执行这个命令：

  ```
  :set runtimepath=/Users/sjl/Desktop
  ```
- 当然，你得根据你的情况修改路径名。现在再尝试color命令：
  ```
  :color mycolor
  ```
- 这次Vim找到了mycolor.vim，所以它将不再报错。
- 由于文件是空的，它事实上什么都没做， 但由于它不再报错，我们确信它找到了。

### 插件管理器原理

- Pathogen插件在你加载Vim的时候自动地把路径加到你的runtimepath中
  - 所有在~/.vim/bundle/下的文件夹将逐个加入到runtimepath。(译注：vundle也是这么做的)
- 这意味着每个`bundle/`下的文件夹应该包括部分或全部的标准的Vim插件文件夹，比如colors/和syntax/
- 现在Vim可以从每个文件夹中加载文件，而且每个插件文件都独立于自己的文件夹中。
- 这么一来更新插件就轻松多了。你只需要整个移除旧的插件文件夹，并迎来新的版本。 
- 如果你通过版本控制来管理~/.vim文件夹(你应该这么做)，
  - 你可以使用Mercurial的subrepo或Git的submodule功能来直接签出(checkout)每个插件的代码库，
  - 然后用一个简单的hg pull; hg update或git pull origin master来更新。

### 如何兼容插件管理器

- Pathogen安装我们写的Potion插件。 我们需要做的：在插件的代码库里，放置我们的文件到正确的文件夹中。就是这么简单！
- 我们插件的代码库展开后看起来就像这样：

  ```
  potion/
      README
      LICENSE
      doc/
          potion.txt
      ftdetect/
          potion.vim
      ftplugin/
          potion.vim
      syntax/
          potion.vim
      ... etc ...
  ```

- 我们把它放置在GitHub或Bitbucket上，这样用户就能简单地clone它到bundle/，一切顺利！

## nvim的lua

- `require()`将加载lua下的文件
- 和 Vimscript 文件很像，位于 runtimepath 中的一些特殊目录中的 Lua 文件也可以被 Neovim 自动加载。目前有以下这些特殊目录：
  > 注意：在同一个运行时目录中，*.vim 文件会先于所有的 *.lua 文件被加载
  - colors/
  - compiler/
  - ftplugin/
  - indent/
  - plugin/
  - syntax/

- 加载一个不存在的模块或者加载的模块有语法错误会直接中止当前正在执行的脚本。pcall() 函数可以用来处理这类错误

  ```lua
  local ok, _ = pcall(require, 'module_with_error')
  if not ok then
    -- not loaded
  end
  ```

# 参考文献

- [中文文档参考](http://vimcdoc.sourceforge.net/doc/)
- 《笨方法学vimscript》
- [在 neovim 中使用 Lua](https://github.com/glepnir/nvim-lua-guide-zh)
- [Vim 中的变量赋值、引用与作用域](https://harttle.land/2017/01/30/variables-in-vim.html)
- [`<plug>`和`<SID>`](https://blog.csdn.net/felix_f/article/details/18808985)
