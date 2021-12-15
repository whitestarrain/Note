
"------------------------------------auto-pairs-------------------------------------
"括号补全
Plug 'jiangmiao/auto-pairs'
"------------------------------------auto-pairs-------------------------------------
"------------------------------------auto-pair-------------------------------------

autocmd vimenter * call PlugConfigAutoPair()
function! PlugConfigAutoPair()
  " 自动删除括号的键设置，为了释放<C-h>
  let g:AutoPairsMapCh=0
endfunction
"------------------------------------auto-pair-------------------------------------
