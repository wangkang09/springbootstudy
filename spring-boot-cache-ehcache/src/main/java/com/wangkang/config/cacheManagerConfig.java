package com.wangkang.config;

import net.sf.ehcache.Ehcache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 即使在这里设置了 setTransactionAware(true) ，已经晚了，当 cacheManger初始化后，就调用afterPropertiesSet，初始化了cache了，
 * 这个cache没有因为transaction == false，所以不会被修饰（被事务代理）
 * 又因为，ehcache的cache只有在.xml中配置，所以不可能有被事务修饰的cache了
 * 只有自定义 ehcacheManager了
 * @Author: wangkang
 * @Date: Created in 14:44 2019/1/21
 * @Modified By:
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class cacheManagerConfig implements CommandLineRunner{
    @Autowired
    CacheManager cacheManager;

    @Override
    public void run(String... strings) throws Exception {

        if (cacheManager instanceof EhCacheCacheManager) {
            ((EhCacheCacheManager) cacheManager).setTransactionAware(true);
        }
        Cache cache = cacheManager.getCache("person");

        if (cache instanceof TransactionAwareCacheDecorator) {
            System.out.println("cache is TransactionAwareCacheDecorator!");
        }
        if (cacheManager instanceof EhCacheCacheManager) {
            System.out.println(1);
            Class cm = cacheManager.getClass();
            Method mt = getMethod(cm,"decorateCache",new Class[]{Cache.class});
            //Caused by: java.lang.NoSuchMethodException: org.springframework.cache.ehcache.EhCacheCacheManager.decorateCache(org.springframework.cache.Cache)

            mt.setAccessible(true);
            cache = (Cache) mt.invoke(cacheManager,cache);

            if (cache instanceof TransactionAwareCacheDecorator) {
                System.out.println("cache is TransactionAwareCacheDecorator!");
            }

        }
    }

    public static Method getMethod(Class clazz, String methodName,
                                   final Class[] classes) throws Exception {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, classes);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName,
                            classes);
                }
            }
        }
        return method;
    }
}
