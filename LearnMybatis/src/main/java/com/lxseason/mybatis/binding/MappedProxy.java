package com.lxseason.mybatis.binding;

import com.lxseason.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

public class MappedProxy implements InvocationHandler {
    private SqlSession session;

    public MappedProxy(SqlSession session) {
        this.session = session;
    }

    /**
     * 实现被代理接口的方法
     * @param proxy 代理对象
     * @param method 调用方法，可以在这里做文章
     * @param args 调用方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();//拿到方法返回参数的类型
        String statement =method.getDeclaringClass().getName()+"."+method.getName();
        Object parameter = args ==null ? null : args[0];//args若为多个参数，要循环处理

        if(Collection.class.isAssignableFrom(returnType)){//Collection是returnType的父类
            return session.selectList(statement ,parameter);
        }else{
            return session.selectOne(statement,parameter);
        }
    }
}
