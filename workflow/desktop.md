# lightdm

- ligthdm-slick-greeter issue: no GI_TYPELIB_PATH in wrap script
  - [gobject-introspection](https://discourse.nixos.org/t/python-gobject-introspection-hell/5018)

# dwm: window manager

mime type，默认打开程序设置

- [ranger support swallow](https://github.com/ranger/ranger/issues/2837)
- [tag-previews](https://dwm.suckless.org/patches/tag-previews/)
- [dwm, alt-tab](https://gist.github.com/smblott-github/5cc6595f6627244fdeac897668a19d72)
- [dwm-anybar](https://github.com/mihirlad55/dwm-anybar)
- [stacker](https://dwm.suckless.org/patches/stacker/)
- [status2d](https://dwm.suckless.org/patches/status2d/)
- [tag layouts](https://dwm.suckless.org/patches/taglayouts/)
- [statuscolors](https://dwm.suckless.org/patches/statuscolors/)
- dwm systray
- dwmblocks
  > get cpu usage: <https://rosettacode.org/wiki/Linux_CPU_utilization>

# st: terminal

- [alpha patch with w3m images](https://www.reddit.com/r/suckless/comments/hfyw2a/st_alpha_patch_with_w3m_images/)
  - 可能需要这个： [w3m](https://st.suckless.org/patches/w3m/)，试过了，不行
  - [Ranger issue: No image preview on st](https://github.com/ranger/ranger/issues/1474)
  - [st FAQ](https://git.suckless.org/st/file/FAQ.html#l177)
    - 可能是 st 刷新机制导致的，ranger中设置0.1s延迟偶尔似乎能展示出图片
  - 或者就用 ueberzug 代替w3m
  - 或者用 alacritty或urxvt 代替 st
  - st 需要第三方patch支持 sixel
- vertcenter
- scrollback
- boxdraw_v2
- nerd icon truncate 问题：[Support for proper glyph rendering in ST](https://www.reddit.com/r/suckless/comments/jt90ai/update_support_for_proper_glyph_rendering_in_st/)

# dmenu: dynamic menu

- suckless 的动态菜单，从 stdin 读取选项，输出用户选择到 stdout
- 默认用法：`echo -e "option1\noption2" | dmenu`
- 常见用途：应用启动器、密码选择、脚本交互
- 通过 patch 扩展功能：fuzzy matching、center 居中显示、lineheight 行高等
- 配置方式与 dwm 相同：修改 config.h 后重新编译

# dunst: notification

- 轻量级通知守护进程，支持自定义样式和规则
- 配置文件：`~/.config/dunst/dunstrc`
- `dunstctl close` 关闭当前通知，`dunstctl history-pop` 显示历史通知
- 支持按应用名、urgency 等条件设置不同样式规则
- 通过 `notify-send "title" "body"` 发送测试通知

# picom: compositor

一些动画需要额外的fork: [pijulius/picom](https://github.com/pijulius/picom)

# clipmenu: clipboard manager

<https://wiki.archlinux.org/title/Clipboard>

- [clipmenu](https://github.com/cdown/clipmenu)
- [clipq](https://github.com/hluk/CopyQ)
- [greenclip](https://github.com/erebe/greenclip)

# skippy-xd: client switcher

- X11 下的窗口切换预览工具，类似 macOS Expose 效果
- 展示所有窗口的实时缩略图，点击或键盘选择切换
- 配置文件：`~/.config/skippy-xd/skippy-xd.rc`
- 通常绑定快捷键触发：`skippy-xd --activate-window-picker`
- 支持多显示器和多工作区

# firefox: browser

- https://sspai.com/post/58605
- [make smooth scrolling in firefox](https://www.reddit.com/r/firefox/comments/1cb0yt8/how_to_make_smooth_scrolling_in_firefox/)
- [better firefox](https://github.com/yokoffing/Betterfox)

- firefox 主题
  - [MaterialFox](https://github.com/muckSponge/MaterialFox)
  - [Firefox-Rounded-Corners](https://github.com/Khalylexe/Firefox-Rounded-Theme)

# status bar

## dwm's bar

- dwm 内置的状态栏，通过 `xsetroot -name` 设置内容
- 使用 shell 脚本循环更新：
  ```bash
  while true; do
    xsetroot -name "$(date '+%Y-%m-%d %H:%M') | $(cat /sys/class/power_supply/BAT1/capacity)%"
    sleep 30
  done
  ```
- 支持通过 status2d patch 添加颜色
- 也可以使用 dwmblocks 实现模块化、可点击的状态栏

## polybar

- 高度可自定义的状态栏，支持丰富的内置模块
- 配置文件：`~/.config/polybar/config.ini`
- 内置模块：cpu, memory, network, date, battery, pulseaudio 等
- 支持自定义脚本模块和 IPC 通信
- 启动：`polybar mybar &`

## lemonbar

- 极简的状态栏，通过管道接收文本输入
- 使用方式：`echo "%{l}Left %{c}Center %{r}Right" | lemonbar`
- 支持简单的格式标记：颜色、对齐、点击事件
- 适合搭配 shell 脚本使用，灵活度高

## waybar

- Wayland 下的状态栏，类似 polybar 但原生支持 wayland
- 配置文件：`~/.config/waybar/config` (JSON) 和 `style.css`
- 支持 Hyprland、Sway 等 wayland 合成器的工作区模块
- 内置大量模块，也支持自定义脚本

# widges

## rofi

- dmenu 的现代替代品，支持窗口切换、应用启动、脚本模式
- 启动应用：`rofi -show drun`
- 窗口切换：`rofi -show window`
- 运行命令：`rofi -show run`
- 支持自定义主题（CSS 风格配置）
- 配置文件：`~/.config/rofi/config.rasi`

## eww

- Elkowar's Wacky Widgets，使用 Yuck 语言定义桌面组件
- 支持创建侧边栏、仪表盘、通知中心等自定义组件
- 配置目录：`~/.config/eww/`
- 基本命令：
  - `eww open mywidget` 打开组件
  - `eww close mywidget` 关闭组件
  - `eww reload` 重新加载配置

## quickshell

比eww更好

[Hyprland's 4th ricing competition](https://www.youtube.com/watch?v=DxEKF0cuEzc)

[quickshell](https://quickshell.outfoxxed.me/)

# wallpaper

## dynamic wallpaper

- pixel wallpaper:
  - artist: waneella
  - 8bit style gif: <https://www.8bitdash.com/>
- [paperview](https://github.com/glouw/paperview)
  - reddit: <https://www.reddit.com/r/unixporn/comments/i1dr44/dwm_high_performance_animated_wallpapers/?rdt=48263>
  - high performance a
- [asetroot](https://github.com/Wilnath/asetroot)
- [hidamari](https://github.com/jeffshee/hidamari)
- [Xwinwrap + mpv](https://www.linuxuprising.com/2019/05/livestream-wallpaper-for-your-gnome.html)

## color schema

catppuccin: <https://github.com/catppuccin/catppuccin>:

- make wallpaper fit colorscheme[farbenfroh](https://farbenfroh.io/)

---

others:

- generate color schema throw wallpaper: [pywal](https://github.com/dylanaraps/pywal)
- convert anything in the most popular palettes: [ImageGoNord-web](https://github.com/Schroedinger-Hat/ImageGoNord-web)
- Recolor images to a certain palette: [repalette](https://github.com/ziap/repalette)

# music

- Mpris(Media Player Remote Interfacing Specification)
- play music: mpd + ncmpcpp

# other apps

- icon, cursor theme, grub theme...: [pling](https://www.pling.com/)

# memo

- get window name: run `xprop` and then right click the window

- debug dwm

  ```bash
  Xephyr -br -ac -noreset -screen 800x600 :1
  DISPLAY=:1 dwm
  ```

- show key press info:

  ```bash
  xinput --test-xi2 --root
  ```
  ```
  xev
  ```
- cursor theme:
  - https://wiki.archlinux.org/title/Cursor_themes
  - same problem: <https://www.reddit.com/r/NixOS/comments/1fmawjl/dwm_statusbar_ignoring_cursor_theme/?show=original>
  - patch for st: <https://st.suckless.org/patches/themed_cursor/>
  - x11 cursor shape: <https://tronche.com/gui/x/xlib/appendix/b/>
  - The following cursor themes need to be set separately
    - X
    - gtk
    - qt
    - suckless: dwm and st, (need patch)
      - check .xresource ?
- control config:
  - wifi: `impala`
  - brightnes: edit `/sys/class/backlight/nvidia_0/brightness`
  - bluetooth: `bluetuith`
  - sound: `pulsmeixer`

# reference

- x window / x11 / xorg
  - https://www.zhihu.com/question/321725817
- x11 compositor
  - [picom](https://wiki.archlinux.org/title/picom)
  - picom, more animation fork
    - [picom-jonaburg-git](https://github.com/jonaburg/picom)
    - [picom-pijulius](https://github.com/pijulius/picom)
- window manager
  - x11
    - i3: <https://github.com/rahriver/Arch-Linux>
    - bspwm: <https://github.com/baskerville/bspwm>
    - dwm fork
      - [dusk](https://github.com/bakkeby/dusk)
    - dwm
      - <https://github.com/Rashad-707/dotfiles>
      - <https://github.com/ayamir/dotfiles>
      - <https://github.com/seeingangelz/dotfiles?tab=readme-ov-file>
      - [从0开始打造完美Manjaro DWM 桌面工作环境](https://mq.rs/0manjaro-dwm): <https://github.com/Arstman/dwm-dotfiles>
      - [gylt37's dwm-config](https://github.com/gylt37/dwm-config)
      - [tagmask](https://dwm.suckless.org/customisation/tagmask/)
    - xmonad
  - wayland
    - [sway](https://github.com/swaywm/sway)
    - [hyprland](https://github.com/hyprwm/Hyprland)
    - [niri](https://github.com/YaLTeR/niri)
    - [dwl](https://github.com/djpohly/dwl)
- dwm
  - [dwm arch cn wiki](https://wiki.archlinuxcn.org/wiki/Dwm)
  - [dwm配置](https://zocoxx.com/archlinux-dwm-incomplete-guide.html)
  - [ArchLinux+DWM不完全指北](https://zocoxx.com/archlinux-dwm-incomplete-guide.html)
  - [dwm patches 中文说明](https://github.com/Katzeee/dwm-patches-chinese/tree/master)
  - [dwm status bar list](https://dwm.suckless.org/status_monitor/)
    - 可以使用c实现，也可以使用bash脚本或者python、go啥的
  - [chadwm: Making dwm as beautiful as possible](https://github.com/siduck/chadwm)
- bar
  - [polybar](https://github.com/polybar/polybar)
  - [waybar](https://github.com/Alexays/Waybar)
- widget
  - [eww, status bar and widget](https://github.com/elkowar/eww)
  - [rofi, replace dmenu, rainmeter, utools](https://github.com/davatorium/rofi)
    - [A huge collection of Rofi based custom Applets, Launchers & Powermenus.](https://github.com/adi1090x/rofi)
  - conky
- notify
  - [Dunst, Notification](https://github.com/dunst-project/dunst)
- 可自动构建 patch 的 dwm 和 dmenu
  - [dmenu-flexipatch](https://github.com/bakkeby/dmenu-flexipatch)
  - [dwm-flexipatch](https://github.com/bakkeby/dwm-flexipatch)
  - [patches](https://github.com/bakkeby/patches/wiki)
- dotfiles:
  - 比较喜欢的一个配置：<https://github.com/elbachir-one/dotfiles.git>
  - nix hyprland config: <https://github.com/Frost-Phoenix/nixos-config>
  - [archlinux配置文件，包括picom混色器。该作者的一些tmux等配置文件也可以参考下，比如tmux插件。](https://github.com/shendypratamaa/.arch)
  - [gh0stzk/dotfiles](https://github.com/gh0stzk/dotfiles)
    > 参考优化下 picom 动画配置？
- rice
  - [dwm archlinux config ayamir](https://github.com/ayamir/dotfiles)
  - [end-4/dots-hyprland](https://github.com/end-4/dots-hyprland)
  - [caelestia-dots/shell](https://github.com/caelestia-dots/shell)
    - [quickshell showcase](https://quickshell.org/)
- others
  - [awesome-tuis](https://github.com/rothgar/awesome-tuis)
  - [xrandr: 处理缩放，显示方向等设置](https://wiki.archlinux.org/title/Xrandr)
