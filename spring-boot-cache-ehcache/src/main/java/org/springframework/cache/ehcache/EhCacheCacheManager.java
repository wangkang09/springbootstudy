package org.springframework.cache.ehcache;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Properties;

import com.wangkang.util.ConfigUtil;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 *
 * @Description: 覆盖框架的EhCacheCacheManager ，设置this.setTransactionAware(true)，开启事务
 *
 * @auther: wangkang
 * @date: 17:29 2019/1/21
 * @param:
 * @return:
 *
 */
public class EhCacheCacheManager extends AbstractTransactionSupportingCacheManager {
    @Nullable
    private CacheManager cacheManager;

    public EhCacheCacheManager() {
    }

    public EhCacheCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setCacheManager(@Nullable CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Nullable
    public CacheManager getCacheManager() {
        return this.cacheManager;
    }

    public void afterPropertiesSet() {
        if(this.getCacheManager() == null) {
            this.setCacheManager(EhCacheManagerUtils.buildCacheManager());
        }

        Properties properties = ConfigUtil.getProperties("application.properties");
        String transaction = properties.getProperty("ehcache_transaction_aware");
        if ("true".equals(transaction)) {
            this.setTransactionAware(true);
        }

        super.afterPropertiesSet();
    }

    protected Collection<Cache> loadCaches() {
        CacheManager cacheManager = this.getCacheManager();
        Assert.state(cacheManager != null, "No CacheManager set");
        Status status = cacheManager.getStatus();
        if(!Status.STATUS_ALIVE.equals(status)) {
            throw new IllegalStateException("An 'alive' EhCache CacheManager is required - current cache is " + status.toString());
        } else {
            String[] names = this.getCacheManager().getCacheNames();
            Collection<Cache> caches = new LinkedHashSet(names.length);
            String[] var5 = names;
            int var6 = names.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String name = var5[var7];
                caches.add(new EhCacheCache(this.getCacheManager().getEhcache(name)));
            }

            return caches;
        }
    }

    protected Cache getMissingCache(String name) {
        CacheManager cacheManager = this.getCacheManager();
        Assert.state(cacheManager != null, "No CacheManager set");
        Ehcache ehcache = cacheManager.getEhcache(name);
        return ehcache != null?new EhCacheCache(ehcache):null;
    }
}
