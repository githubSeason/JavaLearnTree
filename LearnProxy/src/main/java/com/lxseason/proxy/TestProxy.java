package com.lxseason.proxy;

import com.lxseason.proxy.dynamic.ProxyCompany;
import com.lxseason.proxy.dynamic.WomanToolFactoryImpl;
import com.lxseason.proxy.dynamic.WomanToolsFactory;
import com.lxseason.proxy.stat.AmtDaili;
import com.lxseason.proxy.stat.ManToolFactoryImpl;
import com.lxseason.proxy.stat.ManToolsFactory;
import com.lxseason.proxy.utils.ProxyUtils;

/**
 * 定义：给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用
 * 目的：
 * 	1.通过引入代理对象的方式来间接访问目标对象，防止直接访问目标对象给系统带来的不要复杂性
 * 	2.通过代理对象对原有的业务增强
 * 三个要素：
 * 	1.抽象对象：声明了真实对象和代理对象均要继承的的公共接口
 * 	2.真实对象：代理对象所代表的真实对象，最终被引用的对象
 *  	3.代理对象：包含真实对象，从而操作真实主题对象，相当于访问者与真实对象之间的中介
 */
public class TestProxy {
    public static void main(String[] args) {
//        testStaticProxy();
        testDynamicProxy();
    }

    private static void testStaticProxy(){
        ManToolsFactory factory = new ManToolFactoryImpl();
        AmtDaili amtDaili = new AmtDaili(factory);
        amtDaili.saleManTools("D");
        System.out.println("-----------------------");
    }

    /**
     * saleManTools、saleWomanTools都能通过代理类的对象享受到ProxyCompany类中提供的统一增强的业务服务。
     */
    private static void testDynamicProxy(){
        ProxyCompany proxyCompany = new ProxyCompany();

        ManToolsFactory manToolsFactory = new ManToolFactoryImpl();
        proxyCompany.setProxyedObject(manToolsFactory);
        ManToolsFactory manToolsProxyObj =(ManToolsFactory) proxyCompany.getProxyInstance();
        manToolsProxyObj.saleManTools("D");
        System.out.println("-----------------------");

        WomanToolsFactory womanToolsFactory =new WomanToolFactoryImpl();
        proxyCompany.setProxyedObject(womanToolsFactory);
        WomanToolsFactory womanToolsProxyObj = (WomanToolsFactory) proxyCompany.getProxyInstance();
        womanToolsProxyObj.saleWomanTools(180f);
        System.out.println("-----------------------");

        downloadClassFileFromMem2Disk(manToolsFactory,manToolsProxyObj);
        downloadClassFileFromMem2Disk(womanToolsFactory,womanToolsProxyObj);
    }

    private static void downloadClassFileFromMem2Disk(Object instanceObj,Object proxyInstanceObj){
        ProxyUtils.generateClassFile(instanceObj.getClass(),proxyInstanceObj.getClass().getSimpleName());
    }
}
