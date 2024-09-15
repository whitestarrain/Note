# poker2

右手手掌按 pn 或者 fn+pn

对 fn+pn 也进行映射防止误触

## capslock 和 ctrl互换

poker2 没有开关支持，只能靠软件了

- windows: remapkey
- macos: 可以自行设置
- linux:
  - gui: xmodmap
  - tty: /etc/default/keyboard

## 上下左右

- pn+h    : left
- pn+j    : down
- pn+k    : up
- pn+l    : right
- fn+pn+h : left
- fn+pn+j : down
- fn+pn+k : up
- fn+pn+l : right

## esc 和 shift

目标：`pn+e: esc`, `pn+f: shift`

- 编程层，想要e+f的时候，esc 和 fshift都按上
- e 和 f 单独设置好之后

  ```
  pn + e = esc
  pn + f = shift
  pn + fn + e = esc
  pn + fn + f = shift
  ```
- 此时会发现下面的键位无法切换输入法

  ```
  <esc> down
  <shift> down
  <esc> up
  <shift> up

  # 需要下面这种键位才行，但是很难受:
  <esc> press
  <shift> press
  <shift> up
  <esc> up
  ```
- 为了解决上面这个问题，需要重新设置 `e` 的键位

  ```
  # esc 和 shift要同时按下。(shift早点)
  pn + e = (esc shift)
  pn+ fn + e = (esc shift)
  ```
- 使用keycastow查看键位

  ```
  pn+e = [<shift> <shift-esc>] # shift不会生效，但是esc会
  pn+f = <shift>
  ```
- 配置完了记得检查下 pn + hjkl 又没有受影响
