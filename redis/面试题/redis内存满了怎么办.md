### 假如redis内存满了怎么办？
<hr>

1. 通常情况下，我们可以通过设置 **redis.conf** 中 <font color=red>maxmemory</font> 参数值来指定redis内存的大小。
   ```
    maxmemory 100mb
   ```

   当然也可以通过命令行的方式进行设置，
   ```
    config get maxmemory
    config set maxmemory 100mb
   ```
    假如在实际应用中，redis使用的内存超过设定的大小时，redis中有淘汰策略，把需要淘汰的key从内存中给淘汰掉。

<br>
<br>    


### 淘汰策略 ###
<hr/>
redis提供了**六种淘汰策略**，其中默认的是 **noeviction**，如下：
```
2.8 及以前的版本默认的淘汰策略是：volatile-lru
3.0 及以后的版本的默认淘汰策略是：noeviction
```
* <font color=red>noeviction</font>**（默认策略）**：当内存大小达到阀值时，所有申请内存的指令都会报错。
* <font color=red>allkeys-lru</font>：所有key都使用**LRU算法**进行淘汰。
* <font color=red>volatile-lru</font>：所有**设置了过期时间的key**使用LRU算法进行淘汰。
* <font color=red>allkeys-random</font>：所有key使用**随机淘汰**方式进行淘汰。
* <font color=red>volatile-random</font>：所有**设置了过期时间的key**使用随机淘汰方式进行淘汰。
* <font color=red>volatile-ttl</font>：所有设置了过期时间的key**根据过期时间进行淘汰，越早过期就越快淘汰**。

1. 如果redis数据中有热key，冷key区分，或者不清楚缓存使用情况，可以使用 <font color=red>allkeys-lru</font>策略。
2. 如果redis数据中所有key都是随机访问的，访问频率差不多，则可以使用<font color=red>allkeys-random</font>策略。

redis.conf 中配置如下：
```
maxmemory-policy allkeys-lru
```

命令行配置如下：
```
config get maxmemory-policy
config set maxmemory-policy allkeys-lru
```
<br>
<br>

### LRU算法 ###
<hr/>

**LRU(Least Recently Used)**：即表示最近最少使用，也就是最近时间内最少被访问的key，算法根据数据的历史访问记录来进行淘汰数据。**核心思想是：如果一个key在最近很少被使用到，那么在将来也很少会被访问。**

redis使用的是近似的LRU算法，通过随机采集法淘汰key，每次都会随机选出几个key，然后淘汰掉里面最少使用的key。每次采集key的数量可以在配置文件中配置，
```
maxmemory-sample 10
```
当每次采集key的数量越多，越接近真实的LRU算法。

为了根据时间实现LRU算法，redis必须为每个key额外增加一个内存空间用于存储每个key的时间，大小是3字节。在redis 3.0中对近似LRU算法做了优化，redis中会维护大小是16的内存缓冲池。

第一次随机选取的数据，都会放到缓冲池中，缓冲池中的数据根据时间排序。

第二次随机选取的数据，只有小于缓冲池内数据的最小时间，才会被放入缓冲池内。

当某一时刻缓冲池数据满了，时间最大的key会被挤出缓冲池。当执行淘汰时，直接从缓冲池中选取**最近访问时间**最小的key进行淘汰。

这样做的目的是选出最近似符合最近最少被访问的key，淘汰正确的key。因为随机样本中，最小时间可能并不是真正意义上的最小时间。

然而LRU算法有个问题：如果一个key之前并没有经常被访问，最近一次刚好访问到了，那么就会认为他是热点数据，不会被清理。也有可能是一个key以前经常被访问，最近时间内没有被访问到，那么这个key很有可能被清理掉，导致数据误清理。

<br>
<br>

### LFU算法 ###
<hr/>
LFU(Least Frencently Used)：最近频繁被使用，以最近时间段的被访问次数的频率作为判断标注。核心思想是：根据key最近被访问的频率进行淘汰，访问频率低的key优先淘汰。

LFU算法反映了最近时间内key的热度，不会因为偶尔被访问一次就认为是热度数据。

LFU算法中支持 <font color=red>allkeys-lfu</font>和<font color=red>volatile-lfu</font>策略。

<br>
<br>

### 删除过期键策略 ###
redis中有三种删除策略，分别是：
1. 定时删除：创建一个定时器，定时执行对key的删除操作。
2. 惰性删除：每次只有访问key的时候，才会检查key的过期时间，如果过期则删除key。
3. 定期删除：每个一段时间，检查删除过期的key。

定时删除对内存来说是友好的，定时清理出内存空间，但对CPU却是不友好的，需要维护一个定时器，占用CPU资源。

惰性删除对CPU是友好的，不需要额外维护，但对内存却是不友好的，如果有些key一直没被访问，则是一直没法删除，一直占用内存。

定期删除则是折中方案，每个一段时间删除过期key。
