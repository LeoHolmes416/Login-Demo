package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 根据用户名和密码查询对象
     * @param username
     * @param password
     * @return
     */
    //@Select("select * from tb_user where username = #{username} and password = #{password};")
    User select(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查找数据库中是否已经存在对象
     * @param username
     * @return
     */
    User selectByUsername(String username);

    /**
     * 添加新用户
     * @param user
     */
    void add(User user);
}
