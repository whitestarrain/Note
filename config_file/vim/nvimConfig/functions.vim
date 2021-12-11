" 关闭其他标签页
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
map <leader>bo :BcloseOthers<cr><cr>
let g:which_key_map.b.o = "deleteOthers"

" 将链接下的文件下载到指定位置
function! SaveImageByUrl()
    let l:name = input('Image name: ')
    if(strlen(l:name)==0)
      echo "\n"
      echo "image name empty!"
      return ""
    endif
    let l:name = l:name . ".png"
    echo "\n"
    let l:relate_path = expand("%")[0:strlen(expand("%"))-strlen(expand("%:t"))-2]
    let l:image_path = l:relate_path . g:mdip_imgdir[1:] . "/" . l:name
    let l:image_path = substitute(l:image_path,"\\","/","")
    execute("!curl <cfile> >" . l:image_path)
    execute "normal! 0Di![" . l:name . "](" . g:mdip_imgdir . "/" . l:name . ")"
endfunction
nnoremap <leader>zd :call SaveImageByUrl() <cr>
let g:which_key_map.z.d = "downloadImageFile"

" 打开光标下文件
function! OpenUnderCurser(command)
    let l:file_path = expand("<cfile>")
    if l:file_path[0:3] == "http"
      let l:relate_path = l:file_path 
    else
      let l:relate_path = expand("%:p")[0:strlen(expand("%:p"))-strlen(expand("%:t"))-2] . "/" . l:file_path
      let l:relate_path = substitute(l:relate_path,"\\","/","")
    endif
    execute(":!" . a:command . " " . l:relate_path)
endfunction
nnoremap <silent><leader>ocb :call OpenUnderCurser("chrome") <cr> 
nnoremap <silent><leader>ocv :call OpenUnderCurser("code")  <cr> 
let g:which_key_map.o.c = {'name' : '[file under curser]' }
let g:which_key_map.o.c.b = "chrome"
let g:which_key_map.o.c.v =  "vscode"

" 从文件中查找(rg替代)
function! FindFromAllFile()
    let l:find_str = input('what are you find:')
    if(strlen(l:find_str)==0)
      echo "\n"
      echo "finded str is empty!"
      return ""
    endif
    call tagbar#CloseWindow()
    execute("vimgrep /" . l:find_str . "/ ./**/*.md ./**/*.txt ./**/*.java ./**/*.scala ./**/*.py | copen")
endfunction

" 网页搜索选中内容
function! SearchOnline(searchUrl)
     let l:searchterm = getreg("s")
     silent! exec "silent! !chrome " . a:searchUrl . l:searchterm
endfunction
vnoremap <silent><m-g> "sy<Esc>:call  SearchOnline("https://www.google.com/search?q=")<CR>
vnoremap <silent><m-b> "sy<Esc>:call  SearchOnline("https://www.baidu.com/s?wd=")<CR>
