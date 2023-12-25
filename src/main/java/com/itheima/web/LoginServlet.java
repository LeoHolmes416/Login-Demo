package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取复选框数据
        String remember = request.getParameter("remember");

        //2.调用service进行查询
        User user = service.login(username, password);
        //3.判断
        if(user != null){
            //判断对象是否勾选记住我
            if("1".equals(remember)){
                //如勾选，则发送cookie
                //1.创建cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                //设置cookie存活时间
                c_username.setMaxAge(60*60*24);
                c_password.setMaxAge(60*60*24);
                //2.发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //首先将登陆成功后的user对象存储到session中
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            //登陆成功，重定向到查询所有的selectAll
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/selectAllServlet");
        }else{
            //存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误");
            //跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
