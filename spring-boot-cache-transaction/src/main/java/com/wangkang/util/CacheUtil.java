package com.wangkang.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @Description: 这个类是针对，spring 内置cache的，如果是集成了其它cache（如，redis），则会报错异常
 * 原因：f = clazz.getDeclaredField("store");这是个针对性的代码
 * @Author: wangkang
 * @Date: Created in 16:40 2019/1/14
 * @Modified By:
 */
@Slf4j
@Component
public class CacheUtil {

    @Resource
    CacheManager cacheManager;

    /**
     *
     * @Description: 获取缓存详情
     *
     * @auther: wangkang
     * @date: 16:48 2019/1/14
     * @param: []
     * @return: void
     *
     */
    public  void cacheDetails() {
        Cache c = null;
        Collection cacheNames = cacheManager.getCacheNames();
        if (cacheNames.size() == 0) {
            log.info("cacheManager中没有任何cache");
            return;
        }

        cacheNames.stream().forEach(o->{
            reflectToPrintCacheDetail(cacheManager.getCache(o.toString()));
        });
    }

    private  void reflectToPrintCacheDetail(Cache c) {
        Class clazz = c.getClass();
        Field f = null;
        try {
            f = clazz.getDeclaredField("store");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);

        try {
            Map<Object,Object> m = (Map<Object,Object>)f.get(c);
            System.out.println(m.size());
            for (Map.Entry<Object, Object> entry : m.entrySet()) {
                log.info("key：{}",entry.getKey().toString());
                log.info("value：{}",entry.getValue().toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
