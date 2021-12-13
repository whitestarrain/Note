"map syntax sync fromstart，大文件渲染
nnoremap <leader>p :syntax sync fromstart<cr>
let g:which_key_map.p = "syntax"

" 设置插入模式下光标左右移动方式
inoremap <C-l> <Right>
inoremap <C-h> <Left>
inoremap <C-j> <Down>
inoremap <C-k> <Up>

" 分屏移动映射
nnoremap <C-h> <C-w>h
nnoremap <C-j> <C-w>j
nnoremap <C-k> <C-w>k
nnoremap <C-l> <C-w>l

" 设置缓冲区跳转
noremap <M-h> :bp<cr>
noremap <M-l> :bn<cr>

" 设置tab跳转
noremap <M-j> :tabnext <cr>
noremap <M-k> :tabpre<cr>

"设置路径为当前文件所在路径
nnoremap <silent><leader>zp :cd %:h<cr>
let g:which_key_map.z.p = "setPathNow"

nnoremap <silent>o A<cr>

nnoremap <silent><leader>d :bp\|bd # <cr>
let g:which_key_map.d = "deleteNow"

nnoremap < <<
nnoremap > >>

" open in ...
nnoremap <silent><leader>ob :!chrome %:p <cr> <cr>
nnoremap <silent><leader>ov :!code % <cr> <cr>
let g:which_key_map.o.b = "browser"
let g:which_key_map.o.v = "vscode"

" 压缩空行
nnoremap <leader>zl :g/^$/,/./-j<cr> :/jj<cr>
let g:which_key_map.z.l = "compress empty line"

" markmap 编译
nnoremap <silent><leader>zk :!markmap %  <cr> 
let g:which_key_map.z.k = "markmap"

