package com.lxseason.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * InvocationHandler接口用来制定标准
 * InvocationHandler接口的实现中完成对被代理的对象方法的调用
 * 同一个ProxyCompany的对象通过setProxyedObject、getProxyInstance两个方法可以获取到不同的被代理对象，
 * 但在增删改被代理的对象时，对ProxyCompany类本身不受影响（实现的各种业务增强功能不用改动）
 */
public class ProxyCompany implements InvocationHandler {
    private Object proxyedObject;//存储被代理的对象
    public void setProxyedObject(Object proxyedObject) {
        this.proxyedObject = proxyedObject;
    }

    /**
     * 通过Proxy获取动态代理对象
     * 插入参数：1 被代理类的某个对象的类加载器、2 被代理类实现的接口列表、3 当前代理类的对象自身
     * @return
     */
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(proxyedObject.getClass().getClassLoader(), proxyedObject.getClass().getInterfaces(),this);
    }

    /**
     * Proxy是所有通过Proxy.newProxyInstance()方法生成的动态代理对象所继承的父类，
     * 生成的动态代理对象也会实现给newProxyInstance()传入的所有业务接口
     * 制定标准服务接口,父类Proxy中有一个InvocationHandler接口的抽象对象h，h会被赋值传入的代理对象
     * 生成的动态代理对象在调用实现接口的方法中会通过this.h.invoke(this,m3,new Object[]{paramString})调用到代理对象中实现的invoke()方法，
     * 在代理对象中实现的invoke()方法中通过method.invoke(业务接口抽象对象，args)去调用实现了业务接口的类中的具体方法
     * （通过反编译代理对象的字节码文件可以看出以上内容）
     *
     * 即，动态代理对象通过this.h.invoke()调用，实现ProxyCompany代理类中实现的InvocationHandler的invoke接口方法中实现对具体业务方法调用
     *
     * 通过动态代理对象对方法进行增强
     * 扩展或维护功能时，这个类不用改动，实现了开闭原则
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object ret = method.invoke(proxyedObject,args);
        doSomethingEnd();
        return ret;
    }

    /*
    在这些增强的方法中实现需要被解耦的业务，即当这些被代理的对象发生变化时，避免了对这些增强方法的修改
     */
    //前置增强
    private void doSomethingBefore(){
        System.out.println("代理公司 -售前服务：分析需求，产品推荐");
    }
    //后置增强
    private void doSomethingEnd(){
        System.out.println("代理公司 -售后服务：精美包装，邮递货物");
    }

}
