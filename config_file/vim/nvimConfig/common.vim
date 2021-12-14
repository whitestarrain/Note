" =========================编码设置========================= 
"Vim 在与屏幕/键盘交互时使用的编码(取决于实际的终端的设定)        
set encoding=utf-8
set langmenu=zh_CN.UTF-8
" 设置打开文件的编码格式  
set fileencodings=ucs-bom,utf-8,cp936,gb18030,big5,euc-jp,euc-kr,latin1 
set fileencoding=utf-8
"解决菜单乱码
source $VIMRUNTIME/delmenu.vim
source $VIMRUNTIME/menu.vim
"解决consle输出乱码
"set termencoding = cp936  
"设置中文提示
language messages zh_CN.utf-8 
"设置中文帮助
set helplang=cn
"设置为双字宽显示，否则无法完整显示如:☆
" set ambiwidth=double

set nocompatible

" =========================基础设置========================= 
filetype plugin indent on  " Load plugins according to detected filetype.
set number              " 设置行号
set relativenumber      " 相对行号
filetype on                " 开启文档类型检查

set smarttab               " Be smart when using tabs ;)
set expandtab              " Use spaces instead of tabs.
set softtabstop=1          "softtabstop 表示在编辑模式的时候按退格键的时候退回缩进的长度，当使用 expandtab时特别有用。
set shiftwidth=2           "shiftwidth 表示每一级缩进的长度，一般设置成跟 softtabstop 一样。
set tabstop=2              " 设置一个tab的空格长度
"
" 不同的文件显示不同的缩进
autocmd Filetype html setlocal ts=2 sw=2 expandtab
autocmd Filetype md setlocal ts=2 sw=2 expandtab
autocmd Filetype markdown setlocal ts=2 sw=2 expandtab
autocmd Filetype ruby setlocal ts=2 sw=2 expandtab
autocmd Filetype javascript setlocal ts=4 sw=4 sts=0 expandtab
autocmd Filetype java setlocal ts=4 sw=4 sts=0 expandtab
autocmd Filetype coffeescript setlocal ts=4 sw=4 sts=0 expandtab

set autoindent             " 设置自动缩进
set smartindent            " Smart indent
set shiftround             " >> indents to next multiple of 'shiftwidth'.

set backspace=indent,eol,start  " Make backspace work as you would expect.
set hidden                 " Switch between buffers without having to save first.
set laststatus=2         " Always show statusline.
set display=lastline  " Show as much as possible of the last line.
set nowrap                 " No Wrap lines
set scrolloff=3
set linespace=2            " 行距

set showmode               " Show current mode in command-line.
set showcmd                " Show already typed keys when more are expected.

set re=1                   " 设置regex模式
set incsearch              " 设置增量搜索
set hlsearch               " 设置搜索高亮

set ttyfast                " Faster redrawing.
set lazyredraw             " Only redraw when necessary.

set splitbelow             " Open new windows below the current window.
set splitright             " Open new windows right of the current window.

" =========================高亮相关========================= 
set cursorline             "  光标所在行高亮
" set cursorcolumn          "  光标所在列高亮，但是移动时可能会卡顿，所以关闭
set wrapscan               " Searches wrap around end-of-file.
set report      =0         " Always report changed lines.
" Set cursor line color on visual mode
" highlight Visual cterm=NONE ctermbg=236 ctermfg=NONE guibg=Grey40
" highlight LineNr cterm=none ctermfg=240 guifg=#2b506e guibg=#000000
augroup BgHighlight
  autocmd!
  autocmd WinEnter * set cul
  autocmd WinLeave * set nocul
augroup END


" =========================其他========================= 
" 提高限制
set maxmempattern=5000

" F2进入粘贴模式
set pastetoggle=<F2>
" Turn off paste mode when leaving insert
autocmd InsertLeave * set nopaste

" 设置折叠方式
set foldmethod=indent
set foldlevelstart=99       " 打开文件是默认不折叠代码

" 语法隐藏相关，不进行语法隐藏
set conceallevel=0
