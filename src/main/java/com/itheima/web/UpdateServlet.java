package com.itheima.web;

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    //由于brandService的创建需要用到多次，故把它提前
    private BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理post请求的乱码问题
        request.setCharacterEncoding("utf-8");

        //1.接收表单提交的数据并将其封装为一个brand对象
        String id = request.getParameter("id");
        String brandName = request.getParameter("brandName");
        String companyName = request.getParameter("companyName");
        String ordered = request.getParameter("ordered");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        //封装
        Brand brand = new Brand();
        brand.setId(Integer.parseInt(id));
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.parseInt(status));
        //2.调用service完成修改
        brandService.update(brand);

        /**
         * 3.重定向方法到 查询所有 的servlet
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
