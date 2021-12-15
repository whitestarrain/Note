"-----------------------------------格式化化插件--------------------------------------
" prettier 格式化插件
" post install (yarn install | npm install) then load plugin only for editing supported files
" 安装依赖npm install prettier@1.18.2
Plug 'prettier/vim-prettier', {
  \ 'do': 'npm install',
  \ 'for': ['javascript', 'typescript', 'css', 'less', 'scss', 'json', 'graphql', 'markdown', 'vue', 'yaml', 'html', 'java', 'bash', 'c'] }
"-----------------------------------格式化化插件--------------------------------------
autocmd vimenter * call PlugConfigAutoPair()
function! PlugConfigPrettier()
  " prettier config
  " 取消注解需求
  let g:prettier#autoformat_require_pragma = 0
  " 关闭自动格式化
  let g:prettier#autoformat = 0
  let g:prettier#autoformat_config_present = 0

  " vim-which-key
  " By default timeoutlen is 1000 ms
  set timeoutlen=500

  " vim-gitgutter
  " set the default value of updatetime to 600ms
  set updatetime=600
endfunction
