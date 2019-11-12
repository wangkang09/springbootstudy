```java
cacheManage.getCache(obj);
@Nullable
public Cache getCache(String name) {
    Cache cache = (Cache)this.cacheMap.get(name);
    // dynamic默认为true，这样当cacheManage中没有此cache时，会创建一个
    if(cache == null && this.dynamic) { 
        ConcurrentMap var3 = this.cacheMap;
        synchronized(this.cacheMap) {
            cache = (Cache)this.cacheMap.get(name);
            if(cache == null) {
                cache = this.createCaffeineCache(name);
                this.cacheMap.put(name, cache);
            }
        }
    }
    return cache;
}

protected Cache createCaffeineCache(String name) {
    return new CaffeineCache(name, this.createNativeCaffeineCache(name), this.isAllowNullValues());//isAllowNullValues默认为true
}

// 默认cacheLoader为null，这样创建的cache都是默认的！
//所以，创建定义的cache关键在于cacheLoader！
protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
    return (com.github.benmanes.caffeine.cache.Cache)(this.cacheLoader != null?this.cacheBuilder.build(this.cacheLoader):this.cacheBuilder.build());
}

// 第一步，初始化
CaffeineCacheConfiguration(CacheProperties cacheProperties, CacheManagerCustomizers customizers, ObjectProvider<Caffeine<Object, Object>> caffeine, ObjectProvider<CaffeineSpec> caffeineSpec, ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
    this.cacheProperties = cacheProperties;
    this.customizers = customizers;
    this.caffeine = (Caffeine)caffeine.getIfAvailable();
    this.caffeineSpec = (CaffeineSpec)caffeineSpec.getIfAvailable();
    this.cacheLoader = (CacheLoader)cacheLoader.getIfAvailable();
}
// 第二步，
@Bean
public CaffeineCacheManager cacheManager() {
    // 调用第三步
    CaffeineCacheManager cacheManager = this.createCacheManager();
    List<String> cacheNames = this.cacheProperties.getCacheNames();
    if(!CollectionUtils.isEmpty(cacheNames)) {
        cacheManager.setCacheNames(cacheNames);
    }

    return (CaffeineCacheManager)this.customizers.customize(cacheManager);
}

// 第三步，
private CaffeineCacheManager createCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    // 调用第四步
    this.setCacheBuilder(cacheManager);
    if(this.cacheLoader != null) {
        cacheManager.setCacheLoader(this.cacheLoader);
    }

    return cacheManager;
}

// 第四步
private void setCacheBuilder(CaffeineCacheManager cacheManager) {
    String specification = this.cacheProperties.getCaffeine().getSpec();
    // 最关键：设置caffeine cache 的参数
    // 可以看出，最先考虑 spc 的值；然后是caffeineSpec,最后是caffeine
    // 我们可以从这几个点，来设置 caffeine 的参数！！
    if(StringUtils.hasText(specification)) {
        cacheManager.setCacheSpecification(specification);
    } else if(this.caffeineSpec != null) {
        cacheManager.setCaffeineSpec(this.caffeineSpec);
    } else if(this.caffeine != null) {
        cacheManager.setCaffeine(this.caffeine);
    }
}

// 其中 cacheProperties 是通过 spring.cache设置值的
@ConfigurationProperties(
    prefix = "spring.cache"
)
public class CacheProperties {}

// caffeineSpec和caffeine可以手动注入到容器中，即可

// 解析spc字符串！
public static CaffeineSpec parse(String specification) {
    CaffeineSpec spec = new CaffeineSpec(specification);
    String[] var2 = specification.split(",");
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
        String option = var2[var4];
        spec.parseOption(option.trim());
    }
    return spec;
}
void parseOption(String option) {
    if(!option.isEmpty()) {
        String[] keyAndValue = option.split("=");
        Caffeine.requireArgument(keyAndValue.length <= 2, "key-value pair %s with more than one equals sign", new Object[]{option});
        String key = keyAndValue[0].trim();
        String value = keyAndValue.length == 1?null:keyAndValue[1].trim();
        this.configure(key, value);
    }
}
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

    switch(var4) {
        case 0:
            this.initialCapacity(key, value);
            return;
        case 1:
            this.maximumSize(key, value);
            return;
        case 2:
            this.maximumWeight(key, value);
            return;
        case 3:
            this.weakKeys(value);
            return;
        case 4:
            this.valueStrength(key, value, Strength.WEAK);
            return;
        case 5:
            this.valueStrength(key, value, Strength.SOFT);
            return;
        case 6:
            this.expireAfterAccess(key, value);
            return;
        case 7:
            this.expireAfterWrite(key, value);
            return;
        case 8:
            this.refreshAfterWrite(key, value);
            return;
        case 9:
            this.recordStats(value);
            return;
        default:
            throw new IllegalArgumentException("Unknown key " + key);
    }
}
```

```java
cacheManage.getCache(obj);
@Nullable
public Cache getCache(String name) {
    Cache cache = (Cache)this.cacheMap.get(name);
    // dynamic默认为true，这样当cacheManage中没有此cache时，会创建一个
    if(cache == null && this.dynamic) { 
        ConcurrentMap var3 = this.cacheMap;
        synchronized(this.cacheMap) {
            cache = (Cache)this.cacheMap.get(name);
            if(cache == null) {
                cache = this.createCaffeineCache(name);
                this.cacheMap.put(name, cache);
            }
        }
    }
    return cache;
}

protected Cache createCaffeineCache(String name) {
    return new CaffeineCache(name, this.createNativeCaffeineCache(name), this.isAllowNullValues());//isAllowNullValues默认为true
}

// 默认cacheLoader为null，这样创建的cache都是默认的！
//所以，创建定义的cache关键在于cacheLoader！
protected com.github.benmanes.caffeine.cache.Cache<Object, Object> createNativeCaffeineCache(String name) {
    return (com.github.benmanes.caffeine.cache.Cache)(this.cacheLoader != null?this.cacheBuilder.build(this.cacheLoader):this.cacheBuilder.build());
}

// 第一步，初始化
CaffeineCacheConfiguration(CacheProperties cacheProperties, CacheManagerCustomizers customizers, ObjectProvider<Caffeine<Object, Object>> caffeine, ObjectProvider<CaffeineSpec> caffeineSpec, ObjectProvider<CacheLoader<Object, Object>> cacheLoader) {
    this.cacheProperties = cacheProperties;
    this.customizers = customizers;
    this.caffeine = (Caffeine)caffeine.getIfAvailable();
    this.caffeineSpec = (CaffeineSpec)caffeineSpec.getIfAvailable();
    this.cacheLoader = (CacheLoader)cacheLoader.getIfAvailable();
}
// 第二步，
@Bean
public CaffeineCacheManager cacheManager() {
    // 调用第三步
    CaffeineCacheManager cacheManager = this.createCacheManager();
    List<String> cacheNames = this.cacheProperties.getCacheNames();
    if(!CollectionUtils.isEmpty(cacheNames)) {
        cacheManager.setCacheNames(cacheNames);
    }

    return (CaffeineCacheManager)this.customizers.customize(cacheManager);
}

// 第三步，
private CaffeineCacheManager createCacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    // 调用第四步
    this.setCacheBuilder(cacheManager);
    if(this.cacheLoader != null) {
        cacheManager.setCacheLoader(this.cacheLoader);
    }

    return cacheManager;
}

// 第三步
private void setCacheBuilder(CaffeineCacheManager cacheManager) {
    String specification = this.cacheProperties.getCaffeine().getSpec();
    // 最关键：设置caffeine cache 的参数
    if(StringUtils.hasText(specification)) {
        cacheManager.setCacheSpecification(specification);
    } else if(this.caffeineSpec != null) {
        cacheManager.setCaffeineSpec(this.caffeineSpec);
    } else if(this.caffeine != null) {
        cacheManager.setCaffeine(this.caffeine);
    }
}

// 解析spc字符串！
public static CaffeineSpec parse(String specification) {
    CaffeineSpec spec = new CaffeineSpec(specification);
    String[] var2 = specification.split(",");
    int var3 = var2.length;

    for(int var4 = 0; var4 < var3; ++var4) {
        String option = var2[var4];
        spec.parseOption(option.trim());
    }
    return spec;
}
void parseOption(String option) {
    if(!option.isEmpty()) {
        String[] keyAndValue = option.split("=");
        Caffeine.requireArgument(keyAndValue.length <= 2, "key-value pair %s with more than one equals sign", new Object[]{option});
        String key = keyAndValue[0].trim();
        String value = keyAndValue.length == 1?null:keyAndValue[1].trim();
        this.configure(key, value);
    }
}
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

    switch(var4) {
        case 0:
            this.initialCapacity(key, value);
            return;
        case 1:
            this.maximumSize(key, value);
            return;
        case 2:
            this.maximumWeight(key, value);
            return;
        case 3:
            this.weakKeys(value);
            return;
        case 4:
            this.valueStrength(key, value, Strength.WEAK);
            return;
        case 5:
            this.valueStrength(key, value, Strength.SOFT);
            return;
        case 6:
            this.expireAfterAccess(key, value);
            return;
        case 7:
            this.expireAfterWrite(key, value);
            return;
        case 8:
            this.refreshAfterWrite(key, value);
            return;
        case 9:
            this.recordStats(value);
            return;
        default:
            throw new IllegalArgumentException("Unknown key " + key);
    }
}
```