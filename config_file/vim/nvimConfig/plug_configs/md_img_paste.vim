"------------------------------------img-paste-------------------------------------
" 粘贴图片
Plug 'ferrine/md-img-paste.vim'
"------------------------------------img-paste-------------------------------------

let g:mdip_imgdir = './image'  " 图片存放位置
let g:mdip_imgdir_intext = g:mdip_imgdir " md中()中的位置
" 定义设置路径的函数。注意：调用要使用call。
" ! 是重复时强行覆盖
function! SetImagePath()
    call ShowImagePath()
    let l:name = input('Image path: ')
    if(strlen(l:name)==0)
      echo "image path empty!"
      return ""
    endif
    let g:mdip_imgdir = l:name
    let g:mdip_imgdir_intext = l:name
    return name
endfunction

" . 作为字符串连接符
function! ShowImagePath()
  echo 'now:::mdip_imgdir:  ' . g:mdip_imgdir
  echo 'now:::mdip_imgdir_intext:  ' . g:mdip_imgdir_intext
endfunction

"md-img-paste map
autocmd FileType markdown nmap <buffer><silent> <leader>m :call mdip#MarkdownClipboardImage()<CR>
autocmd FileType md nmap <buffer><silent> <leader>m :call mdip#MarkdownClipboardImage()<CR>
let g:which_key_map.m = "imagePaste"

" 上面自定义方法的map
nnoremap <leader>zm :call SetImagePath()<cr>
let g:which_key_map.z.m = "setImagePath"

