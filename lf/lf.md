### Github website
---
[lf](https://github.com/gokcehan/lf) -- A terminal file manager  

### Download  
---
- Build by Go  
- Binary file pre-builted  

### Configuration
---
```shell
"""Put these codes to .bashrc
"""Quit lf to the current directory
lfcd(){
    tmp="$(mktemp)"
    # `command` is needed in case `lfcd` is aliased to `lf`
    command lf -last-dir-path="$tmp" "$@"
    if [ -f "$tmp" ]; then
        dir="$(cat "$tmp")"
        rm -f "$tmp"
        if [ -d "$dir" ]; then
            if [ "$dir" != "$(pwd)" ]; then
                cd "$dir"
            fi
        fi
    fi
}

alias ra="lfcd"
```

### Config file
```shell
"""Path:/etc/lf/lfrc
"""Put below to lfrc file, only for linux
map v $vim $fx
map . set hidden!
set hiddenfiles ".*:*.aux:*.log:*.bbl:*.bcf:*.blg:*.run.xml"
map x $rm $fx -rvi
```

### Operation
```
//mark the current work directory
m
//unmark
"
//jump to the directory marked
'
//delete a file
x
//edit with vim
v
```


