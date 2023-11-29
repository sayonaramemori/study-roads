set relativenumber
"set cursorline
set nohls

set encoding=UTF-8
set history=50

set expandtab
set tabstop=4
set smarttab
set shiftwidth=4
set softtabstop=4

" ===
" === Status/command bar
" ===
"set laststatus=2
set cmdheight=2
set autochdir
set showcmd
set showmode
set ruler

set wildmenu
set autoindent
set smartindent

"set list 
"set listchars=tab:▸\ ,trail:▫
set scrolloff=5
set timeout timeoutlen=300 ttimeoutlen=100

syntax on
filetype plugin indent on

"jump to the point where quit last time."
set viminfo='10,\"100,:20,%,n~/.viminfo
au BufReadPost * if line("'\"") > 0|if line("'\"") <= line("$")|exe("norm '\"")|else|exe "norm $"|endif|endif


" Resize splits with arrow keys
map <up> :vertical resize+5<CR>
map <down> :vertical resize-5<CR>

" Copy to system clipboard
vnoremap Y :w !xclip -i -sel c<CR>

" Set <LEADER> as ;
let mapleader=";"

"open vimrc anytime
nnoremap <leader>rc :e ~/.vimrc<CR>
inoremap <leader><leader> <Esc>

nnoremap <leader>t :tabe<CR>
nnoremap <left> :-tabnext<CR>
nnoremap <right> :+tabnext<CR>

"move between two screen
nnoremap <LEADER>k <C-w>k
nnoremap <LEADER>j <C-w>j
nnoremap <LEADER>h <C-w>h
nnoremap <LEADER>l <C-w>l

nnoremap <leader>w :wq<CR>
nnoremap <leader>v :vs 
nnoremap a A

nnoremap <leader>ta :TagbarToggle<CR>
nnoremap <leader><leader> :source ~/.vimrc<CR>
"inoremap { {<CR><CR>}<ESC>kcc
" Mapping for C and C++ header files
autocmd FileType cpp,c inoremap <buffer> { {<CR><CR>}<ESC>kcc
autocmd FileType java inoremap <buffer> { {<CR><CR>}<ESC>kcc

inoremap { {}<ESC>i
inoremap [ []<ESC>i
nnoremap - 0
nnoremap = $
nnoremap <backspace> d0

"nnoremap <space> zA
"set fdm=indent

call plug#begin()
Plug 'preservim/nerdcommenter'
Plug 'octol/vim-cpp-enhanced-highlight'
Plug 'preservim/nerdtree'
Plug 'vim-airline/vim-airline'
Plug 'iamcco/markdown-preview.nvim'
Plug 'connorholyday/vim-snazzy'
Plug 'rafi/awesome-vim-colorschemes'
call plug#end()

"Plug 'ludovicchabant/vim-gutentags'
""colorschem snazzy
colorschem abstract



let g:cpp_class_scope_highlight = 1
let g:cpp_member_variable_highlight = 1
let g:cpp_class_decl_highlight = 1
let g:cpp_posix_standard = 1
let g:cpp_concepts_highlight = 1

nnoremap <leader>n :NERDTreeFocus<CR>
nnoremap <leader>c :NERDTreeClose<CR>
nnoremap <C-n> :NERDTree<CR>
nnoremap <C-t> :NERDTreeToggle<CR>
nnoremap <C-f> :NERDTreeFind<CR>


" Exit Vim if NERDTree is the only window remaining in the only tab.
autocmd BufEnter * if tabpagenr('$') == 1 && winnr('$') == 1 && exists('b:NERDTree') && b:NERDTree.isTabTree() | quit | endif
" Open the existing NERDTree on each new tab.
"autocmd BufWinEnter * if &buftype != 'quickfix' && getcmdwintype() == '' | silent NERDTreeMirror | endif
" Start NERDTree and put the cursor back in the other window.
"autocmd VimEnter * NERDTree | wincmd p
