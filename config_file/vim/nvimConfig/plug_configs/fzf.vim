"------------------------------------fzf-------------------------------------
" 多文件模糊搜索
Plug 'junegunn/fzf', { 'do': { -> fzf#install() } }
Plug 'junegunn/fzf.vim'
"------------------------------------fzf-------------------------------------

autocmd vimenter * call PlugConfigFZF()
function! PlugConfigFZF()
  " FZF
  nnoremap <silent><leader>ff :FZF<CR>
  let g:which_key_map.f.f = 'fzf-file'

  nnoremap <silent><leader>fb :Buffers<CR>
  nnoremap <silent><leader>/ :Buffers<CR>
  let g:which_key_map.f.b = 'fzf-buffer'
endfunction

