"-------------------------------------vim-startify------------------------------------
" 开始菜单
Plug 'mhinz/vim-startify'
"-------------------------------------vim-startify------------------------------------

autocmd vimenter * call PlugConfigStarify()
function! PlugConfigStarify()
  " startify config
  " 设置Session时不要打开插件，比如NERDTree侧边栏，否则会报错
  " 关闭自动切换目录
  let g:startify_change_to_dir = 0
  " 切换header
  let g:startify_custom_header = [
        \ '      ____   U  ___ u  ____              _   _     ____   ',
        \ '   U /"___|   \/"_ \/ |  _"\    ___     | \ |"| U /"___|u ',
        \ '   \| | u     | | | |/| | | |  |_"_|   <|  \| |>\| |  _ / ',
        \ '    | |/__.-,_| |_| |U| |_| |\  | |    U| |\  |u | |_| |  ',
        \ '     \____|\_)-\___/  |____/ uU/| |\u   |_| \_|   \____|  ',
        \ '    _// \\      \\     |||_.-,_|___|_,-.||   \\,-._)(|_   ',
        \ '   (__)(__)    (__)   (__)_)\_)-   -(_/ (_")  (_/(__)__)  ',
        \ '   不要盲目跟风新技术，永远不要停止学习                   ',
        \ '                  技术没那么值钱，看的是商业模式与市场需求',
        \ '   注意身体健康，尽可能不要996                            '
        \]

  " 设置自动session保存
  let g:startify_session_persistence = 1
  " session保存前执行，防止因为懒加载而出现冲突
  let g:startify_session_before_save = [
      \ 'echo "Cleaning up before saving.."',
      \ 'silent! tabonly',
      \ 'silent! NERDTreeClose',
      \ 'silent! TagbarClose',
      \ ''
      \ ]
  " 开启自动保存的变量名称，保证每个session都可以个性化
  " g:mdip_imgdir: 图片存储路径
  let g:startify_session_savevars = [
          \ 'g:mdip_imgdir',
          \ 'g:mdip_imgdir_intext'
          \ ]
  " 自动跳转到版本管理工具的目录
  " let g:startify_change_to_vcs_root = 1

  " startify map
  " 跳转到开始菜单
  nnoremap <leader>st :Startify<cr>
  nnoremap <leader>ss :SSave<cr>
  let g:which_key_map.s = { 'name' : '[startify]' }
  let g:which_key_map.s.t = "start page"
  let g:which_key_map.s.s = "session save"
endfunction
