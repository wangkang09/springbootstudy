package com.wangkang.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:16 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebServlet(name = "myServlet",urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("execute MyServlet.doGet()");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("execute：MyServlet1.doPost()");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8"); //  设置编码，解决中文乱码问题

        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>this is MyServlet</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}