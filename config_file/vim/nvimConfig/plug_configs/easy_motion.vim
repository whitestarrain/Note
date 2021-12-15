"------------------------------------easymotion-------------------------------------
Plug 'easymotion/vim-easymotion'
"------------------------------------easymotion-------------------------------------
autocmd vimenter * call PlugConfigEasyMotion()
function! PlugConfigEasyMotion()
  " easymotion map
  nmap <leader>j <Plug>(easymotion-s2)
  let g:which_key_map.j = 'easymotion'
  " acejump
  " 其中<leader><leader>{j|k|w|b}，可以快速跳转行和列
endfunction


