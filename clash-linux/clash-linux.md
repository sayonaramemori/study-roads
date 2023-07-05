### 1.clash project repository
- [clash] click here to go

### 2.Get config and Country.mmdb files  
1. open clash on windows.
2. open the folder where config file exists.
3. copy to your device.
4. gether all files into a directory.

### 3.write a service for clash on linux  
- example:edit with the path--`/etc/systemed/system/clash.service`  
```shell
[Unit]
Description=clash-core
[Service]
Type=simple
ExecStart=[Your-clash-directory]/clash -f [config-path] -d [country-path]
[Install]
WantedBy=multi-user.target
Alias=clash.service
```

### 4.using on terminal
```shell
export http_proxy="http://127.0.0.1:7890"
export http_proxys="http://127.0.0.1:7890"
system

```

[clash]:https://github.com/Dreamacro/clash
