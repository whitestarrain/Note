`emacs -nw` 终端模式

# 常用整理

| 按键                    | 命令                                | 功能                                                                              |
| :---------------------- | :---------------------------------- | :-------------------------------------------------------------------------------- |
| **移动**                |
| C-f                     | forward-char                        | 向前一个字符                                                                      |
| C-b                     | backward-char                       | 向后一个字符                                                                      |
| C-p                     | previous-line                       | 上移一行                                                                          |
| C-n                     | next-line                           | 下移一行                                                                          |
| M-f                     | forward-word                        | 向前一个单词                                                                      |
| M-b                     | backward-word                       | 向后一个单词                                                                      |
| C-a                     | beginning-of-line                   | 移到行首                                                                          |
| C-e                     | end-of-line                         | 移到行尾                                                                          |
| M-m                     | back-toindentation                  | 将光标移动到行首的第一个非空白字符                                                |
| M-}                     | forward-paragraph                   | 下移一段                                                                          |
| M-{                     | backward-paragraph                  | 上移一段                                                                          |
| C-v                     | scroll-up                           | 下移一屏                                                                          |
| M-v                     | scroll-down                         | 上移一屏                                                                          |
| C-x ]                   | forward-page                        | 下移一页                                                                          |
| C-x [                   | backward-page                       | 上移一页                                                                          |
| M-<                     | beginning-of-buffer                 | 移到文档头                                                                        |
| M->                     | end-of-buffer                       | 移到文档尾                                                                        |
| M-g g num               | goto-line                           | 移到第n行                                                                         |
| M-g c                   | goto-char                           | 移到第n个字符                                                                     |
| C-l                     | recenter                            | 将当前位置放到页面中间                                                            |
| C-u num                 | universal-argument                  | 重复下个命令n次，n默认为4                                                         |
| C-M-left                | backward-sexp                       | 可用来匹配左括号                                                                  |
| C-M-right               | forward-sexp                        | 可用来匹配右括号                                                                  |
| **删除**                |
| C-d                     | delete-char                         | 删除光标处字符                                                                    |
| Backspace               | delete-backward-char                | 删除光标前字符                                                                    |
| C-x C-o                 | delete-blank-lines                  | 删除光标周围的空白行，保留当前行                                                  |
| `M-\`                   | delete-horizontal-space             | 删除光标处的所有空格和Tab字符                                                     |
| M-SPC                   | just-one-space                      | 删除光标处的所有空格和Tab字符，但留下一个                                         |
| **插入**                |
| C-o                     | open-line                           | 插入空行                                                                          |
| **编辑**                |
| M-^                     | delete-indentation                  | 将两行合为一行，删除之间的空白和缩进                                              |
| C-x C-u                 | upcase-region                       | 将区域中字母改为大写                                                              |
| C-x C-l                 | upcase-region                       | 将区域中字母改为小写                                                              |
| **特殊字符插入**        |
| C-q (n)                 | quoted-insert                       | 插入字符，n表示字符的八进制ASCII码                                                |
| C-x 8                   | ucs-insert                          | 插入Unicode字符，`C-x 8 RET`                                                      |
| **撤销**                |
| C-/                     | undo                                | 撤销                                                                              |
| C-\_                    | undo                                | 撤销                                                                              |
| C-x u                   | undo                                | 撤销                                                                              |
| **文件**                |
| C-x C-f                 | find-file                           | 打开文件                                                                          |
| C-x C-v                 | find-alternate-file                 | 打开另一个文件，kill当前buffer                                                    |
| C-x C-s                 | save-buffer                         | 保存文件                                                                          |
| C-x s                   | save-some-buffers                   | 保存所有文件                                                                      |
| C-x C-w                 | write-file                          | 另存文件                                                                          |
| **查找**                |
| C-s                     | isearch-forward                     | 向前进行增量查找                                                                  |
| C-r                     | isearch-backward                    | 向后进行增量查找                                                                  |
| M-c                     |                                     | (查找状态)切换大写敏感                                                            |
| C-j                     |                                     | newline-and-indent\|(查找状态)输入换行符                                          |
| M-Tab                   | isearch-complete                    | (查找状态)自动匹配（从search ring中）                                             |
| C-w                     |                                     | (查找状态)将光标处单词复制到查找区域                                              |
| M-s w                   | isearch-forward-word                | 向前进行单词查找(依旧使用c-s c-r向后向前查找)                                     |
| **替换**                |
| M-%                     | query-replace                       | 查找替换                                                                          |
| **窗口**                |
| C-x 0                   | delete-window                       | 关闭当前窗口                                                                      |
| C-x 1                   | delete-other-windows                | 关闭其它窗口                                                                      |
| C-x 2                   | split-window-vertically             | 垂直拆分窗口                                                                      |
| C-x 3                   | split-window-horizontally           | 水平拆分窗口                                                                      |
| C-x 4 b                 | switch-to-buffer-other-window       | 在另一个窗口打开缓冲                                                              |
| C-x 4 C-o               | display-buffer                      | 在另一个窗口打开缓冲，但不选中                                                    |
| C-x 4 f                 | find-file-other-window              | 在另一个窗口打开文件                                                              |
| C-x 4 d                 | dired-other-window                  | 在另一个窗口打开文件夹                                                            |
| C-x 4 m                 | mail-other-window                   | 在另一个窗口写邮件                                                                |
| C-x 4 r                 | find-file-read-only-other-window    | 在另一个窗口以只读方式打开文件                                                    |
| C-x 4 0                 | kill-buffer-and-window              | 关闭当前窗口和buffer                                                              |
| C-x o                   | other-window                        | 选择下一个窗口                                                                    |
| **窗口大小**            |
| C-x {                   | shrink-window-horizontally          | 将当前窗口变窄                                                                    |
| C-x }                   | enlarge-window-horizontally         | 将当前窗口变宽                                                                    |
| C-x ^                   | enlarge-window                      | 增高当前窗口                                                                      |
| C-x –                   | shrink-window-if-larger-than-buffer | 如果窗口比缓冲大就缩小                                                            |
| C-x +                   | balance-windows                     | 所有窗口一样高                                                                    |
| **Mark**                |
| C-SPC                   | set-mark-command                    | 在point处开始设置mark                                                             |
| C-x C-x                 | exchange-point-and-mark             | 激活 mark                                                                         |
| M-@                     | mark-word                           | 选取一个单词，不移动point                                                         |
| C-M-@                   | mark-sexp                           | 选中一个balanced expression ，或者说是pair                                        |
| M-h                     | mark-paragraph                      | 选取一段                                                                          |
| C-x C-p                 | mark-page                           | 选取一页                                                                          |
| C-x h                   | mark-whole-buffer                   | 全选                                                                              |
| C-w                     | kill-region                         | 删除区域中内容                                                                    |
| C-SPC C-SPC             |                                     | 设置一个mark，不激活                                                              |
| C-u C-SPC               |                                     | 移动到mark开始位置，并且从mark ring中弹出                                         |
| C-x C-SPC               | pop-global-mark                     | 从global mark ring 中弹出mark并且移动到对应位置（多buffer时才有用）               |
| **Kill**                |
| M-d                     | kill-word                           | 删除光标起单词                                                                    |
| M-Backspace/C-Backspace | backward-kill-word                  | 删除光标前单词，如果有特殊字符，会一并删除。比如`void *` 会看作为一个单词         |
| C-k                     | kill-line                           | 删除光标起当前行                                                                  |
| C-S-Backspace           | kill-whole-line                     | 删除整行                                                                          |
| M-z char                | zap-to-char                         | 删至字符char为止                                                                  |
| M-k                     | kill-sentence                       | 删除光标起句子                                                                    |
| C-x Backspace           | backward-kill-sentence              | 删除光标前句子                                                                    |
| C-0 C-k                 |                                     | 删除当前光标到行首的内容                                                          |
| C-M-k                   | kill-sexp                           | 删除 balanced expression                                                          |
| M-w (复制)              | kill-ring-save                      | 复制到kill 环，而不删除                                                           |
| **粘贴**                |
| C-y                     | yank                                | 召回                                                                              |
| M-y                     | yank-pop                            | C-y 后使用，召回前一个                                                            |
| C-M-w                   | append-next-kill                    | 如果后续的命令是kill，根据kill命令向前或者向后拼接到 kill ring的第一个            |
| **矩形操作**            |
| C-x SPC                 | rectangle-mark-mode                 | 矩形选中                                                                          |
| C-x r k                 | kill-rectangle                      |                                                                                   |
| C-x r M-w               | copy-rectangle-as-kill              |                                                                                   |
| C-x r d                 | delete-rectangle                    |                                                                                   |
| C-x r y                 | yank-rectangle                      |                                                                                   |
| C-x r o                 | open-rectangle                      |                                                                                   |
| C-x r N                 | rectangle-number-lines              |                                                                                   |
| C-x r c                 | clear-rectangle                     |                                                                                   |
| C-x r t                 | string-rectangle                    |                                                                                   |
|                         | delete-whitespace-rectangle         |                                                                                   |
|                         | string-insert-rectangle             |                                                                                   |
| **代码**                |
| M-q                     | prog-fill-reindent-defun            | fill-paragraph 和indent-region 二合一，在代码中时重新缩进，在注释中时填充或者折行 |
| **重复执行**            |
| C-u num                 | universal-argument                  | 重复下个命令n次，n默认为4                                                         |
| C-x z                   | repeat                              | 重复前个命令                                                                      |

# Org
## Document Structure
```
Visibility Cycling:
  TAB (org-cycle)
      Subtree cycling: Rotate current subtree among the states
  S-TAB (org-global-cycle)
  C-u TAB
      Global cycling: Rotate the entire buffer among the states
  C-u C-u TAB (org-cycle-set-startup-visibility)
      Switch back to the startup visibility of the buffer (see Initial visibility).
  C-u C-u C-u TAB (org-show-all)
      Show all, including drawers.
  C-c C-r (org-reveal)
      Reveal context around point, showing the current entry, the following heading and the hierarchy above. It is useful for working near a location that has been exposed by a sparse tree command (see Sparse Trees) or an agenda command (see Commands in the Agenda Buffer). With a prefix argument, show, on each level, all sibling headings. With a double prefix argument, also show the entire subtree of the parent.
  C-c C-k (org-show-branches)
      Expose all the headings of the subtree, but not their bodies.
  C-c TAB (org-show-children)
      Expose all direct children of the subtree. With a numeric prefix argument N, expose all children down to level N.
  C-c C-x b (org-tree-to-indirect-buffer)
      Show the current subtree in an indirect buffer5. With a numeric prefix argument N, go up to level N and then take that tree. If N is negative then go up that many levels. With a C-u prefix, do not remove the previously used indirect buffer.
  C-c C-x v (org-copy-visible)
      Copy the visible text in the region into the kill ring.

Motion:
  C-c C-n (org-next-visible-heading)
      Next heading.
  C-c C-p (org-previous-visible-heading)
      Previous heading.
  C-c C-f (org-forward-heading-same-level)
      Next heading same level.
  C-c C-b (org-backward-heading-same-level)
      Previous heading same level.
  C-c C-u (outline-up-heading)
      Backward to higher level heading.
  C-c C-j (org-goto)
      Jump to a different place without changing the current outline visibility.

Structure Editing:
  M-RET (org-meta-return)
      Insert a new heading, item or row.
      If the command is used at the beginning of a line, and if there is a heading or a plain list item (see Plain Lists) at point, the new heading/item is created before the current line. When used at the beginning of a regular line of text, turn that line into a heading.
      When this command is used in the middle of a line, the line is split and the rest of the line becomes the new item or headline. If you do not want the line to be split, customize org-M-RET-may-split-line.
      Calling the command with a C-u prefix unconditionally inserts a new heading at the end of the current subtree, thus preserving its contents. With a double C-u C-u prefix, the new heading is created at the end of the parent subtree instead.
  C-RET (org-insert-heading-respect-content)
      Insert a new heading at the end of the current subtree.
  M-S-RET (org-insert-todo-heading)
      Insert new TODO entry with same level as current heading. See also the variable org-treat-insert-todo-heading-as-state-change.
  C-S-RET (org-insert-todo-heading-respect-content)
      Insert new TODO entry with same level as current heading. Like C-RET, the new headline is inserted after the current subtree.
  TAB (org-cycle)
      In a new entry with no text yet, the first TAB demotes the entry to become a child of the previous one. The next TAB makes it a parent, and so on, all the way to top level. Yet another TAB, and you are back to the initial level.
  M-LEFT (org-do-promote)
  M-RIGHT (org-do-demote)
      Promote or demote current heading by one level.
      When there is an active region—i.e., when Transient Mark mode is active—promotion and demotion work on all headlines in the region. To select a region of headlines, it is best to place both point and mark at the beginning of a line, mark at the beginning of the first headline, and point at the line just after the last headline to change.
  M-S-LEFT (org-promote-subtree)
      Promote the current subtree by one level.
  M-S-RIGHT (org-demote-subtree)
      Demote the current subtree by one level.
  M-UP (org-move-subtree-up)
      Move subtree up, i.e., swap with previous subtree of same level.
  M-DOWN (org-move-subtree-down)
      Move subtree down, i.e., swap with next subtree of same level.
  C-c @ (org-mark-subtree)
      Mark the subtree at point. Hitting repeatedly marks subsequent subtrees of the same level as the marked subtree.
  C-c C-x C-w (org-cut-subtree)
      Kill subtree, i.e., remove it from buffer but save in kill ring. With a numeric prefix argument N, kill N sequential subtrees.
  C-c C-x M-w (org-copy-subtree)
      Copy subtree to kill ring. With a numeric prefix argument N, copy the N sequential subtrees.
  C-c C-x C-y (org-paste-subtree)
      Yank subtree from kill ring. This does modify the level of the subtree to make sure the tree fits in nicely at the yank position. The yank level can also be specified with a numeric prefix argument, or by yanking after a headline marker like ‘****’. With C-u prefix, force inserting as a sibling. With C-u C-u prefix argument, force inserting as a child.
  C-y (org-yank)
      Depending on the variables org-yank-adjusted-subtrees and org-yank-folded-subtrees, Org’s internal yank command pastes subtrees folded and in a clever way, using the same command as C-c C-x C-y. With the default settings, no level adjustment takes place, but the yanked tree is folded unless doing so would swallow text previously visible. Any prefix argument to this command forces a normal yank to be executed, with the prefix passed along. A good way to force a normal yank is C-u C-y. If you use yank-pop after a yank, it yanks previous kill items plainly, without adjustment and folding.
  C-c C-x c (org-clone-subtree-with-time-shift)
      Clone a subtree by making a number of sibling copies of it. You are prompted for the number of copies to make, and you can also specify if any timestamps in the entry should be shifted. This can be useful, for example, to create a number of tasks related to a series of lectures to prepare. For more details, see the docstring of the command org-clone-subtree-with-time-shift.
  C-c C-w (org-refile)
      Refile entry or region to a different location. See Refile and Copy.
  C-c ^ (org-sort)
      Sort same-level entries. When there is an active region, all entries in the region are sorted. Otherwise the children of the current headline are sorted. The command prompts for the sorting method, which can be alphabetically, numerically, by time—first timestamp with active preferred, creation time, scheduled time, deadline time—by priority, by TODO keyword—in the sequence the keywords have been defined in the setup—or by the value of a property. Reverse sorting is possible as well. You can also supply your own function to extract the sorting key. With a C-u prefix, sorting is case-sensitive.
  C-x n s (org-narrow-to-subtree)
      Narrow buffer to current subtree.
  C-x n b (org-narrow-to-block)
      Narrow buffer to current block.
  C-x n w (widen)
      Widen buffer to remove narrowing.
  C-c * (org-toggle-heading)
      Turn a normal line or plain list item into a headline—so that it becomes a subheading at its location. Also turn a headline into a normal line by removing the stars. If there is an active region, turn all lines in the region into headlines. If the first line in the region was an item, turn only the item lines into headlines. Finally, if the first line is a headline, remove the stars from all headlines in the region.

Sparse Trees:
  C-c / (org-sparse-tree)
      This prompts for an extra key to select a sparse-tree creating command.
  C-c / r or C-c / / (org-occur)
      Prompts for a regexp (see Regular Expressions) and shows a sparse tree with all matches. If the match is in a headline, the headline is made visible. If the match is in the body of an entry, headline and body are made visible. In order to provide minimal context, also the full hierarchy of headlines above the match is shown, as well as the headline following the match. Each match is also highlighted; the highlights disappear when the buffer is changed by an editing command, or by pressing C-c C-c. When called with a C-u prefix argument, previous highlights are kept, so several calls to this command can be stacked.
  M-g n or M-g M-n (next-error)
      Jump to the next sparse tree match in this buffer.
  M-g p or M-g M-p (previous-error)
      Jump to the previous sparse tree match in this buffer.

Plain Lists:
  TAB (org-cycle)
      Items can be folded just like headline levels. Normally this works only if point is on a plain list item. For more details, see the variable org-cycle-include-plain-lists. If this variable is set to integrate, plain list items are treated like low-level headlines. The level of an item is then given by the indentation of the bullet/number. Items are always subordinate to real headlines, however; the hierarchies remain completely separated. In a new item with no text yet, the first TAB demotes the item to become a child of the previous one. Subsequent TABs move the item to meaningful levels in the list and eventually get it back to its initial position.
  M-RET (org-insert-item)
      Insert new item at current level. With a prefix argument, force a new heading (see Structure Editing). If this command is used in the middle of an item, that item is split in two, and the second part becomes the new item. If this command is executed before item’s body, the new item is created before the current one.
  M-S-RET
      Insert a new item with a checkbox (see Checkboxes).
  S-UP
  S-DOWN
      Jump to the previous/next item in the current list, but only if org-support-shift-select is off. If not, you can still use paragraph jumping commands like C-UP and C-DOWN to quite similar effect.
  M-UP
  M-DOWN
      Move the item including subitems up/down, i.e., swap with previous/next item of same indentation. If the list is ordered, renumbering is automatic.
  M-LEFT
  M-RIGHT
      Decrease/increase the indentation of an item, leaving children alone.
  M-S-LEFT
  M-S-RIGHT
      Decrease/increase the indentation of the item, including subitems. Initially, the item tree is selected based on current indentation. When these commands are executed several times in direct succession, the initially selected region is used, even if the new indentation would imply a different hierarchy. To use the new hierarchy, break the command chain by moving point.
      As a special case, using this command on the very first item of a list moves the whole list. This behavior can be disabled by configuring org-list-automatic-rules. The global indentation of a list has no influence on the text after the list.
  C-c C-c
      If there is a checkbox (see Checkboxes) in the item line, toggle the state of the checkbox. In any case, verify bullets and indentation consistency in the whole list.
  C-c -
      Cycle the entire list level through the different itemize/enumerate bullets (‘-’, ‘+’, ‘*’, ‘1.’, ‘1)’) or a subset of them, depending on org-plain-list-ordered-item-terminator, the type of list, and its indentation. With a numeric prefix argument N, select the Nth bullet from this list. If there is an active region when calling this, all lines are converted to list items. With a prefix argument, the selected text is changed into a single item. If the first line already was a list item, any item marker is removed from the list. Finally, even without an active region, a normal line is converted into a list item.
  C-c *
      Turn a plain list item into a headline—so that it becomes a subheading at its location. See Structure Editing, for a detailed explanation.
  C-c C-*
      Turn the whole plain list into a subtree of the current heading. Checkboxes (see Checkboxes) become ‘TODO’, respectively ‘DONE’, keywords when unchecked, respectively checked.
  S-LEFT
  S-RIGHT
      This command also cycles bullet styles when point is in on the bullet or anywhere in an item line, details depending on org-support-shift-select.
  C-c ^
      Sort the plain list. Prompt for the sorting method: numerically, alphabetically, by time, or by custom function.

Drawer
  C-c C-x d
      org-insert-drawer. With a prefix argument, this command calls non-interactive function org-insert-property-drawer, which creates a ‘PROPERTIES’ drawer right below the current headline.
      use M-TAB(C-M-i or ESC TAB ) after ':' to complete over drawer keywords.
      M-TAB aslo can complete at many place
  C-c C-z
      Add a time-stamped note to the ‘LOGBOOK’ drawer.
```

## Table

## hyperlink

## TODO

```
Basic
  C-c C-t (org-todo)
      Rotate the TODO state of the current item among
      ,-> (unmarked) -> TODO -> DONE --.
      '--------------------------------'
      If TODO keywords have fast access keys (see Fast access to TODO states), prompt for a TODO keyword through the fast selection interface; this is the default behavior when org-use-fast-todo-selection is non-nil.
      The same state changing can also be done “remotely” from the agenda buffer with the t command key (see Commands in the Agenda Buffer).
  S-RIGHT S-LEFT
      Select the following/preceding TODO state, similar to cycling. Useful mostly if more than two TODO states are possible (see Extended Use of TODO Keywords). See also Packages that conflict with Org mode, for a discussion of the interaction with shift-selection. See also the variable org-treat-S-cursor-todo-selection-as-state-change.
  C-c / t (org-show-todo-tree)
      View TODO items in a sparse tree (see Sparse Trees). Folds the entire buffer, but shows all TODO items—with not-DONE state—and the headings hierarchy above them. With a prefix argument, or by using C-c / T, search for a specific TODO. You are prompted for the keyword, and you can also give a list of keywords like ‘KWD1|KWD2|...’ to list entries that match any one of these keywords. With a numeric prefix argument N, show the tree for the Nth keyword in the variable org-todo-keywords. With two prefix arguments, find all TODO states, both un-done and done.
  M-x org-agenda t (org-todo-list)
      Show the global TODO list. Collects the TODO items (with not-DONE states) from all agenda files (see Agenda Views) into a single buffer. The new buffer is in Org Agenda mode, which provides commands to examine and manipulate the TODO entries from the new buffer (see Commands in the Agenda Buffer). See The global TODO list, for more information.
  S-M-RET (org-insert-todo-heading)
      Insert a new TODO entry below the current one.

Multiple keyword sets in one file:
  C-u C-u C-c C-t
  C-S-RIGHT
  C-S-LEFT
      These keys jump from one TODO sub-sequence to the next. In the above example, C-u C-u C-c C-t or C-S-RIGHT would jump from ‘TODO’ or ‘DONE’ to ‘REPORT’, and any of the words in the second row to ‘CANCELED’. Note that the C-S- key binding conflict with shift-selection (see Packages that conflict with Org mode).
  S-RIGHT
  S-LEFT
      S-LEFT and S-RIGHT walk through all keywords from all sub-sequences, so for example S-RIGHT would switch from ‘DONE’ to ‘REPORT’ in the example above. For a discussion of the interaction with shift-selection, see Packages that conflict with Org mode.

TODO dependencies:
  C-c C-x o (org-toggle-ordered-property)
      Toggle the ‘ORDERED’ property of the current entry. A property is used for this behavior because this should be local to the current entry, not inherited from entries above like a tag (see Tags). However, if you would like to track the value of this property with a tag for better visibility, customize the variable org-track-ordered-property-with-tag.
  C-u C-u C-u C-c C-t
      Change TODO state, regardless of any state blocking.
  If you set the variable org-agenda-dim-blocked-tasks, TODO entries that cannot be marked as done because of unmarked children are shown in a dimmed font or even made invisible in agenda views (see Agenda Views).

Priorities:
  C-c , (org-priority)
      Set the priority of the current headline. The command prompts for a priority character ‘A’, ‘B’ or ‘C’. When you press SPC instead, the priority cookie, if one is set, is removed from the headline. The priorities can also be changed “remotely” from the agenda buffer with the , command (see Commands in the Agenda Buffer).
  S-UP (org-priority-up)
  S-DOWN (org-priority-down)
      Increase/decrease the priority of the current headline47. Note that these keys are also used to modify timestamps (see Creating Timestamps). See also Packages that conflict with Org mode for a discussion of the interaction with shift-selection.

Breaking Down Tasks into Subtasks:
  C-c C-c
  [/] and [%] are updated each time the TODO status of a child changes, or when pressing C-c C-c on the cookie

Checkboxes:
  C-c C-c (org-toggle-checkbox)
      Toggle checkbox status or—with prefix argument—checkbox presence at point. With a single prefix argument, add an empty checkbox or remove the current one51. With a double prefix argument, set it to ‘[-]’, which is considered to be an intermediate state.
  C-c C-x C-b (org-toggle-checkbox)
      Toggle checkbox status or—with prefix argument—checkbox presence at point. With double prefix argument, set it to ‘[-]’, which is considered to be an intermediate state.
          If there is an active region, toggle the first checkbox in the region and set all remaining boxes to the same status as the first. With a prefix argument, add or remove the checkbox for all items in the region.
          If point is in a headline, toggle checkboxes in the region between this headline and the next—so not the entire subtree.
          If there is no active region, just toggle the checkbox at point.
  C-c C-x C-r (org-toggle-radio-button)
      Toggle checkbox status by using the checkbox of the item at point as a radio button: when the checkbox is turned on, all other checkboxes on the same level will be turned off. With a universal prefix argument, toggle the presence of the checkbox. With a double prefix argument, set it to ‘[-]’.
      C-c C-c can be told to consider checkboxes as radio buttons by setting ‘#+ATTR_ORG: :radio t’ right before the list or by calling M-x org-list-checkbox-radio-mode to activate this minor mode.
  M-S-RET (org-insert-todo-heading)
      Insert a new item with a checkbox. This works only if point is already in a plain list item (see Plain Lists).
  C-c C-x o (org-toggle-ordered-property)
      Toggle the ‘ORDERED’ property of the entry, to toggle if checkboxes must be checked off in sequence. A property is used for this behavior because this should be local to the current entry, not inherited like a tag. However, if you would like to track the value of this property with a tag for better visibility, customize org-track-ordered-property-with-tag.
  C-c # (org-update-statistics-cookies)
      Update the statistics cookie in the current outline entry. When called with a C-u prefix, update the entire file. Checkbox statistic cookies are updated automatically if you toggle checkboxes with C-c C-c and make new ones with M-S-RET. TODO statistics cookies update when changing TODO states. If you delete boxes/entries or add/change them by hand, use this command to get things back into sync.
```

## Tags

```
Setting Tags:
  C-c C-q (org-set-tags-command)
      Enter new tags for the current headline. Org mode either offers completion or a special single-key interface for setting tags, see below. After pressing RET, the tags are inserted and aligned to org-tags-column. When called with a C-u prefix, all tags in the current buffer are aligned to that column, just to make things look nice. Tags are automatically realigned after promotion, demotion, and TODO state changes (see Basic TODO Functionality).
  C-c C-c (org-set-tags-command)
      When point is in a headline, this does the same as C-c C-q.

Tag Searches:
  C-c / m or C-c \ (org-match-sparse-tree)
      Create a sparse tree with all headlines matching a tags search. With a C-u prefix argument, ignore headlines that are not a TODO line.
  M-x org-agenda m (org-tags-view)
      Create a global list of tag matches from all agenda files. See Matching tags and properties.
  M-x org-agenda M (org-tags-view)
      Create a global list of tag matches from all agenda files, but check only TODO items.
```

## Properties and Columns

```
Property:
  M-TAB (pcomplete)
      After an initial colon in a line, complete property keys. All keys used in the current file are offered as possible completions.
  C-c C-x p (org-set-property)
      Set a property. This prompts for a property name and a value. If necessary, the property drawer is created as well.
  C-u M-x org-insert-drawer
      Insert a property drawer into the current entry. The drawer is inserted early in the entry, but after the lines with planning information like deadlines. If before first headline the drawer is inserted at the top of the drawer after any potential comments.
  C-c C-c (org-property-action)
      With point in a property drawer, this executes property commands.
  C-c C-c s (org-set-property)
      Set a property in the current entry. Both the property and the value can be inserted using completion.
  S-RIGHT (org-property-next-allowed-value)
  S-LEFT (org-property-previous-allowed-value)
      Switch property at point to the next/previous allowed value.
  C-c C-c d (org-delete-property)
      Remove a property from the current entry.
  C-c C-c D (org-delete-property-globally)
      Globally remove a property, from all entries in the current file.
  C-c C-c c (org-compute-property-at-point)
      Compute the property at point, using the operator and scope from the nearest column format definition.

Property Searches:
  C-c / m or C-c \ (org-match-sparse-tree)
      Create a sparse tree with all matching entries. With a C-u prefix argument, ignore headlines that are not a TODO line.
  M-x org-agenda m (org-tags-view)
      Create a global list of tag/property matches from all agenda files.
  M-x org-agenda M (org-tags-view)
      Create a global list of tag matches from all agenda files, but check only TODO items.
  C-c / p
      Create a sparse tree based on the value of a property. This first prompts for the name of a property, and then for a value. A sparse tree is created with all entries that define this property with the given value. If you enclose the value in curly braces, it is interpreted as a regular expression and matched against the property values (see Regular Expressions).

  Using column view
    Turning column view on or off
      C-c C-x C-c (org-columns)
          Turn on column view. If point is before the first headline in the file, column view is turned on for the entire file, using the ‘#+COLUMNS’ definition. If point is somewhere inside the outline, this command searches the hierarchy, up from point, for a ‘COLUMNS’ property that defines a format. When one is found, the column view table is established for the tree starting at the entry that contains the ‘COLUMNS’ property. If no such property is found, the format is taken from the ‘#+COLUMNS’ line or from the variable org-columns-default-format, and column view is established for the current entry and its subtree.
      r or g on a columns view line (org-columns-redo)
          Recreate the column view, to include recent changes made in the buffer.
      C-c C-c or q on a columns view line (org-columns-quit)
          Exit column view.
    Editing values
      LEFT, RIGHT, UP, DOWN
          Move through the column view from field to field.
      1..9,0
          Directly select the Nth allowed value, 0 selects the 10th value.
      n or S-RIGHT (org-columns-next-allowed-value)
      p or S-LEFT (org-columns-previous-allowed-value)
          Switch to the next/previous allowed value of the field. For this, you have to have specified allowed values for a property.
      e (org-columns-edit-value)
          Edit the property at point. For the special properties, this invokes the same interface that you normally use to change that property. For example, the tag completion or fast selection interface pops up when editing a ‘TAGS’ property.
      C-c C-c (org-columns-toggle-or-columns-quit)
          When there is a checkbox at point, toggle it. Else exit column view.
      v (org-columns-show-value)
          View the full value of this property. This is useful if the width of the column is smaller than that of the value.
      a (org-columns-edit-allowed)
          Edit the list of allowed values for this property. If the list is found in the hierarchy, the modified values are stored there. If no list is found, the new value is stored in the first entry that is part of the current column view.
    Modifying column view on-the-fly
      < (org-columns-narrow)
      > (org-columns-widen)
          Make the column narrower/wider by one character.
      S-M-RIGHT (org-columns-new)
          Insert a new column, to the left of the current column.
      S-M-LEFT (org-columns-delete)
          Delete the current column.
      M-LEFT (org-columns-move-left)
          Move the current column left.
      M-RIGHT (org-columns-move-right)
          Move the current column right.
      M-UP (org-columns-move-row-up)
          Move the current row up.
      M-DOWN (org-columns-move-row-down)
          Move the current row down.
    Dynamic block:
      org-columns-insert-dblock
          Insert a dynamic block capturing a column view. Prompt for the scope or ID of the view.
          This command can be invoked by calling org-dynamic-block-insert-dblock (C-c C-x x) and selecting “columnview” (see Dynamic Blocks).
      C-c C-c C-c C-x C-u (org-dblock-update)
          Update dynamic block at point. Point needs to be on the ‘#+BEGIN’ line of the dynamic block.
      C-u C-c C-x C-u (org-update-all-dblocks)
          Update all dynamic blocks (see Dynamic Blocks). This is useful if you have several clock table blocks, column-capturing blocks or other dynamic blocks in a buffer.
```

## Date and Times

```
Creating Timestamps
  C-c . (org-timestamp)
      Prompt for a date and insert a corresponding timestamp. When point is at an existing timestamp in the buffer, the command is used to modify this timestamp instead of inserting a new one. When this command is used twice in succession, a time range is inserted.
      When called with a prefix argument, use the alternative format which contains date and time. The default time can be rounded to multiples of 5 minutes. See the option org-time-stamp-rounding-minutes.
      With two prefix arguments, insert an active timestamp with the current time without prompting.
      Canlendr:
        RET	Choose date at point in calendar.
        mouse-1	Select date by clicking on it.
        S-RIGHT	One day forward.
        S-LEFT	One day backward.
        S-DOWN	One week forward.
        S-UP	One week backward.
        M-S-RIGHT	One month forward.
        M-S-LEFT	One month backward.
        >	Scroll calendar forward by one month.
        <	Scroll calendar backward by one month.
        M-v	Scroll calendar forward by 3 months.
        C-v	Scroll calendar backward by 3 months.
        C-.	Select today’s date64
  C-c ! (org-timestamp-inactive)
      Like C-c ., but insert an inactive timestamp that does not cause an agenda entry.
  C-c C-c
      Normalize timestamp, insert or fix day name if missing or wrong.
  C-c < (org-date-from-calendar)
      Insert a timestamp corresponding to point date in the calendar.
  C-c > (org-goto-calendar)
      Access the Emacs calendar for the current date. If there is a timestamp in the current line, go to the corresponding date instead.
  C-c C-o (org-open-at-point)
      Access the agenda for the date given by the timestamp or -range at point (see Weekly/daily agenda).
  S-LEFT (org-timestamp-down-day), S-RIGHT (org-timestamp-up-day)
      Change date at point by one day. These key bindings conflict with shift-selection and related modes (see Packages that conflict with Org mode).
  S-UP (org-timestamp-up), S-DOWN (org-timestamp-down)
      On the beginning or enclosing bracket of a timestamp, change its type. Within a timestamp, change the item under point. Point can be on a year, month, day, hour or minute. When the timestamp contains a time range like ‘15:30-16:30’, modifying the first time also shifts the second, shifting the time block with constant length. To change the length, modify the second time. Note that if point is in a headline and not at a timestamp, these same keys modify the priority of an item (see Priorities). The key bindings also conflict with shift-selection and related modes (see Packages that conflict with Org mode).
  C-c C-y (org-evaluate-time-range)
      Evaluate a time range by computing the difference between start and end. With a prefix argument, insert result after the time range (in a table: into the following column).
Custom time format:
  C-c C-x C-t ( org-toggle-timestamp-overlays )
    Toggle the display of custom formats for dates and times.
Inserting deadlines or schedules:
  C-c C-d (org-deadline)
      Insert ‘DEADLINE’ keyword along with a stamp. The insertion happens in the line directly following the headline. Remove any ‘CLOSED’ timestamp. When called with a prefix argument, also remove any existing deadline from the entry. Depending on the variable org-log-redeadline, take a note when changing an existing deadline68.
  C-c C-s (org-schedule)
      Insert ‘SCHEDULED’ keyword along with a stamp. The insertion happens in the line directly following the headline. Remove any ‘CLOSED’ timestamp. When called with a prefix argument, also remove the scheduling date from the entry. Depending on the variable org-log-reschedule, take a note when changing an existing scheduling time69.
  C-c / d (org-check-deadlines)
      Create a sparse tree with all deadlines that are either past-due, or which will become due within org-deadline-warning-days. With C-u prefix, show all deadlines in the file. With a numeric prefix, check that many days. For example, C-1 C-c / d shows all deadlines due tomorrow.
  C-c / b (org-check-before-date)
      Sparse tree for deadlines and scheduled items before a given date.
  C-c / a (org-check-after-date)
      Sparse tree for deadlines and scheduled items after a given date.
Repeat task:
  C-- 1 C-c C-t(org-todo) mark repeat task to done
Clocking commands
  C-c C-x C-i (org-clock-in)
      Start the clock on the current item (clock-in). This inserts the ‘CLOCK’ keyword together with a timestamp. If this is not the first clocking of this item, the multiple ‘CLOCK’ lines are wrapped into a ‘LOGBOOK’ drawer (see also the variable org-clock-into-drawer). You can also overrule the setting of this variable for a subtree by setting a ‘CLOCK_INTO_DRAWER’ or ‘LOG_INTO_DRAWER’ property. When called with a C-u prefix argument, select the task from a list of recently clocked tasks. With two C-u C-u prefixes, clock into the task at point and mark it as the default task; the default task is always be available with letter d when selecting a clocking task. With three C-u C-u C-u prefixes, force continuous clocking by starting the clock when the last clock stopped.
      While the clock is running, Org shows the current clocking time in the mode line, along with the title of the task. The clock time shown is all time ever clocked in for this task and its children. If the task has an effort estimate (see Effort Estimates), the mode line displays the current clocking time against it75. If the task is a repeating one (see Repeated tasks), show only the time since the last reset of the task76. You can exercise more control over show time with the ‘CLOCK_MODELINE_TOTAL’ property. It may have the values ‘current’ to show only the current clocking instance, ‘today’ to show all time clocked on this task today—see also the variable org-extend-today-until, all to include all time, or auto which is the default77. Clicking with mouse-1 onto the mode line entry pops up a menu with clocking options.
  C-c C-x C-o (org-clock-out)
      Stop the clock (clock-out). This inserts another timestamp at the same location where the clock was last started. It also directly computes the resulting time in inserts it after the time range as ‘=>HH:MM’. See the variable org-log-note-clock-out for the possibility to record an additional note together with the clock-out timestamp78.
  C-c C-x C-x (org-clock-in-last)
      Re-clock the last clocked task. With one C-u prefix argument, select the task from the clock history. With two C-u prefixes, force continuous clocking by starting the clock when the last clock stopped.
  C-c C-x C-e (org-clock-modify-effort-estimate)
      Update the effort estimate for the current clock task.
  C-c C-c or C-c C-y (org-evaluate-time-range)
      Recompute the time interval after changing one of the timestamps. This is only necessary if you edit the timestamps directly. If you change them with S-<cursor> keys, the update is automatic.
  C-S-UP (org-clock-timestamps-up), C-S-DOWN (org-clock-timestamps-down)
      On CLOCK log lines, increase/decrease both timestamps so that the clock duration keeps the same value.
  S-M-UP (org-timestamp-up), S-M-DOWN (org-timestamp-down)
      On ‘CLOCK’ log lines, increase/decrease the timestamp at point and the one of the previous, or the next, clock timestamp by the same duration. For example, if you hit S-M-UP to increase a clocked-out timestamp by five minutes, then the clocked-in timestamp of the next clock is increased by five minutes.
      Only ‘CLOCK’ logs created during current Emacs session are considered when adjusting next/previous timestamp.
  C-c C-t (org-todo)
      Changing the TODO state of an item to DONE automatically stops the clock if it is running in this same item.
  C-c C-x C-q (org-clock-cancel)
      Cancel the current clock. This is useful if a clock was started by mistake, or if you ended up working on something else.
  C-c C-x C-j (org-clock-goto)
      Jump to the headline of the currently clocked-in task. With a C-u prefix argument, select the target task from a list of recently clocked tasks.
  C-c C-x C-d (org-clock-display)
      Display time summaries for each subtree in the current buffer. This puts overlays at the end of each headline, showing the total time recorded under that heading, including the time of any subheadings. You can use visibility cycling to study the tree, but the overlays disappear when you change the buffer (see variable org-remove-highlights-with-change) or press C-c C-c.
The clock table
  org-clock-report
      Insert or update a clock table. When called with a prefix argument, jump to the first clock table in the current document and update it. The clock table includes archived trees.
      This command can be invoked by calling org-dynamic-block-insert-dblock (C-c C-x x) and selecting “clocktable” (see Dynamic Blocks).
  C-c C-c or C-c C-x C-u (org-dblock-update)
      Update dynamic block at point. Point needs to be in the ‘BEGIN’ line of the dynamic block.
  C-u C-c C-x C-u
      Update all dynamic blocks (see Dynamic Blocks). This is useful if you have several clock table blocks in a buffer.
  S-LEFT, S-RIGHT (org-clocktable-try-shift)
      Shift the current ‘:block’ interval and update the table. Point needs to be in the ‘#+BEGIN: clocktable’ line for this command. If ‘:block’ is ‘today’, it is shifted to ‘today-1’, etc.
Effort Estimates
  C-c C-x e (org-set-effort)
      Set the effort estimate for the current entry. With a prefix argument, set it to the next allowed value—see below. This command is also accessible from the agenda with the e key.
  C-c C-x C-e (org-clock-modify-effort-estimate)
      Modify the effort estimate of the item currently being clocked.
Taking Notes with a Relative Timer
  C-c C-x 0 (org-timer-start)
      Start or reset the relative timer. By default, the timer is set to 0. When called with a C-u prefix, prompt the user for a starting offset. The prompt will default to a timer string at point (if any), providing a convenient way to restart taking notes after a break in the process. When called with a double prefix argument C-u C-u, change all timer strings in the active region by a certain amount. This can be used to fix timer strings if the timer was not started at exactly the right moment.
  C-c C-x ; (org-timer-set-timer)
      Start a countdown timer. The user is prompted for a duration. org-timer-default-timer sets the default countdown value. Giving a numeric prefix argument overrides this default value. This command is available as ; in agenda buffers.
  Once started, relative and countdown timers are controlled with the same commands.
  C-c C-x . (org-timer)
      Insert a relative time into the buffer. The first time you use this, the timer starts. Using a prefix argument restarts it.
  C-c C-x - (org-timer-item)
      Insert a description list item with the current relative time. With a prefix argument, first reset the timer to 0.
  M-RET (org-insert-heading)
      Once the timer list is started, you can also use M-RET to insert new timer items.
  C-c C-x , (org-timer-pause-or-continue)
      Pause the timer, or continue it if it is already paused.
  C-c C-x _ (org-timer-stop)
      Stop the timer. After this, you can only start a new timer, not continue the old one. This command also removes the timer from the mode line.
```

## Refiling and Archiving

```
Refile and Copy
  C-c C-w (org-refile)
      Refile the entry or region at point. This command offers possible locations for refiling the entry and lets you select one with completion. The item (or all items in the region) is filed below the target heading as a sub-item. Depending on org-reverse-note-order, it is either the first or last sub-item.
      By default, all level 1 headlines in the current buffer are considered to be targets, but you can have more complex definitions across a number of files. See the variable org-refile-targets for details. If you would like to select a location via a file-path-like completion along the outline path, see the variables org-refile-use-outline-path and org-outline-path-complete-in-steps. If you would like to be able to create new nodes as new parents for refiling on the fly, check the variable org-refile-allow-creating-parent-nodes. When the variable org-log-refile84 is set, a timestamp or a note is recorded whenever an entry is refiled.
  C-u C-c C-w
      Use the refile interface to jump to a heading.
  C-u C-u C-c C-w (org-refile-goto-last-stored)
      Jump to the location where org-refile last moved a tree to.
  C-2 C-c C-w
      Refile as the child of the item currently being clocked.
  C-3 C-c C-w
      Refile and keep the entry in place. Also see org-refile-keep to make this the default behavior, and beware that this may result in duplicated ‘ID’ properties.
  C-0 C-c C-w or C-u C-u C-u C-c C-w (org-refile-cache-clear)
      Clear the target cache. Caching of refile targets can be turned on by setting org-refile-use-cache. To make the command see new possible targets, you have to clear the cache with this command.
  C-c M-w (org-refile-copy)
      Copying works like refiling, except that the original note is not deleted.
  C-c C-M-w (org-refile-reverse)
      Works like refiling, except that it temporarily toggles how the value of org-reverse-note-order applies to the current buffer. So if org-refile would append the entry as the last entry under the target header, org-refile-reverse will prepend it as the first entry, and vice versa.
Moving a tree to an archive file
  C-c C-x C-a ( org-archive-subtree-default ) ¶
      Archive the current entry using the command specified in the variable org-archive-default-command.
  C-c C-x C-s or short C-c $ (org-archive-subtree)
      Archive the subtree starting at point position to the location given by org-archive-location.
  C-u C-c C-x C-s
      Check if any direct children of the current headline could be moved to the archive. To do this, check each subtree for open TODO entries. If none is found, the command offers to move it to the archive location. If point is not on a headline when this command is invoked, check level 1 trees.
  C-u C-u C-c C-x C-s
      As above, but check subtree for timestamps instead of TODO entries. The command offers to archive the subtree if it does contain a timestamp, and that timestamp is in the past.
Internal archiving
  C-c C-x a (org-toggle-archive-tag)
      Toggle the archive tag for the current headline. When the tag is set, the headline changes to a shadowed face, and the subtree below it is hidden.
  C-u C-c C-x a
      Check if any direct children of the current headline should be archived. To do this, check each subtree for open TODO entries. If none is found, the command offers to set the ‘ARCHIVE’ tag for the child. If point is not on a headline when this command is invoked, check the level 1 trees.
  C-c C-TAB (org-cycle-force-archived)
      Cycle a tree even if it is tagged with ‘ARCHIVE’.
  C-c C-x A (org-archive-to-archive-sibling)
      Move the current entry to the Archive Sibling. This is a sibling of the entry with the heading ‘Archive’ and the archive tag. The entry becomes a child of that sibling and in this way retains a lot of its original context, including inherited tags and approximate position in the outline.
```

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
| M-e           | forward-sentence    | 移到句尾                                  |
| M-a           | backward-sentence   | 移到句首                                  |
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
| C-M-left      | backward-sexp       | 可用来匹配左括号                          |
| C-M-right     | forward-sexp        | 可用来匹配右括号                          |

- 其他窗格
  - C-M-v 或 ESC C-v 向下滚动下方窗格
  - C-M-S-v 或 ESC C-S-v 向上滚动下方窗格

## 文件编辑

> 除了Del和C-d其他的删除命令都会按顺序保存起来，用C-y或者M-y来取出，
> 如果想更好的使用undo功能，可以了解undo tree，在Emacs中一切皆可undo，包括undo本身也可以被undo

| 按键            | 命令                    | 功能                                                                      |
| :-------------- | :---------------------- | :------------------------------------------------------------------------ |
| C-q (n)         | quoted-insert           | 插入字符，n表示字符的八进制ASCII码                                        |
| C-x 8           | ucs-insert              | 插入Unicode字符                                                           |
| C-d             | delete-char             | 删除光标处字符                                                            |
| Backspace       | delete-backward-char    | 删除光标前字符                                                            |
| M-d             | kill-word               | 删除光标起单词                                                            |
| M-Backspace     | backward-kill-word      | 删除光标前单词，如果有特殊字符，会一并删除。比如`void *` 会看作为一个单词 |
| C-k             | kill-line               | 删除光标起当前行                                                          |
| M-k             | kill-sentence           | 删除光标起句子                                                            |
| C-x Backspace   | backward-kill-sentence  | 删除光标前句子                                                            |
| (none)          | kill-paragraph          | 删除光标起段落                                                            |
| (none)          | backward-kill-paragraph | 删除光标前段落                                                            |
| C-/             | undo                    | 撤销                                                                      |
| C-\_            | undo                    | 撤销                                                                      |
| C-x u           | undo                    | 撤销                                                                      |
| C-g             | keyboard-quit           | 撤销命令                                                                  |
| C-h t           | help-with-tutorial      | 调出Emacs Tutorial                                                        |
| C-h r           | info-emacs-manual       | 调出Emacs Manual                                                          |
| C-h k (command) | describe-key            | 查看对应command帮助                                                       |
| C-o             | open-line               | 插入空行                                                                  |
| C-x C-o         | delete-blank-line       | 删除空行                                                                  |
| C-x z           | repeat                  | 重复前个命令                                                              |
| C-@             | set-mark-command        | 设定标记                                                                  |
| C-x C-x         | exchange-point-and-mark | 交换标记和光标位置                                                        |
| C-w             | kill-region             | 删除区域中内容                                                            |
| C-x C-u         | upcase-region           | 将区域中字母改为大写                                                      |
| C-x C-l         | upcase-region           | 将区域中字母改为小写                                                      |
| C-x h           | mark-whole-buffer       | 全选                                                                      |
| C-x C-p         | mark-page               | 选取一页                                                                  |
| M-h             | mark-paragraph          | 选取一段                                                                  |
| M-@             | mark-word               | 选取一个单词                                                              |
| C-@ C-@         |                         | 加入点到标记环                                                            |
| C-u C-@         |                         | 在标记环中跳跃                                                            |
| C-x C-@         | pop-global-mark         | 在全局标记环中跳跃                                                        |
| (none)          | transient-mark-mode     | 非持久化标记模式                                                          |
| `M-\`           | delete-horizontal-space | 删除光标处的所有空格和Tab字符                                             |
| M-SPC           | just-one-space          | 删除光标处的所有空格和Tab字符，但留下一个                                 |
| C-x C-o         | delete-blank-lines      | 删除光标周围的空白行，保留当前行                                          |
| M-^             | delete-indentation      | 将两行合为一行，删除之间的空白和缩进                                      |
| C-S-Backspace   | kill-whole-line         | 删除整行                                                                  |
| C-w             | kill-region             | 删除区域                                                                  |
| M-w             | kill-ring-save          | 复制到kill 环，而不删除                                                   |
| M-z char        | zap-to-char             | 删至字符char为止                                                          |
| C-y             | yank                    | 召回                                                                      |
| M-y             | yank-pop                | C-y 后使用，召回前一个                                                    |
| C-M-w           | append-next-kill        | 下一个删掉内容和上次删除合并                                              |
| C-h v           | describe-variable       | 显示变量内容                                                              |
| C-0 C-k         |                         | 删除当前光标到行首的内容                                                  |
| (none)          | append-to-buffer        | 将区域中内容加入到一个buffer中                                            |
| (none)          | prepend-to-buffer       | 将区域中内容加入到一个buffer光标前                                        |
| (none)          | copy-to-buffer          | 区域中内容加入到一个buffer中，删除该buffer原有内容                        |
| (none)          | insert-buffer           | 在该位置插入指定的buffer中所有内容                                        |
| (none)          | append-to-file          | 将区域中内容复制到一个文件中                                              |
| (none)          | cua-mode                | 启用/停用CUA绑定                                                          |
|                 | kill-read-only-ok       | 是否在只读文件启用kill 命令                                               |
|                 | kill-ring               | kill环                                                                    |
|                 | kill-ring-max           | kill环容量                                                                |

- 文件找回
  - `M-x recover-file emacs`定期自动保存文件到其他地方，可以在修改丢失时恢复文件
- 折行
  - C-x f 设置需要折行的长度
  - M-q 手动折行


## 文件操作

| 按键    | 命令                | 功能           |
| :------ | :------------------ | :------------- |
| C-x C-f | find-file           | 打开文件       |
| C-x C-v | find-alternate-file | 打开另一个文件 |
| C-x C-s | save-buffer         | 保存文件       |
| C-x C-w | write-file          | 另存文件       |

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
  - 可以先搜索Info，读一下基础操作
  - `info-apropos` 搜索所有info 文档的 index

## 其他

- `M-:` : minibuffer中执行elisp

# 插件

- Emacs 自力求生指南
  - https://nyk.ma/posts/emacs-write-your-own/
  - https://nyk.ma/posts/emacs-intro/
- [Emacs User Survey 2020 Results](https://emacssurvey.org/2020/)

# Org 使用case

## org-babel

- 执行cpp代码，处理交互输入的场景

  ```
  #+begin_src C++ :results output :cmdline < in.txt
  #include <iostream>
  int main(int argc, char *argv[]) {
    int a;
    std::cin >> a;
    std::cout << a + 1;
    return 0;
  }

  #+end_src

  #+RESULTS:
  : 11
  ```
  ```
  #+name: input_block
  #+BEGIN_SRC elisp :export none :results none
  (completing-read "a=" nil)

  #+END_SRC

  #+BEGIN_SRC C++  :results output :export code :tangle myfile.cpp :var input=input_block
    #include <stdlib.h>
    #include <iostream>
    using namespace std;

    int main()
    {
    int a = atoi(input);
    cout<<a+1;
    }
  #+END_SRC
  ```

# 参考

- [The Emacs Editor](https://www.gnu.org/software/emacs/manual/html_node/emacs/index.html)
  > 这里面有emacs常用的一切，不过大多数文档还是emacs自己里面比较全面
- [Emacs常用快捷键](https://www.clloz.com/programming/assorted/emacs-vim/2019/04/14/emacs-keybinding/)
- [Emacs Lisp 简明教程](https://smacs.github.io/elisp/)

