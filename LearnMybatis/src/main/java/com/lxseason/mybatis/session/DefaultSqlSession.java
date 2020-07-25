package com.lxseason.mybatis.session;

import com.lxseason.mybatis.binding.MappedProxy;
import com.lxseason.mybatis.config.Configuration;
import com.lxseason.mybatis.config.MappedStatment;
import com.lxseason.mybatis.executor.DefaultExecutor;
import com.lxseason.mybatis.executor.Executor;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 1.对外提供
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration conf;
    private Executor executor;
    public DefaultSqlSession(Configuration conf) {
        this.conf = conf;
        executor = new DefaultExecutor(conf);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<Object> selectList = this.selectList(statement,parameter);
        if(selectList == null || selectList.size() ==0){
            return null;
        }
        if(selectList.size() ==1){
            return (T)selectList.get(0);
        }else{
            throw new RuntimeException("too many result !");
        }
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        MappedStatment ms = conf.getMappedStatementMap().get(statement);
        return executor.query(ms ,parameter);
    }

    /**
     *使用动态代理将TUserMapper中的业务逻辑接口（通过MappedProxy的invoke调用）映射到对DefaultSqlSession中的selectOne、selectList的调用上
     * @param type 传入的是没有实现类的TUserMapper接口方法
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> type) {
        MappedProxy mp = new MappedProxy(this);
        //单一职责原则：Proxy只负责生成实现接口的类，接口TUserMapper中的方法在InvocationHandler的实现类MappedProxy中完成
        return (T)Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type} ,mp);//第二个参数，type类可能实现多个接口，所以传数组进来
    }


}
