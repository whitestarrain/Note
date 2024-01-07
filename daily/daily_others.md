> 用来记录一些有的没的

# win10中文用户名问题

- 问题：免密登录linux时，中文用户名乱码，显示无法找到.ssh文件夹

- 设置软链接
  ```bash
  mklink /J C:\users\英文用户名 C:\users\当前中文用户名
  ```

- 修改注册表
  ```
  计算机\HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows NT\CurrentVersion\ProfileList
  ```

  ![others_daily_others-1](./image/others_daily_others-1.png)

# 你需要来自...的权限 或者 一个意外错误....

- 首先试试移动文件夹到C盘的其他地方再删除
- 不行的话获取权限：
  - [递归获取权限右键脚本](https://zhuanlan.zhihu.com/p/82036101)
- 还不行的话就获取权限后，使用winrar压缩后删除

# 解决git的bash.exe中文乱码问题

- etc目录下修改bash.bashrc文件

  ```
  export LANG=zh_CN.utf-8
  alias ls='ls --show-control-chars --color=auto
  ```
