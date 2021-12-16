"------------------------------------latex-------------------------------------
" latex支持
Plug 'lervag/vimtex'
"------------------------------------latex-------------------------------------

autocmd vimenter * call PlugConfigLatex()

function PlugConfigLatex()
  "编译过程中不忽略警告信息
  let g:vimtex_quickfix_open_on_warning=1
endfunction
