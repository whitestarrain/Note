" 关于帮助文档。:help startify 即可
" 然后使用 c-] c-i c-o 进行跳转查看
" nvim-qt 要加上 --no-ext-tabline，才能使用airline的bufferline。加上 --fullscreen全屏显示
" 命令行模式输入 map ,inoremap ,nnoremap等可以查看 map键的映射
" 如果按键相关有什么问题可以查一下。比如auto-pairs占了<C-r>

"=================================================leader start===================================================
if !exists('g:skip_plugs') && !exists('g:vscode')
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
  let g:load_theme="ownTheme"
elseif exists("$WEZTERM")
  let g:set_termguicolors=1
  let g:load_theme="ownTheme"
else
  " nvim in terminal
  let g:set_termguicolors=0
  let g:load_theme="ownTheme"
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
"=================================================leader end===================================================


"=================================================plug config end===================================================
if exists("g:plug_install_path") && strlen(g:plug_install_path)>0
  LoadScript plugs.vim

  LoadScript plug_configs\airline.vim
  if !g:set_termguicolors
    let g:airline_theme='dark'
  endif

  LoadScript plug_configs\auto-pair.vim
  LoadScript plug_configs\coc.vim
  LoadScript plug_configs\easy_motion.vim
  LoadScript plug_configs\fzf.vim
  LoadScript plug_configs\markdown.vim
  LoadScript plug_configs\md-img-paste.vim
  LoadScript plug_configs\nerdtree.vim
  LoadScript plug_configs\prettier.vim
  LoadScript plug_configs\starify.vim
  LoadScript plug_configs\tagbar.vim
  LoadScript plug_configs\UltiSnips.vim
  LoadScript plug_configs\vim-gitgutter.vim
  LoadScript plug_configs\vim-which-key.vim
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

if exists("&termguicolors") && exists("&winblend") && g:set_termguicolors 
  set termguicolors " make nvim slow in fluent terminal
  set background=dark
endif

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
  endif
endif
"=================================================theme===================================================

