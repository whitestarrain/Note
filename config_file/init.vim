let mapleader="\<space>"

" 关于帮助文档。:help startify 即可
" 然后使用 c-] c-i c-o 进行跳转查看
" nvim-qt 要加上 --no-ext-tabline，才能使用airline的bufferline
" 命令行模式输入 map ,inoremap ,nnoremap等可以查看 map键的映射，如果按键相关有什么问题可以查一下。比如auto-pairs占了<C-r>
"================leader end==================


"================plug start==================

" Specify a directory for plugins
" - For Neovim: stdpath('data') . '/plugged'
" - Avoid using standard Vim directory names like 'plugin'
call plug#begin('D:\learn\neovim0.5\Neovim\share\autoload')




"---------------------
" coc 插件
Plug 'neoclide/coc.nvim', {'branch': 'release'}

"---------------------
" 彩虹括号
Plug 'luochen1990/rainbow'


"---------------------
"让翻页更顺畅
"有长文本，syntax过烂，免了吧
Plug 'yuttie/comfortable-motion.vim'
"---------------------

" 优化搜索，移动光标后清除高亮
" Plug 'junegunn/vim-slash'

"---------------------


" prettier 格式化插件
" post install (yarn install | npm install) then load plugin only for editing supported files
" 安装依赖npm i -g prettier
Plug 'prettier/vim-prettier', {
  \ 'do': 'npm install',
  \ 'for': ['javascript', 'typescript', 'css', 'less', 'scss', 'json', 'graphql', 'markdown', 'vue', 'yaml', 'html'] }



"---------------------


" leader键提示插件
" On-demand lazy load
Plug 'liuchengxu/vim-which-key', { 'on': ['WhichKey', 'WhichKey!'] }

"---------------------

" git插件
Plug 'tpope/vim-fugitive'

"---------------------

" 显示文件每行的状态
Plug 'airblade/vim-gitgutter'

"---------------------


"markdonw 语法高亮
Plug 'plasticboy/vim-markdown'

"---------------------

" 注释插件
" Plug 'preservim/nerdcommenter'

"---------------------


" 主题
Plug 'w0ng/vim-hybrid'
Plug 'joshdick/onedark.vim'

"---------------------


" 下方提示栏
Plug 'vim-airline/vim-airline'

"---------------------

" 下方提示栏主题
Plug 'vim-airline/vim-airline-themes'

"---------------------

" 增加缩进线
Plug 'yggdroot/indentline'

"---------------------

" 开始菜单
Plug 'mhinz/vim-startify'

"---------------------


" 文件树
Plug 'scrooloose/nerdtree'

"---------------------


" 文件树 git 显示支持
Plug 'Xuyuanp/nerdtree-git-plugin'

"---------------------


" 单文件模糊搜索跳转
Plug 'ctrlpvim/ctrlp.vim'

"---------------------


" 位置跳转

"---------------------

Plug 'easymotion/vim-easymotion'

" 成对编辑

"---------------------

Plug 'tpope/vim-surround'

"---------------------


" 多文件模糊搜索
Plug 'junegunn/fzf', { 'do': { -> fzf#install() } }
Plug 'junegunn/fzf.vim'

"---------------------


"多文件搜索替换
Plug 'brooth/far.vim'

"---------------------


" python插件
" Plug 'python-mode/python-mode', { 'for': 'python', 'branch': 'develop' }

" 代码大纲
Plug 'majutsushi/tagbar'

"---------------------


" 粘贴图片
Plug 'ferrine/md-img-paste.vim'

"---------------------


"括号补全
Plug 'jiangmiao/auto-pairs'

"---------------------



" java补全方案

call plug#end()


"================plug end==================


" ===============plugConfig start==================

" auto-pairs config
" 自动删除括号的键设置，为了释放<C-h>
let g:AutoPairsMapCh=0

" prettier
" 取消注解需求
let g:prettier#autoformat_require_pragma = 0
" 关闭自动格式化
let g:prettier#autoformat = 0
let g:prettier#autoformat_config_present = 0

" vim-smooth-scroll config
" noremap <silent> <c-u> :call smooth_scroll#up(&scroll, 0, 4)<CR>
" noremap <silent> <c-d> :call smooth_scroll#down(&scroll, 0, 4)<CR>
" noremap <silent> <c-b> :call smooth_scroll#up(&scroll*2, 0, 4)<CR>
" noremap <silent> <c-f> :call smooth_scroll#down(&scroll*2, 0, 4)<CR>


" vim-startify config
" 设置Session时不要打开插件，比如NERDTree侧边栏，否则会报错
" 关闭自动切换目录
let g:startify_change_to_dir = 0
" 切换header
let g:startify_custom_header = [
      \ '      ____   U  ___ u  ____              _   _     ____   ',
      \ '   U /"___|   \/"_ \/ |  _"\    ___     | \ |"| U /"___|u ',
      \ '   \| | u     | | | |/| | | |  |_"_|   <|  \| |>\| |  _ / ',
      \ '    | |/__.-,_| |_| |U| |_| |\  | |    U| |\  |u | |_| |  ',
      \ '     \____|\_)-\___/  |____/ uU/| |\u   |_| \_|   \____|  ',
      \ '    _// \\      \\     |||_.-,_|___|_,-.||   \\,-._)(|_   ',
      \ '   (__)(__)    (__)   (__)_)\_)-   -(_/ (_")  (_/(__)__)  '
      \]

" 设置自动session保存
let g:startify_session_persistence = 1
" session保存前执行，防止因为懒加载而出现冲突
let g:startify_session_before_save = [
    \ 'echo "Cleaning up before saving.."',
    \ 'silent! NERDTreeClose',
    \ 'silent! TagbarClose',
    \ ''
    \ ]
" 开启自动保存的变量名称，保证每个session都可以个性化
" g:mdip_imgdir: 图片存储路径
let g:startify_session_savevars = [
        \ 'g:mdip_imgdir',
        \ 'g:mdip_imgdir_intext'
        \ ]

" 自动跳转到版本管理工具的目录
" let g:startify_change_to_vcs_root = 1
" 跳转到开始菜单
nnoremap <leader>st :Startify<cr>
nnoremap <leader>ss :SSave<cr>



" vim-markdown config
" 禁用折叠
let g:vim_markdown_folding_disabled=1
" 禁用插件按键映射
let g:vim_markdown_no_default_key_mappings = 1
  " gx: 在浏览器中打开光标所在处的链接
  " ge: 在 Vim 中打开光标下的链接指向的文件。主要用于打开使用相对位置标识的 markdown 文件
  " ]]: 跳转到下一个 header
  " [[: 跳转到之前的 header，与 ]c 相反
  " ][: 跳转到下一个兄弟 header
  " []: 跳转到上一个兄弟 header
  " ]c: 跳转到当前文本所属的 header
  " ]u: 跳转到"父"header
 
" 语法隐藏相关，不进行语法隐藏
set conceallevel=2
let g:vim_markdown_conceal=0
" 开启math公式高亮
let g:vim_markdown_math = 1
" 修改缩进格数量
let g:vim_markdown_list_item_indent = 2
" 代码块不隐藏``
let g:vim_markdown_conceal_code_blocks = 0





" NERDTree config

" auto turn on
" autocmd vimenter * NERDTree
" ctrl+n to toggle file tree
noremap <silent><C-n> :NERDTreeToggle<CR>
" ignore file
let NERDTreeIgnore=[
    \ '\.pyc$','\~$','\.swp','\.git$','\.pyo$','\.svn$','\.swp$','__pycache__'
    \ ]
nnoremap <silent><leader>v :NERDTreeFind<cr>
" 样式
" let g:NERDTreeDirArrowExpandable = '>'
" let g:NERDTreeDirArrowCollapsible = '-'
" 注意： m 可以进行创建文件等操作，不要漏了





" ctrlp config

let g:ctrlp_map = '<leader>fa'
" 不能搜索下层文件
" 选择文件后 ,v 来定位


" easymotion config

nmap <leader>j <Plug>(easymotion-s2)
" acejump
" 其中<leader><leader>{j|k|w|b}，可以快速跳转行和列


" tagbar config
" 设置快速开关大纲
" nnoremap ,t :TagbarToggle<CR>



"md-img-paste config 粘贴图片配置
autocmd FileType markdown nmap <buffer><silent> <leader>m :call mdip#MarkdownClipboardImage()<CR>
autocmd FileType md nmap <buffer><silent> <leader>m :call mdip#MarkdownClipboardImage()<CR>
let g:mdip_imgdir = './image'  " 图片存放位置
let g:mdip_imgdir_intext = g:mdip_imgdir " md中()中的位置

" 定义设置路径的函数。注意：调用要使用call。
" ! 是重复时强行覆盖
function! SetImagePath()
    call ShowImagePath()
    let l:name = input('Image path: ')
    let g:mdip_imgdir = l:name
    let g:mdip_imgdir_intext = l:name
    return name
endfunction
" . 作为字符串连接符
function! ShowImagePath()
  echo 'now:::mdip_imgdir:  ' . g:mdip_imgdir
  echo 'now:::mdip_imgdir_intext:  ' . g:mdip_imgdir_intext
endfunction

nnoremap <leader>sm :call SetImagePath()<cr>


" python 插件配置
let g:python3_host_prog='D:/learn/anaconda3/envs/learn/python.exe'




" vim-which-key
nnoremap <silent> <leader> :WhichKey '<Space>'<CR>
" By default timeoutlen is 1000 ms
set timeoutlen=500




" vim-gitgutter
" set the default value of updatetime to 600ms
set updatetime=600
nmap <leader>hf <Plug>(GitGutterNextHunk)
nmap <leader>hb  <Plug>(GitGutterPrevHunk)

" markdown tag
"设置tagber对于markdown的支持
let g:tagbar_type_markdown = {
        \ 'ctagstype' : 'markdown',
        \ 'kinds' : [
                \ 'h:headings',
        \ ],
    \ 'sort' : 0
\ }
" 设置宽度
" let g:tagbar_width = 30
nnoremap <leader>t :TagbarToggle<CR>


" vim-airline config

let g:airline#extensions#tabline#enabled = 1
let g:airline#extensions#bufferline#enabled = 1
let g:airline_theme='bubblegum'
set laststatus=2  "永远显示状态栏

" "取消显示warning部分
let g:airline_section_warning = ''
" "取消显示section_b
" " let g:airline_section_b = ''
" "section_c显示为tagbar检索出来的标题
" let g:airline_section_c = airline#section#create(['tagbar'])
" "section_x显示文件名
" let g:airline_section_x = '%{expand("%")}'
" "section_y显示时间
" "let g:airline_section_y = airline#section#create(['%{strftime("%D")}'])
" "section_z显示日期
let g:airline_section_y = airline#section#create(['%{strftime("%H:%M")}'])
" "激活tagbar扩展
" let g:airline#extensions#tagbar#enabled = 1
" "不显示文件编码（Windows系统）
" " let g:airline#parts#ffenc#skip_expected_string='utf-8[dos]'
" "不显示文档总字数
let g:airline#extensions#wordcount#enabled = 0

" 分割符
" let g:airline_left_sep=' '
" let g:airline_right_sep=' '


"================plugConfig end==================



"================config start==================

"----------------------------------------------------------------
"编码设置 linux上不用写
"----------------------------------------------------------------
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
set ambiwidth=double




set nocompatible

set number              " 设置行号
set relativenumber      " 相对行号

filetype plugin indent on  " Load plugins according to detected filetype.
filetype on                " 开启文档类型检查
syntax on                  " 设置代码高亮 

set autoindent             " 设置自动缩进
set expandtab              " Use spaces instead of tabs.
set shiftround             " >> indents to next multiple of 'shiftwidth'.

set backspace   =indent,eol,start  " Make backspace work as you would expect.
set hidden                 " Switch between buffers without having to save first.
set laststatus  =2         " Always show statusline.
set display     =lastline  " Show as much as possible of the last line.

set showmode               " Show current mode in command-line.
set showcmd                " Show already typed keys when more are expected.

set incsearch              " 设置增量搜索
set hlsearch               " 设置搜索高亮

set ttyfast                " Faster redrawing.
set lazyredraw             " Only redraw when necessary.

set splitbelow             " Open new windows below the current window.
set splitright             " Open new windows right of the current window.

" set cursorcolumn          "  光标所在列高亮，但是移动时可能会卡顿，所以关闭
set cursorline             "  光标所在行高亮
set wrapscan               " Searches wrap around end-of-file.
set report      =0         " Always report changed lines.
set synmaxcol=10000       " 高亮显示行数，小一点节省内存，但是可能对大文件出现渲染错误 默认3000



"[输入法智能切换设置] 8.2以后不需要了
"一些必要的设置,比如是什么按键切换中英文状态.如果加入"imcmdline"选项则命令模式下输入法默认为被开启.
" set imak=S imi=2 ims=2
"VIM启动/切换窗口焦点/离开编辑模式时,禁用输入法
" autocmd VimEnter,FocusLost * set imdisable
"获得焦点/进入编辑模式时,启用输入法
" autocmd FocusGained,InsertEnter * set noimdisable



" 设置字体大小
" set guifont=Cousine_NF:h11
" let g:Guifont="Cousine_NF:h11"

" F2进入粘贴模式
set pastetoggle=<F2>

" 设置折叠方式
set foldmethod=indent

set scrolloff=3


set backspace=indent,eol,start

" 设置一个tab的空格长度
set tabstop=2

"softtabstop 表示在编辑模式的时候按退格键的时候退回缩进的长度，当使用 expandtab时特别有用。
set softtabstop=2

"shiftwidth 表示每一级缩进的长度，一般设置成跟 softtabstop 一样。
set shiftwidth=2

"当设置成 expandtab 时，缩进用空格来表示，noexpandtab 则是用制表符表示一个缩进。
set expandtab


" 主题
" colorscheme hybrid
colorscheme onedark
let g:lightline = {
  \ 'colorscheme': 'onedark',
  \ }
let g:airline_theme='onedark'

" 行距
set linespace=2

" 自动全屏
" if has('gui_running') && has("win32")
"     au GUIEnter * simalt ~x
" endif




" 不同的文件显示不同的缩进
autocmd Filetype html setlocal ts=2 sw=2 expandtab
autocmd Filetype md setlocal ts=2 sw=2 expandtab
autocmd Filetype markdown setlocal ts=2 sw=2 expandtab
autocmd Filetype ruby setlocal ts=2 sw=2 expandtab
autocmd Filetype javascript setlocal ts=4 sw=4 sts=0 expandtab
autocmd Filetype java setlocal ts=4 sw=4 sts=0 expandtab
autocmd Filetype coffeescript setlocal ts=4 sw=4 sts=0 expandtab





set foldlevelstart=99       " 打开文件是默认不折叠代码


set vb t_vb=
au GuiEnter * set t_vb=




"================config end==================



"================map start==================


" 设置插入模式下括号等自动补全。
"inoremap ' ''<ESC>i
"inoremap " ""<ESC>i
"inoremap ( ()<ESC>i
"inoremap [ []<ESC>i
"inoremap { {<CR>}<ESC>O


" 设置插入模式下光标左右移动方式
inoremap <C-l> <Right>
inoremap <C-h> <Left>
inoremap <C-j> <Down>
inoremap <C-k> <Up>

" 分屏移动映射
noremap <C-h> <C-w>h
noremap <C-j> <C-w>j
noremap <C-k> <C-w>k
noremap <C-l> <C-w>l


" 输出两个换行符
nnoremap <leader>o o<br /><br /><cr><esc>

nnoremap <leader>zd o <details><cr><summary style="color:red;"></summary><cr></details><cr><esc>kkwf>a

" 设置缓冲区跳转
noremap <M-h> :bf<cr>
noremap <M-l> :bn<cr>


"设置路径为当前文件所在路径
nnoremap <silent><leader>p :cd %:h<cr>

nnoremap <silent>o A<cr>

nnoremap <silent><leader>d :bd<cr>

nnoremap < <<
nnoremap > >>

command! -range=% Markmap CocCommand markmap.create <line1> <line2>

command! BcloseOthers call <SID>BufCloseOthers()
function! <SID>BufCloseOthers()
   let l:currentBufNum   = bufnr("%")
   let l:alternateBufNum = bufnr("#")
   for i in range(1,bufnr("$"))
     if buflisted(i)
       if i!=l:currentBufNum
         execute("bdelete ".i)
       endif
     endif
   endfor
endfunction
map <leader>bdo :BcloseOthers<cr><cr>
"================map end==================







" 不知道干啥的
"set list                   " Show non-printable characters.
"if has('multi_byte') && &encoding ==# 'utf-8'
"  let &listchars = 'tab:? ,extends:?,precedes:?,nbsp:锟斤拷'
"else
"  let &listchars = 'tab:> ,extends:>,precedes:<,nbsp:.'
"endif
"
"" The fish shell is not very compatible to other shells and unexpectedly
"" breaks things that use 'shell'.
"if &shell =~# 'fish$'
"  set shell=/bin/bash
"endif
"


