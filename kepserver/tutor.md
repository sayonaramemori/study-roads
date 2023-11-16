## 安装kepserver
> 资料和[手册](./使用手册/kepserverex-manual.pdf)  
1. 点击[下载](./安装包/kepserver.zip)  
2. 安装后打开安装目录，将破解补丁复制替换即可  

## PLC to Database
> 以S7-200 smart 和 MySQL 为例。
1. 打开kepserver, 右键点击连接性，然后左键点击新建通道。
2. 然后选择通道类型为 Siemens TCP/IP Ethernet。命名后，选择和PLC同一个网段的网络适配器。然后一直下一步即可。
    ![siemens-ip](./img/plc_ip.png)  
    ![net-adaptor](./img/internet_adaptor.png)
3. 然后单击添加设备,命名后选择相应的PLC型号，这里我选择的是S7-200，然后下一步输入PLC的IP，即上图的192.168.2.1。
4. 一直下一步到这个界面，将其中的TSAP改为 200或者201或者300或者301(这些数字仅仅针对S7-200) 填入其中。然后下一步即可
    ![caution](./img/caution1.png)
5. 然后点击刚刚创建的设备，再点击添加静态标记。
    ![marks](./img/mark.png)
6. 这里我们以读取温度为例，如下图所示，表示温度的浮点类型存储在VD100.
    ![plc-instance](./img/plc_instance.png)
    我们关注三个字段即可，即名称，地址和数据类型。将地址和数据类型填入和PLC中的一致即可。
    ![static-mark](./img/static_mark.png)
7. 点击导航栏上的工具，启动OPC Client即可查看对应的静态标记是否读取成功。
    ![test-read](./img/test_read.png)


### kepserver读PLC


#### 读取plc指定变量地址


#### kepserver写数据库


## Database to PLC


### kepserver读数据库


### kepserver写PLC


### Some bugs
