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

# 参考文献

- [中文文档参考](http://vimcdoc.sourceforge.net/doc/)
