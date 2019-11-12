package com.wangkang.filter;

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
@WebFilter(filterName = "cacheFilter",urlPatterns = "*")
@Slf4j
@Component
public class MyCacheFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(11111);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("进入myfilter");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("退出myfilter");
    }

    @Override
    public void destroy() {

    }
}
