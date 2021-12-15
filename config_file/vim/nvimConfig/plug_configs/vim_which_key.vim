"-----------------------------------vim-which-key--------------------------------------

" leader键提示插件
" On-demand lazy load
Plug 'liuchengxu/vim-which-key', { 'on': ['WhichKey', 'WhichKey!'] }
autocmd User vim-which-key call which_key#register('<Space>', 'g:which_key_map')
"-----------------------------------vim-which-key--------------------------------------
autocmd vimenter * call PlugConfigWhichKey()
function! PlugConfigWhichKey()
  " vim-which-key map
  nnoremap <silent> <leader> :WhichKey '<Space>'<CR>
endfunction


