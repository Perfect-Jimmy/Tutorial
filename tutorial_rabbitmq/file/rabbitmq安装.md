### 安装erlang
1. 下载http://www.erlang.org/downloads,上传到指定目录
2. 解压:tar -xvf otp_src_20.2.tar.gz
3. cd otp_src_20.2,执行`./configure --prefix=/opt/erlang`,就会开始编译安装到/opt/erlang下
4. 执行make和make install
5. 在/etc/profile添加如下,并执行`source /etc/profile`
```
ERLANG_HOME=/opt/erlang
export PATH=$PATH:$ERLANG_HOME/bin
export ERLANG_HOME
```
6. 验证安装是否成功,执行`erl`
```
Erlang/OTP 20 [erts-9.2] [source] [64-bit] [smp:8:8] [ds:8:8:10] [async-threads:10] [hipe] [kernel-poll:false]
```


### 安装rabbitmq
1. 下载
2. 执行`rpm -ivh rabbitmq-server-3.7.3-1.el7.noarch.rpm`
3. 开放端口`firewall-cmd --zone=public --add-port=5672/tcp --permanent`
4. 安装管理界面`rabbitmq-plugins enable rabbitmq_management`
5. 开放端口`firewall-cmd --zone=public --add-port=15672/tcp --permanent`

#### 安装rabbitmq报错
1. `yum install socat`解决如下报错
```
error: Failed dependencies:
	socat is needed by rabbitmq-server-3.7.3-1.el7.noarch
```