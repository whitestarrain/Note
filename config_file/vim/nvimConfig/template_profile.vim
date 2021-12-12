if strlen($term)==0
  " nvim-qt
  let g:set_termguicolors=1
  let g:load_theme="plugTheme"
else
  " nvim in terminal
  let g:load_theme="ownTheme"
endif

if !exists('g:vscode')
  let g:plug_install_path="D:\\learn\\neovim0.5\\Neovim\\share\\autoload"
endif

source D:\Note\config_file\vim\nvimConfig\init.vim
