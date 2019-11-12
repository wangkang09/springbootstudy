package com.wangkang.filter;

import com.wangkang.entity.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:47 2018/12/26
 * @Modified By:
 */
@WebFilter(filterName = "myWebFilter",urlPatterns = "/myServlet")
@Slf4j
public class MyWebFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("initialize myWebFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("before do myFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object ob=session.getAttribute("token");

        session.setAttribute("myFilter",new User());
        session.setAttribute("myFilter2",new User());
        session.removeAttribute("myFilter2");
        session.invalidate();
        request.setAttribute("myRequest",new User());

        request.setAttribute("myRequest2",new User());
        request.removeAttribute("myRequest2");
        InputStream in = servletRequest.getInputStream();

        if(ob==null &&  !request.getServletPath().equals("/checkLogin")){//自己没有登录
            //跳转到专门的 页面 去查询 登录服务器有木有登录
            //response.sendRedirect("/checkLogin");
            log.info("not logined");

        }
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("after do myFilter");

    }

    @Override
    public void destroy() {
        log.info("destory myWebFilter");
    }
}
