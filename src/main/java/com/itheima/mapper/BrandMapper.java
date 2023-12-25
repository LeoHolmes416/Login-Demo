package com.itheima.mapper;

import com.itheima.pojo.Brand;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有品牌
     * @return
     */
    //@Select("select * from tb_brand")
    //@ResultMap("brandResultMap")
    List<Brand> selectAll();

    void add(Brand brand);

    /**
     * 根据id查询，回显数据
     * @param id
     * @return
     */
    Brand selectById(int id);

    /**
     * 修改操作
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除操作
     * @param id
     */
    void delById(int id);

}
