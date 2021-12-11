"------------------------------------coc-------------------------------------
" " 解决启动稍微延迟问题： 让coc服务，在neovim启动后，500ms后才启动
" let g:coc_start_at_startup=0
" function! CocTimerStart(timer)
"     exec "CocStart"
" endfunction
" call timer_start(500,'CocTimerStart',{'repeat':1})
" 
" "解决coc.nvim大文件卡死状况。超过0.2m的文件，禁用coc补全。我们很多时候要打开log文件，tag文件等嘛！
" let g:trigger_size = 0.1 * 1048576
" augroup hugefile
"   autocmd!
"   autocmd BufReadPre *
"         \ let size = getfsize(expand('<afile>')) |
"         \ if (size > g:trigger_size) || (size == -2) |
"         \   echohl WarningMsg | echomsg 'WARNING: altering options for this huge file!' | echohl None |
"         \   exec 'CocDisable' |
"         \ else |
"         \   exec 'CocEnable' |
"         \ endif |
"         \ unlet size
" augroup END
" markmap
command! -range=% Markmap CocCommand markmap.create <line1> <line2>
"------------------------------------coc-------------------------------------
