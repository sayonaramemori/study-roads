### 扫描局域网ip
```shell
//umask is 255.255.255.0 8*3 = 24
nmap -sn 192.168.1.0/24
//umask is 255.255.240.0 2*8+8-4=20
nmap -v -sn 192.168.xx.0/20
//-v is verbose   
```

### 扫描端口
> TCP 全连接扫描
> `nmap -sT IP -p-`  
> TCP 半连接扫描(recommended, less time cost)  
> `nmap -sS IP -p-`  


### 路由追踪
> `traceroute IP`

