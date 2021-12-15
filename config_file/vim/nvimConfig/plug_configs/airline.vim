"-------------------------------------vim-airline------------------------------------
" 下方提示栏
Plug 'vim-airline/vim-airline'
" 下方提示栏主题
Plug 'vim-airline/vim-airline-themes'

"-------------------------------------vim-airline------------------------------------

"------------------------------------airline-------------------------------------

let g:airline#extensions#tabline#enabled = 1
let g:airline#extensions#bufferline#enabled = 1

autocmd vimenter * call AirlineConfig()
function! AirlineConfig()
  set laststatus=2  "永远显示状态栏

  " "取消显示warning部分
  let g:airline_section_warning = ''
  let g:airline_section_y = airline#section#create(['%{strftime("%H:%M")}'])
  let g:airline#extensions#wordcount#enabled = 0

  " let g:lightline = {
  "   \ 'colorscheme': 'onedark',
  "   \ }
  " let g:airline_theme='onedark'

  " 设置状态栏
  let g:airline#extensions#tabline#enabled = 1
  let g:airline#extensions#tabline#left_alt_sep = '|'
  let g:airline#extensions#tabline#buffer_nr_show = 0
  let g:airline#extensions#tabline#formatter = 'default'
  let g:airline#extensions#keymap#enabled = 1
  let g:airline#extensions#tabline#buffer_idx_mode = 1
  let g:airline#extensions#tabline#buffer_idx_format = {
        \ '0': '0 ',
        \ '1': '1 ',
        \ '2': '2 ',
        \ '3': '3 ',
        \ '4': '4 ',
        \ '5': '5 ',
        \ '6': '6 ',
        \ '7': '7 ',
        \ '8': '8 ',
        \ '9': '9 '
        \}
  if !exists('g:airline_symbols')
      let g:airline_symbols = {}
  endif
  " let g:airline_symbols.branch = 'BR'
  let g:airline_symbols.linenr = "CL" " current line
  let g:airline_symbols.whitespace = '|'
  let g:airline_symbols.maxlinenr = 'Ml' "maxline
  let g:airline_symbols.readonly = "RO"
  let g:airline_symbols.dirty = "DT"
  let g:airline_symbols.crypt = "CR" 
endfunction
"------------------------------------airline-------------------------------------
