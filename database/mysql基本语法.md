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

3. 删除字段
alter table `user` drop column `uuid`;

4. 添加索引
alter table `user` add index idx_time(`addtime`);

5. 删除索引
alter table `user` drop index idx_time;

6. 添加唯一索引
alter table `user` add unique `name`(`username`);

7. 修改唯一索引
alter table `user` drop index `name` add unique (`username`, `id`);

8. 修改主键
alter table `user` drop primary key, add primary key(`uid`);
```

### 语句
```
1. 插入语句
insert into user(`id`, `username`, `addtime`) values (1, 'a', 1234567890);
insert into ...
select ...

2. 更新语句
update user set username = 'b' where id = 1;

3. 删除语句
delete from user where id = 1;

4. 清空表
truncate table user;

5. 用户授权
grant select, insert, update, delete on email.* to 'test_user'@identified by '123456' with grant option;
```