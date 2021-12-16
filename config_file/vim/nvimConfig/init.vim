" 关于帮助文档。:help startify 即可
" 然后使用 c-] c-i c-o 进行跳转查看
" nvim-qt 要加上 --no-ext-tabline，才能使用airline的bufferline。加上 --fullscreen全屏显示
" 命令行模式输入 map ,inoremap ,nnoremap等可以查看 map键的映射
" 如果按键相关有什么问题可以查一下。比如auto-pairs占了<C-r>

"=================================================leader start===================================================
if !exists('g:vscode')
    let g:plug_install_path="D:\\learn\\neovim0.5\\Neovim\\share\\autoload"
endif
" 配置项设置,g:plug_install_path 可通过 -c 参数传入
if strlen($term)==0
  " nvim-qt
  let g:set_termguicolors=1
  let g:load_theme="onedark"
  " let g:load_theme="NeoSolarized"
  autocmd vimenter * GuiFont! MesloLGS NF:h11
elseif $term=="alacritty"
  " alacritty
  let g:neosolarized_termtrans=1
  let g:set_termguicolors=1
  let g:load_theme="onedark"
elseif exists("$WEZTERM")
  let g:set_termguicolors=1
  let g:load_theme="onedark"
else
  " nvim in terminal
  let g:set_termguicolors=0
  let g:load_theme="onedark"
endif

" 配置项说明
" 配置加载相关变量
if exists("g:load_theme")
  " ownTheme,plugTheme,默认不加载
  let g:load_theme=g:load_theme              
endif
"
" 是否设置termguicolors，默认不设置
let g:set_termguicolors=exists("g:set_termguicolors") && g:set_termguicolors 

if exists("g:plug_install_path")
  " 插件安装路径，不设置，则不会加载插件
  let g:plug_install_path=g:plug_install_path 
endif

" python 环境
let g:python3_host_prog='D:\\learn\\anaconda3\\envs\\learn\\python.exe'

" 定义载入配置命令
" let g:absolute_config_path = expand("%:p")[0:strlen(expand("%:p"))-strlen(expand("%:t"))-1]
let g:absolute_config_path = expand("<sfile>:p")[0:strlen(expand("<sfile>:p"))-strlen(expand("<sfile>:t"))-1]
command! -nargs=1 LoadScript exec 'source' . ' ' . g:absolute_config_path . '<args>'

let mapleader="\<space>"
let g:which_key_map =  {}
let g:which_key_map.z = { 'name' : '[second]' }
let g:which_key_map.s = { 'name' : '[startify]' }
let g:which_key_map.o = { 'name' : '[open in]' }
let g:which_key_map.h = { 'name' : '[hunk]' }
let g:which_key_map.f = { 'name' : '[find]' }
let g:which_key_map.b = { 'name' : '[buffer]' }
let g:which_key_map.c = { 'name' : '[comment]' }
"=================================================leader end===================================================


"=================================================plug config end===================================================
if exists("g:plug_install_path") && strlen(g:plug_install_path)>0

  " Specify a directory for plugins
  " - For Neovim: stdpath('data') . '/plugged'  "即 C:\Users\稀落的星\AppData\Local\nvim-data\plugs
  " - Avoid using standard Vim directory names like 'plugin'
  call plug#begin(get(g:,"plug_install_path"))

  LoadScript ./plug_configs/theme.vim
  LoadScript ./plug_configs/vim_surround.vim
  LoadScript ./plug_configs/tagbar.vim
  LoadScript ./plug_configs/rainrow.vim
  LoadScript ./plug_configs/auto_pair.vim
  LoadScript ./plug_configs/indentline.vim

  if strlen($term)>0
    " nvim-qt
    " LoadScript ./plug_configs/vim_devicons.vim
  endif

  "load selected plugins
  if !exists('g:skip_plugs')

    LoadScript plug_configs\airline.vim
    if !g:set_termguicolors
      let g:airline_theme='dark'
    endif

    " LoadScript ./plug_configs/far.vim
    " LoadScript ./plug_configs/comfortable_motion.vim
    " LoadScript ./plug_configs/nerdcommenter.vim
    LoadScript ./plug_configs/easy_motion.vim
    LoadScript ./plug_configs/nerdtree.vim
    LoadScript ./plug_configs/git.vim
    LoadScript ./plug_configs/vim_which_key.vim
    LoadScript ./plug_configs/fzf.vim
    LoadScript ./plug_configs/latex.vim
    LoadScript ./plug_configs/starify.vim
    LoadScript ./plug_configs/markdown.vim
    LoadScript ./plug_configs/prettier.vim
    LoadScript ./plug_configs/UltiSnips.vim
    LoadScript ./plug_configs/md_img_paste.vim
  endif

  let g:load_coc = 0
  if exists("g:rust")
    LoadScript ./plug_configs/rust.vim
    let g:load_coc = 1
  endif

  if exists("g:python")
    LoadScript ./plug_configs/python.vim
    let g:load_coc = 1
  endif

  if g:load_coc
    LoadScript plug_configs\coc.vim
  endif

  call plug#end()

endif
"=================================================plug config end===================================================

"=================================================common config end===================================================
LoadScript common.vim
"=================================================commone config end===================================================

"=================================================map start===================================================
LoadScript functions.vim
LoadScript mappings.vim
"=================================================map end===================================================

"=================================================theme===================================================

set synmaxcol=5000       " 高亮显示行数，小一点节省内存，但是可能对大文件出现渲染错误 默认3000
syntax sync minlines=256
syntax enable

if exists("g:load_theme") && strlen(g:load_theme)>0
  set winblend=0
  set wildoptions=pum
  set pumblend=5

  if g:load_theme=="ownTheme"
    " Use own theme
    LoadScript .\colors\theme.vim
  elseif strlen(g:load_theme)>0
    " use plugTheme
    exe 'colorscheme' . " " . g:load_theme

    if strlen($term)>0
      " for opacity in terminal
      autocmd vimenter * hi Normal guibg=NONE ctermbg=NONE
    endif
  endif
endif

if exists("&termguicolors") && exists("&winblend") && g:set_termguicolors 
  set termguicolors " make nvim slow in fluent terminal
  set background=dark
else
  set notermguicolors 
endif

"=================================================theme===================================================

