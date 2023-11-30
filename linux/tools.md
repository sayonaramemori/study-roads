### WSL INSTALL
1. search string "功能" in windows menu.
2. click the setting -- "启动或关闭Windows功能"
3. selet subsystem for linux and virtual machine platform.
4. restart your computer
5. open Microsoft store and search string "wsl" and install what you want.
6. click open after finishing installation. Then a terminal will be shown.
7. if encounter error, open powershell in a new terminal and run `wsl --update`


#### some examples
> `wsl -l -v`
> `wsl --help`
> `wsl --export <Distri> <Path-to-store>`
> `wsl --import <New-Name> <Installation-DIR> <Path-exported-Package>`
> `wsl --unregister <Distri>`
> `wsl -d <Distri> -u <User-Name>`


### 扫描局域网ip
```shell
//umask is 255.255.255.0 8*3 = 24
nmap -sn 192.168.1.0/24
//umask is 255.255.240.0 2*8+8-4=20
nmpa -sn 192.168.xx.0/20
```

### 扫描端口
nmap -sT -p- IP
