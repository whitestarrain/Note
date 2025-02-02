# nvidia prime

nvidia prime 主要用于处理笔记本上混合显卡的问题

## PRIME GPU offloading

命令: `xrandr --setprovideroffloadsink provider sink`

使 `offload provider`(一般是性能较强的独立GPU) 渲染数据，并把输出发送到 `provider sink`(连接显示器的GPU)

不过大多数驱动都默认设置了，可以通过观察`nvtop`中的显卡使用情况或者执行下面的命令检查当前默认使用的显卡

```bash
glxinfo | grep "OpenGL render"
```

如果想要使用独立显卡，可以指定程序运行时的环境变量:

- nixos 中:

  ```
  nvidia-offload glxinfo | grep "OpenGL render"
  OpenGL renderer string: NVIDIA GeForce RTX 3060 Laptop GPU/PCIe/SSE2
  ```
- 某些系统安装nvidia时自带的命令: `prime-run`
- 手动写的offload脚本：

  ```
  #! /usr/bin/bash
  export __NV_PRIME_RENDER_OFFLOAD=1
  export __NV_PRIME_RENDER_OFFLOAD_PROVIDER=NVIDIA-G0
  export __GLX_VENDOR_LIBRARY_NAME=nvidia
  export __VK_LAYER_NV_optimus=NVIDIA_only
  exec "$@"
  ```

另外，如果不与 `PRIME offloading` 或者 `reverse PRIME`一起使用, 内核 PCI 电源管理会关闭 GPU， 下面这些驱动程序此功能: modesetting, xf86-video-amdgpu, xf86-video-intel, xf86-video-nouveau

在较新的cpu上，可以通过配置 udev 规则，可以在不使用gpu时完全关闭gpu, 详细可以参考文档：<https://wiki.archlinux.org/title/PRIME#NVIDIA>

## PRIME synchronization

使用主要 GPU 渲染屏幕以及应用程序，然后传递给辅助 GPU 进行展示。

设置 NVIDIA GPU 渲染内容，并传递给辅助 AMD 集成显卡显示：

检查是否开启: `xrandr --prop | grep -i prime`

开启: `xrandr --output <output-name> --set "PRIME Synchronization" 1`

使用条件：

1. [启用 modesetting:](https://wiki.archlinux.org/title/NVIDIA#DRM_kernel_mode_setting)
2. 不适用于 AMDGPU DDX 驱动程序: amdgpu /  xf86-video-amdgpu

## Reverse PRIME

```
xrandr --setprovideroutputsource "`xrandr --listproviders | grep -i AMD | sed -n 's/^.*name://p'`" NVIDIA-0
```

## 场景

### R9000K

> nixos 配置: <https://github.com/whitestarrain/nixos-config/blob/master/hosts/R9000K/nvidia.nix>

R9000K 不同模式下的屏幕接口：

- 混合显卡模式：外接屏幕DP口，使用独立 nvidia 显卡，笔记本屏幕接eDP口，使用集成的 amd 显卡。
- 独立显卡模式：外接屏幕和笔记本屏幕都接DP口，使用独立 nvidia 显卡

---

混合显卡模式：

- 不外接屏幕时
  - 因为 amdgpu 驱动不支持 sync, 所以要使用`PRIME GPU offloading`: 没有任何异常
  - 使用`PRIME synchronization`
    - 亮度默认会最高
    - 发现鼠标在移动时消失，屏幕刷新对比`PRIME offloading`也有些滞后
    - 鼠标移动时，独立显卡使用率和功率突然升高
    - `set "PRIME Synchronization" 1` 后，鼠标和显卡使用率正常，
    - 但使用一段时间后，屏幕整个冻结，只能重启
- 使用外接屏幕时
  - 使用`PRIME GPU offloading`:
    - 因为外接显示器需要独立显卡渲染，集成显卡和独立显卡都有较高的使用率。暂且不清楚显卡间的是否有数据交互
    - xrandr scale 时，无法正确缩放或放大，会导致屏幕只显示一部分(`--scale 1.2x1.2`)，或者，整个屏幕只显示一小块(`--scale 0.8x0.8`)
  - 使用 `PRIME synchronization`
    - 观察：
      - 当使用笔记本屏幕时，默认 60fps ， 空闲状态下，独立显卡会处于低功率，且使用率几乎为 0
      - 但如果使用`xrandr`调整外接显示器刷新率为 144 后，会发现独立显卡使用率会在 25% 左右波动，即使把刷新率调回 60 也无法降低
      - 登陆进入后，使用 `xrandr` 关闭笔记本屏幕，使用率仍旧会比较高
    - 猜测：
      - xorg 启动阶段后，因为集成显卡为amd显卡，与`PRIME synchronization`冲突，
      - 其中看了下生成的 `lightdm-display-setup`，其中有调用`xrandr --setprovideroutputsource`
      - 但nixos配置中，也会为`PRIME Synchronization` 调用`--setprovideroutputsource`，需要进一步调查。
      - TODO: nvidia reverse PRIME
    - 解决：
      - 在 display manager setup script 中，使用 `xrandr` 关闭笔记本屏幕，初步观察gpu在空闲时间维持低功率0使用率，正常。
    - 后续观察：在播放视频时，会出现高clock 低使用率的情况，此时功率相比windows较高。
      - windows上clock会波动到较高 clock，但会很快降下来，但使用率会高一些。
      - 此时 windows 上的显卡温度(44左右)要低于 linux 上的显卡温度(48左右)
      - 暂不清楚是不是驱动导致的

- 独立显卡模式：
  - 不外接屏幕
    - 登陆后刷新率60，xrandr 设置 刷新率后，正常。但是gpu在空闲(idle)时,低clock但使用率维持在20%
    - 如果在 display mananger setup script 中设置好 xrandr，gpu 空闲时使用率为0
  - 外接屏幕：
    - 待测试

# nvidia clock

设置gpu工作频率

# reference

- [nvidia-prime](https://wiki.archlinux.org/title/PRIME)
- [nvidia prime-sync pdf](https://www.x.org/wiki/Events/XDC2016/Program/xdc-2016-prime-sync.pdf)
- [NVIDIA-SMI系列命令详解(8)-设备修改选项(3) ](https://juejin.cn/post/7121912988948234253)
- [Advanced API Performance: SetStablePowerState](https://developer.nvidia.com/blog/advanced-api-performance-setstablepowerstate/)
- [seful nvidia-smi Queries](https://nvidia.custhelp.com/app/answers/detail/a_id/3751/~/useful-nvidia-smi-queries)


