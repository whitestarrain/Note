Plug 'rust-lang/rust.vim'
Plug 'tenfyzhong/tagbar-rust.vim'

autocmd vimenter * call PlugConfigRust()

function PlugConfigRust()
  " 保存时代码自动格式化
  let g:rustfmt_autosave = 1

  let g:which_key_map.r = { 'name' : '[rust]' }

  " 手动调用格式化， Visual 模式下局部格式化，Normal 模式下当前文件内容格式化
  " 有时候代码有错误时，rust.vim 不会调用格式化，手动格式化就很方便
  " vnoremap <leader>rf :RustFmtRange<CR>
  " nnoremap <leader>rt :RustFmt<CR>
  " 设置编译运行 (来自 rust.vim，加命令行参数则使用命令 `:RustRun!`)
  " nnoremap <leader>rr :RustRun<CR>
  " 使用 `:verbose nmap <M-t>` 检测 Alt-t是否被占用
  " 使用 `:verbose nmap` 则显示所有快捷键绑定信息
  " nnoremap <leader>rt :RustTest<CR>

  " tagbar
endfunction
