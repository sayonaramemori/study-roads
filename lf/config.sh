#!/bin/bash
if [ -e ./lf ]; then
    sudo mkdir /etc/lf -p
    sudo cp ./lfrc /etc/lf/
    sudo cat ./lfcd >> ~/.bashrc
    source ~/.bashrc
    echo "Config OK, use ra to run lfcd"
else
    echo "Please download lf first"
fi
