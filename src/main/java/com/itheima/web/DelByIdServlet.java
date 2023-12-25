package com.itheima.web;

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delByIdServlet")
public class DelByIdServlet extends HttpServlet {
    //由于brandService的创建需要用到多次，故把它提前
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理post请求的乱码问题
        request.setCharacterEncoding("utf-8");

        //1.接收id
        String id = request.getParameter("id");

        //2.调用service完成修改
        brandService.delById(Integer.parseInt(id));

        /**
         * 3.重定向方法到 查询所有 的servlet (做出刷新页面的效果)
         * 简化方法，浏览器使用，需要加入虚拟目录
         * 为了减少耦合性，需要动态获取虚拟目录
         */
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/selectAllServlet");
        //3.请求转发 到 查询所有 的servlet
        //request.getRequestDispatcher("/selectAllServlet").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request,response);
    }
}
