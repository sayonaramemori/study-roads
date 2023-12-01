#!/bin/bash
if [ -e ./lf ]; then
    sudo mkdir /etc/lf -p
    sudo cp ./lfrc /etc/lf/
    sudo cat ./lfcd >> ~/.bashrc
    sudo cp ./lf /usr/bin/
    source ~/.bashrc
    echo "Config OK, use ra to run lfcd, use q to return to the terminal"
else
    echo "Please download lf binary file first"
fi
