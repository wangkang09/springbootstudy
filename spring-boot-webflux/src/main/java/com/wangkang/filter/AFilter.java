package com.wangkang.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:25 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebFilter(filterName = "aFilter",urlPatterns = "/myServlet")
public class AFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init afilter");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("before do afilter");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("after do afilter");
    }

    @Override
    public void destroy() {
        log.info("destroy afilter");

    }
}
