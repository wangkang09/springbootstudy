package com.wangkang.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:37 2019/1/9
 * @Modified By:
 */
@WebFilter(filterName = "cacheFilter",urlPatterns = "*")
@Slf4j
@Component
public class MyCacheFilter implements Filter {
    @Resource
    CacheManager cacheManager;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(11111);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        printCacheDetail();
    }

    private void printCacheDetail() {
        Cache c = null;
        Collection cacheNames = cacheManager.getCacheNames();
        cacheNames.stream().forEach(o->{
            reflectToPrintCacheDetail(cacheManager.getCache(o.toString()));
        });
    }

    private void reflectToPrintCacheDetail(Cache c) {
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

    @Override
    public void destroy() {

    }
}
