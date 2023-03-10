set number
set cursorline
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
set cmdheight=1
set autochdir
set showcmd
set showmode
set ruler

set wildmenu
set autoindent
set smartindent

set list
set listchars=tab:▸\ ,trail:▫
set scrolloff=5

syntax on
colorschem ron
filetype plugin indent on

"jump to the point where quit last time."
set viminfo='10,\"100,:20,%,n~/.viminfo
au BufReadPost * if line("'\"") > 0|if line("'\"") <= line("$")|exe("norm '\"")|else|exe "norm $"|endif|endif


" Resize splits with arrow keys
map <up> :res +5<CR>
map <down> :res -5<CR>
map <left> :vertical resize-5<CR>
map <right> :vertical resize+5<CR>

" Copy to system clipboard
vnoremap Y :w !xclip -i -sel c<CR>

" Set <LEADER> as ;
let mapleader=";"

"open vimrc anytime
map <leader>rc :e ~/.vimrc<CR>

"move between two screen
map <LEADER>k <C-w>k
map <LEADER>j <C-w>j
map <LEADER>h <C-w>h
map <LEADER>l <C-w>l

map <leader>w :wq<CR>
"map <leader>q :q!<CR>
map <leader>v :vs 

nnoremap <leader>ta :TagbarToggle<CR>

nnoremap <leader><leader> I<ESC>d0i<backspace><CR><ESC>
inoremap { {<CR><CR>}<ESC>kcc
inoremap [ []<ESC>i
inoremap " ""<ESC>i
