package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    //调用brandMapper.selectAll
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //工厂类初始化，就做一次故放在成员里

    /**
     * 用户登录
     * @return
     */
    public User login(String username,String password){
        //2.获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.调用方法
        User user = mapper.select(username, password);
        //5.释放资源
        sqlSession.close();
        return user;
    }
    /**
     * 用户注册
     * @return
     */
    public boolean register(User user){
        //2.获取sqlSession
        SqlSession sqlSession = factory.openSession();
        //3.获取UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.判断用户名是否存在
        User u = mapper.selectByUsername(user.getUsername());
        if(u == null){
            //用户名不存在，注册
            mapper.add(user);
            //提交事务
            sqlSession.commit();}
            /*return true;
        }else{
            //用户名存在
            return false;
        }*/
        sqlSession.close();
        return u == null; //三元运算
    }
}
