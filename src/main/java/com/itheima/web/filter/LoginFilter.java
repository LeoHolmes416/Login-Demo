package com.itheima.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 登陆验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //向下强转
        HttpServletRequest req = (HttpServletRequest) request;

        //判断访问路径是否和登陆注册有关
        String[] urls = {"/index.html","/login.jsp","/imgs/","/css/","/loginServlet","/register.jsp","/registerServlet","/checkCodeServlet"};
        //获取当前资源访问的路径
        String url = req.getRequestURL().toString();
        //循环判断
        for(String u : urls){
            if(url.contains(u)){
                //找到了,放行
                chain.doFilter(request,response);
                //不执行后面的语句
                return;
            }
        }

        // 判断session中是否有user（即判断是否登陆了）
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null){  //登陆过
            //放行
            chain.doFilter(request, response);
        }else{
            //没登陆，存储提示信息，跳转到登陆页面
            req.setAttribute("login_msg","您尚未登陆");
            req.getRequestDispatcher("/login.jsp").forward(req,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


}
