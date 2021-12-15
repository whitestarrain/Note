"
"-----------------------------------snippt插件--------------------------------------
"
Plug 'SirVer/ultisnips'
Plug 'honza/vim-snippets'
"-----------------------------------snippt插件--------------------------------------
autocmd vimenter * call PlugConfigUntiSnips()
function! PlugConfigUntiSnips()
  let g:UltiSnipsSnippetDirectories=[$HOME.'/.vim/UltiSnips']
  let g:UltiSnipsExpandTrigger="<tab>"               
  let g:UltiSnipsJumpForwardTrigger="<tab>"
  let g:UltiSnipsJumpBackwardTrigger="<c-b>"
  let g:UltiSnipsSnippetDirectories=[$HOME.'/.vim/UltiSnips']
endfunction
