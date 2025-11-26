# Font

## Nerd Font

打上icon patch的字体

3.0 版本后，icon 码点有变化，需要确定好自己环境下icon的码点 3.0 版本还是 3.0 之前的版本

https://github.com/ryanoasis/nerd-fonts/issues/1190

## Iosevka: 等宽字体

https://github.com/be5invis/Iosevka

## victor-mono: 关键字手写体

https://github.com/rubjo/victor-mono

# Workflow

## search

### grep

### ag

### ripgreg

### fzf

[fzf theme generator](https://vitormv.github.io/fzf-themes/)

others:

- skim
- atuin

### fd

[fd](https://github.com/sharkdp/fd?tab=readme-ov-file)

简单、快速、友好的 find 替代品

## quick cd

### z

### zoxide

### z.lua

## file manager

### ranger

### lf

## stow: symlink manager

管理文件夹之间的映射

[参考](https://github.com/chaneyzorn/dotfiles)

使用 [GNU Stow](https://www.gnu.org/software/stow/) 管理的 linux 配置文件.

Gnu Stow 使用两个文件夹来管理两个文件树之间的映射，分别是：

- `stow dir`：默认为当前文件夹
- `target dir`：默认为当前文件夹的父文件夹

`stow dir`下每一个顶层的子文件夹都是一个单独的文件树,
`target dir`下是多个这样的文件树在同一起始路径的层叠展开，后者的文件树使用**符号链接**指向前者的文件树。

### 收集配置文件场景

第一次收集配置文件时，推荐使用`--adopt`选项转移配置文件。具体操作如下：

1. 在家目录下创建`stow dir`， 比如命名为 dotfiles。

2. 在`stow dir`下分门别类的创建好各类配置文件的文件夹，比如专门放置 bash 相关配置文件的文件夹。

3. 在 bash 文件夹下创建对应的配置文件，文件内容为空即可，比如`.bashrc`，`.bash_profile`。

4. 在`stow dir`下执行`stow --adopt bash`，这一命令分别对比`target dir`（也即是家目录）和 bash 目录的结构，
   并将家目录下同树结构的文件采纳到`stow dir`下：将`.bashrc`移到bash目录覆盖掉对应的空配置文件，
   并在家目录创建符号链接指向转移过来的配置文件。

5. 重复上述 2-4 步，陆续添加其他文件夹和配置文件。

收集到配置文件后，我们一般使用 git 来管理，这样可以方便的跟踪修改，也可以方便的拉取到另一台电脑上。
实际上，我们上面使用的`--adopt` 命令可以结合 git 来方便的对比两台电脑间配置文件的不同。
`--adopt`选项会处理 stow 遇到的冲突文件，并统一采用`target dir`下的文件**替换**`stow dir` 下的文件，
当`stow dir`被 git 管理时，被替换的文件就可以方便的和仓库中的文件对比，方面你修改，采纳，或者删除。

**注意**: `--adopt`选项会修改`stow dir`下的内容，非第一次收集配置文件，或者`stow dir`没有被版本控制软件管理前，请谨慎使用。

### 将收集的配置文件应用到新的系统中

将项目拉取到新的机器的家目录，切换到`stow dir`，执行`stow -S bash`可以将bash的配置文件展开到家目录下。
你也可以使用多个组合命令：`stow -S pkg1 pkg2 -D pkg3 pkg4 -S pkg5 -R pkg6`

- `-d` 指定 stow 文件夹
- `-t` 指定 target 文件夹
- `-D` 移除已创建的文件树
- `-S` 创建指定的文件树
- `-R` 移除并重新创建指定的文件树

- `--ignore=regexp` 忽略`stow dir`下指定匹配模式的文件
- `--defer=regexp` 跳过`target dir`下指定匹配模式的文件
- `--override=regexp` 强制替换`target dir`下指定匹配模式的文件

- `--no-folding` stow 默认创建最少的符号链接。这一选项会使 stow 逐一创建每一个文件的符号链接，而不是创建一整个文件夹的链接。
- `--dotfiles` 在 stow dir 下的文件名如果有`dot-`前缀，在创建链接时，链接名字会替换为以`.`为前缀，
  比如：`～/.zshrc -> dotfiles/zsh/dot-zshrc`

GNU Stow 不会对冲突的文件做任何处理，并中断所有操作，我没有找到一个快捷的覆盖已存在文件的选项，
`-D` 和 `--override=regexp` 并不能对已存在的文件进行覆盖替换，除非它是符号链接或者文件夹并已经处于`stow dir`的管理之下。

如果因为文件已经存在而发生了冲突，一个直接的办法是先删掉已存在的文件。而另一个方法则是结合使用 `--adopt`选项和 git。
首先将已存在的配置文件全部采纳到 git 管理之下的 stow dir，然后选择性的采纳，修改，或舍弃，
使用 git 的 commit 和 checkout，你可以方便的取舍冲突的配置文件。
如果你知道stow更好的方法，请在 issue 中告诉我，谢谢～

更多信息请参见 [GNU Stow 手册页](https://www.gnu.org/software/stow/manual/stow.html)

### 软件包备份和恢复

```sh
# 获取当前系统中主动安装的包
pacman -Qqet > pkglist.txt
# 从列表文件安装软件包
pacman -S --needed - < pkglist.txt
# 如果其中包含AUR等外部包，需要过滤后再执行
pacman -S --needed $(comm -12 <(pacman -Slq | sort) <(sort pkglist.txt))
# 移除没有列在文件中的包
pacman -Rsu $(comm -23 <(pacman -Qq | sort) <(sort pkglist.txt))
```

更多信息请参见 [pacman archlinux wiki](https://wiki.archlinux.org/index.php/Pacman/Tips_and_tricks#Install_packages_from_a_list)

## leetcode

### leetcode-cli

- [链接](https://github.com/skygragon/leetcode-cli)
- 说明：拿命令行进行刷题

  ![leetcode-cli](./image/leetcode-cli-1.gif)

- 注意：
  - 官方那个默认连美国leetcode
  - 而且leetcode.cn装插件还需要全局翻墙(proxifier)
  - 登录上去后，还有404报错
- 解决：用下面这个修改过的就行
  - `npm install -g ketankr9/leetcode-cli`。
  - [参考链接](https://github.com/skygragon/leetcode-cli/issues/201)

### leetgo

- [leetgo](https://github.com/j178/leetgo)
- 支持leetcode-cn

## monitor

### htop

[htop](https://github.com/htop-dev/htop)

### bashtop

[bashtop](https://github.com/aristocratos/bashtop)

## terminal

### urxvt

### st

### alacritty

### wezterm

## delta: git pager

`~/.gitconfig`:

```
[core]
    pager = delta

[interactive]
    diffFilter = delta --color-only

[delta]
    navigate = true    # use n and N to move between diff sections

    # delta detects terminal colors automatically; set one of these to disable auto-detection
    # dark = true
    # light = true

[merge]
    conflictstyle = diff3

[diff]
    colorMoved = default
```

## thefuck: autofix command

- [thefuck](https://github.com/nvbn/thefuck)

## bat: better cat

## tldr: cheatsheet

# Development

## croc 文件传输

- 基于go语言

## cloc 代码统计

代码行数统计工具

- perl版本
- node版本

## entr 监控文件修改

https://github.com/eradman/entr

## utools

## regex101 正则工具

- [regex101](https://github.com/nedrysoft/regex101)

## calcurse 终端日历

vim like 键位的日程管理工具

## whistle http 抓包工具

[whistle](https://github.com/avwo/whistle)

## Charles 手机端抓包工具

## curl 获取ip

```
curl http://ifconfig.me
```

## madness markdown预览server

[madness](https://github.com/DannyBen/madness)

支持docker部署

## jq json处理命令行工具

## Tengine

淘宝基于 nginx 进行优化开发的版本

## supervisor

## ab

压测工具

## hey

压测工具

## Die (Detect It Easy)

支持查看 elf 在内多种文件类型的反汇编与查看。

## Justfile

## 内网穿透frp

### 基本说明

- 说明
  - 简单地说，frp就是一个反向代理软件，
  - 它体积轻量但功能很强大，可以使处于内网或防火墙后的设备对外界提供服务，
  - 支持HTTP、TCP、UDP等众多协议
- 原理图

  ![linux-1](./image/linux-1.png)

### 服务端设置

- **部署在vps上**
- 下载frp
  ```bash
  wget https://github.com/fatedier/frp/releases/download/v0.33.0/frp_0.33.0_linux_amd64.tar.gz
  ```
- 解压
  > 此处解压到 `/opt/frp`
- 编辑服务端配置文件`frps.ini`
  ```ini
  [common]
  # frp监听的端口，默认是7000，可以改成其他的
  bind_port = 7000
  # 授权码，请改成更复杂的
  token = 13730395968

  # frp管理后台端口，请按自己需求更改
  dashboard_port = 7500
  # frp管理后台用户名和密码，请改成自己的
  dashboard_user = admin
  dashboard_pwd = 13730395968
  enable_prometheus = true

  # frp日志配置
  log_file = /var/log/frps.log
  log_level = info
  log_max_days = 3

  vhost_http_port = 10080
  vhost_https_port = 10443
  ```
- 新建一个启动文件`start_server.sh`
  ```bash
  nohup ./frps -c frps.ini >& output.log &
  # chmod +x ./start_server.sh
  # jobs 查看nohup运行任务
  ```

- 启动：`./start_server.sh`

### 客户端设置

- 安装
  ```bash
  scoop install frp
  # 使用scoop软件管理器安装
  ```
- 配置`frpc.ini`
  ```ini
  # 服务端配置
  [common]
  server_addr = 服务器ip
  # 请换成设置的服务器端口
  server_port = 7000
  # 换成服务端设置的token
  token = 13730395968

  # 配置ssh服务
  [ssh]
  type = tcp
  local_ip = 127.0.0.1
  local_port = 22
  remote_port = 自定义的远程服务器端口，例如2222

  # 配置http服务，可用于小程序开发、远程调试等
  [web]
  type = http
  local_ip = 127.0.0.1
  local_port = 8080
  subdomain = test.hijk.pw
  remote_port = 自定义的远程服务器端口，例如8080

  [rdp] # 即Remote Desktop 远程桌面，Windows的RDP默认端口是3389，协议为TCP，
        # 建议使用frp远程连接前，在局域网中测试好，能够成功连接后再使用frp穿透连接。
  type = tcp
  local_ip = 127.0.0.1
  local_port = 3389
  remote_port = 7001

  [smb] # SMB，即Windows文件共享所使用的协议，默认端口号445，协议TCP，本条规则可实现远程文件访问。
  type = tcp
  local_ip = 127.0.0.1
  local_port = 445
  remote_port = 7002
  ```
  ```
  说明：
    “[xxx]”表示一个规则名称，自己定义，便于查询即可。
    “type”表示转发的协议类型，有TCP和UDP等选项可以选择，如有需要请自行查询frp手册。
    “local_port”是本地应用的端口号，按照实际应用工作在本机的端口号填写即可。
    “remote_port”是该条规则在服务端开放的端口号，自己填写并记录即可。
  ```

- 注意：**一个服务端可以同时给多个客户端使用**
- 启动客户端 `./frpc.exe -c frpc.ini`

## perf_events

perf，c 性能检查工具， 可以搭配 [flamegraph](https://github.com/brendangregg/FlameGraph) 使用

## flatpak

<https://ubuntukylin.github.io/wiki/快速入门/4.其他/震惊！没想到你是这样的flatpak/>

用于构建，分发，安装和运行应用程序，支持多版本安装，运行时隔离，限制访问的目录权限、网络、DBUS等。

主要基于：

- bubblewrap：依赖它作为沙箱的底层实现, 限制了应用程序访问操作系统或用户数据的能力，并且提供了非特权用户使用容器的能力。
- Systemd：将各个subsystem和cgroup树关联并挂载好，为沙箱创建 cgroups。
- D-Bus, 为应用程序提供高层APIs。
- 使用Open Container Initiative的OCI格式作为单文件的传输格式，方便传输。
- 使用OSTree系统用于版本化和分发文件系统树。
- 使用Appstream 元数据，使得Flatpak应用程序在软件中心可以完美呈现出来。

而其中最重要的当属bubblewrap，它是整个应用沙箱构建的关键，主要利用了如下内核特性：

- Namespaces: 命名空间是对全局系统资源的一个封装隔离，使得处于不同namespace的进程拥有独立的全局系统资源，改变一个namespace中的系统资源只会影响当前namespace里的进程，对其他namespace中的进程没有影响。它控制了进程的可见范围，例如网络、挂载点、进程等等。同时使得非特权用户可以创建沙箱。它有以下几类：
  - Mount namespace (CLONE_NEWNS):
    - 用来隔离文件系统的挂载点, 使得不同的mount namespace拥有自己独立的挂载点信息，不同的namespace之间不会相互影响，这对于构建用户或者容器自己的文件系统目录非常有用。
    - bubblewrap 总是创建一个新的mount namespace, root挂载在tmpfs上，用户可以明确指定文件系统的哪个部分在沙盒中是可见的。
  - User namespaces (CLONE_NEWUSER):
    - 用来隔离用户权限相关的Linux资源，包括用户ID 和组ID， 在不同的user namespace中，同样一个用户的user ID 和group ID可以不一样，
    - 换句话说，一个用户可以在父user namespace中是普通用户，在子user namespace中是超级用户（超级用户只相对于子user namespace所拥有的资源，无法访问其他user namespace中需要超级用户才能访问资源）。
  - IPC namespaces (CLONE_NEWIPC):
    - 沙箱会得到所有不同形式的IPCs的一份拷贝，比如SysV 共享内存和信号量等。
  - PID namespaces (CLONE_NEWPID):
    - 用来隔离进程的ID空间，沙箱内的程序看不见任何沙箱外的进程，此外, bubblewrap 会运行一个pid为1的程序在容器中,用来处理回收子进程的需求。
  - Network namespaces (CLONE_NEWNET):
    - 用来隔绝网络，在它自己的network namespace中只有一个回环设备。
  - UTS namespace (CLONE_NEWUTS):
    - 允许沙箱拥有自己独立的hostname和domain name.
- Cgroups:
  - cgroup和namespace类似，也是将进程进行分组，
  - 但它的目的和namespace不一样，namespace是为了隔离进程组之间的资源，而cgroup是为了对一组进程进行统一的资源监控和限制。
- Bind Mount:
  - 将一个目录（或文件）中的内容挂载到另一个目录（或文件）上.
- Seccomp rules：
  - Linux kernel 所支持的一种简洁的sandboxing机制。它能使一个进程进入到一种“安全”运行模式，
  - 该模式下的进程只能调用4种系统调用（system calls），即read(), write(), exit()和sigreturn()，否则进程便会被终止。

同时，bubblewrap 使用PR_SET_NO_NEW_PRIVS 关闭 setuid 二进制程序。
当一个进程或其子进程设置了PR_SET_NO_NEW_PRIVS 属性,则其不能访问一些无法share的操作,如setuid, 和chroot。

# wireshark

抓包工具

# Linux

## Arch

自由，激进，滚动发布系统与软件的最新版本。

完善的文档。

有基于 Arch linux 的稳定发行版本： Manjaro Linux、EndeavourOS

基于 Arch linux 的，且使用 openrc 而不是 systemd 作为初始化系统的发行版

## Gentoo

开放，稳定，滚动发布，基于源码构建系统与软件。

支持历史硬件。

## Void

稳定、灵活，滚动发布。

抛弃了笨重的systemd，使用runit

支持选择 glibc 还是 musl

## Nixos

基于nix配置的系统构建， 稳定的版本管理与可复现性

文档饱受诟病。

## Kail

安全与渗透测试

## Alpine

轻量，专注于安全、简单和高效。

适合 ARM 架构，对硬件要求低，更为安全

使用OpenRC 作为初始化（init）系统

适合用来做路由器、防火墙、VPNs、VoIP 盒子以及服务器的操作系统

## Athena

基于Arch和Nixos

## Asahi

为支持mac芯片的linux

[mac安装asahi linux](https://cloud-atlas.readthedocs.io/zh-cn/latest/linux/asahi_linux/index.html)

# Darwin

## AeroSpace

i3-like tiling window manager

# USB OS

## rufus

- [rufus](https://github.com/pbatard/rufus)

## Ventoy

- [Ventoy](https://github.com/ventoy/Ventoy)

# charm 开源终端工具集

[charm](https://github.com/charmbracelet)

## vhs

制作终端gif

# 软件

## 笔记

### logseq

支持markdown，org mode，支持双链

也支持进行日程管理，可以考虑作为emacs org的代替品。

### siyuan

## 音乐可视化 cava

https://github.com/karlstav/cava

## KeyCastOW

监听并显示按键

## Arc browser

高颜值，极简：https://arc.net/

## Zen browser

## nyxt browser

hacker's browser

## 电路模拟器

- Digital-Logic-Sim: 简单的数字逻辑模拟器
  - [官网](https://sebastian.itch.io/digital-logic-sim)
  - [github](https://github.com/SebLague/Digital-Logic-Sim)
- [logisim-evolution](https://github.com/logisim-evolution/logisim-evolution)
- [openvcb](https://github.com/kittybupu/openVCB/)
- [logiccircuit](https://github.com/eugenelepekhin/LogicCircuit)

## linux steam 中间层

### proton

### wine

# 杂项,图片,gif,taag

## github 图标制作

- [链接](https://shields.io/)
- 示例

  ![little-tools-1](./image/little-tools-1.png)

## 抖动字gif生成

- [链接](https://aidn.jp/ugomoji/)
- 示例

  ![little-tools-2](./image/little-tools-2.gif)

## typing svg生成

<https://github.com/DenverCoder1/readme-typing-svg>

## 生成taag

- [链接](https://patorjk.com/software/taag/)
- 示例

  ```
            _____                    _____                    _____                        _____                    _____                    _____                    _____
           /\    \                  /\    \                  /\    \                      /\    \                  /\    \                  /\    \                  /\    \
          /::\____\                /::\    \                /::\____\                    /::\    \                /::\    \                /::\    \                /::\    \
         /::::|   |               /::::\    \              /:::/    /                    \:::\    \              /::::\    \              /::::\    \              /::::\    \
        /:::::|   |              /::::::\    \            /:::/   _/___                   \:::\    \            /::::::\    \            /::::::\    \            /::::::\    \
       /::::::|   |             /:::/\:::\    \          /:::/   /\    \                   \:::\    \          /:::/\:::\    \          /:::/\:::\    \          /:::/\:::\    \
      /:::/|::|   |            /:::/__\:::\    \        /:::/   /::\____\                   \:::\    \        /:::/__\:::\    \        /:::/__\:::\    \        /:::/  \:::\    \
     /:::/ |::|   |           /::::\   \:::\    \      /:::/   /:::/    /                   /::::\    \      /::::\   \:::\    \      /::::\   \:::\    \      /:::/    \:::\    \
    /:::/  |::|   | _____    /::::::\   \:::\    \    /:::/   /:::/   _/___                /::::::\    \    /::::::\   \:::\    \    /::::::\   \:::\    \    /:::/    / \:::\    \
   /:::/   |::|   |/\    \  /:::/\:::\   \:::\    \  /:::/___/:::/   /\    \              /:::/\:::\    \  /:::/\:::\   \:::\    \  /:::/\:::\   \:::\    \  /:::/    /   \:::\ ___\
  /:: /    |::|   /::\____\/:::/__\:::\   \:::\____\|:::|   /:::/   /::\____\            /:::/  \:::\____\/:::/  \:::\   \:::\____\/:::/  \:::\   \:::\____\/:::/____/  ___\:::|    |
  \::/    /|::|  /:::/    /\:::\   \:::\   \::/    /|:::|__/:::/   /:::/    /           /:::/    \::/    /\::/    \:::\  /:::/    /\::/    \:::\  /:::/    /\:::\    \ /\  /:::|____|
   \/____/ |::| /:::/    /  \:::\   \:::\   \/____/  \:::\/:::/   /:::/    /           /:::/    / \/____/  \/____/ \:::\/:::/    /  \/____/ \:::\/:::/    /  \:::\    /::\ \::/    /
           |::|/:::/    /    \:::\   \:::\    \       \::::::/   /:::/    /           /:::/    /                    \::::::/    /            \::::::/    /    \:::\   \:::\ \/____/
           |::::::/    /      \:::\   \:::\____\       \::::/___/:::/    /           /:::/    /                      \::::/    /              \::::/    /      \:::\   \:::\____\
           |:::::/    /        \:::\   \::/    /        \:::\__/:::/    /            \::/    /                       /:::/    /               /:::/    /        \:::\  /:::/    /
           |::::/    /          \:::\   \/____/          \::::::::/    /              \/____/                       /:::/    /               /:::/    /          \:::\/:::/    /
           /:::/    /            \:::\    \               \::::::/    /                                            /:::/    /               /:::/    /            \::::::/    /
          /:::/    /              \:::\____\               \::::/    /                                            /:::/    /               /:::/    /              \::::/    /
          \::/    /                \::/    /                \::/____/                                             \::/    /                \::/    /                \::/____/
           \/____/                  \/____/                  ~~                                                    \/____/                  \/____/

  ```

## 在线代码高亮工具

- 往word里面粘贴用的
- [链接](https://highlightcode.com/)

## 统计github提交生成图片

- [code prints](https://codeprints.dev/)

  ![little-tools-2](./image/little-tools-2.png)

## CR常见术语

- CR: Code Review. 请求代码审查。
- PR： pull request. 拉取请求，给其他项目提交代码。
- MR： merge request. 合并请求。
- LGTM： Looks Good To Me.对我来说，还不错。表示认可这次PR，同意merge合并代码到远程仓库。
- WIP: Work In Progress. 进展中，主要针对改动较多的 PR，可以先提交部分，标题或 Tag 加上 WIP，表示尚未完成，这样别人可以先 review 已提交的部分。
- PTAL: Please Take A Look. 请帮我看下, 邀请别人review自己的代码。
- ACK: Acknowledgement. 承认，同意。表示接受代码的改动。
- NACK/NAK: Negative acknowledgement. 不同意，不接受这次的改动。
- RFC: Request For Comment. 请求进行讨论，表示认为某个想法很好，邀请大家一起讨论一下。
- ASAP: As Soon As Possible. 请尽快完成。
- IIRC: If I Recall Correctly. 如果我没有记错的话。
- IMO: In My Opinion. 在我看来。
- TBD: To Be Done. 未完成，待续。
- TL;DR: Too Long; Didn't Read. 太长懒得看。常见于README文档。

## 大文件测试

```bash
# imgui.h
wget https://raw.githubusercontent.com/ocornut/imgui/fb7f6cab8c322731da336e553915e944bf386e62/imgui.h
# sqlite.c:
wget https://raw.githubusercontent.com/IreneKnapp/direct-sqlite/a74cc50c735053c7c49c487a66e7756b524db883/cbits/sqlite3.c
```

## linux 吉祥物

[penger.city](https://penger.city/)

## ascii art

- [textik 画图](https://textik.com)
- [ascii rain](https://github.com/nkleemann/ascii-rain)
- [ascii 盆景](https://gitlab.com/jallbrit/cbonsai)

# 参考

- [awesome tuis](https://github.com/rothgar/awesome-tuis)
- [awesome toolbox: 收集的一些工具和库](https://github.com/lqhuang/awesome)
