
# 说明

# others

- `<prefix> :`: 进入tmux命令行模式
  - `<tab>` 补全

- 当前 pane 移动到一个新打开的window: `break-pane`
- 移动pane到指定window
  - 单独一个pane拆出来: `<prefix> !`
  - 将当前所在pane加到指定window: `join-pane [-h/-v] -t <window_index>`
    - `-h`: horizontal-split
    - `-v`: vertical-split

- 修改当前window index: `move-window -t <window_id>`

# 参考资料
