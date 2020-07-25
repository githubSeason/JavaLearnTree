package com.lxseason.mybatis;

import com.lxseason.mybatis.entity.TUser;
import com.lxseason.mybatis.mapper.TUserMapper;
import com.lxseason.mybatis.session.SqlSession;
import com.lxseason.mybatis.session.SqlSessionFactory;

import java.util.List;

/**
 * MyBatis实现思路：
 * 1.创建SqlSessionFactory实例
 * 2.实例化过程中，加载配置文件创建configuration对象
 * 3.通过factory创建SqlSession
 *
 * 4.通过SqlSession获取mapper接口动态代理
 * 5.动态代理回调SqlSession中某些查询方法
 *
 * 6.SqlSession将查询方法转发给Executor
 * 7.Executor基于JDBC访问数据库获取数据
 * 8.Executor通过反射将数据转换成POJO并返回给SqlSession
 * 9.SqlSession将数据返回给调用者
 */
public class TestMybatis {
    public static void main(String[] args) {
        //1.实例化SqlSessionFactory，加载数据库配置文件以及mapper.xml文件到Configuration对象
        SqlSessionFactory factory = new SqlSessionFactory();
        //2.获取sqlsession对象
        SqlSession session = factory.openSqlSession();
        System.out.println(session);
        //3.获取对应的mapper，通过动态代理跨越面向接口编程和ibatis编程模型的鸿沟
        TUserMapper userMapper = session.getMapper(TUserMapper.class);
        //4.执行查询语句返回单体数据，遵循jdbc规范，通过底层的四大对象的合作完成数据查询和数据转化
        TUser user= userMapper.selectByPrimaryKey(1);//userMapper为动态代理对象，实际调用MappedProxy中的invoke方法
        System.out.println(user);
        System.out.println("-----------------------");
        //执行查询语句，返回多条数据
        List<TUser> selectAll = userMapper.selectAll();
        for(TUser tUser :selectAll){
            System.out.println(tUser);
        }
    }
/**
 * MyBatis入门步骤：
 * 1.加入MyBatis的依赖
 * 2.添加MyBatis的配置文件
 * 场景介绍
 * 编写实体类、mapper接口、mapper xml文件
 * 编写测试代码
 */

}
