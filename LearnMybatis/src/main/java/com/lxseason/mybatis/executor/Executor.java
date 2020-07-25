package com.lxseason.mybatis.executor;

import com.lxseason.mybatis.config.MappedStatment;

import java.util.List;

/**
 * MyBatis核心接口之一，定义了数据库操作的最基本的方法，SqlSession的功能都是基于它来实现的
 */
public interface Executor {
    /**
     * 查询接口
     * @param ms 封装sql语句的MappedStatement对象
     * @param parameter 传入sql的参数
     * @param <E>
     * @return 将数据转换成指定对象结果集返回
     */
    <E> List<E> query(MappedStatment ms ,Object parameter);
}
