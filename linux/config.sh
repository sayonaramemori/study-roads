#!/bin/bash

plugPath=~/.vim/autoload
plug=plug.vim

if [ -d "$plugPath" ]
then
    echo "Exists autoload"
    if [ -e "$plugPath/$plug" ]
    then
        echo "Exists plug.vim"
    else
        cp ./vim-config/plug.vim ~/.vim/autoload/
    fi
else
    mkdir ~/.vim/autoload -p
    cp ./vim-config/plug.vim ~/.vim/autoload/
    echo "cp plug.vim to ~/.vim/autoload completed"
fi

cp ./vim-config/.vimrc ~/.vimrc
echo "cp .vimrc to home directory completed"
