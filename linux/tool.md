### 扫描局域网ip
```shell
//umask is 255.255.255.0 8*3 = 24
nmap -sn 192.168.1.0/24
//or
nmap -sP IP-Range

//umask is 255.255.240.0 2*8+8-4=20
//-v is verbose   
nmap -v -sn 192.168.xx.0/20
```

### 扫描端口
> TCP 全连接扫描
> `nmap -sT IP -p-`  
> TCP 半连接扫描(recommended, less time cost)  
> `nmap -sS IP -p-`  

### for finding plc
> `nmap -sn 192.168.31.0/24 | grep for | cut -d ' ' -f5,6`

### 路由追踪
> `traceroute IP`

### Rename host
> `hostnamectl set-hostname [name]`


### User add
```shell
'''default config /etc/default/useradd
'''-m denote create HOME
useradd -m [name]
'''show default config
useradd -D 
```

### bg work
```shell
cmd &
nohup cmd
jobs -l
'''restart bg job
bg [num]
```

### File descriptor  
|descriptor|acronym|description|
|:----|:-----|:------|
|0|STDIN||
|1|STDOUT||
|2|STDERR||
```shell
'''Temporarily impose to output to descriptor, add & to >n
echo "This is err" >&2
ls 1> res.txt

'''force output to specified file, often used in shell script
'''stdout to outputfile and stderr to outputerr
exec 1>outputfile
exec 2>outputErr

'''create your won file descriptor
exec 3>outputfile
ls >&3
'''or append
exec 3>>outputfile

'''redirect descriptor
'''3 redirect ro 1
exec 3>&1

'''close descriptor, use &-
exec 3>&-

'''prevent output
ls -al > /dev/null
```
