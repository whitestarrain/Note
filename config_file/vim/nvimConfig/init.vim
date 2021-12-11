" 关于帮助文档。:help startify 即可
" 然后使用 c-] c-i c-o 进行跳转查看
" nvim-qt 要加上 --no-ext-tabline，才能使用airline的bufferline。加上 --fullscreen全屏显示
" 命令行模式输入 map ,inoremap ,nnoremap等可以查看 map键的映射
" 如果按键相关有什么问题可以查一下。比如auto-pairs占了<C-r>
"
if exists('g:vscode')
    let g:load_plugs=0
endif

let g:load_plugs = exists('g:load_plugs') && g:load_plugs

" 定义载入配置命令
" let g:absolute_config_path = expand("%:p")[0:strlen(expand("%:p"))-strlen(expand("%:t"))-1]
let g:absolute_config_path = expand("<sfile>:p")[0:strlen(expand("<sfile>:p"))-strlen(expand("<sfile>:t"))-1]
command! -nargs=1 LoadScript exec 'source' . ' ' . g:absolute_config_path . '<args>'

" python 环境
let g:python3_host_prog='D:/learn/anaconda3/envs/learn/python.exe'

let mapleader="\<space>"
let g:which_key_map =  {}
let g:which_key_map.z = { 'name' : '[second]' }
let g:which_key_map.s = { 'name' : '[startify]' }
let g:which_key_map.o = { 'name' : '[open in]' }
let g:which_key_map.h = { 'name' : '[hunk]' }
let g:which_key_map.f = { 'name' : '[find]' }
let g:which_key_map.b = { 'name' : '[buffer]' }
"=================================================leader end===================================================


"=================================================plug start===================================================
if g:load_plugs
  LoadScript plugs.vim
endif
"=================================================plug end===================================================

"=================================================plug config start===================================================
if g:load_plugs
  LoadScript plug_configs\airline.vim
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
""=================================================plug config end===================================================

"=================================================common config end===================================================
LoadScript common.vim
"=================================================commone config end===================================================

"=================================================map start===================================================
LoadScript functions.vim
LoadScript mappings.vim
"=================================================map end===================================================

"=================================================theme===================================================
if exists("&termguicolors") && exists("&winblend")
  syntax enable
  set termguicolors
  set winblend=0
  set wildoptions=pum
  set pumblend=5
  set background=dark
  " Use NeoSolarized
  " let g:neosolarized_termtrans=1
  " runtime .\colors\NeoSolarized.vim
  " colorscheme NeoSolarized
endif
set synmaxcol=5000       " 高亮显示行数，小一点节省内存，但是可能对大文件出现渲染错误 默认3000
syntax sync minlines=256
"=================================================theme===================================================

