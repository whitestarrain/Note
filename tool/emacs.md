`emacs -nw` 终端模式

# 基本命令

## 移动

| 按键          | 命令                | 功能                                      |
| :------------ | :------------------ | :---------------------------------------- |
| C-q tab / M-i |                     | 输入制表符                                |
| C-f           | forward-char        | 向前一个字符                              |
| C-b           | backward-char       | 向后一个字符                              |
| C-p           | previous-line       | 上移一行                                  |
| C-n           | next-line           | 下移一行                                  |
| M-f           | forward-word        | 向前一个单词                              |
| M-b           | backward-word       | 向后一个单词                              |
| C-a           | beginning-of-line   | 移到行首                                  |
| C-e           | end-of-line         | 移到行尾                                  |
| M-m           | back-toindentation  | 将光标移动到行首的第一个非空白字符        |
| M-e           | forward-sentence    | 移到句首                                  |
| M-a           | backward-sentence   | 移到句尾                                  |
| M-}           | forward-paragraph   | 下移一段                                  |
| M-{           | backward-paragraph  | 上移一段                                  |
| C-v           | scroll-up           | 下移一屏                                  |
| M-v           | scroll-down         | 上移一屏                                  |
| C-x ]         | forward-page        | 下移一页                                  |
| C-x [         | backward-page       | 上移一页                                  |
| M-<           | beginning-of-buffer | 移到文档头                                |
| M->           | end-of-buffer       | 移到文档尾                                |
| M-g g num     | goto-line           | 移到第n行                                 |
| M-g c         | goto-char           | 移到第n个字符                             |
| C-l           | recenter            | 将当前位置放到页面中间(Emacs最喜欢的地方) |
| M-num         | digit-argument      | 重复下个命令n次                           |
| C-u num       | universal-argument  | 重复下个命令n次，n默认为4                 |

- 其他窗格
  - C-M-v 或 ESC C-v 向下滚动下方窗格
  - C-M-S-v 或 ESC C-S-v 向上滚动下方窗格

## 文件编辑

> 除了Del和C-d其他的删除命令都会按顺序保存起来，用C-y或者M-y来取出，
> 如果想更好的使用undo功能，可以了解undo tree，在Emacs中一切皆可undo，包括undo本身也可以被undo

| 按键            | 命令                    | 功能                                               |
| :-------------- | :---------------------- | :------------------------------------------------- |
| C-q (n)         | quoted-insert           | 插入字符，n表示字符的八进制ASCII码                 |
| C-x 8           | ucs-insert              | 插入Unicode字符                                    |
| C-d             | delete-char             | 删除光标处字符                                     |
| Backspace       | delete-backward-char    | 删除光标前字符                                     |
| M-d             | kill-word               | 删除光标起单词                                     |
| M-Backspace     | backward-kill-word      | 删除光标前单词                                     |
| C-k             | kill-line               | 删除光标起当前行                                   |
| M-k             | kill-sentence           | 删除光标起句子                                     |
| C-x Backspace   | backward-kill-sentence  | 删除光标前句子                                     |
| (none)          | kill-paragraph          | 删除光标起段落                                     |
| (none)          | backward-kill-paragraph | 删除光标前段落                                     |
| C-/             | undo                    | 撤销                                               |
| C-\_            | undo                    | 撤销                                               |
| C-x u           | undo                    | 撤销                                               |
| C-g             | keyboard-quit           | 撤销命令                                           |
| C-h t           | help-with-tutorial      | 调出Emacs Tutorial                                 |
| C-h r           | info-emacs-manual       | 调出Emacs Manual                                   |
| C-h k (command) | describe-key            | 查看对应command帮助                                |
| C-o             | open-line               | 插入空行                                           |
| C-x C-o         | delete-blank-line       | 删除空行                                           |
| C-x z           | repeat                  | 重复前个命令                                       |
| C-@             | set-mark-command        | 设定标记                                           |
| C-x C-x         | exchange-point-and-mark | 交换标记和光标位置                                 |
| C-w             | kill-region             | 删除区域中内容                                     |
| C-x C-u         | upcase-region           | 将区域中字母改为大写                               |
| C-x C-l         | upcase-region           | 将区域中字母改为小写                               |
| C-x h           | mark-whole-buffer       | 全选                                               |
| C-x C-p         | mark-page               | 选取一页                                           |
| M-h             | mark-paragraph          | 选取一段                                           |
| M-@             | mark-word               | 选取一个单词                                       |
| C-@ C-@         |                         | 加入点到标记环                                     |
| C-u C-@         |                         | 在标记环中跳跃                                     |
| C-x C-@         | pop-global-mark         | 在全局标记环中跳跃                                 |
| (none)          | transient-mark-mode     | 非持久化标记模式                                   |
| `M-\`           | delete-horizontal-space | 删除光标处的所有空格和Tab字符                      |
| M-SPC           | just-one-space          | 删除光标处的所有空格和Tab字符，但留下一个          |
| C-x C-o         | delete-blank-lines      | 删除光标周围的空白行，保留当前行                   |
| M-^             | delete-indentation      | 将两行合为一行，删除之间的空白和缩进               |
| C-S-Backspace   | kill-whole-line         | 删除整行                                           |
| C-w             | kill-region             | 删除区域                                           |
| M-w             | kill-ring-save          | 复制到kill 环，而不删除                            |
| M-z char        | zap-to-char             | 删至字符char为止                                   |
| C-y             | yank                    | 召回                                               |
| M-y             | yank-pop                | C-y 后使用，召回前一个                             |
| C-M-w           | append-next-kill        | 下一个删掉内容和上次删除合并                       |
| C-h v           | describe-variable       | 显示变量内容                                       |
| (none)          | append-to-buffer        | 将区域中内容加入到一个buffer中                     |
| (none)          | prepend-to-buffer       | 将区域中内容加入到一个buffer光标前                 |
| (none)          | copy-to-buffer          | 区域中内容加入到一个buffer中，删除该buffer原有内容 |
| (none)          | insert-buffer           | 在该位置插入指定的buffer中所有内容                 |
| (none)          | append-to-file          | 将区域中内容复制到一个文件中                       |
| (none)          | cua-mode                | 启用/停用CUA绑定                                   |
|                 | kill-read-only-ok       | 是否在只读文件启用kill 命令                        |
|                 | kill-ring               | kill环                                             |
|                 | kill-ring-max           | kill环容量                                         |


- 文件找回
  - `M-x recover-file emacs`定期自动保存文件到其他地方，可以在修改丢失时恢复文件
- 折行
  - C-x f 设置需要折行的长度
  - M-q 手动折行


## 文件操作

| 按键                        | 命令                          | 功能                                               |
| C-x C-f                     | find-file                     | 打开文件                                           |
| C-x C-v                     | find-alternate-file           | 打开另一个文件                                     |
| C-x C-s                     | save-buffer                   | 保存文件                                           |
| C-x C-w                     | write-file                    | 另存文件                                           |

- C-x C-f 寻找，打开文件
- C-x C-s 储存文件
  - `M-x customize-variable <Return> make-backup-file <Return>` 关闭自动生成 后缀为 ~ 的备份文件

## 查找替换

| 按键          | 命令                    | 功能                                         |
| :------------ | :---------------------- | :------------------------------------------- |
| C-s           | isearch-forward         | 向前进行增量查找                             |
| C-r           | isearch-backward        | 向后进行增量查找                             |
| M-c           |                         | (查找状态)切换大写敏感                       |
| C-j           |                         | newline-and-indent\|(查找状态)输入换行符     |
| M-Tab         | isearch-complete        | (查找状态)自动匹配                           |
| C-h C-h       |                         | (查找状态)进入查找帮助                       |
| C-w           |                         | (查找状态)将光标处单词复制到查找区域         |
| C-y           |                         | (查找状态)将光标处直到行尾内容复制到查找区域 |
| M-y           |                         | (查找状态)把kill 环中最后一项复制到查找区域  |
| C-M-w         |                         | (查找状态)删除查找区域最后一个字符           |
| C-M-y         |                         | (查找状态)将光标处字符复制到查找区域最后     |
| C-f           |                         | (查找状态)将光标处字符复制到查找区域最后     |
| C-s RET       | search-forward          | 向前进行简单查找                             |
| C-r RET       | search-backward         | 向后进行简单查找                             |
| M-s w         | isearch-forward-word    | 向前进行词组查找                             |
| M-s w RET     | word-search-forward     | 向前进行词组查找（非增量方式）               |
| M-s w C-r RET | word-search-backward    | 向后进行词组查找（非增量方式）               |
| C-M-s         | isearch-forward-regexp  | 向前进行正则查找                             |
| C-M-r         | isearch-backward-regexp | 向后进行正则查找                             |
|               | replace-string          | 全文替换                                     |
|               | replace-regexp          | 全文正则替换                                 |
| M-%           | query-replace           | 查找替换                                     |
|               | recursive-edit          | 进入递归编辑                                 |
|               | abort-recursive-edit    | 退出递归编辑                                 |
|               | top-level               | 退出递归编辑                                 |

<details>
<summary style="color:red;">M-% 后：</summary>

---

| 输入       | 响应                                |
| :--------- | :---------------------------------- |
| SPC 或者 y | 替换当前匹配并前进到下一个匹配处    |
| DEL 或者 n | 忽略此次匹配并前进到下一个匹配处    |
| .          | 替换当前匹配并退出                  |
| ,          | 替换当前匹配并停在此处，再按y后前进 |
| !          | 替换所有剩余匹配                    |
| ^          | 回到前一个匹配处                    |
| RET 或者 q | 直接退出                            |
| e          | 修改新字符串                        |
| C-r        | 进入递归编辑状态                    |
| C-w        | 删除当前匹配并进入递归编辑状态      |
| C-M-c      | 退出递归编辑状态，返回查找替换      |
| C-]        | 退出递归编辑状态，同时退出查找替换  |
| C-h        | 显示帮助                            |

---

</details>

## 文本选择

- C-S-e  选中从当前位置到行尾的文本
- C-S-n  从当前位置开始往下选中一行文本
- C-S-p  从当前位置开始往上选中一行文本
- C-S-f  从当前位置开始往后选中一个字符
- C-S-b  从当前位置开始往前选中一个字符
- M-S-f  从当前位置开始往后选中一个单词
- M-S-b  从当前位置开始往前选中一个单词
- M-S-e  选中从当前位置开始到当前句尾的文本
- M-S-a  选中从当前位置开始到当前句首的文本
- `C-S-@ [其他移动操作]` 从一个起始位置，选中连续的字符，比如C-S-@ C-e就表示选中从当前光标位置到行尾的所有字符
- C-S-v  向下选择一屏
- M-S-v  向上选择一屏
- C-S-l  让当前光标所在行居中
- C-x h  全选

## 搜索

- C-s 向下渐进搜素
- C-r 向上渐近搜索

## 重复执行

C-u 重复执行， C-u 8 C-f 会向前移动 8 个字符。

## 窗格(window)和buffer

| 按键      | 命令                                | 功能                                   |
| :-------- | :---------------------------------- | :------------------------------------- |
| C-x 2     | split-window-vertically             | 垂直拆分窗口                           |
| C-x 3     | split-window-horizontally           | 水平拆分窗口                           |
| C-x o     | other-window                        | 选择下一个窗口                         |
| C-M-v     | scroll-other-window                 | 滚动下一个窗口                         |
| C-x 4 b   | switch-to-buffer-other-window       | 在另一个窗口打开缓冲                   |
| C-x 4 C-o | display-buffer                      | 在另一个窗口打开缓冲，但不选中         |
| C-x 4 f   | find-file-other-window              | 在另一个窗口打开文件                   |
| C-x 4 d   | dired-other-window                  | 在另一个窗口打开文件夹                 |
| C-x 4 m   | mail-other-window                   | 在另一个窗口写邮件                     |
| C-x 4 r   | find-file-read-only-other-window    | 在另一个窗口以只读方式打开文件         |
| C-x 0     | delete-window                       | 关闭当前窗口                           |
| C-x 1     | delete-other-windows                | 关闭其它窗口                           |
| C-x 4 0   | kill-buffer-and-window              | 关闭当前窗口和缓冲                     |
| C-x ^     | enlarge-window                      | 增高当前窗口                           |
| C-x {     | shrink-window-horizontally          | 将当前窗口变窄                         |
| C-x }     | enlarge-window-horizontally         | 将当前窗口变宽                         |
| C-x –     | shrink-window-if-larger-than-buffer | 如果窗口比缓冲大就缩小                 |
| C-x +     | balance-windows                     | 所有窗口一样高                         |
|           | windmove-right                      | 切换到右边的窗口(类似：up, down, left) |

- C-x 0  关闭当前窗格
- C-x 1   只保留一个窗格（也就是关掉其它所有窗格）。
- C-x C-b 新窗格列出缓冲区
- C-x b 切换缓冲区
- C-x k 关闭buffer
- C-x s 保存多个缓冲区
- C-x o 移动到其他(other)窗格
- C-x 4 C-f 新窗格打开指定文件

## buffer 管理

输入`M-x buffer-menu`进入`buffer menu`对`buffer`进行管理，操作方式如下：

| 按键                      | 功能                               | 备注      |
| :------------------------ | :--------------------------------- | :-------- |
| SPC, n                    | 移动到下一项                       |           |
| p                         | 移动到上一项                       |           |
| d, k                      | 标记删除缓冲，并移动到下一项       | 按x后生效 |
| C-d                       | 标记删除缓冲，并移动到上一项       | 按x后生效 |
| s                         | 标记保存缓冲                       | 按x后生效 |
| x执行标记删除或保存的缓冲 |                                    |           |
| u                         | 取消当前缓冲的标记，并移动到下一项 |           |
| Backspace                 | 取消当前缓冲的标记，并移动到上一项 |           |
| ~                         | 设置缓冲为未修改                   |           |
| %                         | 切换缓冲的只读属性                 |           |
| 1                         | 将选中缓冲满窗口显示               |           |
| 2                         | 将选中缓冲显示在一半窗口中         |           |
| t                         | 缓冲用tags table 方式显示          |           |
| f, RET                    | 显示选择缓冲                       |           |
| o                         | 缓冲在新窗口显示，并选中该窗口     |           |
| C-o                       | 缓冲在新窗口显示，但不选中该窗口   |           |
| b                         | 将选中缓冲移动到最后一行           |           |
| m                         | 标记缓冲在新窗口显示               | 按v后生效 |
| v                         | 显示标记的缓冲                     |           |
| g                         | 刷新buffer menu                    |           |
| T                         | 切换显示文件关联缓冲               |           |
| q                         | 退出Buffer Menu                    |           |

> 需要注意的是大部分功能是立即生效的，但像d,s,m这些只会起标记作用，在确认之后才会执行，而且按了这三个键后对应会在缓冲名前显示”D”, “S”, “>” 三个符号用作提示。

## 窗口(frame)

- C-x 5 2 打开新窗口(gui下才行)
- C-x 5 0 关闭当前窗口，或者窗口上的x号也行

## 命令集扩展

- C-x 字符扩展
- M-x 命令扩展

## 模式

- 主模式切换
  - M-x fundamental-mode
  - M-x text-mode
- 辅模式，辅助主模式的行为
  - M-x auto-fill-mode 启动/关闭自动折行模式

## 递归编辑

- `M-%` 交互式替换时，C-s进行搜索
- `Esc Esc Esc` “离开”命令，离开递归编辑，也可以用来关闭多余的窗格

## 终止

- C-z 挂起emacs
- C-g 来安全地终止命令
- C-x C-c 退出emacs

## 扩展包

- `M-x list-packages` 浏览所有可安装的软件包

## 帮助

- C-h k key/mouse/item 打开新的窗格显示函数名和文档
- C-h c key/mouse/item 显示后续操作对应的函数名
- C-h x command        解释一个命令
- C-h m 查看当前主模式相关文档
- C-h v 显示Emcas变量的文档
- C-h f 显示Emcas函数的文档
- C-h a 命令搜索
- C-h i 阅读emacs手册

# elisp

# 参考

- [Emacs常用快捷键](https://www.clloz.com/programming/assorted/emacs-vim/2019/04/14/emacs-keybinding/)
- [Emacs Lisp 简明教程](https://smacs.github.io/elisp/)

