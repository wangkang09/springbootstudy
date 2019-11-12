### caffeine 配置
- spring.cache.caffeine.spec = "a=b,c=d"
- caffeine的可配置项就spec一个，内容如下 configure 方法中所示的内容
- 如果配置了refreshAfterWrite，必须自定义 刷新策略 CachingLoad

### expireAfterWrite策略

    @Nullable
    public ValueWrapper get(Object key) {
        Object value = this.lookup(key);
        return this.toValueWrapper(value);
    }
    @Nullable
    protected Object lookup(Object key) {
        return this.cache.getIfPresent(key);//关键，内部做手段
    }

```java
void configure(String key, @Nullable String value) {
    byte var4 = -1;
    switch(key.hashCode()) {
        case -1076762142:
            if(key.equals("expireAfterWrite")) {
                var4 = 7;
            }
            break;
        case -737229428:
            if(key.equals("weakKeys")) {
                var4 = 3;
            }
            break;
        case -83937812:
            if(key.equals("softValues")) {
                var4 = 5;
            }
            break;
        case 336225217:
            if(key.equals("expireAfterAccess")) {
                var4 = 6;
            }
            break;
        case 502967994:
            if(key.equals("weakValues")) {
                var4 = 4;
            }
            break;
        case 706249886:
            if(key.equals("refreshAfterWrite")) {
                var4 = 8;
            }
            break;
        case 817286328:
            if(key.equals("maximumWeight")) {
                var4 = 2;
            }
            break;
        case 1306358478:
            if(key.equals("recordStats")) {
                var4 = 9;
            }
            break;
        case 1685649985:
            if(key.equals("maximumSize")) {
                var4 = 1;
            }
            break;
        case 1725385758:
            if(key.equals("initialCapacity")) {
                var4 = 0;
            }
    }

```
