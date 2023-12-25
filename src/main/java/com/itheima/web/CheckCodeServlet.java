package com.itheima.web;

import com.itheima.util.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用验证码工具类
        //1.传入验证码图片输出流
        ServletOutputStream os = response.getOutputStream();
        //2.显示图片，并得到验证码数据字符串
        String checkCode = CheckCodeUtil.outputVerifyImage(100,50, os ,4);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
