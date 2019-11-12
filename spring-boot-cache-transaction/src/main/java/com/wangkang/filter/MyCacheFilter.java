package com.wangkang.filter;

import com.wangkang.util.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:37 2019/1/9
 * @Modified By:
 */
//@WebFilter(filterName = "cacheFilter",urlPatterns = "*")
@Slf4j
//@Component
public class MyCacheFilter implements Filter {

    @Resource
    CacheUtil cacheUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        printCacheDetail();//掉用结束后的缓存
    }

    private void printCacheDetail() {
        cacheUtil.cacheDetails();
    }


    @Override
    public void destroy() {

    }
}
