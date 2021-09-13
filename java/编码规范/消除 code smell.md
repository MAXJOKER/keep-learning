## 自勉
写代码要有点追求好吧，这么多code smell，自己都觉得不合适，更别说别人了。code smell 日积月累，不仅影响自身的编码水平，代码的性能也可能变差，屎上雕花这种事情咱们还是不要做了。精益求精。

### 性能提升

  1. 需要取Map的键值时，应使用 entrySet()
   
当需要取Map的key时，使用 keySet() 是正确的。但如果需要取 key，value，使用 entrySet() 更加高效。// todo 为啥更高效

<B>keySet() 的做法</B>
   ```
        Map<String, String> map = ...;
        for (String key : map.keySet()) {
            String value = map.get(key);
        }
   ```

<B>entrySet() 的做法</B>
   ```
        Map<String, String> map = ...;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();
        }
   ```

   2. 使用 Collection.isEmpty() 检测空
使用Collection.size() 检测空是没问题的，但使用 Collection.isEmpty() 更加易读，并且能获得更好的性能。任何 Collection.isEmpty() 实现的时间复杂度都是O(1)，而某些 Collection.size() 实现的时间复杂度可能是O(n)。 如果还需要检查是否为空，可以使用 CollectionUtils.isEmpty。//todo 哪些？