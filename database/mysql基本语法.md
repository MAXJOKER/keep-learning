## mysql基本语法

### 建表
```
create table if not exists `user` (
    `id` int(11) unsigned auto_increment,
    `username` varchar(32) not null comment '',
    `cid` int(11) not null default '100' commnet '',
    `addtime` datetime not null comment '',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '',
    primary key (`id`),
    unique key `idx_unq`(`username`),
    key `idx_time`(`addtime`)
) engine=innodb auto increment = 1 default charset=utf8
```

### 查看表结构
```
desc user;
```

### 查看创建语句
```
show create table user;
```

### 删除表
```
drop table user;
```

### 修改表
```
1. 添加字段
alter table `user` add column `uuid` varchar(32) not null default '1' comment '';

2. 修改字段
alter table `user` change  `username` `name` varchar(32) not null default '' comment '';

3. 
```