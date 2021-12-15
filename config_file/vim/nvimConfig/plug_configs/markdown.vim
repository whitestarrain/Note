"-----------------------------------vim-markdown--------------------------------------
"markdonw 语法高亮
Plug 'plasticboy/vim-markdown'
"-----------------------------------vim-markdown--------------------------------------

"------------------------------------markdown-------------------------------------
autocmd vimenter * call PlugConfigMarkDown()
function! PlugConfigMarkDown()
  " markdown config
  " 禁用折叠
  let g:vim_markdown_folding_disabled=1
  " 禁用插件按键映射
  let g:vim_markdown_no_default_key_mappings = 1
    " gx: 在浏览器中打开光标所在处的链接
    " ge: 在 Vim 中打开光标下的链接指向的文件。主要用于打开使用相对位置标识的 markdown 文件
    " ]]: 跳转到下一个 header
    " [[: 跳转到之前的 header，与 ]c 相反
    " ][: 跳转到下一个兄弟 header
    " []: 跳转到上一个兄弟 header
    " ]c: 跳转到当前文本所属的 header
    " ]u: 跳转到"父"header
  
  let g:vim_markdown_conceal=0
  " 开启math公式高亮
  let g:vim_markdown_math = 1
  " 修改缩进格数量
  let g:vim_markdown_list_item_indent = 2
  " 代码块不隐藏
  let g:vim_markdown_conceal_code_blocks = 0
  let g:indentLine_conceallevel=0
endfunction
"------------------------------------markdown-------------------------------------
