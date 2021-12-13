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

#### 基本配置 ####
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
# debug 输出debug日志
# verbose 冗长的信息，包括很多不太有用的信息，但比debug少
# notice 适用于生产线上环境
# warning 警告信息 
loglevel notice

# 设置pid文件存放路径
pidfile "/cache1/redis/data/redis.pid"

# 指定日志记录方式，可以写一个路径，也可以放空输出到stdout.
# 如果以守护进程方式运行但又放空，日志会写给/dev/null
logfile ""

# 限制可设置数据库的数量，默认只允许16个，第一个数据库编号为0
database 16

# 允许使用最大的内存(要配合maxmemory-policy使用)，0表示不限制
# 当redis内容数据快达到maxmemory时，会实行数据淘汰策略
maxmemory 0

# 淘汰策略
maxmemory-policy no-enviction

# 为了防止某个脚本执行时间过长导致redis无法提供服务，redis提供了
# lua-time-limit 参数限制脚本的最长运行时间，默认为5s。当超时后，
# redis接收其他命令但不执行，返回BUSY错误
lua-time-limit 5000

# 设置最多同时可连接的客户端数量，默认为0 无限制
# 如果达到了限制，redis会关闭所有新连接并返回“max number of clients reached”
maxclients 10000

# 读入多个配置文件
include /path/xxx1.conf
include /path/xxx2.conf
```

#### 慢查询配置相关 ####
```
# 单位为微妙,当命令执行时间超过该值则会被redis记录为慢查询
# 配置为负数则禁用慢查询日志
# 配置为0则记录所有命令
slowlog-log-slower-than 10000

# 设置慢查询日志长度，已满则删掉旧的保留新的
# 可使用 slowlog reset 释放内存
slowlog-max-len 128
```

#### RDB 快照相关配置 ####
```
# save <seconds> <changes>
# 900秒内有一个key修改，就保存快照到磁盘
save 900 1 

# 存储到本地时是否使用压缩，默认为yes，使用lzf算法，压缩消耗cpu,不压缩消耗磁盘
rdbcompression yes

# 本地数据库磁盘中的文件名
dbfilename dump.rdb

# crc64校验，更加耐攻击，但消耗10%性能
rdbchecksum yes

# 指定本地数据库路径，默认当前目录
dir ./

# 配置yes，持久化失败，redis停止接收更新操作
# 配置no，持久化失败，redis继续处理
stop-writes-on-bgsave-error yes
```

#### AOF持久化相关配置 ####
```
 - append-only 可以做到全部数据不丢失，但性能差些。原理是以日志的形式记录每个写操作，将redis执行过的所有写指令记录下来，只允许追加文件，但不可以改写文件，reids启动时读取改文件重新构建数据。

 # 开启AOF
 appendonly yes

 # 设置aof文件名
 appendfilename "xxx.aof"

 # aof落盘方式：
 # always 总是写入aof文件，并完成磁盘同步
 # ererysec 每秒写入aof文件，并完成磁盘同步
 # no 写入aof文件，不等待磁盘同步
 # 从持久化角度，always是最安全的；从效率上看，no是最快的。
 # redis默认设置选择折中方案，everysec
 appendfsync everysec
```

#### 安全相关设置 ####
```
# 本机redis密码
requirepass "xxx"

# 将危险的命令重命名或禁用
rename-command flushall ""

# 重命名后，需要用新的命令来操作，否则会报错
rename-command flushdb abcdef
```