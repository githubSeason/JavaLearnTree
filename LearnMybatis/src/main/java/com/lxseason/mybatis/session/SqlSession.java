package com.lxseason.mybatis.session;

import java.util.List;

/**
 * myabatis暴露给外部的接口，实现增删改查的能力
 * SqlSession意味着创建一次数据库连接会话，是MyBatis对外提供数据访问的主要API
 * SqlSession对内的底层功能实现都是基于Executor实现的（类的单一职责性）
 */
public interface SqlSession {
    /**
     * 根据传入的条件查询单一结果
     * @param statement 方法对应的sql语句，namespace+id
     * @param parameter 要传入到sql语句中的查询参数
     * @param <T>
     * @return 返回指定的结果对象
     */
    <T> T selectOne(String statement,Object parameter);

    /**
     * 根据条件查询，返回泛型集合
     * @param statement mapper接口中方法的路径
     * @param parameter
     * @param <E> 返回指定的结果对象的List
     * @return
     */
    <E> List<E> selectList(String statement ,Object parameter);

    <T> T getMapper(Class<T> type);
}
