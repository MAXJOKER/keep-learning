### 常用命令 ###
```
# 返回redis服务器的各种信息和统计数据
# info [section]
> info CPU
# CPU
used_cpu_sys:0.031250
used_cpu_user:0.062500
used_cpu_sys_children:0.000000
used_cpu_user_children:0.000000

# 返回所有的客户端连接信息和统计数据
> client list
id=3 addr=127.0.0.1:61211 fd=11 name= age=96 idle=42 flags=O db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=0 obl=0 oll=0 omem=0 events=r cmd=monitor
id=4 addr=127.0.0.1:61233 fd=12 name= age=65 idle=0 flags=N db=0 sub=0 psub=0 multi=-1 qbuf=26 qbuf-free=32742 obl=0 oll=0 omem=0 events=r cmd=client

# 实时打印redis服务器接收到的指令
> monitor

# 将数据同步保存到磁盘
> save

# 将数据异步保存到磁盘
> bgsave

# 返回最近一次成功将数据保存到磁盘的时间戳
> lastsave
(integer) 1638971270

# 获取redis服务器配置参数
> config get parameter

# 设置redis服务器配置参数
> config set parameter value

# 获取日志级别
> config get loglevel
1) "loglevel"
2) "notice"

# 设置日志级别 debug 、verbose、notice、warning
> config set loglevel debug

# 将内存中的redis配置写入磁盘redis.conf中
> config rewrite

# 重置慢查询记录
> slowlog reset

# 查询前10个慢日志（默认为10个）
> slowlog get 10
1) 1) (integer) 0
   2) (integer) 1638971402
   3) (integer) 15090
   4) 1) "set"
      2) "test"
      3) "1"
   5) "127.0.0.1:61233"
   6) ""

# 返回慢日志条数
> slowlog len
(integer) 0

# 选择数据库，默认为0
> select 1

# 查询当前库所有key
> keys *
1) "test"

# 随机返回一个key
> randomkey
"test"

# 查询key总数
> dbsize
(integer) 2

# 清空当前数据库所有key
> flushdb
or
> flushall
```

### 配置文件 redis.conf ###
```
# 监听端口
port 6379

# 以守护进程方式启动
daemonize yes

# 监听ip
bind 127.0.0.1

# 客户端不操作等待多久后自动断开，0 - 不自动断开
timeout 0

# 日志级别
loglevel notice
```