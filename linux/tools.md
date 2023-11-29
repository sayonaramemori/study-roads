### 扫描局域网ip
```shell
//umask is 255.255.255.0 8*3 = 24
nmap -sn 192.168.1.0/24
//umask is 255.255.240.0 2*8+8-4=20
nmpa -sn 192.168.xx.0/20
```

### 扫描端口
nmap -sT -p- IP
