# 注意

本文只用来记录一些遇见的问题，具体学习地址： [Rust 程序设计语言](https://kaisery.github.io/trpl-zh-cn/title-page.html)

# 安装与toolchain切换

- scoop安装
  ```
  scoop install rust
  ```

- 设置rustup default host
  ```bash
  $ rustup show
    Default host: x86_64-pc-windows-msvc
    rustup home:  D:\ProgramFiles\scoop\apps\rustup\current\.cargo

  # rustup --help
  # rustup toolchain --help

  # 可以换为gnu或者其他
  # 所有toolchain列表，针对不同机器
  $ rustup toolchain install stable-x86_64-pc-windows-gnu
  $ rustup set default-host stable-x86_64-pc-windows-gnu
  $ rustup default stable-x86_64-pc-windows-gnu
  $ rustup show
    Default host: x86_64-pc-windows-gnu
    rustup home:  D:\ProgramFiles\scoop\apps\rustup\current\.cargo

    installed toolchains
    --------------------

    stable-x86_64-pc-windows-gnu
    stable-x86_64-pc-windows-msvc (default)

    active toolchain
    ----------------

    stable-x86_64-pc-windows-msvc (default)
    rustc 1.57.0 (f1edd0429 2021-11-29)
  ```

# 参考资料

- **[Rust语言圣经(Rust Course)](https://course.rs/about-book.html)**
