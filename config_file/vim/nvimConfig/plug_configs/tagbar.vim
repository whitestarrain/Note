"------------------------------------tagbar-------------------------------------
" 设置宽度
" let g:tagbar_width = 30
"设置tagber对于markdown的支持
let g:tagbar_type_markdown = {
        \ 'ctagstype' : 'markdown',
        \ 'kinds' : [
                \ 'h:headings',
        \ ],
    \ 'sort' : 0
\ }
" 位置调整
"------------------------------------tagbar-------------------------------------

" tagbar map
nnoremap <leader>t :TagbarToggle<CR>
let g:which_key_map.t = "tagbar"

