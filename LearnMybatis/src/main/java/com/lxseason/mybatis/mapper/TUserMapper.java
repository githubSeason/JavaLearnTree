package com.lxseason.mybatis.mapper;

import com.lxseason.mybatis.entity.TUser;
import java.util.List;

/**
 * 面向接口开发思想
 * 调用TUserMapper的接口最终还是通过动态代理对象invoke方法，调用SqlSession接口实现类中具体的方法
 */
public interface TUserMapper {
    TUser selectByPrimaryKey(Integer id);//在MappedProxy代理对象中想办法把selectByPrimaryKey对应到DefaultSqlSession中的selectOne方法

    List<TUser> selectAll();//在MappedProxy代理对象中想办法把selectByPrimaryKey对应到DefaultSqlSession中的selectList方法
}
