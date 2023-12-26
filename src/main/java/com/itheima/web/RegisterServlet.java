package com.itheima.web;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取对应的用户名和密码数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //封装成user对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //  获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //  从session中获取程序生成的验证码
        HttpSession session = request.getSession();
        //object类强转成字符串
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        //  比对（忽略大小写）
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            //不允许注册,跳转到注册页面register.jsp
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        //2.调用service进行注册
        boolean flag = service.register(user);
        //3.判断注册成功与否
        if(flag){
            //注册成功，跳转到登陆页面login.jsp，给出提示
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //注册失败,跳转到注册页面register.jsp
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
