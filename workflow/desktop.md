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

# dunst: notification

# picom: compositor

动画需要额外的fork: [pijulius/picom](https://github.com/pijulius/picom)

# clipmenu: clipboard manager

<https://wiki.archlinux.org/title/Clipboard>

- [clipmenu](https://github.com/cdown/clipmenu)
- [clipq](https://github.com/hluk/CopyQ)
- [greenclip](https://github.com/erebe/greenclip)

# skippy-xd: client switcher

# firefox: browser

- https://sspai.com/post/58605
- [make smooth scrolling in firefox](https://www.reddit.com/r/firefox/comments/1cb0yt8/how_to_make_smooth_scrolling_in_firefox/)
- [better firefox](https://github.com/yokoffing/Betterfox)

- firefox 主题
  - [MaterialFox](https://github.com/muckSponge/MaterialFox)
  - [Firefox-Rounded-Corners](https://github.com/Khalylexe/Firefox-Rounded-Theme)

# status bar

## dwm's bar

## polybar

## lemonbar

## waybar

# widges

## rofi

## eww

# dynamic wallpaper

- [paperview](https://github.com/glouw/paperview)
  - reddit: <https://www.reddit.com/r/unixporn/comments/i1dr44/dwm_high_performance_animated_wallpapers/?rdt=48263>
  - pixel gif artist: waneella
  - 8bit style gif: <https://www.8bitdash.com/>
- [hidamari](https://github.com/jeffshee/hidamari)
- [Xwinwrap + mpv](https://www.linuxuprising.com/2019/05/livestream-wallpaper-for-your-gnome.html)

# other apps

- play music: mpd + ncmpcpp
- generate color schema throw wallpaper: [pywal](https://github.com/dylanaraps/pywal)
- icon, cursor theme, grub theme...: [pling](https://www.pling.com/)
- convert anything in the most popular palettes: [ImageGoNord-web](https://github.com/Schroedinger-Hat/ImageGoNord-web)

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
  - sway
  - hyprland
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
  - nix hyperland config: <https://github.com/Frost-Phoenix/nixos-config>
  - [archlinux配置文件，包括picom混色器。该作者的一些tmux等配置文件也可以参考下，比如tmux插件。](https://github.com/shendypratamaa/.arch)
  - [dwm archlinux config ayamir](https://github.com/ayamir/dotfiles)
  - [gh0stzk/dotfiles](https://github.com/gh0stzk/dotfiles)
    > 参考优化下 picom 动画配置？
- others
  - [awesome-tuis](https://github.com/rothgar/awesome-tuis)
  - [xrandr: 处理缩放，显示方向等设置](https://wiki.archlinux.org/title/Xrandr)
