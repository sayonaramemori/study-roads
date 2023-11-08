### clash 项目地址
- [clash] click here to go
- <https://github.com/Dreamacro/clash>
- 下载已编译好的clash执行程序

### 配置文件
1. 打开windows里的clash，右键打开配置文件所在目录
2. 复制配置文件，包括节点列表文件`config.yml`(一个以时间戳命名的文件)和`Country.mmdb`
3. 将上述文件放到文件夹clash里，然后传送至/opt下，解压并赋予执行权限
4. 执行`./clash --help`进行测试，如果执行失败，说明下载错了版本，需重新下载对应版本

### 将clash设为守护进程
> 键入`cd /etc/systemd/system`  
> 然后`vim clash.service`
> 文件内容如下，直接复制粘贴即可(按i进入插入模式，然后shift+Insert粘贴)，然后退出保存(ESC + ZZ)
```shell
[Unit]
Description=clash-core
[Service]
Type=simple
ExecStart=/opt/clash/clash -f /opt/clash/config.yml -d /opt/clash/
[Install]
WantedBy=multi-user.target
```

### 在终端中启用clash
```shell
#可将下面两行放入~/.bashrc中，否则仅对当次终端有效。
export http_proxy="http://127.0.0.1:7890"
export https_proxy="http://127.0.0.1:7890"

#查看环境变量
export

#启动clash
systemctl daemon-reload
systemctl start clash

#把clash设为开机自启
systemctl enable clash.service
#检查是否开机自启
systemctl is-enabled clash.service
```


### 测试
```shell
curl -i google.com
```

### 关闭clash
```shell
#终止clash服务
systemctl stop clash

#关闭开机自启
systemctl disable clash

#删除设置的环境变量
unset http_proxy
unset https_proxy
```

[clash]:https://github.com/Dreamacro/clash

