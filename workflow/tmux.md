# 说明

```bash
man tmux
```

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

# 样式配置注意

- window-status-format 会覆盖 window-status-activity-style 的 style
  - 只有 window-status-format 中没有配置fg等啥的时候，window-status-activity-style才会生效
  - 也就是format会覆盖style
  - 可以尝试在 window-status-format 中用 window_activity_flag，进行 if else 判断
    > [参考](https://github.com/mhartington/dotfiles/blob/main/config/tmux/tmux-status.conf)

    ```tmux
    # Not active window
    #{?window_activity_flag,#[fg=white bg=#343d46],#[fg=white bg=#343d46]} #I 
    set-window-option -g window-status-format "\
    #{?window_activity_flag,#[bg=#343d46 fg=#343d46],#[fg=#343d46 bg=#343d46]} \
    #{?window_activity_flag,#[bg=#ffffff fg=#343d46],#[fg=#ffffff bg=#343d46]}#I   \
    #{?window_activity_flag,#[bg=#fac863 fg=#343d46 bold],#[fg=white bg=#343d46]}#W \
    #{?window_activity_flag,#[bg=#343d46 fg=#343d46],#[fg=#343d46 bg=#343d46]}"

    # Active window
    set-window-option -g window-status-current-format "\
    #[fg=#343d46 bg=blue] \
    #[fg=#ffffff bg=blue]#I   \
    #[fg=#ffffff bg=blue bold]#W \
    #[fg=blue bg=#343d46]"
    ```
- 另外，`[default]` 颜色可能是从其他配置中得来的(man tmux, STYLES一节)，
  - 比如`window-status-format`中的`[default]` 应该就是`window-status-style`
  - `status-right` 中的`[default]` 来自 `set status-fg=cyan`

# 参考资料

