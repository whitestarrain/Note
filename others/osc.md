# OSC

## 原理

## 实现

- remote server上执行，粘贴`osc_test_data`到本地剪切板

  ```bash
  echo -e "\e]52;c;$(base64 <<< osc_test_data)\a"
  ```

# 扩展

## vim

## emacs

## tmux

# 参考

- [一日一技 | SSH 下跨系统连通剪贴板](https://sspai.com/post/71018)
- [tmux osc](https://github.com/tmux/tmux/wiki/Clipboard)
- [tmux，emacs和剪切板](https://chunhuitrue.github.io/posts/emacs-tmux-clipboard/)

