package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandService {
    //调用brandMapper.selectAll
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //工厂类初始化，就做一次故放在成员里

    /**
     * 查询所有
     * @return
     */
    public List<Brand> selectAll(){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }
    /**
     * 添加
     */
    public void add(Brand brand){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.add(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 根据id查询
     * @return
     */
    public Brand selectById(int id){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        Brand brand = mapper.selectById(id);
        sqlSession.close();

        return brand;
    }
    /**
     * 修改
     */
    public void update(Brand brand){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.update(brand);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * 根据id删除
     * @return
     */
    public void delById(int id){
        //获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        mapper.delById(id);
        //提交与释放
        sqlSession.commit();
        sqlSession.close();
    }
}
